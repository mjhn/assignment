
package assignment.main;

import java.io.IOException;
import java.util.Properties;
import assignment.api.GetSearchResults;
import assignment.util.ErrorDoc;
import java.io.File;
import org.apache.commons.io.*;

public class Main {

	static String json = "{\"streetNumber\": \"2114\",\"streetName\": \"Bigelow Ave\",\"city\": \"Seattle\",\"state\":\"WA\",\"zip\":\"98109\"}";
	static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Zillowquery><treetNumber>2114</streetNumber><streetName>Bigelow Ave</streetName><city>Seattle</city><state>WA</state><zip>98109</zip></Zillowquery>";
	static Properties properties_;
	static String InvalidArgsMessage = "Invalid args \"-f inputFile -t [xml|json]\"";
	
	/**
	 * 
	 * @param args 
	 * 
	 * Commandline: -f inputFileName -t format ([xml|json])
	 * 
	 * Submit givin input file to respective input formatter and submit to 'GetSearchResults' 
	 * 
	 */
	public static void main(String[] args) {
		
		// TODO improve command line processing.
		
		if(args.length != 4)  {
			System.out.println(new ErrorDoc().createErrorDoc(InvalidArgsMessage));
			return;
		}
		else if(!args[0].equals("-f") || !args[2].equals("-t")) {
			System.out.println(new ErrorDoc().createErrorDoc(InvalidArgsMessage));
			return;
		}
		else if(!args[3].equals("json") && !args[3].equals("xml")) {
			System.out.println(new ErrorDoc().createErrorDoc(InvalidArgsMessage));
			return;
		}
		else if(new File(args[1]).isFile() == false) {
			System.out.println(new ErrorDoc().createErrorDoc("data file does not exist"));
			return;
		}
		try {
			if(args[3].equals("json")) {	
					System.out.println(GetSearchResults.submitJSON(FileUtils.readFileToString(new File(args[1]), "UTF-8")));
			}
			else if(args[3].equals("xml")) {	
				System.out.println(GetSearchResults.submitXML(FileUtils.readFileToString(new File(args[1]), "UTF-8")));
			}
		} catch (IOException e) {
			System.out.println(new ErrorDoc().createErrorDoc(e.getMessage()));
		}
	}
}

