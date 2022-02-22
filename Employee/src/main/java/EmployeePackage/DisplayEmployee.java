package EmployeePackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
@WebServlet("/displayServlet")
public class DisplayEmployee extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");

		
		try {
			Statement statement = connection.createStatement();
			int id = Integer.parseInt(request.getParameter("id"));
			ResultSet resultSet = statement.executeQuery("select * from employee where EmployeeId='"+id+"'");
			PrintWriter out = response.getWriter();
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>");
			out.println("Employee Id<br>");
			out.println("</th>");
			out.println("<th>");
			out.println("Employee Name<br>");
			out.println("</th>");
			out.println("<th>");
			out.println("Designation");
			out.println("</th>");
			out.println("<th>");
			out.println("salary");
			out.println("</th>");
			out.println("</tr>");
			while(resultSet.next()) {
				out.println("<tr>");
				
				out.println("<td>");
				out.println(resultSet.getInt(1));
				out.println("</td>");
				out.println("<td>");
				out.println(resultSet.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.println(resultSet.getString(3));
				out.println("</td>");
				out.println("<td>");
				out.println(resultSet.getInt(4));
				out.println("</td>");
				
				out.println("</tr>");
			}
			
		out.println("</table>");
		RequestDispatcher rd = request.getRequestDispatcher("/main.html");
		rd.include(request, response);
		
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
