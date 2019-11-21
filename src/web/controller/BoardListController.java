package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Paging;
import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = boardService.getPaging(req);
//		System.out.println(paging);
		
		//Paging 객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);
		
		
		List<Board> list = boardService.getList(paging);
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
		.forward(req, resp);
		
	}

}
