/** *****************************************************************
    twoButtons.java   servlet example

        @author Jeff Offutt
********************************************************************* */

// Import Java Libraries
import java.io.*;
import java.util.*;

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.WebServlet;

// twoButtons class
// CONSTRUCTOR: no constructor specified (default)
//
// ***************  PUBLIC OPERATIONS  **********************************
// public void doPost ()  --> prints a blank HTML page
// public void doGet ()  --> prints a blank HTML page
// private void PrintHead (PrintWriter out) --> Prints the HTML head section
// private void PrintBody (PrintWriter out) --> Prints the HTML body with
//              the form. Fields are blank.
// private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
//              Prints the HTML body with the form.
//              Fields are filled from the parameters.
// private void PrintTail (PrintWriter out) --> Prints the HTML bottom
//***********************************************************************

@WebServlet( name = "assignment_8", urlPatterns = {"/assignment_8"} )

public class assignment_8 extends HttpServlet
{
	
	static String RESOURCE_FILE = "entries.txt";
	static final String VS = ";"; //value separator

// Location of servlet.
static String Domain  = "pauldoodyparticipation.herokuapp.com";
static String Path    = "/"; 
static String Servlet = "assignment_8";

// Button labels
static String OperationAdd = "Add";
static String OperationSub = "Subtract";
static String OperationMultiply = "Multiply";

// Other strings.
static String Style ="https://www.cs.gmu.edu/~offutt/classes/432/432-style.css";

/** *****************************************************
 *  Overrides HttpServlet's doPost().
 *  Converts the values in the form, performs the operation
 *  indicated by the submit button, and sends the results
 *  back to the client.
********************************************************* */
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
	String seating_location = request.getParameter("seating-location");
	String comfort_rating = request.getParameter("comfort_rating");
	String crowded_rating = request.getParameter("crowded_rating");
	String volume_rating = request.getParameter("volume_rating");
	String food_rating = request.getParameter("food_rating");
	String overall_rating = request.getParameter("overall_rating");
	
	String name_field = request.getParameter("name_field");
	String email_field = request.getParameter("email_field");
	
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   
   
   PrintWriter entriesPrintWriter = new PrintWriter(new FileWriter(RESOURCE_FILE, true), true);
   entriesPrintWriter.println(name_field+VS+email_field+VS+seating_location+VS+comfort_rating+VS+crowded_rating+VS+
   volume_rating+VS+food_rating+VS+overall_rating);
   entriesPrintWriter.close();
   
   
   PrintHead(out);
   PrintReturn(out,seating_location, comfort_rating, crowded_rating, volume_rating, food_rating, overall_rating,
   name_field, email_field);
   PrintTail(out);
}  // End doPost

/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  Prints an HTML page with a blank form.
********************************************************* */
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   PrintHead(out);
   PrintBody(out , RESOURCE_FILE);
   PrintTail(out);
} // End doGet

public void PrintReturn(PrintWriter out, String seating, String comfort, String crowded, String volume, String food, String overall,
String name, String email )
{
	out.println("<body class = \"general\">");
	out.println("Thanks for your Input!");
	out.println("<table border = 1 align=\"center\">");
	out.println("<tr><td>Seating Location: </td>");
	out.println("<td> " + seating);
	out.println("</td></tr>");
	out.println("<tr><td>Comfort Rating: </td>");
	out.println("<td> " + comfort);
	out.println("</td></tr>");
	out.println("<tr><td>Crowd Rating: </td>");
	out.println("<td> " + crowded);
	out.println("</td></tr>");
	out.println("<tr><td>Volume Rating: </td>");
	out.println("<td> " + volume);
	out.println("</td></tr>");
	out.println("<tr><td>Food Rating: </td>");
	out.println("<td> " + food);
	out.println("</td></tr>");
	out.println("<tr><td>Overall Rating: </td>");
	out.println("<td> " + overall);
	out.println("</td></tr>");
	out.println("<tr><td>Your Name: </td>");
	out.println("<td> " + name);
	out.println("</td></tr>");
	out.println("<tr><td>Your Email: </td>");
	out.println("<td> " + email);
	out.println("</td></tr>");
	out.println("</body>");

}

