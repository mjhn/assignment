package assignment.util;

/**
 * 
 * @author Martin
 *
 * ErrorDoc: Wraps error string into xml file. Xml format is compatible with Zillow documents.
 * All application output is in xml format. Zillow output provides <message> data within their payload
 * In conveying system issues. System exceptions and data validation issues are conveying to the caller
 * using the same format. 
 *
 * <text> contains error message
 * <code> defaults to -1 for process exceptions. (ie: runtime exceptions, data validation errors.) 
 * Example:
 * 
 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 *	<SearchResults>
 *	<message>
 *		<text>Failed : HTTP error code : 404</text>
 *		<code>-1</code>
 *	</message>
 * </SearchResults>
 * 
 */
public class ErrorDoc {

	private final String xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><SearchResults><message><text>%s</text><code>-1</code></message></SearchResults>";
	public ErrorDoc() {
		
	}

	public String createErrorDoc(String err) {
		return String.format(this.xmlMessage, err);
	}
	
}
