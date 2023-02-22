package webd4201.shanmugathasanj;

import java.text.DecimalFormat;

/**
 * The mark class is meant to hold the grades for the user's
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */
public class Mark {

    /**
     * Minimum GPA
     */
    public static final float MINIMUM_GPA = 0.0f;
    /**
     * Maximum GPA
     */
    public static final float MAXIMUM_GPA = 5.0f;
    /**
     * The format of the printed GPAs
     */
    String newDecimal = "0#.##";
    /**
     * Updates the format to the new one created
     */
    DecimalFormat GPA = new DecimalFormat(newDecimal);

    /**
     * Private String variable to hold the course code
     */
    private String courseCode;
    /**
     * Private String to hold the course name
     */
    private String courseName;
    /**
     * Integer to hold the result
     */
    private int result;
    /**
     * Float the holds the GPA weighting
     */
    private float gpaWeighting;

    /**
     * Method to gets the Course Code
     *
     * @return courseCode   as a String
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Method to set the Course Name
     *
     * @param courseCode in a String
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Method to gets the Course Name
     *
     * @return courseName   as a String
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Method to set the Course Code
     *
     * @param courseName in a String
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method to gets the result
     *
     * @return result   as an int
     */
    public int getResult() {
        return result;
    }

    /**
     * Method to set the grade Result
     *
     * @param result in an int
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Method to gets the GPA Weight
     *
     * @return gpaWeighting  as a float
     */
    public float getGpaWeighting() {
        return gpaWeighting;
    }

    /**
     * Method to set the GPA Weight
     *
     * @param gpaWeighting in a float
     */
    public void setGpaWeighting(float gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }

    /**
     * Parameterized Constructor
     *
     * @param courseCode   Course Code
     * @param courseName   Course Name
     * @param result       Course Grade
     * @param gpaWeighting GPA Weighting
     */
    public Mark(String courseCode, String courseName, int result, float gpaWeighting) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.gpaWeighting = gpaWeighting;
        this.result = result;
    }

    /**
     * Default Contractor
     */
    public Mark() {
        this.courseCode = "00000";
        this.courseName = "DEFAULT";
        this.gpaWeighting = 0.0f;
        this.result = 0;
    }

    /**
     * An Override method that returns the object in a string format
     *
     * @return object as a string Formatted for the output
     */
    @Override
    public String toString() {
        return String.format("%-20s", this.courseCode) +
                String.format("%-35s", this.courseName) +
                String.format("%-15s", this.result) +
                String.format("%-15s", this.gpaWeighting);
    }


}
