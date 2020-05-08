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
		 * @return the  slot
		 * @param temp - the slot index to get
		 */
		public Slot get_slot(int slot){
		    return this.slots[slot];
		  }
		/**
		 * @return the number of slots
		 */
		public int get_num_of_slots() {
			return num_of_slots;
		}
		
		/**
		 * @return the section code
		 */
		public String get_section_code(){
			    return this.section_code;
			  }
		/**
		 * @return the section ID
		 */
		public int get_section_ID(){
			    return this.section_id;
			  }
		/**
		  * Adds a slot into the Section
		  * @param s - the slot that must be added
		  * @return True or False if the slot was successfully added or not
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
		  * @return string containing the section info and its slots
		  */
		 public String toString(){

			    String temp = section_code.split(" ")[1] + " " + String.format("(%d)\n", section_id);
			    for(int i = 0; i < this.num_of_slots; i++) {
				      temp += String.format("\t\t%s\n", slots[i].toString());
			    	}
			    return temp;
			    }

}
