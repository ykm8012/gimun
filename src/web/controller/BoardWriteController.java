package web.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import web.dao.face.BoardDao;
import web.dao.impl.BoardDaoImpl;
import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/board/write.jsp")
		.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
//		Board board = new Board();
//		
//		board.setTitle(req.getParameter("title"));
//		board.setId((String)req.getSession().getAttribute("userid"));
//		board.setContent(req.getParameter("content"));
		
//		boardService.write(board);
		
//		boardService.write(req, resp);
		
		// --- 매개변수 준비 ---
		
		// 1. 요청 객체 - req
				
		// 2. 파일 저장 위치 - 서버 real path를 이용
		ServletContext context = getServletContext();
		String saveDirectory = context.getRealPath("upload");
				
		// 3. 업로드 제한 사이즈
		int maxPostSize = 10 * 1024 * 1024; // 10MB
				
		// 4. 인코딩
				
		String encoding = "UTF-8";
				
		// 5. 중복 파일 이름 정책
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
				
		// --- 준비 완료 ---
				
				
				
		// --- COS 파일 업로드 객체 생성 ---
		MultipartRequest mul = new MultipartRequest
				(req, saveDirectory, maxPostSize, encoding, policy);
		// ---------------------------
				
				
		// --- 업로드 정보 확인 ---
		resp.setContentType("text/html; charset=utf-8");
				
		resp.getWriter()
		.append("- - - 전달 파라미터 - - -<br>")
		.append("title : " + mul.getParameter("title") + "<br>")
		.append("nick : " + mul.getParameter("nick") + "<br>")
		.append("<br>")
		.append("- - - 저장된 파일의 이름 - - -<br>")
		.append(mul.getFilesystemName("upfile") + "<br>" )
		.append("<br>")
		.append("- - - 원본 파일의 이름 - - -<br>")
		.append(mul.getOriginalFileName("upfile") + "<br>" )
		.append("<br>")
		.append("- - - 파일 형식 - - -<br>")
		.append(mul.getContentType("upfile") + "<br>" );
				
		// -------------------
		Board board = new Board();
		
		board.setTitle(mul.getParameter("title"));
		board.setId((String)req.getSession().getAttribute("userid"));
		board.setContent(mul.getParameter("content"));
		
				
		BoardFile boardFile = new BoardFile();
				
		boardFile.setOriginname(mul.getOriginalFileName("upfile"));
		boardFile.setStoredname(mul.getFilesystemName("upfile"));
		
		BoardDao boardDao = new BoardDaoImpl();
				
		int nextVal = boardDao.selectBoardno(); // dual테이블에서 시퀀스 값 가져오기

		board.setBoardno(nextVal);
		boardFile.setBoardno(nextVal);
		
		System.out.println(board);
		System.out.println(boardFile);
				
		boardDao.insert(board);
		boardDao.insertFile(boardFile);
		
		resp.sendRedirect("/board/list");
		
	}

}
