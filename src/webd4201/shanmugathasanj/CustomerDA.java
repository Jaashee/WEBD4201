package webd4201.shanmugathasanj; /**
 * CustomerDA - this file is contains all of the data access methods, that actually get/set data to the database. 
 * Note: that all the methods are static this is because you do not really create CustomerDA objects (does not make sense)
 * @author Darren Puffer
 * @version 1.0 (13 March 2015)
 * @since 1.0
 */

import java.util.Vector;
import java.sql.*;

public class CustomerDA
{
	static Vector<Customer> customers = new Vector<Customer>();	// contains customer references
	static Customer aCustomer;
	
	// declare variables for the database connection
	static Connection aConnection;
	static Statement aStatement;

	// declare static variables for all Customer instance attribute values
	static String name;
	static String address;
	static String phoneNumber;

	// establish the database connection
	public static void initialize(Connection c)
	{
            try {
                aConnection=c;
                aStatement=aConnection.createStatement();
            }
            catch (SQLException e)
            { System.out.println(e);	}
	}

	// close the database connection
	public static void terminate()
	{
            try
            { 	// close the statement
                aStatement.close();
            }
            catch (SQLException e)
            { System.out.println(e);	}
	}

	public static Customer retrieve(String key) throws NotFoundException
	{ // retrieve Customer and Boat data
		aCustomer = null;
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT Name, Address, PhoneNumber " +
                                    " FROM Customers " +
                                    " WHERE PhoneNumber = '" + key +"'" ;
                //System.out.println(sqlQuery);
	 	// execute the SQL query statement
		try
 		{
                    ResultSet rs = aStatement.executeQuery(sqlQuery);
                    // next method sets cursor & returns true if there is data
                    boolean gotIt = rs.next();
                    if (gotIt)
                    {	// extract the data
			name = rs.getString("Name");
			address = rs.getString("Address");
			phoneNumber = rs.getString("PhoneNumber");
			
			// create Customer
                        try{
                            aCustomer = new Customer(name, address, phoneNumber);
                        }catch (InvalidPhoneNumberException e)
                        { System.out.println("Record for " + name + " contains an invalid phone number.  Verify and correct.");}
                        
                    } else	// nothing was retrieved
                    {throw (new NotFoundException("Problem retrieving Customer record, phone number " + key +" does not exist in the system."));}
                    rs.close();
	   	}catch (SQLException e)
		{ System.out.println(e);}
                
		return aCustomer;
	}

	public static Vector<Customer> retrieveAll()
        {   // retrieve Customers and their boats
            // define the SQL query statement for get all
            String sqlQuery = "SELECT Name, Address, PhoneNumber " +
                                "FROM Customers";
            try
            {   // execute the SQL query statement
                ResultSet rs = aStatement.executeQuery(sqlQuery);
                boolean moreData = rs.next();
                
                while (moreData)
                {	// extract the data
                    name = rs.getString(1);
                    address = rs.getString(2);
                    phoneNumber = rs.getString(3);
                    
                    // try tp create Customer instance
                    try{
                        aCustomer = new Customer(name, address, phoneNumber);
                    }catch (InvalidPhoneNumberException e)
                    { System.out.println("Record for " + name + " contains an invalid phone number.  Verify and correct.");}
                    
                    customers.addElement(aCustomer);
                    moreData = rs.next();
                }
                rs.close();
            }
            catch (SQLException e)
                    { System.out.println(e);}
            return customers;
	}

	public static boolean create(Customer aCustomer) throws DuplicateException
	{	
		boolean inserted = false; //insertion success flag
		// retrieve the customer attribute values
		name = aCustomer.getName();
		address = aCustomer.getAddress();
		phoneNumber = aCustomer.getPhoneNumber();
		
		// create the SQL insert statement using attribute values
		String sqlInsert = "INSERT INTO Customers " +
                                    "(Name, Address, PhoneNumber) " +
                                    "VALUES ('" + name + "', '" +
                                    address + "', '" +
                                    phoneNumber + "')";

		// see if this customer already exists in the database
		try
		{
			retrieve(phoneNumber);
			throw (new DuplicateException("Problem with creating Customer record, phone number " + phoneNumber +" already exists in the system."));
		}
		// if NotFoundException, add customer to database
		catch(NotFoundException e)
		{
			try
 			{  // execute the SQL update statement
	    		inserted = aStatement.execute(sqlInsert);
			}
			catch (SQLException ee)
				{ System.out.println(ee);	}
		}
		return inserted;
	}

	public static int delete(Customer aCustomer) throws NotFoundException
	{	
		int records = 0;
		// retrieve the phone no (key)
		phoneNumber = aCustomer.getPhoneNumber();
		// create the SQL delete statement
		String sqlDelete = "DELETE FROM Customers " +
                                    "WHERE PhoneNumber = '" + phoneNumber +"'";

		// see if this customer already exists in the database
		try
		{
			Customer.retrieve(phoneNumber);  //used to determine if record exists for the passed Customer
    		// if found, execute the SQL update statement
    		records = aStatement.executeUpdate(sqlDelete);
		}catch(NotFoundException e)
		{
			throw new NotFoundException("Customer with phone number " + phoneNumber 
					+ " cannot be deleted, does not exist.");
		}catch (SQLException e)
			{ System.out.println(e);	}
		return records;
	}

	public static int update(Customer aCustomer) throws NotFoundException
	{	
		int records = 0;  //records updated in method
		
		// retrieve the customer argument attribute values
		phoneNumber = aCustomer.getPhoneNumber();
		name = aCustomer.getName();
		address = aCustomer.getAddress();

		// define the SQL query statement using the phone number key
		String sqlUpdate = "Update Customers " +
                                    " SET Name      = '" + name +"', " +
                                    " Address   = '" + address +"' " +
                                    " WHERE PhoneNumber = '" + phoneNumber + "'";

		// see if this customer exists in the database
		// NotFoundException is thrown by find method
		try
		{
                    Customer.retrieve(phoneNumber);  //determine if there is a Customer recrod to be updated
                    // if found, execute the SQL update statement
                    records = aStatement.executeUpdate(sqlUpdate);
		}catch(NotFoundException e)
		{
			throw new NotFoundException("Customer with phone number " + phoneNumber 
					+ " cannot be updated, does not exist in the system.");
		}catch (SQLException e)
		{ System.out.println(e);}
		return records;
	}
}

