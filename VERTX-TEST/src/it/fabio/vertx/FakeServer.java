package it.fabio.vertx;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;


/**
 * 
 * This is a test!
 * Rif:
 * http://www.javaworld.com/article/2078838/mobile-java/open-source-java-projects-vert-x.html
 */
public class FakeServer extends Verticle {
	
	private String risolviRisorsa(Logger logger,String path){
		
		if (path.equalsIgnoreCase("/persone/oliver")){
			logger.info("Itercettato oliver");
			return "oliver.json";
		}
		if (path.equalsIgnoreCase("/persone/stan")){
			logger.info("Itercettato stan");
			return "stan.json";
		}
		logger.info("Itercettato errore!");
		return "err.html";
	}
	
	 public void start() {
		    // Ho un mio event-loop....
	        container.deployVerticle("it.fabio.vertx.FakeServer");
	        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
	            public void handle(HttpServerRequest req) {
	                Logger logger = container.logger();
	                logger.info("Ricevuta richiesta: " + req.path());
	                String file = req.path().equals("/") ? "index.html" : risolviRisorsa(logger,req.path());
	                logger.info("Devo inviare il file "+ file);
	                req.response().sendFile("webroot/" + file);
	                // Io pubblico anche se per ora questo messaggio non viene usato da nessuno
	                EventBus eb = vertx.eventBus();
	                eb.publish( "it.fabio.vertx.FakeServer.annunci", "Il falkeServer annuncia di aver ricevuto questa richiesta:" + req.path() );
	            }
	        }).listen(8080);
	    }
	
	

}
