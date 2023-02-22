package webd4201.shanmugathasanj;

/**
 * NotFoundException - this file contains extends the generic Exception class so that
 *                 we have a custom Exception, this one will be used to flag when 
 *                 no record was found in the database (and therefore nothing can be
 *                 done to it)
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */

@SuppressWarnings("serial")
public class NotFoundException extends Exception
{
	public NotFoundException()
	{ super();}
	
	public NotFoundException(String message)
	{ super(message);}
}