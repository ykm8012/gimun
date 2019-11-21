package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javafx.scene.control.Alert;
import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = null;
		session = req.getSession();
		
		Member member = memberService.getLoginMember(req);
		
//		Member member = memberService.getMemberByUserid(loginMember);
		
		if(memberService.login(member)) {
			
			Member mem = memberService.getMemberByUserid(member);
			
			session.setAttribute("login", "true");
			session.setAttribute("userid", mem.getUserid());
			session.setAttribute("usernick", mem.getUsernick());

			
		} else {
			
			System.out.println("로그인 실패");
			
		}
		
		resp.sendRedirect("/main");
	}
}
