package comp3111.coursescraper;

import java.awt.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.util.Random;
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
    private CheckBox Mon;
    @FXML
    private CheckBox Tues;
    @FXML
    private CheckBox Wed;
    @FXML
    private CheckBox Thrs;
    @FXML
    private CheckBox Fri;
    @FXML
    private CheckBox Sat;
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
    public Button SelectAll;
    
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
    /**
     * Global variable to keep track of Instructors 
     */
     public static List<Instructor> INSTRUCTORS_IN_SEARCH  = new ArrayList<Instructor>();
    /**
     * Global variable to keep track of number of sections 
     */
     public static int NUM_SECTIONS = 0;
     
     /**
     * Global variable to keep track of the number of prefixes
     */
     public static int NUM_PREFIXES = 0;

    
     public static int inInstructorSearch(String _ins){

        if(INSTRUCTORS_IN_SEARCH.size() == 0) {
        	
        	return -1;
        }

        for(int i = 0; i < INSTRUCTORS_IN_SEARCH.size(); i++){
          if(INSTRUCTORS_IN_SEARCH.get(i).getName().equals(_ins)) {
        	  	return i;
          }
        }
        return -1;
      }
    @FXML
    void search() {
    	Controller.NUM_SECTIONS = 0;
        int NUM_COURSES = 0;

    	List<Course> v = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    	for (Course c : v) {
    		String newline = c.getTitle() + "\n";
    		for (int i = 0; i < c.getNumSlots(); i++) {
    			Slot t = c.getSlot(i);
    			newline += "Slot " + i + ":" + t + "\n";
    		}
    		textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);
    	}
    	if(v == null) {
    		textAreaConsole.setText("Errors: check your terminal");
    		}


        // handling 404 error - Anish
        if(v.size() == 1) {

          if(v.get(0).getTitle().equals("404PageNotFound")){
            textAreaConsole.setText("Error 404: Page not Found\nPlease check your parameters");
            return;
          }

          if(v.get(0).getTitle().equals("UnknownHTTPSError")){
            textAreaConsole.setText("UnknownHTTPSError");
            return;
          }

        }

        for(Course c : v) if(c.isValid()) NUM_COURSES++;


      // number of prefixes
      textAreaConsole.setText("Total Number of Categories/Code Prefix: " + Controller.NUM_PREFIXES);

      // number of courses found
      textAreaConsole.setText(textAreaConsole.getText() + "\nTotal Number of different courses in this search: " + NUM_COURSES);


      // number of sections Found
      textAreaConsole.setText(textAreaConsole.getText() + "\n" +
      "Total Number of difference sections in this search: " + Controller.NUM_SECTIONS + "\n\n");

      //Free instructors
      List<String> freeInstructors = new ArrayList<String>();
      for(Instructor ins : Controller.INSTRUCTORS_IN_SEARCH) if(ins.isFreeTu310()) freeInstructors.add(ins.getName());
      Collections.sort(freeInstructors);
      String freeIns = "";
      for(String str : freeInstructors) freeIns  += str + "\n";
      textAreaConsole.setText(textAreaConsole.getText() + "\n" +
      "Instructors who has teaching assignment this term but does not need to teach at Tu 3:10pm:\n" + freeIns + "\n\n");

//      for(Instructor ins : INSTRUCTORS_IN_SEARCH)
  //
//        if(ins.getName().equals("HUI, Pan")) for(Section s : ins.getSections()) System.out.println(s.get;

      //print sections
      String newline = "";
    	for (Course c : v) if(c.isValid()) newline += c.toString() + "\n\n\n";
      textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline);

        // clear storage for next search
        freeInstructors.clear();
        INSTRUCTORS_IN_SEARCH.clear();
        Controller.NUM_SECTIONS = 0;
      
    	
    	//Add a random block on Saturday
    	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
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
    
    	ap.getChildren().addAll(randomLabel);
    	
    	

    }

}