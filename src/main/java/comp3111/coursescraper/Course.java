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

	// constructor
	public Course() {
		slots = new Slot[DEFAULT_MAX_SLOT];
		for (int i = 0; i < DEFAULT_MAX_SLOT; i++) slots[i] = null;
		numSlots = 0;
		sections = new ArrayList<Section>();
	}
	
	public void addSlot(Slot s) {
		if (numSlots >= DEFAULT_MAX_SLOT)
			return;
		slots[numSlots++] = s.clone();
	}
	/**
	 * @param i index of course slot
	 * @returns the slot with the given index
	 */
	public Slot getSlot(int i) {
		if (i >= 0 && i < numSlots)
			return slots[i];
		return null;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the exclusion
	 */
	public String getExclusion() {
		return exclusion;
	}

	/**
	 * @param exclusion the exclusion to set
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}

	/**
	 * @return the numSlots
	 */
	public int getNumSlots() {
		return numSlots;
	}

	/**
	 * @param numSlots the numSlots to set
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}
	/**
	 * @return whether course is common core or not
	 */
	public boolean get_common_core() { 
		return is_common_core; 
		}
	
	/**
	 * @param c - if a course is a common core
	 */
	public void set_common_core(boolean c) 
	{ 
		this.is_common_core = c;
		}
	
	/**
	 *	@param s - section of the course
	 */
	public void add_section(Section s){
		this.sections.add(s);
}

	/**
	* @return the sections
	*/
	public List<Section> get_sections(){
		return this.sections;
	}
	
	
	/**
	* @return True False - if course has at least one lecture, lab or tutorial
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
	* returns a string can be used for printing information about the course
	* @return String newline which contains the course title and its section info
	*/
	public String toString(){
		String new_line = this.title + "\n";
		for(Section sec : sections) {
			new_line += sec.toString();
		}
		return new_line;
	}
}