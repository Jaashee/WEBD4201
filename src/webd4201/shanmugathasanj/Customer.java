package webd4201.shanmugathasanj; /**
 * Customer - this file contains the Problem Domain (PD) class that
 *              will be used to create Customer objects, in addition to all
 *              of the normal attributes and methods, it also has methods that
 *              connect the Customer to the static CustomerDA and CustomerConnectDatabase
 *              methods  
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */

import java.util.Vector;
import java.sql.*;

public class Customer
{
	// attribute definitions
 	private String name;
 	private String address;
 	private String phoneNumber;
 	
 	//class constants
    private final static int VALID_PHONE_NUMBER_LENGTH = 10;
	// DA static methods, you DO NOT need to be a Customer object to do these *********************************
	public static void initialize(Connection c)
		{
            CustomerDA.initialize(c);}
	public static Customer retrieve(String key) throws NotFoundException
		{return CustomerDA.retrieve(key);}
	public static Vector<Customer> retrieveAll()
		{return CustomerDA.retrieveAll();}
	public static void terminate()
		{
            CustomerDA.terminate();}

	// DA instance methods, you NEED to be a Customer object to do these *********************************
	public void create() throws DuplicateException
		{
            CustomerDA.create(this);}
	public void delete() throws NotFoundException
		{
            CustomerDA.delete(this);}
	public void update() throws NotFoundException
		{
            CustomerDA.update(this);}

	// constructor with parameters
	public Customer(String name, String address, String phoneNumber)throws InvalidPhoneNumberException
	{
		// invoke accessors to populate attributes
		setName(name);
		setAddress(address);
                try{
                    setPhoneNumber(phoneNumber);
                }catch(InvalidPhoneNumberException ipne)
                { throw ipne;}		
    	}

	// get accessors
 	public String getName()
 		{ return name;}
	public String getAddress()
 		{ return address;}
	public String getPhoneNumber()
 		{ return phoneNumber;}

	// set accessors
	public void setName(String newName)
	{ name = newName;}
	public void setAddress(String newAddress)
	{ address = newAddress;}
	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException
    { 
        if(isValidPhoneNumber(phoneNumber))
            this.phoneNumber = phoneNumber;
        else
            throw new InvalidPhoneNumberException(phoneNumber + " is invalid");
    }
        
    private static boolean isValidPhoneNumber(String possibleNumber) {
        boolean validity = true;
        if(possibleNumber.length() != VALID_PHONE_NUMBER_LENGTH)
        {
            validity = false;
        }else{
            try {
                Long.parseLong(possibleNumber);
            }catch (NumberFormatException nfe) {
                validity = false;
            }
        }
        return validity;
    }
    public static String formatPhoneNumber(String arg) {
        String formattedPhoneNumber = arg;
        if(isValidPhoneNumber(arg))
        {
            //if the argument is a valid phone number format it, no else means
            //it will return the same string passed if invalid
             formattedPhoneNumber = "(" + arg.substring(0,3) + ")" +
                    arg.substring(3,6) + "-" + arg.substring(6,10);
        }
        return formattedPhoneNumber;
        
    }
	public String toString()
	{
		String customerDetails = "Owner is " + getName() +
				" living in " + getAddress() +
				" with phone " + formatPhoneNumber(getPhoneNumber());
		return customerDetails;
	}
}
