/** *****************************************************************
    twoButtons.java   servlet example

        @author Jeff Offutt
********************************************************************* */

//package com.howtodoinjava.demo.jsonsimple;

// Import Java Libraries
import java.io.*;
import java.util.*;

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.WebServlet;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;

//import org.json.simple.JSONObject;

//import com.fasterxml.jackson.databind.ObjectMapper;

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

@WebServlet( name = "echo", urlPatterns = {"/echo"} )

public class echo extends HttpServlet
{

// Location of servlet.
static String Domain  = "pauldoodyparticipation.herokuapp.com";
static String Path    = "/"; 
static String Servlet = "echo";


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
	
   response.setContentType("application/json;charset=UTF-8");
   PrintWriter out = response.getWriter();
   
   Map<String , String> output = new HashMap<String , String>();
   output.put("0","zero");
   output.put("1","one");
   output.put("2","two");
   output.put("3","three");
   
   //ObjectMapper mapped = new ObjectMapper();
   //String finalOutput = mapped.writeValueAsString(output);
   //JSONObject jsonObject =  HTTP.toJSONObject(output.toString());
	out.print(output.toString());
   
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
   out.print("ECHO");
} // End doGet

}


