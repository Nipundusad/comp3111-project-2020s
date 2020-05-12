package comp3111.coursescraper;


/**
* Task 1
* Class for storing section Information for a given course
* @author mgoyal
*/
public class Section {
		private int section_id;
		private String section_code;
		private int num_of_slots;
		private Slot [] slots;
		private static final int MAX_SLOTS = 3;

		
		/**
		  * Constructor for the Section class
		  * @param sec_code - sectionCode of the Section 
		  * @param sec_id - sectionID of the Section 
		  */
		public Section (String sec_code, int sec_id) {
			
			this.slots = new Slot [MAX_SLOTS];
			for(int i = 0; i < MAX_SLOTS; i++) {
				for(int j = 0; j < MAX_SLOTS; j++) {
					slots[j] = null;
				}
				this.num_of_slots = 0;
			}
			this.section_code = sec_code;
			this.section_id = sec_id;
		}
		
		/**
		 * Returns the slot of the section for the given index
		 * @param slot - the slot index to get
		 * @return the slot with the given index	 
		 */
		public Slot get_slot(int slot){
		    return this.slots[slot];
		  }
		/**
		 * Returns the number of slots the section has
		 * @return the number of slots
		 */
		public int get_num_of_slots() {
			return num_of_slots;
		}
		
		/**
		 * returns the section code of the section
		 * @return the section code
		 */
		public String get_section_code(){
			    return this.section_code;
			  }
		/**
		 * returns the section ID of the section
		 * @return the section ID
		 */
		public int get_section_ID(){
			    return this.section_id;
			  }
		/**
		  * Adds a slot to the Section
		  * @param s - the slot that must be added
		  * @return (T/F) - if the slot was successfully added or not
		  */
		public boolean add_slot(Slot s){

		    if (this.num_of_slots > MAX_SLOTS) {
		    	return false;
		    }

		    this.slots[this.num_of_slots] = s;
		    this.num_of_slots = this.num_of_slots + 1;
		    return true;
		  }
		
		/**
		 * returns a string to print the Section
		  * @return string - containing the section details and its slots
		  */
		 public String toString(){

			    String temp = section_code.split(" ")[1] + " " + String.format("(%d)\n", section_id);
			    for(int i = 0; i < this.num_of_slots; i++) {
				      temp += String.format("\t\t%s\n", slots[i].toString());
			    	}
			    return temp;
			    }

}
