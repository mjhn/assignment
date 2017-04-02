package assignment.json;

import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.apache.commons.io.*;
import java.io.IOException;
import assignment.exceptions.InputValidationException;
import assignment.api.EndpointValidation;
import assignment.util.ZillowQueryObj;

/**
 * 
 * @author Martin
 * Simple Json parser support for validating Zillow GetSearchResults
 *  
 */
public class JsonParse {

	
	/**
	 * 
	 * @param data
	 *  {
	 *		"streetNumber": "2114",
	 *		"streetName": "Bigelow Ave",
	 *		"city": "Seattle",
	 *		"state":"WA",
	 *		"zip":"98109"
	 *	}
	 * @param validator
	 * @return ZillowQueryObj
	 * @throws ParseException
	 * @throws InputValidationException
	 */
	public static ZillowQueryObj parse(String data, EndpointValidation validator) throws ParseException, InputValidationException {
		
		if(data == null || data.length() == 0) {
			throw new InputValidationException("No input found");
		}
	
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(data);
		JSONObject o = (JSONObject)obj;
		
		StringBuffer inputErrors = new StringBuffer("");
		for(int index = 0; index < validator.count(); index++) {
			if((o.get(validator.variableName(index)) == null || o.get(validator.variableName(index)).toString().trim().length()==0) &&
					validator.isMandatory(index) == true) {
				inputErrors.append("\nRequired: [" + validator.variableName(index) + "] empty or missing" );
			}
		}
		
		if(inputErrors.length() > 0) {
			throw new InputValidationException(inputErrors.toString());
		}
		
		ZillowQueryObj query = new ZillowQueryObj();
		query.setStreetNumber(o.get(validator.variableName(0)).toString().trim());
		query.setStreetName(o.get(validator.variableName(1)).toString().trim());
		query.setCity(o.get(validator.variableName(2)).toString().trim());
		query.setState(o.get(validator.variableName(3)).toString().trim());
		if(o.get(validator.variableName(4)) != null && o.get(validator.variableName(4)).toString().trim().length() > 0) {
			query.setZip(o.get(validator.variableName(4)).toString().trim());
		}
		
		return query;
	} 
}