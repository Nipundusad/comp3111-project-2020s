package comp3111.coursescraper;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import java.util.Iterator;
import javafx.scene.paint.Color;
import java.util.regex.Pattern;
import java.time.LocalTime;

//import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.control.CheckBox;

public class Controller {

    @FXML
    private Tab tabMain;

    @FXML
    private TextField textfieldTerm;

    @FXML
    private TextField textfieldSubject;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textfieldURL;

    @FXML
    private Tab tabStatistic;

    @FXML
    private ComboBox<?> comboboxTimeSlot;

    @FXML
    private Tab tabFilter;

    @FXML
    private Tab tabList;

    @FXML
    private Tab tabTimetable;

    @FXML
    private Tab tabAllSubject;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private TextField textfieldSfqUrl;

    @FXML
    private Button buttonSfqEnrollCourse;

    @FXML
    private Button buttonInstructorSfq;

    @FXML
    private TextArea textAreaConsole;
    
    private Scraper scraper = new Scraper();
    @FXML
    private CheckBox AM;
    @FXML
    private CheckBox PM;
    @FXML
    private CheckBox MON;
    @FXML
    private CheckBox TUE;
    @FXML
    private CheckBox WED;
    @FXML
    private CheckBox THU;
    @FXML
    private CheckBox FRI;
    @FXML
    private CheckBox SAT;
    @FXML
    private CheckBox CC;
    @FXML
    private CheckBox NE;
    @FXML
    private CheckBox LT;
    @FXML
    /**
     * Select All Button in Filter Tab
     */
    public Button SELECT_ALL;
    
    @FXML
    private TableView<List_Table_Class> prop_list;
    @FXML
    private TableColumn<List_Table_Class, String> prop_course_code;
    @FXML
    private TableColumn<List_Table_Class, String> prop_section;
    @FXML
    private TableColumn<List_Table_Class, String> prop_course_name;
    @FXML
    private TableColumn<List_Table_Class, String> prop_instructor;
    @FXML
    private TableColumn<List_Table_Class, CheckBox> prop_enroll;
    
    ObservableList<List_Table_Class> data_enroll_lost = FXCollections.observableArrayList();
    
    List<List_Table_Class> data_all = new ArrayList<List_Table_Class>();
    
    ObservableList<List_Table_Class> new_list = FXCollections.observableArrayList();
    
    @FXML
    void allSubjectSearch() {
    	
    }

    @FXML
    void findInstructorSfq() {
    	buttonInstructorSfq.setDisable(true);
    }

    @FXML
    void findSfqEnrollCourse() {

    }
    
