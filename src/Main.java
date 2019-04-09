import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;

import javax.xml.stream.XMLStreamReader;


public class Main {
	
	private static String pathCF = "C:\\Users\\dchia\\Documents\\_GitHub\\ProvaLetturaXML\\src\\codiciFiscali.xml";		
	private static XMLInputFactory xmlif = null;
	private static XMLStreamReader xmlr = null;
	private static String lastTag;

	public static void main(String[] args) {
		ArrayList<String> codiciLetti = new ArrayList<String>();
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
		System.out.println("Numero risultati: " + codiciLetti.size());
	}

}
