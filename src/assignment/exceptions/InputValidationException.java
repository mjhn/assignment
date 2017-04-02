package assignment.exceptions;

public class InputValidationException extends Exception {
	
	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 2196771059580514214L;

	public InputValidationException(String arg) {
	    super(arg);
	}
	
	public String getMessage()
    {
        return super.getMessage();
    }
}