    public void filterResults() {
        String newline= "";
        List<Boolean> is_ticked = new ArrayList<Boolean>();
        Boolean[] is_checked = new Boolean[11];

        for (int i = 0; i < 11; i++) {
            is_checked[i] = false;
            is_ticked.add(i, false);
        }
        
        set_properties_list();
        textAreaConsole.setText("");
        enrollment_lost();
        new_list.clear();


        List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
        for (Course c : v) {
        	List<Section> sect_list = new ArrayList<Section>();
        	String curr_line = "";
            Boolean check_has_one;
            if (AM.isSelected()) {
                is_checked[0] = check_AM(c);
                is_ticked.add(0, true);
               
                
                for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        				if(t.getStartHour() >= 9 && t.getStartHour() < 12 ) {	
        					newline += c.getTitle() + "\n" + r.toString() + "\n";
        					curr_line +=  r.toString();
        					sect_list.add(r);
        				}
            		}
            		}
            }
            if (PM.isSelected()) {
                is_checked[1] = check_PM(c);
                is_ticked.add(1, true);
                
                
                for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        				if(t.getStartHour() >= 12) {
        					newline += c.getTitle() + "\n" + r.toString() + "\n";
        					curr_line +=  r.toString();   
        					sect_list.add(r);
        				}
            		}
            		}
            }
          
            
            if (MON.isSelected()) {
                is_checked[2] = check_MON(c);
                is_ticked.add(2, true);
              
                
            	for (int i = 0; i < c.get_sections().size(); i++) {
            	
            	Section r = c.get_sections().get(i);
    			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
    				Slot t = r.get_slot(temp);
    				if(t.getDay() == 0) {
    					newline += c.getTitle() + "\n" + r.toString() + "\n";
    					curr_line +=  r.toString();               
    					sect_list.add(r);
    				}
        		}
        		}

                

               }
            
            if (TUE.isSelected()) {
                is_checked[3] = check_TUE(c);
                is_ticked.add(3, true);
                    
                	for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        				if(t.getDay() == 1) {
        					newline += c.getTitle() + "\n" + r.toString() + "\n";
        					curr_line +=  r.toString();
        					sect_list.add(r);
        		}
        			}
        			}

                

            }
            if (WED.isSelected()) {
                is_checked[4] = check_WED(c);
                is_ticked.add(4, true);
                
                for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        				if(t.getDay() == 2) {
            					newline += c.getTitle() + "\n" + r.toString() + "\n";
            					curr_line +=  r.toString();
            					sect_list.add(r);
        		}
        			}
        			}
            }
            if (THU.isSelected()) {
                is_checked[5] = check_THU(c);
                is_ticked.add(5, true);
                for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        				if(t.getDay() == 3) {
        					newline += c.getTitle() + "\n" + r.toString() + "\n";
        					curr_line += r.toString();  
        					sect_list.add(r);
        		}
        			}
        			}
            }
            if (FRI.isSelected()) {
                is_checked[6] = check_FRI(c);
                is_ticked.add(6, true);
                for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        				if(t.getDay() == 4) {
        					newline += c.getTitle() + "\n" + r.toString() + "\n";
        					curr_line +=  r.toString(); 
        					sect_list.add(r);
        		}
        			}
        			}
            }
            if (SAT.isSelected()) {
                is_checked[7] = check_SAT(c);
                is_ticked.add(7, true);
                for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        				if(t.getDay() == 5) {
        					newline += c.getTitle() + "\n" + r.toString() + "\n";
        					curr_line +=  r.toString();
        					sect_list.add(r);
        		}
        			}
        			}
            }
            if (LT.isSelected()) {
                is_checked[8] = check_LT(c);
                is_ticked.add(8, true);
                
                for (int i = 0; i < c.get_sections().size(); i++) {
                	
                	Section r = c.get_sections().get(i);
        			for(int temp = 0; temp < r.get_num_of_slots(); temp++) {
        				Slot t = r.get_slot(temp);
        	           
        	            if (t.getType().contains("LA") || t.getType().contains("T"))
        				 {
        					newline += c.getTitle() + "\n" + r.toString() + "\n";
        					curr_line +=  r.toString(); 
        					sect_list.add(r);
            					}
        			}
        			}
                
                
            }
            if (CC.isSelected()) {
                is_checked[9] = check_CC(c);
                is_ticked.add(9, true);
                
                    if(c.get_common_core()) {
                    
                    	newline += c.toString() + "\n\n\n";  
    					curr_line +=  c.toString();
    					for (int i = 0; i < c.get_sections().size(); i++) {
    	                	
    	                	Section r = c.get_sections().get(i);
    	                	sect_list.add(r);
    					}
                    
                    }
                    

                }

            
            if (NE.isSelected()) {
                is_checked[10] = check_NE(c);
                is_ticked.add(10, true);
                
                for (Course x : v) {
                    if(x.getExclusion() != "") {
                    
                    	newline += x.toString() + "\n\n\n"; 
    					for (int i = 0; i < c.get_sections().size(); i++) {
    	                	
    	                	Section r = c.get_sections().get(i);
    	                	sect_list.add(r);
    					}
                    	
                    }
                    }
            }
            if(newline == "") 
            {
                textAreaConsole.setText(textAreaConsole.getText() );             
            }
            else {
    		
            	textAreaConsole.setText(newline);
            	}

            List<Integer> index = new ArrayList<>();
            int cou = 0;
            while(cou < 11) {
                if(is_ticked.get(cou)) {
                    index.add(cou);
                }
                cou++;
            }
             
            Iterator i = index.iterator();
            check_has_one = true; 
            while (i.hasNext()) {
                if (!is_checked[(int)i.next()]) {
                	check_has_one = false;
                }
            }
            
            if (check_has_one) {
      		//for (int j = 0; j < c.getNumSlots(); j++) {
          	for (int m = 0; m < sect_list.size(); m++) {
                String sect = " ";
              	Color col = new Color(Math.random(), Math.random(), Math.random(), 0.5);
              	String prev_sect_type = " ";
          		Section temp_sect = sect_list.get(m);
          		for (int j = 0; j < temp_sect.get_num_of_slots(); j++) {
      				Slot slt = temp_sect.get_slot(j);
    	    		//System.out.println(c.getTitle().substring(0, 10));
    	    		//System.out.println("   ");
    	    		//System.out.println(t.getType().substring(0, 3));
    	    		//System.out.println("   ");
    	    		//System.out.println(c.getTitle().substring(12));
    	    		//System.out.println("   ");
    	    		//System.out.println(t.getDay());
    	    		//System.out.println("   ");
      				
      				//textAreaConsole.setText(curr_line);
      				/*int ind = 3;
      				
      				
      				if (slt.getType().contains("LA")) {
      					textAreaConsole.setText(textAreaConsole.getText() + "\n" + slt);
      					ind = 4;
      				}*/
      				//textAreaConsole.setText(textAreaConsole.getText() + "\n" + slt.getType());
      				List_Table_Class cour = new List_Table_Class(c.getTitle().substring(0, 10), slt.getType().substring(0,3), c.getTitle().substring(12), "1", col, slt.getDay());
    	    		//List_Table_Class obj2 = new List_Table_Class(c.getTitle().substring(0, 10), slt.getType().substring(0, 3), c.getTitle().substring(12), "1", col, slt.getDay());
    	    		
      				
                    String instr = "";
                    for(int k=0; k<INSTRUCTOR.size(); ++k)
                    {
                        if(INSTRUCTOR.get(k).teaches(cour.getCoursecode()+" "+cour.getSection()))
                        {
                            instr += INSTRUCTOR.get(k).getName() + "\n";
                        }
                    }
                    if(instr.equals(""))
                    {
                        instr = "TBA";
                    }
                    cour.setInstructor(instr);
                    //System.out.println(obj.get_instructor());
    	    		
    	            //Prevent duplication
    	            List_Table_Class duplicate_check = new List_Table_Class("0", "0", "0", "0", Color.color(Math.random(), Math.random(), Math.random(), 0.5), 0);
    	           
    	            int check = 0;
    	            int ext = 0;
    	        	for(int k=0; k<data_all.size(); ++k)
    	        	{
    	        		List_Table_Class duplicate = data_all.get(k);
    	        		if(duplicate.getCoursecode().equals(cour.getCoursecode()))
    	        		{
    	        			if(duplicate.getSection().equals(cour.getSection()))
    	        			{
    	        				if((duplicate.getCoursename().equals(cour.getCoursename()))&&((duplicate.getToday())==cour.getToday()))
    	        				{
    	        					//cour = dup;
    	        					check = 1;
    	        					duplicate_check = duplicate;
    	        					break;
    	                        }
    	                    }
    	        		}
    	            }
    	        	
    	    		
		            if((slt.getType()!=sect)&&(slt.getType().length()<11))
		            {
		            	if(check != 1)
		            	{
		            		//System.out.println("First Check != 1");
		            		data_enroll_lost.add(cour);
		            	}
		
		            	if(check == 1)
		            	{
		            		//System.out.println("Check = 1");
		                    new_list.add(duplicate_check);
		            	}
		            	else
		            	{
		            		//count = count + 1;
		            		//System.out.println("Check != 1");
		            		new_list.add(cour);
		            	}
		
		            	sect = slt.getType();
		            	col = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		            	cour.setColours(col);
		            }
		            
		            if(slt.getType().length()>11)
		            {
		                cour.setSection(prev_sect_type.substring(0, 3));
		                cour.setColours(col);
		            }
		
		            //
		            for(int l=0; l<data_all.size(); ++l)
		        	{
		        		List_Table_Class duplicate = data_all.get(l);
		        		if(duplicate.getCoursecode().equals(cour.getCoursecode()))
		        		{
		        			if(duplicate.getSection().equals(cour.getSection()))
		        			{
		        				if((duplicate.getCoursename().equals(cour.getCoursename()))&&((duplicate.getToday())==cour.getToday()))
		        				{
		                            ext++;
		                        }
		                    }
		        		}
		            }
		            
		            if(check != 1)
		            {
		                if(ext == 0)
		                {
		                    data_all.add(cour);
		                }
		                //datasAll.add(cour);
		            }
		            prop_list.setItems(new_list);
		
		            //newline += cour.getCoursecode() + " " + cour.getSection() + "Slot " + j + ":" + slt + "\n";	//My version which adds sections
		            
		
		            if(check !=1)
		            {
		            	cour.getEnroll().selectedProperty().addListener(new ChangeListener<Boolean>() {
		            		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		
		            			if(newValue==true)
		            			{
		            				//System.out.println("Checkbox is checked");
		                			add_block(cour, slt);
		                			same_section(cour, slt);
		                			print_enrolled(cour);
		            			}
		            			else if((newValue==false)&&(oldValue==true))
		            			{
		            				//Need to remove label from TimeTable and print on console
		            				if(data_enroll_lost.contains(cour))
		            				{
		            					print_removed_enrolled(cour);
		            				}
		            				remove_block(cour, slt);
		            			}
		
		                	}
		            	});
		            }
		            
		            //System.out.println(count);
    	        	prev_sect_type = sect;
      			}
          	}
      		//textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
        }
        is_ticked.removeAll(is_ticked);
        for (int k = 0; k < 11; k++) {
            is_checked[k] = false;
            is_ticked.add(k, false);
        }
        }
        
        
   }// end function
    
    public static Boolean check_AM(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            LocalTime time = LocalTime.parse("12:00");
            
            if (t.getStart().compareTo(time) < 0) {
                check = true;
            }
        }
        return check;
    }
    
    public static Boolean check_PM(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            LocalTime time = LocalTime.parse("12:00");
            
            if (t.getStart().compareTo(time) > 0) {
                check = true;
            }
        }
        return check;
    }
    
    public static Boolean check_LT(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            String type = t.getType();
            Boolean b = Pattern.matches("T\\d", type);
            if (type.contains("LA") || b) {
                check = true;
            }
        }
        return check;
    }
    
    public static Boolean check_CC(Course c) {
        return c.get_common_core();

    }
    
    public static Boolean check_NE(Course c) {
        if (c.getExclusion() == "null") {
            return true;
        }
        return false;
    }

    public static Boolean check_MON(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 0) {
                check = true;
            }
        }
        return check;
    }
    
    public static Boolean check_TUE(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 1) {
                check = true;
            }
        }
        return check;
    }
    
    public static Boolean check_WED(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 2) {
                check = true;
            }
        }
        return check;
    }
    
    public static Boolean check_THU(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 3) {
                check = true;
            }
        }
        return check;
    }
    
    public static Boolean check_FRI(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 4) {
                check = true;
            }
        }
        return check;
    }

    public static Boolean check_SAT(Course c) {
        Boolean check = false;
        for (int i = 0; i < c.getNumSlots(); i++) {
            Slot t = c.getSlot(i);
            if (t.getDay() == 5) {
                check = true;
            }
        }
        return check;
    }
    
    //IMPLEMENTATION OF SELECT_ALL BUTTON
    
    /**
     * Implementation of SelectAll Button in Filter Tab
     * Selects All filters and changes text to DeselectAll after being clicked
     */
    public void select_all() {
        AM.setSelected(true);
        PM.setSelected(true);
        LT.setSelected(true);
        CC.setSelected(true);
        NE.setSelected(true);
        MON.setSelected(true);
        TUE.setSelected(true);
        WED.setSelected(true);
        THU.setSelected(true);
        FRI.setSelected(true);
        SAT.setSelected(true);
        
        filterResults();
        SELECT_ALL.setText("Deselect All");
        SELECT_ALL.setOnAction(e -> deselect_all());
    }
    
    
    //IMPLEMENTATION OF DESELECT_ALL BUTTON
    /**
     * Implementation of DeselectAll Button in Filter Tab
     * De-selects all filters and changes text to SelectAll after being clicked
     */
    public void deselect_all() {
        AM.setSelected(false);
        PM.setSelected(false);
        LT.setSelected(false);
        CC.setSelected(false);
        NE.setSelected(false);
        MON.setSelected(false);
        TUE.setSelected(false);
        WED.setSelected(false);
        THU.setSelected(false);
        FRI.setSelected(false);
        SAT.setSelected(false);
       
        SELECT_ALL.setText("Select All");
        SELECT_ALL.setOnAction(t -> select_all());

    
    /**
     * Scrapes based on textfieldURL, textfieldTerm, textfieldSubject and returns the number of courses
     * @return numcourses - within result of scraping
     */

    } 
    
    public int searchCount() {
        int numcourses = 0;
        List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
        for (Course c : v) {
            numcourses++;
            String newline = c.getTitle() + "\n";
            for (int i = 0; i < c.getNumSlots(); i++) {
                Slot t = c.getSlot(i);
                newline += "Slot " + i + ":" + t + "\n";
            }
            textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
        }
        return numcourses;
    }
    
    // Global helper variables
     public static int NUM_PREFIXES = 0;
     public static List<Instructor> INSTRUCTOR  = new ArrayList<Instructor>();
     public static int NUM_SECTIONS = 0;
     

     /**
      * This function checks whether instructor has already been scraped or not
      * @param instr - name of the instructor to check
      * @return index of the instructor - found in the list of Instructors scraped
      * 
      */
     public static int inInstructorSearch(String instr){

        if(INSTRUCTOR.size() == 0) {
        	
        	return -1;
        }

        for(int i = 0; i < INSTRUCTOR.size(); i++){
          if(INSTRUCTOR.get(i).getName().equals(instr)) {
        	  	return i;
          }
        }
        return -1;
      }
     
    @FXML
    void search() {
    	Controller.NUM_SECTIONS = 0;		//initializing controller - number of sections
        int NUM_COURSES = 0;				//initializing number of courses
        
    	List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    	/*for (Course c : v) {
    		String newline = c.getTitle() + "\n";
    		for (int i = 0; i < c.getNumSlots(); i++) {
    			Slot t = c.getSlot(i);
    			newline += "Slot " + i + ":" + t + "\n";
    		}
    		textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
    	}*/
    	// terminal errors
    	if(v == null) {
    		textAreaConsole.setText("Errors: check your terminal");
    		}


        // handling 404 error 
        if(v.size() == 1) {

        // handling unknown https error
           if(v.get(0).getTitle().equals("UnknownHTTPSError")){
        	   textAreaConsole.setText("UnknownHTTPSError");
        	   return;
           }
           
          if(v.get(0).getTitle().equals("404PageNotFound")){
            textAreaConsole.setText("Error 404: Page not Found\nPlease check your parameters");
            return;
          }
         
        }

        for(Course c : v) if(c.is_valid()) NUM_COURSES++;


      // number of prefixes
      textAreaConsole.setText("Total Number of Categories/Code Prefix: " + Controller.NUM_PREFIXES);

      // number of courses found
      textAreaConsole.setText(textAreaConsole.getText() + "\nTotal Number of different courses in this search: " + NUM_COURSES);


      // number of sections Found
      textAreaConsole.setText(textAreaConsole.getText() + "\n" +
      "Total Number of difference sections in this search: " + Controller.NUM_SECTIONS + "\n\n");

      //Instructors free on tuesday 3:10 PM
      List<String> freeInstructors = new ArrayList<String>();
      for(Instructor ins : Controller.INSTRUCTOR) {
    	  if(ins.free_Tuesday_310_PM()) {
    		  freeInstructors.add(ins.getName());
    	  }
      }
      Collections.sort(freeInstructors);
      String freeIns = "";
      for(String str : freeInstructors) {
    	  freeIns  += str + "\n";
      }
      textAreaConsole.setText(textAreaConsole.getText() + "\n" +
      "Instructors who has teaching assignment this term but does not need to teach at Tu 3:10pm:\n" + freeIns + "\n\n");


      //print sections
      String newline = "";
      for (Course c : v) {
    		if(c.is_valid()) {
    			newline += c.toString() + "\n\n\n";
    		}
    	}
      textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
      
    	
    	//Add a random block on Saturday
    	/*AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	Label randomLabel = new Label("COMP1022\nL1");
    	Random r = new Random();
    	double start = (r.nextInt(10) + 1) * 20 + 40;

    	randomLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    	randomLabel.setLayoutX(600.0);
    	randomLabel.setLayoutY(start);
    	randomLabel.setMinWidth(100.0);
    	randomLabel.setMaxWidth(100.0);
    	randomLabel.setMinHeight(60);
    	randomLabel.setMaxHeight(60);
    
    	ap.getChildren().addAll(randomLabel);*/
      
        /*set_properties_list();
        
        enrollment_lost();
        
        new_list.clear();
      	
      	for (Course c : v) {
          	String sect = " ";
          	Color col = new Color(Math.random(), Math.random(), Math.random(), 0.5);
          	String prev_sect_type = " ";
      		for (int j = 0; j < c.getNumSlots(); j++) {
      				Slot slt = c.getSlot(j);
    	    		//System.out.println(c.getTitle().substring(0, 10));
    	    		//System.out.println("   ");
    	    		//System.out.println(t.getType().substring(0, 3));
    	    		//System.out.println("   ");
    	    		//System.out.println(c.getTitle().substring(12));
    	    		//System.out.println("   ");
    	    		//System.out.println(t.getDay());
    	    		//System.out.println("   ");
    	    		
    	    		List_Table_Class cour = new List_Table_Class(c.getTitle().substring(0, 10), slt.getType().substring(0, 3), c.getTitle().substring(12), "1", col, slt.getDay());
    	    		//List_Table_Class obj2 = new List_Table_Class(c.getTitle().substring(0, 10), slt.getType().substring(0, 3), c.getTitle().substring(12), "1", col, slt.getDay());
    	    		
    	    		
                    String instr = "";
                    for(int k=0; k<INSTRUCTOR.size(); ++k)
                    {
                        if(INSTRUCTOR.get(k).teaches(cour.getCoursecode()+" "+cour.getSection()))
                        {
                            instr += INSTRUCTOR.get(k).getName() + "\n";
                        }
                    }
                    if(instr.equals(""))
                    {
                        instr = "TBA";
                    }
                    cour.setInstructor(instr);
                    //System.out.println(obj.get_instructor());
    	    		
    	            //Prevent duplication
    	            List_Table_Class duplicate_check = new List_Table_Class("0", "0", "0", "0", Color.color(Math.random(), Math.random(), Math.random(), 0.5), 0);
    	           
    	            int check = 0;
    	            int ext = 0;
    	        	for(int k=0; k<data_all.size(); ++k)
    	        	{
    	        		List_Table_Class duplicate = data_all.get(k);
    	        		if(duplicate.getCoursecode().equals(cour.getCoursecode()))
    	        		{
    	        			if(duplicate.getSection().equals(cour.getSection()))
    	        			{
    	        				if((duplicate.getCoursename().equals(cour.getCoursename()))&&((duplicate.getToday())==cour.getToday()))
    	        				{
    	        					//cour = dup;
    	        					check = 1;
    	        					duplicate_check = duplicate;
    	        					break;
    	                        }
    	                    }
    	        		}
    	            }
    	        	
    	    		
		            if((slt.getType()!=sect)&&(slt.getType().length()<11))
		            {
		            	System.out.println("First if after duplicate");
		            	if(check != 1)
		            	{
		            		//System.out.println("First Check != 1");
		            		data_enroll_lost.add(cour);
		            	}
		
		            	if(check == 1)
		            	{
		            		//System.out.println("Check = 1");
		                    new_list.add(duplicate_check);
		            	}
		            	else
		            	{
		            		//count = count + 1;
		            		//System.out.println("Check != 1");
		            		new_list.add(cour);
		            	}
		
		            	sect = slt.getType();
		            	col = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		            	cour.setColours(col);
		            }
		            
		            if(slt.getType().length()>11)
		            {
		            	System.out.println("Second if");
		                cour.setSection(prev_sect_type.substring(0, 3));
		                cour.setColours(col);
		            }
		
		            //
		            for(int l=0; l<data_all.size(); ++l)
		        	{
		        		List_Table_Class duplicate = data_all.get(l);
		        		if(duplicate.getCoursecode().equals(cour.getCoursecode()))
		        		{
		        			if(duplicate.getSection().equals(cour.getSection()))
		        			{
		        				if((duplicate.getCoursename().equals(cour.getCoursename()))&&((duplicate.getToday())==cour.getToday()))
		        				{
		                            ext++;
		                        }
		                    }
		        		}
		            }
		            
		            if(check != 1)
		            {
		                if(ext == 0)
		                {
		                    data_all.add(cour);
		                }
		                //datasAll.add(cour);
		            }
		            prop_list.setItems(new_list);
		            
		            //Label y = new Label(cour.getCoursecode());
		            //ap.getChildren().addAll(y);
		           
		            //System.out.println(prop_list);
		
		            newline += cour.getCoursecode() + " " + cour.getSection() + "Slot " + j + ":" + slt + "\n";	//My version which adds sections
		            
		
		            if(check !=1)
		            {
		            	cour.getEnroll().selectedProperty().addListener(new ChangeListener<Boolean>() {
		            		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		
		            			if(newValue==true)
		            			{
		            				//System.out.println("Checkbox is checked");
		                			add_block(cour, slt);
		                			same_section(cour, slt);
		                			print_enrolled(cour);
		            			}
		            			else if((newValue==false)&&(oldValue==true))
		            			{
		            				//Need to remove label from TimeTable and print on console
		            				if(data_enroll_lost.contains(cour))
		            				{
		            					print_removed_enrolled(cour);
		            				}
		            				remove_block(cour, slt);
		            			}
		
		                	}
		            	});
		            }
		            
		            //System.out.println(count);
    	        	prev_sect_type = sect;
      			}
      		textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
      		
      	}*/
        // clear storage for next search
        freeInstructors.clear();
        INSTRUCTOR.clear();
        Controller.NUM_SECTIONS = 0;
    }
    
    /**
     * Sets the properties of the columns in the List Tab
     * Task 3
     */
	@FXML
	public void set_properties_list() {
		prop_course_code.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
    	prop_section.setCellValueFactory(new PropertyValueFactory<>("section"));
    	prop_course_name.setCellValueFactory(new PropertyValueFactory<>("coursename"));
    	prop_instructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
    	prop_enroll.setCellValueFactory(new PropertyValueFactory<>("enroll"));
	}
	
	/**
	 * Adds a block on the timetable Tab for an enrolled section of a course
	 * @param ltc List_Table_Class object with the section that is to be added
	 * @param slt Slot of the section to be added
	 * Task 4
	 */
	@FXML
	public void add_block(List_Table_Class ltc, Slot slt)
    {
    	ltc.getLabels().setText(ltc.getCoursecode()+"\n"+ltc.getSection());
    	ltc.getLabels().setBackground(new Background(new BackgroundFill(ltc.getColours(), CornerRadii.EMPTY, Insets.EMPTY)));

    	int day = slt.getDay();
    	
    	int start_time_hr = slt.getStartHour();
    	int start_time_min = slt.getStartMinute();
    	int start_time = (start_time_hr*60)+start_time_min;
    	int end_time_hr = slt.getEndHour();
    	int end_time_min = slt.getEndMinute();
    	int end_time  = (end_time_hr*60)+end_time_min;
    	int dur = end_time-start_time;
    	
    	ltc.getLabels().setLayoutX((day*100.0)+100.0);
    	ltc.getLabels().setLayoutY(40.0 + (start_time_hr-9)*20.0 + (start_time_min*0.33));
    	ltc.getLabels().setMinWidth(100.0);
    	ltc.getLabels().setMaxWidth(100.0);
    	ltc.getLabels().setMinHeight(dur*0.33);
    	ltc.getLabels().setMaxHeight(dur*0.33);

    	if(dur<60)
    	{
    		ltc.getLabels().setText(ltc.getCoursecode()+" "+ltc.getSection());
    	}

        ltc.getLabels().setFont(ltc.getLabels().getFont().font(10));
        
        AnchorPane ap = (AnchorPane)tabTimetable.getContent();
    	ap.getChildren().addAll(ltc.getLabels());
    }
	
	/**
	 * Check if the slot slt belongs to the ltc object's section
	 * @param ltc List_Table_Class object with the section that is to be checked with 
	 * @param slt Slot that is to be checked
	 * Task 3
	 */
	@FXML
    public void same_section(List_Table_Class ltc, Slot slt)
    {
    	for(int i=0; i<data_all.size(); i++)
    	{
    		if((data_all.get(i).getCoursecode().equals(ltc.getCoursecode()))&&(data_all.get(i).getSection().equals(ltc.getSection())))
    		{
    			if((data_all.get(i).getEnroll().isSelected()==false)&&((ltc.getEnroll().isSelected())==true))
    			{
                    //System.out.println(":/");
                    data_all.get(i).setColours(ltc.getColours());
                    data_all.get(i).getEnroll().setSelected(true);
                    break;
    			}
    		}
    	}
    }
	
	/**
	 * Print all sections enrolled on console after a new section is enrolled
	 * @param ltc List_Table_Class with the new enrolled section
	 * Task 3
	 */
	public void print_enrolled(List_Table_Class ltc)
    {
    	if(textAreaConsole.getText().substring(0, 36).equals("The following sections are enrolled:"))
    	{
            String str = "";
            String console = textAreaConsole.getText();

    		for(int i=37; i<textAreaConsole.getText().length(); i++)		//change 1000 later
    		{
    			if(console.charAt(i)=='\n')
    			{
    				break;
    			}
    			else
    			{
    				str += console.charAt(i);
    			}
    		}

    		if((ltc.getCoursecode() + " " + ltc.getSection()).equals(str)==false)
    		{
    			textAreaConsole.setText(textAreaConsole.getText().substring(0, 37) + ltc.getCoursecode() + " " + ltc.getSection() + "\n" + textAreaConsole.getText().substring(37));
    		}
    	}
    	else
    	{
    		textAreaConsole.setText("The following sections are enrolled:" + "\n" + ltc.getCoursecode() + " " + ltc.getSection() +"\n" + textAreaConsole.getText());
    	}
    }
	
    public void print_removed_enrolled(List_Table_Class ltc)
    {
    	String alike = ltc.getCoursecode() + " " + ltc.getSection();
    	String output = "";
        int end = 0;

    	for(int i=37; i<textAreaConsole.getText().length(); i=i+15)		//change 10000 later
    	{
    		if(alike.equals(textAreaConsole.getText().substring(i, i+14)))
    		{
    			end = i;
    			break;
    		}
    		else
    		{
    			output += "\n" + textAreaConsole.getText().substring(i, i+14);
    		}
    	}

    	output += textAreaConsole.getText().substring(end+14);

    	if((textAreaConsole.getText().charAt(end+14)=='\n')&&(textAreaConsole.getText().charAt(end+15)=='\n')&&(end==37))
    	{
    		textAreaConsole.setText(output.substring(1));
    	}
    	else
    	{
    		textAreaConsole.setText("The following sections are enrolled:" + output);
    	}
    }
    
    public void enrollment_lost()
    {
    	String first_line = "";
    	int check = 0;

    	for(int i=0; i<data_enroll_lost.size(); ++i)
    	{
    		if(data_enroll_lost.get(i).getEnroll().isSelected())
    		{
    			first_line += data_enroll_lost.get(i).getCoursecode() + " " + data_enroll_lost.get(i).getSection() + "\n";
    			check = 1;
    		}
    	}

    	if(check==1)
    	{
    		first_line = "The following sections are enrolled:" + "\n" + first_line;
    	}

    	textAreaConsole.setText(first_line);
    }
    
    public void remove_block(List_Table_Class ltc, Slot slt)
    {
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();

    	for(int i=0; i<data_all.size(); ++i)
    	{
    		if((data_all.get(i).getLabels().getText()).equals(ltc.getLabels().getText()))
    		{
    			ap.getChildren().remove(data_all.get(i).getLabels());
    			if(data_all.get(i).getEnroll().isSelected())
    			{
    				data_all.get(i).getEnroll().setSelected(false);
    			}
    		}
    	}
    }

}