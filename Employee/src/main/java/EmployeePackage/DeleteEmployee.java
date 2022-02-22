package EmployeePackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/deleteServlet")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private Connection connection;

	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "Pavan@123");
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		int id = Integer.parseInt(request.getParameter("id"));
	
		
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("delete from employee where EmployeeId='"+id+"'");
			PrintWriter out = response.getWriter();
			if(result>0) {
				out.print("<h1>Employee Account deleted</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("/main.html");
				rd.include(request, response);
			}
			else {
				out.print("<h1>ERROR while updating  the salary");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
