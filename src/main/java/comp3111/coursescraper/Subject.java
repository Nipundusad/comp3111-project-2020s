package comp3111.coursescraper;

import java.util.List;
import java.util.ArrayList;

public class Subject {
	private String title ;
	private List<Course> courses;

	public Subject () {
		courses = new ArrayList<Course>();
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
	public void add_course(Course c){
		this.courses.add(c);
}

	/**
	* @return the sections
	*/
	public List<Course> get_courses(){
		return this.courses;
	}
}
