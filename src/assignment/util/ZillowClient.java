package assignment.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
//import java.net.UnknownHostException;
import java.net.URL;
import assignment.util.ZillowQueryObj;
import assignment.exceptions.ZillowServiceException;
import assignment.util.Settings;
/**
 * 
 * @author Martin
 *
 * Simple HTTP client accessing Zillow GetSearchResults.htm 
 * 
 * Usage: new ZillowClient().initialiseURL(ZillowQueryObj).callEndpoint();
 *  
 * 
 */
public class ZillowClient {

	URL url_;
	
	/*
	 * URL containing configuration key <ZWS-ID>.
	 */
	final String zillowURL = "http://www.zillow.com/webservice/GetSearchResults.htm?zws-id=X1-ZWz19acgynvzm3_517ck";
	
	public ZillowClient() {
		
	}
	
	public ZillowClient initialiseURL(ZillowQueryObj query) throws MalformedURLException, UnsupportedEncodingException {
		if(Settings.getProperty("ZillowURL").length() > 0 &&
		   Settings.getProperty("ZillowID").length() > 0) {
			
			url_ = new URL(Settings.getProperty("ZillowURL") + "?zws-id=" + Settings.getProperty("ZillowID") + query.packGET());
		}
		else {
			url_ = new URL(zillowURL + query.packGET());
		}
		return this;
	}
	
	public String callEndpoint() throws IOException {

		HttpURLConnection conn = (HttpURLConnection) url_.openConnection();
		StringBuffer returnVal = new StringBuffer();
		try {
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/xml");
	
			if (conn.getResponseCode() != 200) {
				throw new ZillowServiceException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
	
			String output;
			while ((output = br.readLine()) != null) {
				returnVal.append(output);
			}
		}
		finally {
			conn.disconnect();
		}
		return returnVal.toString();
	}
}
