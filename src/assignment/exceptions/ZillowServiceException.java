package assignment.exceptions;

import java.io.IOException;

public class ZillowServiceException extends IOException {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 5148637175400769183L;

	public ZillowServiceException(String arg) {
	    super(arg);
	}
	
	public String getMessage()
    {
        return super.getMessage();
    }
}
