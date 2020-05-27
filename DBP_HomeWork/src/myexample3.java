import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 - 서버 사이드 저장 기술은 어느것들인가?
 	ServletContext, HttpSession, HttpServletRequest, Cookie

   - ServletContext와 HttpSession의 차이?
   	  ServletContext: 웹 어플리케이션 내의 서블릿 간 정보 공유가 가능
   	  HttpSession: 클라이언트마다 독립적인 세션이 존재.

   - HttpSession 키는 어디에 저장되어 있는지?
   	 클라이언트의 브라우저에 저장

   - Cookie의 옵션 중 Path와 MaxAge 기능 설명
   	Path: 서블릿 마다 쿠키를 다르게 사용가능

   	MaxAge: 브라우저가 닫혀도 쿠키가 유지되서 계속 사용 가능

   - 서버 사이드 저장 기술 대비 클라이언트 사이드 저장 기술의 장점?
   	옵션 설정에 따라서 특정 URL에서만 상태정보 저장할 수 있다.

 */
@WebServlet("/my3")
public class myexample3 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Cookie[] cookies = req.getCookies();
		
		String v_ = req.getParameter("value");
		int v = 0;
		v = Integer.parseInt(v_);
		String op = req.getParameter("operator");

		ServletContext application = req.getServletContext();
		HttpSession session = req.getSession();
		// 계산
		if(op.equals("=")) {
			int result = 0;
			//int x = (int) application.getAttribute("value");
			//String operator = (String)application.getAttribute("op");
			//int x = (int) session.getAttribute("value");
			//String operator = (String)session.getAttribute("op");
			
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}	
			}
			
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}	
			}
			
				
			int y = v;
			if(operator.equals("+"))
				result = x + y;
			else
				result = x - y;
					
				resp.getWriter().printf("result is %d\n", result);
			}			
		else{
			// 값을 저장
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			// cookie는 문자열만 저장됨 (JSON, XML 사용 가능)
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setMaxAge(60*60);
			resp.addCookie(valueCookie);
			resp.addCookie(opCookie);
			
			resp.sendRedirect("Calculator2.html");
			
		}
	}
}
