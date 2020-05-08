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
	private String course_code;
	private String section;
	private String course_name;
	private String instructor;
	private CheckBox enrolled;
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
		this.course_code = cc;
		this.section = sect;
		this.course_name = cn;
		this.instructor = instr;
		this.enrolled = new CheckBox();
		this.colours = col;
		this.labels = new Label();
		this.today = t;
	}
	
	/**
	 * Default constructor
	 */
	
	public List_Table_Class() {
		this.course_code = " ";
		this.section = " ";
		this.course_name = " ";
		this.instructor = " ";
		this.colours = Color.rgb(0, 0, 0, 0);
		this.today = 1;
	}
	
	/**
	 * Gets course code of the object
	 * @return course code of the object
	 */
	
	public String get_course_code()
	{
		return this.course_code;
	}
	
	/**
	 * Gets lecture section of the object
	 * @return lecture section of the object
	 */
	public String get_section()
	{
		return this.section;
	}
	
	/**
	 * Gets course name of the object
	 * @return course name of the object
	 */
	public String get_course_name() {
		return this.course_name;
	}
	
	/**
	 * Gets the enrollment status of the object
	 * @return course name of the object
	 */
	public CheckBox get_enroll() {
		return this.enrolled;
	}
	
	/**
	 * Gets the instructor of the object
	 * @return instructor of the label
	 * 
	 */
	public String get_instructor() {
		return this.instructor;
	}
	
	/**
	 * Gets the colour of the label
	 * @return colour of the label
	 * 
	 */
	public Color get_colours() {
		return this.colours;
	}
	
	/**
	 * Gets the label of the object
	 * @return label of the object
	 * 
	 */
	public Label get_labels() {
		return this.labels;
	}
	
	/**
	 * Gets the day of the slot of the object
	 * @return day of slot of the object
	 * 
	 */
	public int get_today() {
		return this.today;
	}
	
	/**
	 * Set the course code
	 * @param cc Course code
	 */
	public void set_course_code(String cc) {
		this.course_code = cc;
	}
	
	/**
	 * Set the lecture section
	 * @param sect Lecture section
	 */
	public void set_section(String sect) {
		this.section = sect;
	}
	
	/**
	 * Set the course name
	 * @param cn Course name
	 */
	public void set_course_name(String cn) {
		this.course_name = cn;
	}
	
	/**
	 * Set the enrollment status 
	 * @param enr Enrollment status checkbox
	 */
	public void set_enroll(CheckBox enr) {
		this.enrolled = enr;
	}
	
	/**
	 * Set the instructor 
	 * @param instr Instructor
	 */
	public void set_instructor(String instr) {
		this.instructor = instr;
	}
	
	/**
	 * Set the colour
	 * @param col Colour
	 */
	public void set_colours(Color col) {
		this.colours = col;
	}
	
	/**
	 * Set the label
	 * @param la Label
	 */
	public void set_label(Label la) {
		this.labels = la;
	}
	
	/**
	 * Set the day
	 * @param t Day
	 */
	public void set_today(int t) {
		this.today = t;
	}
}


