package webd4201.shanmugathasanj;

/**
 * The CollegeInterface.java class is the super  class to the rest of the classes
 * and extends CollegeInterface
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */

public interface CollegeInterface {

    /**
     * String to hold the college's name
     */
    String COLLEGE_NAME = "Durham College";

    /**
     * String to hold the school's phone number
     */
    String PHONE_NUMBER = "(905)721-2000";

    /**
     * Returns the User's type depending on which child class calls for it
     */
    String getTypeForDisplay();
}
