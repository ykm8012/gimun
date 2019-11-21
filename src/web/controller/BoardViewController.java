package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.face.MemberService;
import web.service.impl.BoardServiceImpl;
import web.service.impl.MemberServiceImpl;

@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService = new BoardServiceImpl();
	MemberService memberService = new MemberServiceImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Board board = boardService.getBoardno(req);
		BoardFile boardFile = boardService.getBoardFileByBoardno(board.getBoardno());
		
		System.out.println(boardFile);
		
		req.setAttribute("board", board);
		req.setAttribute("boardFile", boardFile);
		
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp")
		.forward(req, resp);
		
	}
}
