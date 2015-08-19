import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoopingDatabase")
public class LoopingDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static Connection conn;

	 public void init() throws ServletException
	  {
	      // Do required initialization

	  }
	
    public LoopingDatabase() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set response content type
		response.setContentType("text/html");
		 
		String allCust = "";
		
		try {
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	    
		//properties for creating connection to Oracle database
	    Properties props = new Properties();
	    props.setProperty("user", "testdb");
	    props.setProperty("password", "password");
	   
	    //creating connection to Oracle database using JDBC
	    
			conn = DriverManager.getConnection(url,props);
			
	    	PreparedStatement preStatement = conn.prepareStatement("select * from demo_customers");
	        

    		ResultSet result = preStatement.executeQuery();
    		while (result.next())
    		{
    		allCust += "<tr>";
    		allCust += "<td>";
    		allCust += "<a href=\"details?customerID=" + result.getString("customer_id") + "\">";
    		allCust+= result.getString("customer_id");
    		allCust += "</a>";
    		allCust+= "</td>";
    		allCust += "<td>";
    		allCust+= result.getString("cust_first_name");
    		allCust+= "</td>";
    		allCust += "<td>";
    		allCust += result.getString("cust_last_name");
    		allCust+= "</td>";
    		allCust += "</tr>";
    		}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		request.setAttribute("custname", allCust);
		getServletContext().getRequestDispatcher("/allcust.jsp").forward(request,  response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
