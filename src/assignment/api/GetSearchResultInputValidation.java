package assignment.api;


/**
 * 
 * @author Martin
 *
 *	Simple validation for Zillow GetSearchResults.htm
 *
 */
public class GetSearchResultInputValidation implements EndpointValidation {

	/*
	 * Valid attribute names.
	 */
	static String [] keys = { "streetNumber", "streetName",  "city", "state", "zip" };
	
	/*
	 * Index based - true: attrib required, false: attrib not required.
	 */
	static boolean [] mandatory = { true, true, true, true, false };
	
	@Override
	public boolean isMandatory(int index) {
		if(index > mandatory.length) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		return mandatory[index];
	}

	@Override
	public String variableName(int index) {
		if(index > keys.length) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		return keys[index];
	}
	
	@Override
	public int count() {
		return keys.length;
	}

}
