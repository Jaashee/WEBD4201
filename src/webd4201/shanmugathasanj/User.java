package webd4201.shanmugathasanj;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static java.lang.Double.*;

/**
 * The User.java class  is the parent class to Faculty.java, Student.java
 * and extends CollegeInterface
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */

public class User implements CollegeInterface {
    /**
     * Holds the minimum value the ID should be
     */
    public static final long MINIMUM_ID_NUMBER = 100000000L;

    /**
     * Holds maximum value the ID can be
     */
    public static final long MAXIMUM_ID_NUMBER = 999999999L;

    /**
     * Variable for the timezone and format for the date
     */
    protected static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);

    /**
     * The default user id
     */
    protected static long DEFAULT_ID = 100123456;

    /**
     * The default password
     */
    protected static String DEFAULT_PASSWORD = "password";

    /**
     * The minimum character the password can have
     */
    protected static byte MINIMUM_PASSWORD_LENGTH = 8;

    /**
     * The maximum character length the password can have
     */
    protected static byte MAXIMUM_PASSWORD_LENGTH = 40;

    /**
     * The default name set for firstname
     */
    protected static String DEFAULT_FIRST_NAME = "John";

    /**
     * The default lastname
     */
    protected static String DEFAULT_LAST_NAME = "Doe";

    /**
     * The default email address
     */
    protected static String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";

    /**
     * Sets the boolean to enabled
     */
    protected static boolean DEFAULT_ENABLED_STATUS = true;

    /**
     * Default type set for user
     */
    protected static char DEFAULT_TYPE = 's';

    /**
     * The ID can only be 9 characters long
     */
    protected static byte ID_NUMBER_LENGTH = 9;

    /**
     * Private long to hold id
     */
    private long id;

    /**
     * String to hold password
     */
    private String password;

    /**
     * String to hold the first name
     */
    private String firstName;

    /**
     * string to hold the last name
     */
    private String lastName;

    /**
     * String to hold the email address
     */
    private String emailAddress;

    /**
     * Date variable to last access time and date
     */
    private Date lastAccess;

    /**
     * Date variable to hold time and date enroll date
     */
    private Date enrolDate;

    /**
     * Variable for the boolean that's enabled
     */
    private boolean enabled;

    /**
     * Variable to hold User type
     */
    private char type;

    /**
     * Parameterized Constructor
     *
     * @param id           User ID
     * @param password     User password
     * @param firstName    User firstname
     * @param lastName     User lastname
     * @param emailAddress User email address
     * @param lastAccess   User last access date
     * @param enrolDate    User enroll date
     * @param enabled      User status enabled or not
     * @param type         User account type
     * @throws InvalidUserDataException Throws an exception if method fails
     */
    public User(long id, String password, String firstName, String lastName,
                String emailAddress, Date lastAccess, Date enrolDate,
                boolean enabled, char type) throws InvalidUserDataException {
        try {
            this.id = id;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
            this.lastAccess = lastAccess;
            this.enrolDate = enrolDate;
            this.enabled = enabled;
            this.type = type;
        } catch (final Exception e) {
            throw new InvalidUserDataException(e.getMessage());
        }
    }

    /**
     * The default constructor
     */
    public User() {
        this.id = DEFAULT_ID;
        this.password = DEFAULT_PASSWORD;
        this.firstName = DEFAULT_FIRST_NAME;
        this.lastName = DEFAULT_LAST_NAME;
        this.emailAddress = DEFAULT_EMAIL_ADDRESS;
        this.lastAccess = new Date();
        this.enrolDate = new Date();
        this.enabled = true;
        this.type = DEFAULT_TYPE;
    }

    /**
     * Returns bool if the id is within mac and min
     *
     * @param id numeric variable to represent id
     */
    public static boolean verifyId(long id)
    {
        boolean flag = true;
        if (Long.toString(id).length() != ID_NUMBER_LENGTH)
        {
            flag = false;
        }
        return flag;
    }

    /**
     * Method to get the id
     * @return the id as a long
     */
    public long getId() {
        return id;
    }

    /**
     * Method to set the id
     * @param id must be between a min and max
     * @throws InvalidIdException is thrown if id isn't set
     */
    public void setId(long id) throws InvalidIdException {
        if (MINIMUM_ID_NUMBER <= id && MAXIMUM_ID_NUMBER >= id) {
            this.id = id;
        } else {
            throw new InvalidIdException(id + "is not a valid ID");
        }
    }

    /**
     * Method to get the password
     *
     * @return password     user's password in string format
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set the password
     * @param password must be between the min and max length set
     * @throws InvalidPasswordException is thrown if anything fails to set the password
     */
    public void setPassword(final String password) throws InvalidPasswordException {
        if (password.length() >= User.MINIMUM_PASSWORD_LENGTH && password.length() <= User.MAXIMUM_PASSWORD_LENGTH) {
            this.password = password;
        } else {
            throw new InvalidPasswordException("The Password is invalid, the length should be between " + User.MINIMUM_PASSWORD_LENGTH + " and " + User.MAXIMUM_PASSWORD_LENGTH + " .");
        }

    }

    /**
     * Method to get the first name
     *
     * @return firstName    in a string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to set the first name
     *     * @param firstName must be more than 0 and not have numbers
     * @throws InvalidNameException is thrown if anything fails to set the first name
     */
    public void setFirstName(final String firstName) throws InvalidNameException {
        boolean flag = false;
        try {
            final double test = parseDouble(firstName);
        } catch (final NumberFormatException ex) {
            flag = true;
        }

        if (!(firstName.isEmpty()) && flag) {
            this.firstName = firstName;
        } else {
            throw new InvalidNameException(firstName + " The firstname is entered is not valid.");
        }
    }

    /**
     * Method to gets the last name
     *
     * @return lastName     in a String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to set the last name
     * @param lastName must be more than 0 and not have numbers
     * @throws InvalidNameException is thrown if anything fails to set the last name
     */
    public void setLastName(final String lastName) throws InvalidNameException {
        boolean flag = false;
        try {
            final double test = parseDouble(lastName);
        } catch (final NumberFormatException ex) {
            flag = true;
        }

        if (!(lastName.isEmpty()) && flag) {
            this.lastName = lastName;
        } else {
            throw new InvalidNameException(lastName + " is not a valid lastname.");
        }
    }

    /**
     * Method to get the email address
     *
     * @return emailAddress     in a String
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Method to set the email address
     *
     * @param emailAddress a string for the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Method to gets the last access date
     *
     * @return lastAccess   in the Date format (Medium, Canadian)
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Method to gets the enrolment date
     *
     * @return enrolDate    in the Date format (Medium, Canadian)
     */
    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * Method to get the enabled status
     *
     * @return enabled   in a boolean format
     */
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Method to gets the user type
     *
     * @return type     as a char
     */
    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    /**
     * @return
     */
    public String getTypeForDisplay() {
        return "User";
    }

    @Override
    public String toString() {
        return getTypeForDisplay() + "\n" +
                "Student ID    = " + this.id + "\n" +
                "Name          = " + this.firstName + ' ' + this.lastName + "\n" +
                "Email Address = " + this.emailAddress + '\n' +
                "Created On    = " + this.enrolDate + "\n" +
                "Last Access   = " + this.lastAccess + "\n";
    }

    public void dump() {
        System.out.println(this);
    }

    public static String hashPassword(String password)
    {
        String createdPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update((password.getBytes()));
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<bytes.length; i++)
            {
                sb.append(String.format("%02x",bytes[i]));
            }
            createdPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return createdPassword;
    }

}







