import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;

import javax.xml.stream.XMLStreamReader;


public class Main {

	public static void main(String[] args) {
		LetturaXML lettura = new LetturaXML();
		
		//lettura.letturaCodiciFiscali("C:\\Users\\dchia\\Documents\\_GitHub\\ProvaLetturaXML\\src\\codiciFiscali.xml");
		
		lettura.letturaPersona("C:\\Users\\francesco\\Desktop\\Arnaldo\\programmi\\AcquisizioneDatiDaXMLBanale\\src\\inputPersone.xml");
		lettura.generaOutput();
	}

}
