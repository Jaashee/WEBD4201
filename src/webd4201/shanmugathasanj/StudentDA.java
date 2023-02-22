package webd4201.shanmugathasanj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.sql.*;

public class StudentDA {

    static Vector<Student> customers = new Vector<Student>();	// contains Student references
    static Student aStudent;

    // declare variables for the database connection
    static Connection aConnection;
    static Statement aStatement;

    // declare static variables for all Customer instance attribute values
    static long id;
    static String password;
    static String firstName;
    static String lastName;
    static String emailAddress;
    static Date lastAccess;
    static Date enrolDate;
    static boolean enabled;
    static char type;
    static String programCode;
    static String programDescription;
    static int year;
    static Vector<Mark> marks;

    //Class content date format
    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-mm-dd");

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

    public static boolean create(Student aStudent) throws DuplicateException, SQLException {
        boolean inserted = false; //insertion success flag
        // retrieve the customer attribute values
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = aStudent.getLastAccess();
        enrolDate = aStudent.getEnrolDate();
        enabled = aStudent.isEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();
        String hashedPassword = aStudent.hashPassword(password);

        // Prepared Statements
        PreparedStatement sqlStudentQuery = aConnection.prepareStatement("INSERT INTO students (id, programCode, programDescription, year)" + "VALUES (?,?,?,?);");

        PreparedStatement sqlUserQuery = aConnection.prepareStatement("INSERT INTO users (id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

        sqlStudentQuery.setLong(1,id);
        sqlStudentQuery.setString(2, programCode);
        sqlStudentQuery.setString(3, programDescription);
        sqlStudentQuery.setInt(4, year);

        sqlUserQuery.setLong(1, id);
        sqlUserQuery.setString(2, password);
        sqlUserQuery.setString(3, firstName);
        sqlUserQuery.setString(4, lastName);
        sqlUserQuery.setString(5, emailAddress);
        sqlUserQuery.setDate(6, lastAccess);
        sqlUserQuery.setDate(7, enrolDate);
        sqlUserQuery.setBoolean(8, enabled);
        sqlUserQuery.setString(9, String.valueOf(type));

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

}
