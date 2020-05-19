package servlet;
// Written by David Gonzalez, April 2020
// Modified by Jeff Offutt
// Built to deploy in github with Heroku

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "final", urlPatterns = {"/final"})
public class final_class extends HttpServlet{
  static enum Data {BOOL};
  static String RESOURCE_FILE = "data.txt";
  static final String VALUE_SEPARATOR = ";";

  static String Domain  = "";
  static String Path    = "";
  static String Servlet = "final";

   static String OperationAdd = "Add";
  
  @Override
  public void doPost (HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
  {
     String bool = request.getParameter(Data.BOOL.name());

     String error = "";
     if(bool == null){
       error= "<li>Input is required</li>";
     }
	 
	 if(!bool.contains("AND") && !bool.contains("OR") &&
		!bool.contains("and") && !bool.contains("or"))
	 {
		 error = "<li>Must be a Predicate</li>";
	 }


     response.setContentType("text/html");
     PrintWriter out = response.getWriter();

     if (error.length() == 0){
       PrintWriter entriesPrintWriter = new PrintWriter(new FileWriter(RESOURCE_FILE, true), true);
       entriesPrintWriter.println(bool);
       entriesPrintWriter.close();

       PrintHead(out);
       PrintResponseBody(out, RESOURCE_FILE ,  bool);
       PrintTail(out);
     }else{
       PrintHead(out);
	   
	 out.println("<p>" + error + "</p>");
       PrintBody(out, bool);
       PrintTail(out);
     }
  }

  /** *****************************************************
   *  Overrides HttpServlet's doGet().
   *  Prints an HTML page with a blank form.
  ********************************************************* */
  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException{
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     PrintHead(out);
     PrintBody(out, "");
     PrintTail(out);
  }

  /** *****************************************************
   *  Prints the <head> of the HTML page, no <body>.
  ********************************************************* */
  private void PrintHead (PrintWriter out){
     out.println("<html>");
     out.println("");
     out.println("<head>");
     out.println("<title>Paul Doody Final</title>");
     // Put the focus in the name field
     out.println("</head>");
     out.println("");
	 
	 /*out.println("<script>");
	 out.println("function SubmissionTest()");
	out.println("{");
	out.println("if(document.the_form.Data.BOOL.name().value == \"\")");
	out.println("{");
	out.println("	alert(\"Field cannot be empty.\");");
	out.println("	return false;");
	out.println("}");
	out.println("}");
	out.println("</script>");*/
  }

  /** *****************************************************
   *  Prints the <BODY> of the HTML page
  ********************************************************* */
  private void PrintBody (PrintWriter out, String bool){
     out.println("<body onLoad=\"setFocus()\">");
     out.println("<p>");
     out.println("Enter a Boolean Predicate for its Truth Table using AND, OR, and, or");
     out.println("</p>");

     /*if(error != null && error.length() > 0){
       out.println("<p style=\"color:red;\">Please correct the following and resubmit.</p>");
       out.println("<ol>");
       out.println(error);
       out.println("</ol>");
     }*/

     out.print  ("<form name=\"the_form\" method=\"post\"");
     out.println(" action=\""+Domain+Path+Servlet+"\">");
     out.println("");
     out.println(" <table>");
     out.println("  <tr>");
     out.println("   <td>Predicate:</td>");
     out.println("   <td><input type=\"text\" name=\""+Data.BOOL.name()
      +"\" value=\""+bool+"\" size=30 required></td>");
     out.println("  </tr>");
    
	 
     out.println(" </table>");
     out.println(" <br>");
     out.println(" <br>");
     out.println(" <input type=\"submit\" value=\"" + "Go"
      + "\" name=\"Operation\"  );
     out.println(" <input type=\"reset\" value=\"Reset\" name=\"reset\">");
     out.println("</form>");
     out.println("");
     out.println("</body>");
  }

  /** *****************************************************
   *  Prints the <BODY> of the HTML page
  ********************************************************* */
  private void PrintResponseBody (PrintWriter out, String resourcePath , String bool){
    out.println("<body onLoad=\"setFocus()\">");
    out.println("<p>");
    out.println("Truth Table:");
    out.println("</p>");
    out.println("");
    //out.println(" <table>");

    //try {
        //out.println("  <tr>");
        //out.println("   <p>Boolean Predicates</p>");
        //out.println("  </tr>");
        /*File file = new File(resourcePath);
        if(!file.exists()){
          out.println("  <tr>");
          out.println("   <td>No entries persisted yet.</td>");
          out.println("  </tr>");
          return;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          String []  entry= line.split(VALUE_SEPARATOR);
          out.println("  <tr>");
          for(String value: entry){
              out.println("   <td>"+value+"</td>");
          }
          out.println("  </tr>");
        }
        bufferedReader.close();*/
		
		String[] ands = bool.split("AND|OR|and|or");
		int count = ands.length;
		//out.println(" Count: " + count);
		out.println("<p>" + bool + "</p>");
		printTruthTable(out, count , 0 , new int[count]);
		
		
      /*} catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
     //out.println(" </table>");
     out.println("");
     out.println("</body>");
  }

  /** *****************************************************
   *  Prints the bottom of the HTML page.
  ********************************************************* */
  private void PrintTail (PrintWriter out){
     out.println("");
     out.println("</html>");
  }
  
  private void printTruthTable(PrintWriter out ,int N, int index, int[] truthVals) {
	  
   if (index == N) {
	   
	   Boolean [] operators = new Boolean[N];
	   String listing = "<p>";
      for (int i=0; i<N; i++)
	  {
		  //operators[i] = truthVals[i];
         listing += truthVals[i] + " ";
	  }
		out.println(listing + "</p>");
   } else {
      for (int i=0; i<2; i++) {
         truthVals[index] = i;
         printTruthTable(out , N, index + 1, truthVals);
      }
   }
}

}