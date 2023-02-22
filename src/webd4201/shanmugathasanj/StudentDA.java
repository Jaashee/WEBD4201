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

    public static boolean create(Student aStudent) throws DuplicateException, NotFoundException, SQLException {
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
        java.sql.Date lastAccessDate = new java.sql.Date(lastAccess.getTime());
        java.sql.Date orginal0EnrolDate = new java.sql.Date(enrolDate.getTime());

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
        sqlUserQuery.setDate(6, lastAccessDate);
        sqlUserQuery.setDate(7, orginal0EnrolDate);
        sqlUserQuery.setBoolean(8, enabled);
        sqlUserQuery.setString(9, String.valueOf(type));

        // see if this customer already exists in the database
        try
        {
            retrieve(id);
            throw (new DuplicateException("Problem with creating Customer record, phone number " + id +" already exists in the system."));
        }
        catch(DuplicateException | NotFoundException | SQLException | InvalidNameException | InvalidUserDataException |
              InvalidPasswordException | InvalidIdException e)
        {
            try
            {
                int stmt1 = sqlUserQuery.executeUpdate();
                int smtmt2 = sqlStudentQuery.executeUpdate();
                if (stmt1 > 0 && smtmt2 > 0)
                {
                    inserted = true;
                }
            }
            catch (SQLException ee)
            {
                System.out.println(ee.getMessage());
            }
        }
        return inserted;
    }

    public static Student retrieve(long id) throws DuplicateException, NotFoundException, SQLException, InvalidNameException, InvalidUserDataException, InvalidPasswordException, InvalidIdException {

        aStudent = new Student();
        PreparedStatement sqlQuery = aConnection.prepareStatement("SELECT users.id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year FROM users, students WHERE users.id = students.id AND students.id = ?;");
        sqlQuery.setLong(1, id);
        try
        {
            ResultSet rs = sqlQuery.executeQuery();
            boolean gotIt = rs.next();
            if (gotIt)
            {
                id = rs.getLong("id");
                password = rs.getString("password");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                emailAddress = rs.getString("emailAddress");
                lastAccess = rs.getDate("lastAccess");
                enrolDate = rs.getDate("enrolDate");
                enabled = rs.getBoolean("enabled");
                type = 's';
                programCode = rs.getString("programCode");
                programDescription = rs.getString("programDescription");
                year = rs.getInt("year");
                marks = new Vector<>();
                try
                {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year);
                }
                catch (InvalidUserDataException e)
                {

                    System.out.println(e.getMessage() + id + " has a invalid ID. Please check again.");
                }

            }
            else
            {
                throw (new NotFoundException("There was a Problem getting the Student record, The ID entered:" + id +" does not exist."));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return aStudent;
    }

    public static int update(Student aStudent) throws NotFoundException, InvalidIdException, InvalidNameException,
            InvalidPasswordException, InvalidUserDataException, SQLException {
        int records = 0;

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
        marks = aStudent.getMarks();
        java.sql.Date lastAccessDate = new java.sql.Date(lastAccess.getTime());
        java.sql.Date enrolDate1 = new java.sql.Date(enrolDate.getTime());

        String sqlUpdateUser = "UPDATE users " +
                "SET first_name = ?, " +
                "last_name = ?, " +
                "email_address = ?, " +
                "last_access = ?, " +
                "enrol_date = ?, " +
                "type = ?, " +
                "enabled = ? " +
                "WHERE id = ?";
        PreparedStatement sqlUserUpdate = aConnection.prepareStatement(sqlUpdateUser);
        sqlUserUpdate.setString(1, firstName);
        sqlUserUpdate.setString(2, lastName);
        sqlUserUpdate.setString(3, emailAddress);
        sqlUserUpdate.setDate(4, lastAccessDate);
        sqlUserUpdate.setDate(5, enrolDate1);
        sqlUserUpdate.setString(6, String.valueOf(type));
        sqlUserUpdate.setBoolean(7, enabled);
        sqlUserUpdate.setLong(8, id);

        String sqlUpdateStudent = "UPDATE students " +
                "SET program_code = ?, " +
                "program_description = ?, " +
                "year = ? " +
                "WHERE id = ?";
        PreparedStatement sqlStudentUpdate = aConnection.prepareStatement(sqlUpdateStudent);
        sqlStudentUpdate.setString(1, programCode);
        sqlStudentUpdate.setString(2, programDescription);
        sqlStudentUpdate.setInt(3, year);
        sqlStudentUpdate.setLong(4, id);
        try
        {
            retrieve(id);
            records = sqlUserUpdate.executeUpdate();
            records = sqlStudentUpdate.executeUpdate();
        }
        catch(NotFoundException | DuplicateException e)
        {
            throw new NotFoundException("Student with the id " + id + " cannot be updated, does not exist in the system.");
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return records;
    }

    public static int delete(Student aStudent) throws NotFoundException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException, SQLException {
        int records = 0;

        id = aStudent.getId();

        String sqlDeleteStudent = "DELETE FROM students WHERE id = ?;";
        String sqlDeleteUser = "DELETE FROM users WHERE id = ?;";
        PreparedStatement sqlUserDelete = aConnection.prepareStatement(sqlDeleteUser);
        PreparedStatement sqlStudentDelete = aConnection.prepareStatement(sqlDeleteStudent);
        sqlUserDelete.setLong(1, id);
        sqlStudentDelete.setLong(1, id);

        try
        {
            retrieve(id);
            records = sqlStudentDelete.executeUpdate();
            records = sqlUserDelete.executeUpdate();

        }
        catch(NotFoundException | DuplicateException e)
        {
            throw new NotFoundException("Student with the id " + id + " cannot be deleted, it doesn't exist in the system.");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return records;
    }


}