/** *****************************************************
 *  Prints the <head> of the HTML page, no <body>.
********************************************************* */
private void PrintHead (PrintWriter out)
{
   out.println("<html>");
   out.println("");

   out.println("<head>");
   
   
   out.println("<title>Paul Doody</title>");
out.println("<style>");
out.println("BODY.general{background-color:#EBFAF2}");
out.println("P{text-align:justify}");
out.println("P.title{font-size:300% ; color:#000000 ; text-align:center}");
out.println("P.general{width:600 ; font-size:100% ; color:#000000 ; text-align:justify }");
out.println("A:hover{background:white}");
out.println("</style>");
out.println("<script>");


out.println(" function SubmissionTest()");
out.println("{");
out.println("	if(document.my_form.name_field.value == \"\")");
out.println("	{");
out.println("		alert(\"Please enter your Name.\");");
out.println("		return false;");
out.println("	}");
out.println("	if(document.my_form.email_field.value == \"\")");
out.println("	{");
out.println("		alert(\"Please enter your Email.\")");
out.println("		return false;");
out.println("	}");

out.println("	return (true);");
out.println("}");

out.println("var hasExpanded = 0;");

out.println("function addAnotherTextField()");
out.println("{");
out.println("	if(window.hasExpanded == 1) ");
out.println("	{");
out.println("		return;");
out.println("	}");
out.println("	 window.hasExpanded = 1;");
out.println("  var r = document.getElementById(\"radio_table\").insertRow(document.getElementById(\"radio_table\").rows.length);");
out.println("  var c0 = r.insertCell(0);");
out.println("  var c1 = r.insertCell(1);");
 out.println(" c0.innerHTML = \"<p>Another Location:</p>\";");
out.println("  c1.innerHTML = \"<input type=text name=newlocation>\";");
out.println("  document.recalc();");
 
  out.println("return;");
out.println("}");
out.println("</script>");
   
   out.println("</head>");
   out.println("");
} // End PrintHead

