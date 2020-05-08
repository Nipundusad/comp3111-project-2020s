package comp3111.coursescraper;


import java.util.ArrayList;
import java.util.List;

/**
* Task 1
* Class for storing Instructor Information.
* @author mgoyal
*/
public class Instructor{

  private List<Section> sectionsTaught;
  private String name;
  private boolean freeTu310;


  /**
  * Member function to add a given section
  * @param s Section - to be assigned
  */
  public void addSection(Section s){
    this.sectionsTaught.add(s);

    if(s.get_num_of_slots() == 0) return;
    for(int i = 0; i < s.get_num_of_slots(); i++){

      int startHour = s.get_slot(i).getStartHour();
      int endHour = s.get_slot(i).getEndHour();
      int startMinute = s.get_slot(i).getStartMinute();
      int endMinute = s.get_slot(i).getEndMinute();

      if(startHour <= 15 && endHour >= 15 &&
         startMinute <= 10 && endMinute >= 10){
           this.freeTu310 = false;
         }
    }

  }

  /**
  * Constructor for instructor 
  * @param name - name of the instructor
  * @param section - section assigned to instructor
  */
  public Instructor(String name, Section section){
    this.name = name;
    this.freeTu310 = true;
    this.sectionsTaught = new ArrayList<Section>();
    this.addSection(section);
  }



  /**
  * Return the Instructor name
  * @return {@link #name}
  */
  public String getName(){
    return this.name;
  }

  /**
  * Returns if Instructor is free on Tuesday at 3:10 PM
  * @return {@link #freeTu310}
  */
  public boolean free_Tuesday_310_PM(){
    for(Section s : this.sectionsTaught) {
    	if(s.get_num_of_slots() != 0) {
    		return freeTu310;
    	}
    }
    return false;
  }

  /**
  * Checks if Instructor teaches a given section
  * @param section_code - section code of the Section
  * @return if the Instructor teaches the section or not
  */
  public boolean teaches(String section_code){
    String [] secname = section_code.split("\\s+");
    String temp = String.join(" ", secname);
    temp = temp.substring(0, 4) + temp.substring(5);
    for(Section sec : sectionsTaught) {
    	
        if(sec.get_section_code().equals(temp)) {
        	return true;
        }

    }

    return false;
  }


  /**
  * Returns the sections taught by an Instructor
  * @return {@link #sectionsTaught}
  */
  public List<Section> getSections(){
    return this.sectionsTaught;
  }


}
