import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class LetturaXML {

	private static String pathComuni = "C:\\Users\\francesco\\Desktop\\Arnaldo\\programmi\\AcquisizioneDatiDaXMLBanale\\src\\comuni.xml";
	private static XMLInputFactory xmlifComuni = null;
	private static XMLStreamReader xmlrComuni = null;
	private static XMLInputFactory xmlif = null;
	private static XMLStreamReader xmlr = null;
	private static XMLOutputFactory xmlof = null;
	private static XMLStreamWriter xmlw = null;
	private ArrayList<String> codiciLetti = new ArrayList<String>();
	private ArrayList<Persona> persone = new ArrayList<Persona>();

	public void letturaCodiciFiscali(String pathCF) {

		String lastTag = "";
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(pathCF, new FileInputStream(pathCF));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}

		try {
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				switch (xmlr.getEventType()) { // switch sul tipo di evento
				case XMLStreamConstants.START_DOCUMENT: // inizio del documento

					break;
				case XMLStreamConstants.START_ELEMENT:

					lastTag = xmlr.getLocalName();// passo ad una variabile d'appoggio l'ultima tag che ho letto

					break;
				case XMLStreamConstants.END_ELEMENT: // fine di un elemento

					break;
				case XMLStreamConstants.COMMENT:

					break;
				case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi

						if (lastTag.equals("codice")) {// se l'ultima tag che ho letto è "codice" prendo il testo
							codiciLetti.add(xmlr.getText());
						}
					break;
				}
				xmlr.next();
			}
		} catch (Exception e) {
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < codiciLetti.size(); i++) {
			//System.out.println(i);
			System.out.println(codiciLetti.get(i));
		}
		System.out.println("Numero CodiciFiscali: " + codiciLetti.size());
	}

	public void letturaPersona(String pathInputPersone) {
		
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
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:

					break;
				case XMLStreamConstants.START_ELEMENT:

					lastTag = xmlr.getLocalName();// passo ad una variabile d'appoggio l'ultima tag che ho letto

					break;
				case XMLStreamConstants.END_ELEMENT:
					if (xmlr.getLocalName().equals("persona")) {
						Persona newPersona = new Persona(nome, cognome, sesso, comune, ricercaComune(comune), data);
						persone.add(newPersona);
					}
					break;
				case XMLStreamConstants.COMMENT:

					break;
				case XMLStreamConstants.CHARACTERS:

					if (lastTag.equals("nome")) {// se l'ultima tag che ho letto è "nome" prendo il testo
						nome = xmlr.getText();
						lastTag = "";// resetto la variabile d'appoggio
					}
					if (lastTag.equals("cognome")) {// se l'ultima tag che ho letto è "cognome" prendo il testo
						cognome = xmlr.getText();
						lastTag = "";// resetto la variabile d'appoggio
					}
					if (lastTag.equals("sesso")) {// se l'ultima tag che ho letto è "sesso" prendo il testo
						sesso = xmlr.getText();
						lastTag = "";// resetto la variabile d'appoggio
					}
					if (lastTag.equals("comune_nascita")) {// se l'ultima tag che ho letto è "comune_nascita" prendo il
															// testo
						comune = xmlr.getText();
						lastTag = "";// resetto la variabile d'appoggio
					}
					if (lastTag.equals("data_nascita")) {// se l'ultima tag che ho letto è "data_nascita" prendo il
															// testo
						data = xmlr.getText();
						lastTag = "";// resetto la variabile d'appoggio
					}
					break;
				}
				xmlr.next();
			}
		} catch (Exception e) {
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < persone.size(); i++) {
			// System.out.println(i);
			persone.get(i).stampa();
		}
		System.out.println("Numero persone: " + persone.size());
	}

	private String ricercaComune(String comune) {
		String nomeComune = "";
		String lastTag = "";
		try {
			xmlifComuni = XMLInputFactory.newInstance();
			xmlrComuni = xmlifComuni.createXMLStreamReader(pathComuni, new FileInputStream(pathComuni));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}

		try {
			while (xmlrComuni.hasNext()) { // continua a leggere finché ha eventi a disposizione
				switch (xmlrComuni.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:

					break;
				case XMLStreamConstants.START_ELEMENT:

					lastTag = xmlrComuni.getLocalName();// passo ad una variabile d'appoggio l'ultima tag che ho letto

					break;
				case XMLStreamConstants.END_ELEMENT:
					if (xmlrComuni.getLocalName().equals("persona")) {

					}
					break;
				case XMLStreamConstants.COMMENT:

					break;
				case XMLStreamConstants.CHARACTERS:

					if (lastTag.equals("nome")) {// se l'ultima tag che ho letto è "nome" prendo il testo
						nomeComune = xmlrComuni.getText();
						lastTag = "";// resetto la variabile d'appoggio
					}
					if (lastTag.equals("codice") && nomeComune.equals(comune)) {// se l'ultima tag che ho letto è "nome"
																				// prendo il testo
						return xmlrComuni.getText();
					}
					break;
				}
				xmlrComuni.next();
			}
		} catch (Exception e) {
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}
		return "Comune non trovato";
	}

	public void generaOutput() {// eliminati perchè li può prendere da solo da codici
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("codiciPersone.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}

		try { // blocco try per raccogliere eccezioni
			xmlw.writeStartElement("output"); // scrittura del tag radice <programmaArnaldo>
			xmlw.writeComment("INIZIO LISTA"); // scrittura di un commento
			xmlw.writeStartElement("persone");
			xmlw.writeAttribute("numero", Integer.toString(persone.size()));
			for (int i = 0; i < persone.size(); i++) {
				xmlw.writeStartElement("persona"); 
				xmlw.writeAttribute("id", Integer.toString(i));
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(persone.get(i).getNome()); 
				xmlw.writeEndElement(); 
				xmlw.writeStartElement("cognome");
				xmlw.writeCharacters(persone.get(i).getCognome()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("sesso");
				xmlw.writeCharacters(persone.get(i).getSesso()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("comune_nascita");
				xmlw.writeCharacters(persone.get(i).getComune()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("data_nascita");
				xmlw.writeCharacters(persone.get(i).getDataDiNascita()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("codice_fiscale");
				xmlw.writeCharacters(persone.get(i).getCF()); 
				xmlw.writeEndElement();
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement(); // chiusura di </programmaArnaldo>
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
		} catch (Exception e) { // se c’è un errore viene eseguita questa parte
			System.out.println("Errore nella scrittura");
		}

	}

}
