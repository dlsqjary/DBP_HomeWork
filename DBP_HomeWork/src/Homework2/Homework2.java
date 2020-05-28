package Homework2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Homework2.MemberTable;


public class Homework2 extends HttpServlet {

private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private Statement stmt;
	private ResultSet resultSet;
	
	private String id, pwd, name, tel, email, dept, sex, birth, intro;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Homework2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		id = request.getParameter("id");
		pwd = request.getParameter("pwd");
		name = request.getParameter("name");
		tel = request.getParameter("tel");
		email = request.getParameter("email");
		dept = request.getParameter("dept");
		sex = request.getParameter("gender");
		birth = request.getParameter("birth");
		intro = request.getParameter("intro");
		
		
		String sql = "insert into meminfo values('" + name + "', '" + id + "', '" + pwd + "', '" + name + "', '" + tel + "', '"+ email + "', '" + dept + "')";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressdb?characterEncoding=UTF-8&serverTimezone=UTC" , "root" , "1234");
			stmt = connection.createStatement();
			resultSet =  stmt.executeQuery(sql);
			int i = stmt.executeUpdate(sql);
			
		
		
			
			//response.sendRedirect("loginResult.jsp");
			System.out.println(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB Error");
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(stmt != null) stmt.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("DB Error");
			}
		}
		
	}
}




