package comp3111.coursescraper;

import java.net.URLEncoder;
import java.util.List;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.util.Vector;
import org.apache.commons.lang3.StringUtils;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * WebScraper provide a sample code that scrape web content. After it is constructed, you can call the method scrape with a keyword,
 * the client will go to the default url and parse the page by looking at the HTML DOM.
 * <br>
 * In this particular sample code, it access to HKUST class schedule and quota page (COMP).
 * <br>
 * https://w5.ab.ust.hk/wcq/cgi-bin/1830/subject/COMP
 *  <br>
 * where 1830 means the third spring term of the academic year 2018-19 and COMP is the course code begins with COMP.
 * <br>
 * Assume you are working on Chrome, paste the url into your browser and press F12 to load the source code of the HTML. You might be freak
 * out if you have never seen a HTML source code before. Keep calm and move on. Press Ctrl-Shift-C (or CMD-Shift-C if you got a mac) and move your
 * mouse cursor around, different part of the HTML code and the corresponding the HTML objects will be highlighted. Explore your HTML page from
 * body &rarr; div id="classes" &rarr; div class="course" &rarr;. You might see something like this:
 * <br>
 * <pre>
 * {@code
 * <div class="course">
 * <div class="courseanchor" style="position: relative; float: left; visibility: hidden; top: -164px;"><a name="COMP1001">&nbsp;</a></div>
 * <div class="courseinfo">
 * <div class="popup attrword"><span class="crseattrword">[3Y10]</span><div class="popupdetail">CC for 3Y 2010 &amp; 2011 cohorts</div></div><div class="popup attrword"><span class="crseattrword">[3Y12]</span><div class="popupdetail">CC for 3Y 2012 cohort</div></div><div class="popup attrword"><span class="crseattrword">[4Y]</span><div class="popupdetail">CC for 4Y 2012 and after</div></div><div class="popup attrword"><span class="crseattrword">[DELI]</span><div class="popupdetail">Mode of Delivery</div></div>
 *    <div class="courseattr popup">
 * 	    <span style="font-size: 12px; color: #688; font-weight: bold;">COURSE INFO</span>
 * 	    <div class="popupdetail">
 * 	    <table width="400">
 *         <tbody>
 *             <tr><th>ATTRIBUTES</th><td>Common Core (S&amp;T) for 2010 &amp; 2011 3Y programs<br>Common Core (S&amp;T) for 2012 3Y programs<br>Common Core (S&amp;T) for 4Y programs<br>[BLD] Blended learning</td></tr><tr><th>EXCLUSION</th><td>ISOM 2010, any COMP courses of 2000-level or above</td></tr><tr><th>DESCRIPTION</th><td>This course is an introduction to computers and computing tools. It introduces the organization and basic working mechanism of a computer system, including the development of the trend of modern computer system. It covers the fundamentals of computer hardware design and software application development. The course emphasizes the application of the state-of-the-art software tools to solve problems and present solutions via a range of skills related to multimedia and internet computing tools such as internet, e-mail, WWW, webpage design, computer animation, spread sheet charts/figures, presentations with graphics and animations, etc. The course also covers business, accessibility, and relevant security issues in the use of computers and Internet.</td>
 *             </tr>
 *          </tbody>
 *      </table>
 * 	    </div>
 *    </div>
 * </div>
 *  <h2>COMP 1001 - Exploring Multimedia and Internet Computing (3 units)</h2>
 *  <table class="sections" width="1012">
 *   <tbody>
 *    <tr>
 *        <th width="85">Section</th><th width="190" style="text-align: left">Date &amp; Time</th><th width="160" style="text-align: left">Room</th><th width="190" style="text-align: left">Instructor</th><th width="45">Quota</th><th width="45">Enrol</th><th width="45">Avail</th><th width="45">Wait</th><th width="81">Remarks</th>
 *    </tr>
 *    <tr class="newsect secteven">
 *        <td align="center">L1 (1765)</td>
 *        <td>We 02:00PM - 03:50PM</td><td>Rm 5620, Lift 31-32 (70)</td><td><a href="/wcq/cgi-bin/1830/instructor/LEUNG, Wai Ting">LEUNG, Wai Ting</a></td><td align="center">67</td><td align="center">0</td><td align="center">67</td><td align="center">0</td><td align="center">&nbsp;</td></tr><tr class="newsect sectodd">
 *        <td align="center">LA1 (1766)</td>
 *        <td>Tu 09:00AM - 10:50AM</td><td>Rm 4210, Lift 19 (67)</td><td><a href="/wcq/cgi-bin/1830/instructor/LEUNG, Wai Ting">LEUNG, Wai Ting</a></td><td align="center">67</td><td align="center">0</td><td align="center">67</td><td align="center">0</td><td align="center">&nbsp;</td>
 *    </tr>
 *   </tbody>
 *  </table>
 * </div>
 *}
 *</pre>
 * <br>
 * The code
 * <pre>
 * {@code
 * List<?> items = (List<?>) page.getByXPath("//div[@class='course']");
 * }
 * </pre>
 * extracts all result-row and stores the corresponding HTML elements to a list called items. Later in the loop it extracts the anchor tag
 * &lsaquo; a &rsaquo; to retrieve the display text (by .asText()) and the link (by .getHrefAttribute()).
 *
 *
 */
