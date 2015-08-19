
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection conn;
       

    public Details() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String CustID = request.getParameter("customerID");

		
		
		
		// Set response content type
		response.setContentType("text/html");
		 
		String allDetail = "";
		
		try {
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	    
		//properties for creating connection to Oracle database
	    Properties props = new Properties();
	    props.setProperty("user", "testdb");
	    props.setProperty("password", "password");
	   
	    //creating connection to Oracle database using JDBC
	    
			conn = DriverManager.getConnection(url,props);
			
	    	PreparedStatement preStatement = conn.prepareStatement("select * from demo_customers where customer_id = " + CustID);
	        

    		ResultSet result = preStatement.executeQuery();
    		result.next();
    		
    		allDetail += "<tr>";
    		allDetail += "<td>";
    		allDetail += "<a href=\"details?customerID=" + result.getString("customer_id") + "\">";
    		allDetail+= result.getString("customer_id");
    		allDetail += "</a>";
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail+= result.getString("cust_first_name");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("cust_last_name");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("cust_street_address1");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("cust_city");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("cust_state");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("cust_postal_code");
    		allDetail+= "</td>";
    		allDetail += "<td>";
    		allDetail += result.getString("phone_number1");
    		allDetail+= "</td>";
    		allDetail += "</tr>";
    		
		}catch (Exception e) {

			e.printStackTrace();}
		
		request.setAttribute("custname", allDetail);	
		getServletContext().getRequestDispatcher("/custdetails.jsp").forward(request, response);
		}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
