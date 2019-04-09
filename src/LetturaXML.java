import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class LetturaXML {
	
	private static XMLInputFactory xmlif = null;
	private static XMLStreamReader xmlr = null;
	
	public void letturaCodiciFiscali(String pathCF) {
		ArrayList<String> codiciLetti = new ArrayList<String>();
		String lastTag = "";
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(pathCF, new FileInputStream(pathCF));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlr.hasNext()) { // continua a leggere finch� ha eventi a disposizione
				switch (xmlr.getEventType()) { // switch sul tipo di evento
					case XMLStreamConstants.START_DOCUMENT: // inizio del documento 
						
						break;
					case XMLStreamConstants.START_ELEMENT:
						
						lastTag = xmlr.getLocalName();//passo ad una variabile d'appoggio l'ultima tag che ho letto
	
						break;
					case XMLStreamConstants.END_ELEMENT: // fine di un elemento 
						  
						break;
					case XMLStreamConstants.COMMENT:
						 
						break;  
					case XMLStreamConstants.CHARACTERS: // content all�interno di un elemento: stampa il testo
						if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
 
						if (lastTag.equals("codice")) {//se l'ultima tag che ho letto � "codice" prendo il testo
							codiciLetti.add(xmlr.getText());
						}
						break;
					}
					xmlr.next();
				}
		}catch (Exception e){
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}
		
		for (int i = 0; i < codiciLetti.size(); i++) {
			System.out.println(i);
			System.out.println(codiciLetti.get(i));
		}
		System.out.println("Numero CodiciFiscali: " + codiciLetti.size());
	}
	
	public void letturaPersona(String pathInputPersone) {
		ArrayList<Persona> persone = new ArrayList<Persona>();
		String nome = "";
		String cognome = "";
		String sesso = "";
		String comune = "";
		String data = "";
		String lastTag = "";
		
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(pathInputPersone, new FileInputStream(pathInputPersone));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlr.hasNext()) { // continua a leggere finch� ha eventi a disposizione
				switch (xmlr.getEventType()) { // switch sul tipo di evento
					case XMLStreamConstants.START_DOCUMENT: // inizio del documento 
						
						break;
					case XMLStreamConstants.START_ELEMENT:
						
						lastTag = xmlr.getLocalName();//passo ad una variabile d'appoggio l'ultima tag che ho letto
						
						break;
					case XMLStreamConstants.END_ELEMENT: // fine di un elemento 
						if (xmlr.getLocalName().equals("nome")) {
							Persona newPersona = new Persona(nome, cognome, sesso, comune, comune, data);
							persone.add(newPersona);
						}
						break;
					case XMLStreamConstants.COMMENT:
						
						break;  
					case XMLStreamConstants.CHARACTERS: // content all�interno di un elemento: stampa il testo
						if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
							
						if (lastTag.equals("nome")) {//se l'ultima tag che ho letto � "codice" prendo il testo
							nome = xmlr.getText();
						}
						if (lastTag.equals("cognome")) {//se l'ultima tag che ho letto � "codice" prendo il testo
							cognome = xmlr.getText();
						}	
						if (lastTag.equals("sesso")) {//se l'ultima tag che ho letto � "codice" prendo il testo
							sesso = xmlr.getText();
						}
						if (lastTag.equals("comune_nascita")) {//se l'ultima tag che ho letto � "codice" prendo il testo
							comune = xmlr.getText();
						}
						if (lastTag.equals("data_nascita")) {//se l'ultima tag che ho letto � "codice" prendo il testo
							data = xmlr.getText();
						}
						
						break;
					}
					xmlr.next();
				}
		}catch (Exception e){
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}
		
		for (int i = 0; i < persone.size(); i++) {
			System.out.println(i);
			persone.get(i).stampa();
		}
		System.out.println("Numero persone: " + persone.size());
	}

}