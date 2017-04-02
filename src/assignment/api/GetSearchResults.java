package assignment.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import assignment.exceptions.InputValidationException;
import assignment.exceptions.ZillowServiceException;
import assignment.json.JsonParse;
import assignment.util.ZillowClient;
import assignment.util.ZillowQueryObj;
import assignment.util.ErrorDoc;
import assignment.xml.XmlParse;

/**
 * 
 * @author Martin
 *
 *	GetSearchResults
 *
 *	Exposes access to Zillow GetSearchResults.
 *	Supports JSON and XML input methods.  
 *
 */
public class GetSearchResults {

	GetSearchResults() {
		
	}
	/**
	 * 
	 * @param jsonStr - 
	 *  {
	 *		"streetNumber": "2114",
	 *		"streetName": "Bigelow Ave",
	 *		"city": "Seattle",
	 *		"state":"WA",
	 *		"zip":"98109"
	 *	}	
	 *
	 * @return String - xml formatted result of invocation 
	 */
	public static String submitJSON(String jsonStr) {
		
		String resultDoc = "";
		try {
			ZillowQueryObj query = JsonParse.parse(jsonStr, new GetSearchResultInputValidation());
			resultDoc = new ZillowClient().initialiseURL(query).callEndpoint();
		}
		catch(ParseException pe){	
			resultDoc = new ErrorDoc().createErrorDoc("JSON parse error - position: " + pe.getPosition() + " " + pe);
		}
		catch (InputValidationException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		}
		catch(MalformedURLException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		}
		catch(UnsupportedEncodingException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		}
		catch (UnknownHostException e) {
			resultDoc = new ErrorDoc().createErrorDoc("Unknown Host:" + e.getMessage());
		}
		catch (ZillowServiceException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		}
		catch (IOException e) {
			resultDoc = new ErrorDoc().createErrorDoc("Unexpected IO message:" + e.getMessage());
		}
		catch (Exception e) {
			resultDoc = new ErrorDoc().createErrorDoc("Unexpected error occured:" + e.getMessage());
		}
		return resultDoc;
	}
	
	/**
	 * 
	 * @param xmlStr
	 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
	 * <Zillowquery>
	 *     <streetNumber>5678</streetNumber>
	 *     <streetName>this street</streetName>
	 *     <city>Wako</city>
	 *     <state>TX</state>
	 *     <zip>12345</zip>
	 * </Zillowquery>
	 * 
	 * @return String xml formatted result of invocation  
	 */
	public static String submitXML(String xmlStr) {
		
		String resultDoc = "";
		try {
			ZillowQueryObj query;
			query = XmlParse.parse(xmlStr, new GetSearchResultInputValidation());
			resultDoc = new ZillowClient().initialiseURL(query).callEndpoint();	
		} 
		catch (ParserConfigurationException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		} 
		catch (SAXException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		} 
		catch (IOException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		} 
		catch (InputValidationException e) {
			resultDoc = new ErrorDoc().createErrorDoc(e.getMessage());
		}
		return resultDoc;
	}
}
