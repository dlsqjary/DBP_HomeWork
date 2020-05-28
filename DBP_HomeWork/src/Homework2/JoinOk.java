package Homework2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class JoinOk
 */
@WebServlet("/joinOk")
public class JoinOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Connection connection;
	private Statement stmt;
	
	
	private String name, id, pwd, tel, dept, gender, birth, introduction, email;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinOk() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		name = request.getParameter("name");
		id = request.getParameter("id");
		pwd = request.getParameter("pwd");
		tel = request.getParameter("tel");
		dept = request.getParameter("dept");
		gender = request.getParameter("gender");
		birth = request.getParameter("birth");
		introduction = request.getParameter("introduction");
		email = request.getParameter("email");
		
		String query = "insert into meminfo values('" + id + "', '" + pwd + "', '" + name + "', '" + tel + "', '" + dept + "', '"+ gender + "', '" + birth + "', '"+introduction+"', '"+email+ "' )";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressdb?characterEncoding=UTF-8&serverTimezone=UTC" , "root" , "1234");
			stmt = connection.createStatement();
			int i = stmt.executeUpdate(query);
			if(i == 1){
				System.out.println("insert success");
				show(stmt);
				response.sendRedirect("modify.jsp");
				
			} else {
				System.out.println("insert fail");
				response.sendRedirect("join.html");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(connection != null) connection.close();
			} catch (Exception e) {}
		}
		
	}
	
	public static List<MemberDto> show(Statement st) throws SQLException {
		String sql = "select * from meminfo";
		ResultSet rs = st.executeQuery(sql);
		List<MemberDto>data = new ArrayList<MemberDto>();
		int cnt = 0;
		
		while(rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String tel = rs.getString("tel");
			String email = rs.getString("email");
			String dept = rs.getString("dept");
			String gender = rs.getString("gender");
			String birth = rs.getString("gender");
			String introduction = rs.getString("introduction");
			
			
			
			System.out.printf("id:  %d, name: %s, tel: %s, email: %s, dept: %s, gender: %s, birth: %s, intro: %s "
					+ "\n", id, name, tel, email, dept,gender ,birth ,introduction);
			//data.add(mem);
			cnt++;
		}
		rs.close();
		return data;

	}

}