public class Scraper {
	private WebClient client;

	/**
	 * Default Constructor
	 */
	public Scraper() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
	}

	
	private void addSlot(HtmlElement e, Course c, boolean secondRow) {
		String type = e.getChildNodes().get(secondRow ? 0 : 1).asText();
		String times[] =  e.getChildNodes().get(secondRow ? 0 : 3).asText().split(" ");
		String venue = e.getChildNodes().get(secondRow ? 1 : 4).asText();

		if (times[0].equals("TBA"))
			return;
		for (int j = 0; j < times[0].length(); j+=2) {
			String code = times[0].substring(j , j + 2);
			if (Slot.DAYS_MAP.get(code) == null)
				break;
			Slot s = new Slot();
			s.setDay(Slot.DAYS_MAP.get(code));
			s.setStart(times[1]);
			s.setEnd(times[3]);
			s.setVenue(venue);
			s.setType(type);
			c.addSlot(s);

		}

	}

		


	/**
	* Adds a section  to the given course
	* @param e - a HtmlElement row consisting of section information
	* @param c the course - to which this section must be added
	* @param secondRow T/F - if secondRow 
	*/

	private void addSection(HtmlElement e, Course c, boolean secondRow){

		String type = e.getChildNodes().get(secondRow ? 0 : 1).asText();
		String times[] =  e.getChildNodes().get(secondRow ? 0 : 3).asText().split(" ");
		String venue = e.getChildNodes().get(secondRow ? 1 : 4).asText();


		DomNode next = e.getNextSibling();
		boolean addNext = false;
		if(next != null){
			String day = next.asText().substring(0, 2);
			for(int i = 0; i < Slot.DAYS.length; i++) if(Slot.DAYS[i].equals(day)) addNext = true;
		}

		String sectionCode = c.getTitle().split(" ")[0] + c.getTitle().split(" ")[1];
		sectionCode += " " + type.split(" ")[0];
		String sID = StringUtils.substringBetween(type, "(", ")");
		if(sID == null) return;
		int sectionID = Integer.parseInt(sID);

		if(!type.startsWith("R")) Controller.NUM_SECTIONS++;


		Section sec = new Section(sectionCode, sectionID);


		for (int j = 0; j < times[0].length(); j+=2) {
			String code = times[0].substring(j , j + 2);
			if (Slot.DAYS_MAP.get(code) == null)
				break;
			Slot s = new Slot();
			s.setDay(Slot.DAYS_MAP.get(code));
			s.setStart(times[1]);
			s.setEnd(times[3]);
			s.setVenue(venue);
			s.setType(type);
			sec.add_slot(s);

	}

	if(addNext){
		String timesNext[] = next.asText().split(" ");
		Slot s = new Slot();
		s.setDay(Slot.DAYS_MAP.get(timesNext[0]));
		s.setStart(times[1]);
		s.setEnd(times[3]);
		s.setVenue(venue);
		s.setType(type);
		sec.add_slot(s);
	}


	c.add_section(sec);


	if(e!= null){
		List<?> instructors = (List<?>) e.getByXPath(".//a[contains(@href,'instructor')]");
		for(HtmlElement ins: (List<HtmlElement>)instructors){

			// find the name
			String insName = ins.asText();

			// check if instructor already in search
			int insIndex = Controller.inInstructorSearch(insName);
			if(insIndex == -1) Controller.INSTRUCTOR.add(new Instructor(insName, sec));
			else Controller.INSTRUCTOR.get(insIndex).addSection(sec);
		}
	}


}


	/**
	* A function for scraping  Course info 
	* @param baseurl the domain of the webpage to be scraped
	* @param term the term of the calendar year that must be scraped format (YYTT)
	* @param sub the code of the department whose courses to be scraped
	* @return a List of Courses that were found in the webpage {@link Course}
	*/
	public List<Course> scrape(String baseurl, String term, String sub) {

		try {

			HtmlPage page = client.getPage(baseurl + "/" + term + "/subject/" + sub);

      List<?> items = (List<?>) page.getByXPath("//div[@class='course']");

			List<?> depts = (List<?>) page.getByXPath("//div[@class='depts']/a");
			Controller.NUM_PREFIXES = depts.size();


			Vector<Course> result = new Vector<Course>();

			for (int i = 0; i < items.size(); i++) {
				Course c = new Course();
				HtmlElement htmlItem = (HtmlElement) items.get(i);

				HtmlElement title = (HtmlElement) htmlItem.getFirstByXPath(".//h2");
				c.setTitle(title.asText());

				List<?> popupdetailslist = (List<?>) htmlItem.getByXPath(".//div[@class='popupdetail']/table/tbody/tr");
				HtmlElement exclusion = null;
				HtmlElement attributes = null;/// make attributes = null
				for ( HtmlElement e : (List<HtmlElement>)popupdetailslist) {
					HtmlElement t = (HtmlElement) e.getFirstByXPath(".//th");
					HtmlElement d = (HtmlElement) e.getFirstByXPath(".//td");

					if (t.asText().equals("EXCLUSION")) {
						exclusion = d;
					}
					if (t.asText().equals("ATTRIBUTES")) {	///checks if equals ATTRIBUTES
						if (d.asText().contains("4Y") && d.asText().contains("Common Core")) {
							attributes = d;
						}
					}
				}
				c.setExclusion((exclusion == null ? "null" : exclusion.asText()));

				c.set_common_core((attributes != null));
				
				
				List<?> sections = (List<?>) htmlItem.getByXPath(".//tr[contains(@class,'newsect')]");
				for ( HtmlElement e: (List<HtmlElement>)sections) {
					addSlot(e, c, false);
					addSection(e, c, false);
					e = (HtmlElement)e.getNextSibling();
					if (e != null && !e.getAttribute("class").contains("newsect")){
						addSlot(e, c, true);
						addSection(e, c, true);/// ADD SECTION
					}

				}

				result.add(c);
			}
			client.close();
			return result;
		} 
		catch (FailingHttpStatusCodeException e) {

			// handling 404 exception with by returning null

			Course page_error = new Course();
			if(e.getStatusCode() == 404) {
				page_error.setTitle("404PageNotFound");
			}
			else page_error.setTitle("UnknownHTTPSError");
			Vector<Course> errors = new Vector<Course>();
			errors.add(page_error);

			return errors;

		}catch (Exception e){
			System.out.println(e);

			return null;
		}

	}
	
}
