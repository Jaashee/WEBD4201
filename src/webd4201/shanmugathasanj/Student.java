package webd4201.shanmugathasanj;

import java.util.Date;
import java.util.Vector;

/**
 * Student class extends the user class and is another user type
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */
public class Student extends User {

    /**
     * The Default Program Codes
     */
    public static final String DEFAULT_PROGRAM_CODE = "UNDC";

    /**
     * The Default Program Description
     */
    public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";

    /**
     * The Default Year the student user is in
     */
    public static final int DEFAULT_YEAR = 1;

    /**
     * Private String variable to hold Program Code
     */
    private String programCode;
    /**
     * Private String variable to hold the program description
     */
    private String programDescription;
    /**
     * Private Int variable to hold the student year
     */
    private int year;
    /**
     * Private Vector to hold the marks the student
     */
    private Vector<Mark> marks;

    /**
     * Method that returns the Program Code
     *
     * @return programCode  In the String variable
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Method that sets the Program Code
     *
     * @param programCode In the String Format
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * Method that returns the Program Description
     *
     * @return programDescription  In the String variable
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * Method that sets the Program Description
     *
     * @param programDescription In the String variable
     */
    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * Method that returns the Students current year
     *
     * @return year     In the int variable
     */
    public int getYear() {
        return year;
    }

    /**
     * Method that sets user Student current enrolled year
     *
     * @param year In the int variable
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Method that returns a vector marks
     *
     * @return marks    As a vector
     */
    public Vector<Mark> getMarks() {
        return marks;
    }

    /**
     * Method that sets a vector of marks for grades
     *
     * @param marks In a vector
     */
    public void setMarks(Vector<Mark> marks) {
        this.marks = marks;
    }

    /**
     * Parameterized Constructor that accepts a vector of marks
     *
     * @param id                 User's ID
     * @param password           User's Password
     * @param firstName          User's First Name
     * @param lastName           User's Last Name
     * @param emailAddress       User's Email Address
     * @param lastAccess         User's Last Access Date
     * @param enrolDate          User's Enrolment Date
     * @param enabled            User's Enabled Status
     * @param type               User's Account Type
     * @param programCode        User's Program Code
     * @param programDescription User's Program Description
     * @param year               User's Current Year
     * @param marks              User's Vector of Marks
     * @throws InvalidUserDataException throws an exception
     */
    public Student(final long id, final String password, final String firstName, final String lastName, final String emailAddress, final Date lastAccess, final Date enrolDate, final boolean enabled, final char type, final String programCode, final String programDescription, final int year, final Vector<Mark> marks)
            throws InvalidUserDataException {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        this.programCode = programCode;
        this.programDescription = programDescription;
        this.year = year;
        this.marks = marks;
    }

    /**
     * Parameterized Constructor without vector
     *
     * @param id                 User's ID
     * @param password           User's Password
     * @param firstName          User's First Name
     * @param lastName           User's Last Name
     * @param emailAddress       User's Email Address
     * @param lastAccess         User's Last Access Date
     * @param enrolDate          User's Enrolment Date
     * @param enabled            User's Enabled Status
     * @param type               User's Account Type
     * @param programCode        User's Program Code
     * @param programDescription User's Program Description
     * @param year               User's Current Year
     * @throws InvalidUserDataException throws an exception
     */
    public Student(final long id, final String password, final String firstName, final String lastName, final String emailAddress,
                   final Date lastAccess, final Date enrolDate, final boolean enabled, final char type, final String programCode,
                   final String programDescription, final int year) throws InvalidNameException, InvalidIdException, InvalidPasswordException, InvalidUserDataException {
        this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
                enabled, type, programCode, programDescription, year, new Vector<>());
    }

    /**
     * Default Contractor
     */
    public Student() throws InvalidNameException, InvalidIdException, InvalidPasswordException, InvalidUserDataException {
        this(User.DEFAULT_ID, User.DEFAULT_PASSWORD, User.DEFAULT_FIRST_NAME, User.DEFAULT_LAST_NAME, User.DEFAULT_EMAIL_ADDRESS,
                new Date(), new Date(), User.DEFAULT_ENABLED_STATUS, User.DEFAULT_TYPE, Student.DEFAULT_PROGRAM_CODE,
                Student.DEFAULT_PROGRAM_DESCRIPTION, Student.DEFAULT_YEAR, new Vector<>());
    }


    /**
     * An Override method that returns the user account type as student
     *
     * @return Student
     */
    @Override
    public String getTypeForDisplay() {
        return "Student";
    }

    /**
     * An Override method that returns the object in a string format
     *
     * @return object as formatted output with student information
     */
    @Override
    public String toString() {
        return getTypeForDisplay() + "\n" + getFirstName() + " " + getLastName()
                + "(" + getId() + ")" + "\n" + "Currently in " + this.year + " year of "
                + this.programDescription + " " + this.programCode + "\n" + "Enrolled: " + getEnrolDate();
    }
}
