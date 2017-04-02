package assignment.util;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

	static Properties properties_;
	
	
	private static Properties loadProps() {

		if(properties_ != null)
			return properties_;
		
		String propFilename = "assignment.properties";
	    FileInputStream is  = null;
	    try
	    {
	    	is = new FileInputStream(propFilename);
	    	if(is != null)
	    	{   
	    		properties_ = new Properties();
	    		properties_.load(is);
	    	}
	    }
	    catch (java.io.IOException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();      
	    }
	    
	    return properties_;
	}    
	
	protected static String getProperty(String name) {
		String retVal = "";
		try {
			retVal = loadProps().getProperty(name);
		}
		catch (Exception e) {
			
		}
		return retVal;
	}
}
