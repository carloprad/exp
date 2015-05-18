package it.fabio.vertx;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Container;


// Handler che gestisce la richiesta di una persona
public class HandlerPersona implements Handler<HttpServerRequest>{

	// Riferimento al container
	private  Container container = null;
	
	public  HandlerPersona(Container container) {
		this.container = container;
		
	}
	
	// Associa alla richiesta il path della risorsa da restitutire
	// Ritorna un testo di errore in caso di errore.
	private String risolviRisorsa(String path){
		
		// Chiedo un logger al container 
		Logger logger = container.logger();
		
		// Se viene chiesto oliver ritorno il nome del file che contiene i dati di Oliver
		if (path.equalsIgnoreCase("/persone/oliver")){
			logger.info("Itercettato oliver");
			return "oliver.json";
		}
		
		// Se viene chiesto stan ritorno il nome del file che contiene i dati di Stan
		if (path.equalsIgnoreCase("/persone/stan")){
			logger.info("Itercettato stan");
			return "stan.json";
		}
		// Se arrivo qui significa che è stato richiesto qualcosa di diverso 
		// dal previsto...
		logger.info("Itercettato errore!");
		//... e quindi ritorno il testo di errore
		return "error.json";
	}
	
	
	// Questo metodo implementa l'interfaccia di vertx Handle
	@Override
	public void handle(HttpServerRequest req) {
		// Richiedo un logger al container
		Logger logger = container.logger();
		logger.info("Ricevuta richiesta: " + req.path());
		// Il nome del file è index.html se non viene specificato un path
		// altrimenti tale nome viene deciso dal metodo risolviRisorsa()
        String file = req.path().equals("/") ? "index.html" : risolviRisorsa(req.path());
        logger.info("Devo inviare il file "+ file);
        // Come response invio al client il file deciso al passo precedente
        req.response().sendFile("webroot/" + file);
	}

}
