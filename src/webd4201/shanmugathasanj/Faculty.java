package webd4201.shanmugathasanj;

import java.util.Date;

/**
 * Faculty class extends the user class and is another User type
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */
public class Faculty extends User {
    /**
     * The default school code
     */
    public static final String DEFAULT_SCHOOL_CODE = "SET";
    /**
     * the default school description
     */
    public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
    /**
     * the default office code
     */
    public static final String DEFAULT_OFFICE = "H-140";
    /**
     * the default phone extension
     */
    public static final int DEFAULT_PHONE_EXTENSION = 1234;

    /**
     * String to hold school code
     */
    private String schoolCode;
    /**
     * String to hold school description
     */
    private String schoolDescription;
    /**
     * String to hold office code
     */
    private String office;
    /**
     * String to hold phone extension
     */
    private int extension;

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolDescription() {
        return schoolDescription;
    }

    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    /**
     * Parameterized Constructor
     *
     * @param id                User's ID
     * @param password          User's Password
     * @param firstName         User's First Name
     * @param lastName          User's Last Name
     * @param emailAddress      User's Email Address
     * @param lastAccess        User's Last Time Logged In
     * @param enrolDate         User's Enrolment Date
     * @param enabled           User's Enabled Status
     * @param type              User's Account Type (Faculty)
     * @param schoolCode        User's School Code
     * @param schoolDescription User's School Description
     * @param office            User's Office
     * @param extension         User's Phone Extension
     * @throws InvalidUserDataException throws an exception if anything doesn't set
     */
    public Faculty(final long id, final String password, final String firstName, final String lastName, final String emailAddress, final Date lastAccess, final Date enrolDate, final boolean enabled, final char type, final String schoolCode, final String schoolDescription, final String office, final int extension)
            throws InvalidUserDataException {

        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        this.schoolCode = schoolCode;
        this.schoolDescription = schoolDescription;
        this.office = office;
        this.extension = extension;
    }

    /**
     * Default contractor that uses the parent class constructor
     */
    public Faculty() {

        this.schoolCode = DEFAULT_SCHOOL_CODE;
        this.schoolDescription = DEFAULT_SCHOOL_DESCRIPTION;
        this.office = DEFAULT_OFFICE;
        this.extension = DEFAULT_PHONE_EXTENSION;
    }

    /**
     * Override method that returns the user account type for Faculty
     *
     * @return Faculty as a string
     */
    @Override
    public String getTypeForDisplay() {
        return "Faculty";
    }

    /**
     * Override method that returns the object in a
     *
     * @return object as in formatted output as string
     */
    @Override
    public String toString() {
        return getTypeForDisplay() + "\n" +
                "Faculty ID    = " + getId() + "\n" +
                "Name          = " + getFirstName() + ' ' + getLastName() + "\n" +
                "Email Address = " + getEmailAddress() + '\n' +
                "Created On    = " + getEnrolDate() + "\n" +
                "Last Access   = " + getLastAccess() + "\n" +
                this.schoolDescription + "\n" +
                "Office        = " + this.office + "\n" + this.extension + "\n";

    }
}
