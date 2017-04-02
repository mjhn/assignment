package assignment.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 
 * @author Martin
 *
 *	Simple POJO covering URL parameters to Zillow GetSearchResults.
 */
public class ZillowQueryObj {

	private String zip_;
	private String streetNumber_;
	private String streetName_;
	private String city_;
	private String state_;
	
	public ZillowQueryObj() {
		
	}

	public String getZip() {
		return zip_;
	}

	public void setZip(String zip) {
		zip_ = zip;
	}

	public String getStreetNumber() {
		return streetNumber_;
	}

	public void setStreetNumber(String streetNumber) {
		streetNumber_ = streetNumber;
	}

	public String getStreetName() {
		return streetName_;
	}

	public void setStreetName(String streetName) {
		streetName_ = streetName;
	}

	public String getCity() {
		return city_;
	}

	public void setCity(String city) {
		city_ = city;
	}

	public String getState() {
		return state_;
	}

	public void setState(String state) {
		state_ = state;
	}
	
	public String toString() {
		return 
		"streetNumber:" + this.getStreetNumber() + "\n" +
		"streetName:" + this.getStreetName() + "\n" +
		"city:" + this.getState() + "\n" +
		"state:" + this.getCity() + "\n";
	}
	
	/**
	 * packGET
	 * @return Url endcoded paremeters for GET request.
	 * @throws UnsupportedEncodingException
	 */
	public String packGET() throws UnsupportedEncodingException  {
		StringBuffer sb = new StringBuffer();
		sb.append("&address=" + URLEncoder.encode(this.getStreetNumber() + " " + this.getStreetName(), "UTF-8"));
		sb.append("&citystatezip=" + URLEncoder.encode(this.getCity() + "," + this.getState(), "UTF-8"));
		if(this.getZip() != null && this.getZip().length() > 0) {
			sb.append(URLEncoder.encode("," + this.getZip(), "UTF-8"));
		}
		return sb.toString();
		
	}
}
