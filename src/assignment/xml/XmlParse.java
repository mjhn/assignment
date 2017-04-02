package assignment.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import assignment.exceptions.InputValidationException;
import assignment.util.ZillowQueryObj;
import assignment.api.EndpointValidation;
import java.io.IOException;
import java.io.StringReader;

/**
 * 
 * @author Martin
 *
 * Simple document parser for validating Zillow GetSearchResults
 * 
 */
public class XmlParse {
	
	/**
	 * Parse input document, validate known elements, build ZillowQueryObj
	 * 
	 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
     * <Zillowquery>
     *     <streetNumber>5678</streetNumber>
     *     <streetName>this street</streetName>
     *     <city>Wako</city>
     *     <state>TX</state>
     *     <zip>12345</zip>
     * </Zillowquery>
	 * 
	 * @param xmlStr
	 * @param validator
	 * @return ZillowQueryObj
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws InputValidationException
	 */
	public static ZillowQueryObj parse(String xmlStr, EndpointValidation validator) throws ParserConfigurationException, SAXException, IOException, InputValidationException {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource( new StringReader( xmlStr )));
		doc.getDocumentElement().normalize();
		
		NodeList nodeList = doc.getElementsByTagName("Zillowquery");
		if(nodeList.getLength() == 0) {
			throw new InputValidationException("Invalid Document - Zillowquery element");
		}
	
        Node nNode = nodeList.item(0);
        if (nNode.getNodeType() != Node.ELEMENT_NODE) {
        	throw new InputValidationException("Invalid Document - Unexpected formatting");
        }
        
    	Element eElement = (Element) nNode;
    	StringBuffer inputErrors = new StringBuffer("");
    	for(int index = 0; index < validator.count(); index++) {
    		
    		/*
    		 * Access document by Tag Name - provided by EndpointValidation validator.
    		 */
    		Element e = (Element)eElement.getElementsByTagName(validator.variableName(index)).item(0);
    		if(validator.isMandatory(index) == true && (e == null || e.getTextContent() == null || e.getTextContent().trim().length() == 0)) {
    			inputErrors.append("\nRequired: [" + validator.variableName(index) + "] empty or missing" );
    		}
		}
    	if(inputErrors.length() > 0) {
			throw new InputValidationException(inputErrors.toString());
		}
    	      	     
    	/**
    	 * Create ZillowQueryObj
    	 */
        ZillowQueryObj query = new ZillowQueryObj();
		query.setStreetNumber(eElement.getElementsByTagName("streetNumber").item(0).getTextContent().trim());
		query.setStreetName(eElement.getElementsByTagName("streetName").item(0).getTextContent().trim());
		query.setCity(eElement.getElementsByTagName("city").item(0).getTextContent().trim());
		query.setState(eElement.getElementsByTagName("state").item(0).getTextContent().trim());
		if(eElement.getElementsByTagName("zip").item(0) != null &&
				eElement.getElementsByTagName("zip").item(0).getTextContent().trim().length() > 0) {
			query.setZip(eElement.getElementsByTagName("zip").item(0).getTextContent().trim());
		}
		
		return query;
	}

}
