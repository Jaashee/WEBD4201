package webd4201.shanmugathasanj;

/**
 * InvalidPhoneNumberException - 	this file contains extends the generic Exception class so that
 * 			            			we have a custom Exception, this one will be used to flag an invalid
 * 					                phone number (use for data validation for Customer creation)
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */ 

@SuppressWarnings("serial")
public class InvalidPhoneNumberException extends Exception
{
    public InvalidPhoneNumberException()
    { super();}

    public InvalidPhoneNumberException(String message)
    { super(message);}
}