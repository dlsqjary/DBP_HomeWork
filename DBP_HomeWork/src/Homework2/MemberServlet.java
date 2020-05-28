package Homework2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Homework2.MemberDto;


@WebServlet("/insert")
public class MemberServlet extends HttpServlet{

	private Connection con;
	private Statement st;
	PreparedStatement pstmt;
	private String id, pwd, name, tel, email, dept, sex, birth, introduction;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		id = req.getParameter("id");
		pwd = req.getParameter("pwd");
		name = req.getParameter("name");
		tel = req.getParameter("tel");
		email = req.getParameter("email");
		dept = req.getParameter("dept");
		sex = req.getParameter("gender");
		birth = req.getParameter("birth");
		introduction = req.getParameter("intro");

		MemberTable mem = new MemberTable(id, pwd, name, tel, dept, sex, birth, introduction, email);

		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/addressdb?serverTimezone=UTC";
		
		
		String sql = "insert into member1 values('" + name + "', '" + id + "', '" + pwd + "', '" + tel + "', '" + email + "', '"+ dept + "', '" + sex + "', '"+birth+ "', '" + introduction +"')";
		
		try {
			Class.forName(jdbc_driver).newInstance();
			con = DriverManager.getConnection(jdbc_url, "root", "1234");
			st = con.createStatement();
			pstmt = con.prepareStatement(sql);
			int i = st.executeUpdate(sql);
			
			if(i==1) {
				System.out.println("insert Ok");
			}
			

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("sql error");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
