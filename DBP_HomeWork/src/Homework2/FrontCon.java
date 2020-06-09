package Homework2;

import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontCon
 */
@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontCon() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("actionDo");

		String uri = request.getRequestURI();
		String conPath = request.getContextPath(); 
		String command = uri.substring(conPath.length());

		if(command.equals("/membersAll.do")) {
			response.setContentType("text/html; charset=EUC-KR");
			PrintWriter writer = response.getWriter();
			writer.println("<html><head></head><body>");

			Service service = new MembersAllService();
			ArrayList<MemberDto> dtos = service.execute(request, response);

			writer.println("Id, name, dept, email, sex, tel, birth, intrudcution" + "<hr />");
			writer.println("<hr />");

			for (int i = 0; i < dtos.size(); i++) {
				MemberDto dto = dtos.get(i);
				String id = dto.getId();
				String pwd = dto.getPwd();
				String name = dto.getName();
				String email = dto.getEmail();
				String dept = dto.getDept();
				String sex = dto.getSex();
				String tel = dto.getTel();
				String birth = dto.getBirth();
				String introduction = dto.getIntroduction();

				writer.println(id + ", " + name + ", " + dept + ", " + email + ", " + sex + ", " + tel + ", " + birth + ", " + introduction +"<hr />");
			}

			writer.println("</body></html>");
		}else if(command.equals("/deletePage.do")) {
			response.setContentType("text/html; charset=EUC-KR");
			PrintWriter writer = response.getWriter();
			writer.println("<html><head></head><body>");

			Service service = new MemberDelete();
			ArrayList<MemberDto> dtos = service.execute(request, response);
		}

	}

}