/** *****************************************************
 *  Prints the <BODY> of the HTML page with the form data
 *  values from the parameters.
********************************************************* */
private void PrintBody (PrintWriter out , String FILE_PATH)
{
   out.println("<body class = \"general\">");
   
out.println("   <table border = 10 align = \"center\">");
out.println("<tr><td>");
out.println("<p class=\"title\">");
out.println("GMU Campus Seating Reviews");
out.println("</p>");
out.println("</td></tr>");
out.println("</table>");

out.println("<table border = 1 align = \"center\">");
out.println("<tr><td>");
out.println("<p class=\"general\">");
out.println("   There's lots of seating on campus, but where's the best spot? What makes it so great?");
out.println("Answer the questions below to rate your favorite (and least favorite) locations. With your");
out.println("feedback, we'll be able to improve all of our seating spaces to help everyone study, eat, and");
out.println("simply relax between classes.");
out.println("</p>");
out.println("</td></tr>");
out.println("</table>");
out.println("<b>");
out.println("<form method=\"post\" action=\"https://pauldoodyparticipation.herokuapp.com/assignment_8\" name=\"my_form\" onSubmit=\"return SubmissionTest()\")>");


out.println("<table border = 1 align = \"center\" id = \"radio_table\">");

out.println("<tr><td> Location to Rate: </td>");
out.println("<td>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"exploratory\" value=\"exploratory\" required>");
out.println("<label for=\"exploratory\">Exploratory Hall</label>");
out.println("</br>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"fountains\" value=\"fountains\">");
out.println("<label for=\"fountains\">Fountains by the Mason Statue</label>");
out.println("</br>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"fenwick\" value=\"fenwick\">");
out.println("<label for=\"fenwick\">Fenwick Library</label>");
out.println("</br>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"hub\" value=\"hub\">");
out.println("<label for=\"hub\">The Hub</label>");
out.println("</br>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"jc1\" value=\"jc1\">");
out.println("<label for=\"jc1\">Johnson Center Dining Area</label>");
out.println("</br>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"jc_upper\" value=\"jc_upper\">");
out.println("<label for=\"jc_upper\">Johnson Center Upper Levels</label>");
out.println("</br>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"sub\" value=\"sub\">");
out.println("<label for=\"sub\">Student Union Building</label>");
out.println("</br>");
out.println("<input type = \"radio\" name = \"seating-location\" id=\"other\" value=\"other\" OnClick= \"addAnotherTextField()\">");
out.println("<label for=\"other\">Another Location</label>");
out.println("</br>");
out.println("</td>");
out.println("</tr>");



out.println("</table>");

out.println("<b>");
out.println("<table border = 1 align=\"center\">");
out.println("<tr><td>Rate the Comfort of Seating: </td>");
out.println("<td> ");
out.println("Very Uncomfortable");
out.println("<input type = \"radio\" name = \"comfort_rating\" id=\"1\" value=\"1\" required>");
out.println("<input type = \"radio\" name = \"comfort_rating\" id=\"2\" value=\"2\">");
out.println("<input type = \"radio\" name = \"comfort_rating\" id=\"3\" value=\"3\">");
out.println("<input type = \"radio\" name = \"comfort_rating\" id=\"4\" value=\"4\">");
out.println("<input type = \"radio\" name = \"comfort_rating\" id=\"5\" value=\"5\">");
out.println("Very Comfortable");
out.println("</td></tr>");

out.println("<tr><td>Rate how Crowded: </td>");
out.println("<td> ");
out.println("Empty");
out.println("<input type = \"radio\" name = \"crowded_rating\" id=\"1\" value=\"1\" required>");
out.println("<input type = \"radio\" name = \"crowded_rating\" id=\"2\" value=\"2\">");
out.println("<input type = \"radio\" name = \"crowded_rating\" id=\"3\" value=\"3\">");
out.println("<input type = \"radio\" name = \"crowded_rating\" id=\"4\" value=\"4\">");
out.println("<input type = \"radio\" name = \"crowded_rating\" id=\"5\" value=\"5\">");
out.println("Very Crowded");
out.println("</td></tr>");

out.println("<tr><td>Rate the Volume: </td>");
out.println("<td> ");
out.println("Silent");
out.println("<input type = \"radio\" name = \"volume_rating\" id=\"1\" value=\"1\" required>");
out.println("<input type = \"radio\" name = \"volume_rating\" id=\"2\" value=\"2\">");
out.println("<input type = \"radio\" name = \"volume_rating\" id=\"3\" value=\"3\">");
out.println("<input type = \"radio\" name = \"volume_rating\" id=\"4\" value=\"4\">");
out.println("<input type = \"radio\" name = \"volume_rating\" id=\"5\" value=\"5\">");
out.println("Ear Shattering");
out.println("</td></tr>");

out.println("<tr><td>How many food options are nearby?: </td>");
out.println("<td> ");
out.println("No Food");
out.println("<input type = \"radio\" name = \"food_rating\" id=\"1\" value=\"1\" required>");
out.println("<input type = \"radio\" name = \"food_rating\" id=\"2\" value=\"2\">");
out.println("<input type = \"radio\" name = \"food_rating\" id=\"3\" value=\"3\">");
out.println("<input type = \"radio\" name = \"food_rating\" id=\"4\" value=\"4\">");
out.println("<input type = \"radio\" name = \"food_rating\" id=\"5\" value=\"5\">");
out.println("Food Everywhere");
out.println("</td></tr>");

out.println("<tr><td>Your Overall Rating of the Location: </td>");
out.println("<td> ");
out.println("Poor");
out.println("<input type = \"radio\" name = \"overall_rating\" id=\"1\" value=\"1\" required>");
out.println("<input type = \"radio\" name = \"overall_rating\" id=\"2\" value=\"2\">");
out.println("<input type = \"radio\" name = \"overall_rating\" id=\"3\" value=\"3\">");
out.println("<input type = \"radio\" name = \"overall_rating\" id=\"4\" value=\"4\">");
out.println("<input type = \"radio\" name = \"overall_rating\" id=\"5\" value=\"5\">");
out.println("Excellent");
out.println("</td></tr>");





out.println("<tr><td>Enter any other thoughts on the location:</td><td><textarea rows = \"5\" cols = \"50\" ></textarea></td>");
out.println("</tr>");

out.println("<tr><td> Name: </td><td> <input type = \"text\" name = \"name_field\" > </td> </tr>");
out.println("<tr><td> GMU Email: </td><td> <input type = \"text\" name = \"email_field\" > </td> </tr>");

out.println("<tr> <td>");
out.println("<input type=\"submit\" name=\"submission\" value=\"Submit\"  >");
out.println("</td></tr>");

out.println("</table>");
out.println("</form>");

		out.println("<table>"):
		out.println("  <tr>");
        out.println("   <th>Name</th>");
        out.println("   <th>Email</th>");
		out.println("   <th>Seating Location</th>");
		out.println("   <th>Comfort Rating</th>");
		out.println("   <th>Crowded Rating</th>");
		out.println("   <th>Volume Rating</th>");
		out.println("   <th>Food Rating</th>");
		out.println("   <th>Overall Rating</th>");
        out.println("  </tr>");

		try{
		File file = new File(FILE_PATH);
        if(!file.exists()){
          out.println("   <p>No entries persisted yet.</p>");
        }
		else
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line;
        while ((line = bufferedReader.readLine()) != null) {
          String []  entry= line.split(VS);
          out.println("  <tr>");
          for(String value: entry){
              out.println("   <td>"+value+"</td>");
          }
          out.println("  </tr>");
        }
        bufferedReader.close();
		}
		} catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		out.println("</table>");
   
   out.println("</body>");
} // End PrintBody


/** *****************************************************
 *  Prints the bottom of the HTML page.
********************************************************* */
private void PrintTail (PrintWriter out)
{
   out.println("");
   out.println("</html>");
} // End PrintTail

}  // End 