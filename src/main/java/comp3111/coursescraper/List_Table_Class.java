package comp3111.coursescraper;

import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundFill;

import javafx.scene.paint.Color;

import javafx.scene.control.CheckBox;

import javafx.scene.layout.CornerRadii;
import javafx.scene.control.Label;

/**
 * This class is implemented for both the List Tab and the TimeTable Tab. 
 * 
 * @author nipundusad
 * 
 * Task 3 and 4
 */
 
public class List_Table_Class {
	private String coursecode;
	private String section;
	private String coursename;
	private String instructor;
	private CheckBox enroll;
	private Color colours;
	private Label labels;
	private int today;
	
	/**
	 * Parameterized constructor   
	 * @param cc Course code
	 * @param sect Lecture section
	 * @param cn Course name
	 * @param instr Instructor
	 * @param col Colour
	 * @param t Day
	 */
	
	public List_Table_Class(String cc, String sect, String cn, String instr, Color col, int t) {
		this.coursecode = cc;
		this.section = sect;
		this.coursename = cn;
		this.instructor = instr;
		this.enroll = new CheckBox();
		this.colours = col;
		this.labels = new Label();
		this.today = t;
	}
	
	/**
	 * Default constructor
	 */
	
	public List_Table_Class() {
		this.coursecode = " ";
		this.section = " ";
		this.coursename = " ";
		this.instructor = " ";
		this.colours = Color.rgb(0, 0, 0, 0);
		this.today = 1;
	}
	
	/**
	 * Gets course code of the object
	 * @return course code of the object
	 */
	
	public String getCoursecode()
	{
		return this.coursecode;
	}
	
	/**
	 * Gets lecture section of the object
	 * @return lecture section of the object
	 */
	public String getSection()
	{
		return this.section;
	}
	
	/**
	 * Gets course name of the object
	 * @return course name of the object
	 */
	public String getCoursename() {
		return this.coursename;
	}
	
	/**
	 * Gets the enrollment status of the object
	 * @return course name of the object
	 */
	public CheckBox getEnroll() {
		return this.enroll;
	}
	
	/**
	 * Gets the instructor of the object
	 * @return instructor of the label
	 * 
	 */
	public String getInstructor() {
		return this.instructor;
	}
	
	/**
	 * Gets the colour of the label
	 * @return colour of the label
	 * 
	 */
	public Color getColours() {
		return this.colours;
	}
	
	/**
	 * Gets the label of the object
	 * @return label of the object
	 * 
	 */
	public Label getLabels() {
		return this.labels;
	}
	
	/**
	 * Gets the day of the slot of the object
	 * @return day of slot of the object
	 * 
	 */
	public int getToday() {
		return this.today;
	}
	
	/**
	 * Set the course code
	 * @param cc Course code
	 */
	public void setCoursecode(String cc) {
		this.coursecode = cc;
	}
	
	/**
	 * Set the lecture section
	 * @param sect Lecture section
	 */
	public void setSection(String sect) {
		this.section = sect;
	}
	
	/**
	 * Set the course name
	 * @param cn Course name
	 */
	public void setCoursename(String cn) {
		this.coursename = cn;
	}
	
	/**
	 * Set the enrollment status 
	 * @param enr Enrollment status checkbox
	 */
	public void setEnroll(CheckBox enr) {
		this.enroll = enr;
	}
	
	/**
	 * Set the instructor 
	 * @param instr Instructor
	 */
	public void setInstructor(String instr) {
		this.instructor = instr;
	}
	
	/**
	 * Set the colour
	 * @param col Colour
	 */
	public void setColours(Color col) {
		this.colours = col;
	}
	
	/**
	 * Set the label
	 * @param la Label
	 */
	public void setLabel(Label la) {
		this.labels = la;
	}
	
	/**
	 * Set the day
	 * @param t Day
	 */
	public void setToday(int t) {
		this.today = t;
	}
}


