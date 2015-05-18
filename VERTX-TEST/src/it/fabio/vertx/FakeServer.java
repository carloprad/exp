package it.fabio.vertx;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;


/**
 * 
 * This is a test! 
 * Rif:
 * http://www.javaworld.com/article/2078838/mobile-java/open-source-java-projects-vert-x.html
 *   
 * Codice prolisso per capire i meccanismi della gestione degli eventi di vertx
 * 
 * Per fare la prova:
 * Copiare sotto la bin di vertx la cartella webroot
 * Esportare il jar (testServer.jar) sotto la cartella vertx
 * lanciare vertx run it.fabio.vertx.FakeServer -cp ./testServer.jar
 * 
 * Risorse disponibili:
 * http://localhost:8080/persone/oliver
 * http://localhost:8080/persone/stan
 */
public class FakeServer extends Verticle {
	
	
	// Ridefinosco il metodo start() di Verticle
	@Override
	 public void start() {
		 	// Istanzio un handler capace di trattare le richieste 
		 	HandlerPersona hp = new HandlerPersona(container);
		 	// Lego l'handler ad un server http
	        HttpServer httpServer = vertx.createHttpServer().requestHandler(hp);
	        // avvio il server http su porta 8080
	        httpServer.listen(8080);
	    }
	
	

}
