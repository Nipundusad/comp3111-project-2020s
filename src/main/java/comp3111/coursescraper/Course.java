package comp3111.coursescraper;

import java.util.List;
import java.util.ArrayList;

public class Course {
	private static final int DEFAULT_MAX_SLOT = 20;
	private String title ; 
	private String description ;
	private String exclusion;
	private Slot [] slots;
	private int numSlots;
	private boolean is_common_core;
	private List<Section> sections;

	/**
	 * Constructor of Course
	 */	
	public Course() {
		slots = new Slot[DEFAULT_MAX_SLOT];
		for (int i = 0; i < DEFAULT_MAX_SLOT; i++) slots[i] = null;
		numSlots = 0;
		sections = new ArrayList<Section>();
	}
	
	/**
	 * Adds a Slot to a Course
	 * @param s Slot - slot to be added
	 */
	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT)
			return;
		slots[numSlots++] = s.clone();
	}
	/**
	 * Returns a slot of a course 

	 * @param i - index of course slot
	 * @returns the slot with the given index
	 */
	public Slot getSlot(int i) {
		if (i >= 0 && i < numSlots)
			return slots[i];
		return null;
	}

	/**
	 * Returns the title of the course
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets String - title of course
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns String - description of course

	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets String - description of course 
	 * @param description - the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns String - exclusion of course
	 * @return the exclusion
	 */
	public String getExclusion() {
		return exclusion;
	}

	/**
	 * Sets String - exclusion of course
	 * @param exclusion the exclusion to set
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}

	/**
	 * Returns int - number of slots in the course
	 * @return the numSlots
	 */
	public int getNumSlots() {
		return numSlots;
	}

	/**
	 * sets int - numSlots in Course
	 * @param numSlots the numSlots to set
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}
	/**
	 * Returns boolean if a Course is a Common Course

	 * @return (T/F) - whether course is common core or not
	 */
	public boolean get_common_core() { 
		return is_common_core; 
		}
	
	/**
	 * Sets if a Course is a Common Course
	 * @param c - if a course is a common core
	 */
	public void set_common_core(boolean c) 
	{ 
		this.is_common_core = c;
		}
	
	/**
	Inserts a section founded by scraper 
	@param s - A section related to this course
	 */
	public void add_section(Section s){
		this.sections.add(s);
}

	/**
	* returns the list of sections in this course
	* @return {@link #sections}
	*/
	public List<Section> get_sections(){
		return this.sections;
	}
	
	
	/**
	* checks if course has at least has one lecture, lab or tutorial
	* @return (T/F) - if course has at least one lecture, lab or tutorial
	*/
	public boolean is_valid(){

		boolean temp = false;

		for(Section s : this.sections){

			String type = s.get_section_code().split(" ")[1];
			boolean b1 = type.startsWith("T");
			boolean b2 = type.startsWith("L");
			if(b1 || b2) {
				temp = true;
			}

		}

		return temp;
	}
	
	
	/**
	* returns a string to print the course and its details
	* @return String newline - which contains the course title and its section information
	*/
	public String toString(){
		String new_line = this.title + "\n";
		for(Section sec : sections) {
			new_line += sec.toString();
		}
		return new_line;
	}
}