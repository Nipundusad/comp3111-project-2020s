package comp3111.coursescraper;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalTime;
import java.util.Locale;
import java.time.format.DateTimeFormatter;


/**
 * Slot class (E.G. We14:00-15:50:Rm 5620, Lift 31-32 (70))
 * @author mgoyal
 * Task 1
 */
public class Slot {
	private int day;
	private LocalTime start;
	private LocalTime end;
	private String venue;
	private String type; /// type data member to help clone a slot 
	/**
	 * Array of Dates
	 */
	public static final String DAYS[] = {"Mo", "Tu", "We", "Th", "Fr", "Sa"};
	/**
	 * Mapping of days with numbers ( EG: MON:0, TUE:1...)
	 */
	public static final Map<String, Integer> DAYS_MAP = new HashMap<String, Integer>();
	static {
		for (int i = 0; i < DAYS.length; i++)
			DAYS_MAP.put(DAYS[i], i);
	}

	/**
	 * Creates a Clone of a Slot
	 * @return new Slot
	 */
	@Override
	public Slot clone() {
		Slot s = new Slot();
		s.day = this.day;
		s.start = this.start;
		s.end = this.end;
		s.venue = this.venue;
		s.type = this.type;
		return s;
	}
	
	/**
	 * Returns String of Slot
	 * @return String representation of a Slot
	 */
	public String toString() {
		return DAYS[day] + start.toString() + "-" + end.toString() + ":" + venue;
	}
	
	/**
	 * Returns the starting hour in int
	 * @return int - starting hour of slot
	 */
	public int getStartHour() {
		return start.getHour();
	}

	/**
	 * Returns the starting minute in int
	 * @return int - starting minute of slot
	 */
	public int getStartMinute() {
		return start.getMinute();
	}

	/**
	 * Returns the ending hour in int
	 * @return int - ending hour of slot
	 */
	public int getEndHour() {
		return end.getHour();
	}

	/**
	 * Returns the ending minute in int
	 * @return int - ending minute of slot
	 */	
	public int getEndMinute() {
		return end.getMinute();
	}

	/**
	 * returns LocalTime obj of start
	 * @return the start
	 */
	public LocalTime getStart() {
		return start;
	}

	/**
	 * * Sets start time of Slot to the pattern ("hh:mm")
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}

	/**
	 *  returns LocalTime Object of End

	 * @return the end
	 */
	public LocalTime getEnd() {
		return end;
	}

	/**
	 *  Sets the end time of Slot to the pattern ("hh:mm")

	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = LocalTime.parse(end, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}

	/**
	 *returns string venue of slot
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * sets param string venue of Slot
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
	 * returns int day of Slot
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * sets int day of Slot
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	// Task 1 additional member functions
	
	/**
	 * returns string type of slot
	 * @return type of Slot 
	 */
	public String getType() {
		return type; 
		}

	/**
	 * sets string type of Slot
	 * @param type the type of Slot 
	 */
	public void setType(String type) { 
		this.type = type; 
		}


	/**
	* Checks if slot is valid
	* @param s slot to be check
`	* return (T/F) - whether a slot is valid i.e between 9AM - 10PM
	*/
	public static boolean isValidSlot(Slot s){
				if(s == null) {
					return false;
				}
				if (s.getStartHour() >= 9 && s.getEndHour() <= 22) {
					return true;
				}
				else return false;
}

}
