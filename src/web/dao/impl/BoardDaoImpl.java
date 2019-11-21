package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConn;
import util.Paging;
import web.dao.face.BoardDao;
import web.dto.Board;
import web.dto.BoardFile;

public class BoardDaoImpl implements BoardDao{
	
	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL수행 결과 객체

	@Override
	public List<Board> selectAll() {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT boardno, title, id, content, hit, writtendate";
		sql += " FROM board ORDER BY boardno DESC";
		
		List<Board> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
//			System.out.println(rs.next());
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("writtendate"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List<Board> selectAll(Paging paging) {

		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql	+=	"    SELECT rownum rnum, B.* FROM (";
		sql	+=	"        SELECT *";
		sql	+=	"        FROM board";
		sql	+=	"        ORDER BY boardno DESC";
		sql	+=	"    ) B";
		sql	+=	"    ORDER BY rnum";
		sql	+=	" ) BOARD";
		sql	+=	" WHERE rnum BETWEEN ? AND ?";

		List<Board> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
//			System.out.println(rs.next());
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setWrittendate(rs.getDate("writtendate"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	@Override
	public Board selectBoardByBoardno(Board board) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT boardno, title, id, content, hit, writtendate";
		sql += " FROM board WHERE boardno=?";
		
		Board boarddetail = new Board();
		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
			
			rs = ps.executeQuery();
			
			rs.next();
				
			boarddetail.setBoardno(rs.getInt("boardno"));
			boarddetail.setTitle(rs.getString("title"));
			boarddetail.setId(rs.getString("id"));
			boarddetail.setContent(rs.getString("content"));
			boarddetail.setHit(rs.getInt("hit"));
			boarddetail.setWrittendate(rs.getDate("writtendate"));
				
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return boarddetail;
	}


	
	@Override
	public void updateHit(Board board) {
		
	
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "UPDATE board SET hit = hit+1";
		sql += " WHERE boardno=?";
		

			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, board.getBoardno());
				ps.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
	}


	@Override
	public int selectCntAll() {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT count(*)";
		sql += " FROM board ORDER BY boardno";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				cnt = rs.getInt(1);
			}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return cnt;
	}

	@Override
	public void insert(Board board) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "INSERT INTO board(boardno,title,id,content,hit,writtendate)";
		sql += " VALUES(?,?,?,?,0,sysdate)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getBoardno());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getId());
			ps.setString(4, board.getContent());
			
			ps.executeQuery();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	
	@Override
	public int selectBoardno() {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT board_seq.nextVal FROM dual";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				res = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public void insertFile(BoardFile boardFile) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "INSERT INTO boardfile(fileno, boardno, originname, storedname, filesize, writedate)";
		sql += " VALUES(boardfile_seq.nextVal,?,?,?,?,sysdate)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardFile.getBoardno());
			ps.setString(2,	boardFile.getOriginname());
			ps.setString(3,	boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public BoardFile selectBoardFileByBoardno(int boardno) {

		BoardFile boardFile = new BoardFile();
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM boardfile";
		sql += " WHERE boardno = ? AND fileno=(SELECT MAX(fileno)";
		sql += " FROM boardfile WHERE boardno=?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardno);
			ps.setInt(2, boardno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setBoardno(rs.getInt("boardno"));
				boardFile.setOriginname(rs.getString("originname"));
				boardFile.setStoredname(rs.getString("storedname"));
				boardFile.setFilesize(rs.getInt("filesize"));
				boardFile.setWritedate(rs.getDate("writedate"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boardFile;
	}

	@Override
	public BoardFile selectByFileno(BoardFile boardFile) {
		
		BoardFile resFile = new BoardFile();
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT * FROM boardfile";
		sql += " WHERE fileno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardFile.getFileno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				resFile.setFileno(rs.getInt("fileno"));
				resFile.setBoardno(rs.getInt("boardno"));
				resFile.setOriginname(rs.getString("originname"));
				resFile.setStoredname(rs.getString("storedname"));
				resFile.setFilesize(rs.getInt("filesize"));
				resFile.setWritedate(rs.getDate("writedate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resFile;
	}

	@Override
	public Board selectByBoardno(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Board board) {
		
		conn = DBConn.getConnection();

		String sql = "";
		sql += "UPDATE board SET";
		sql += " title=?,";
		sql += " content=?";
		sql += " WHERE boardno=?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoardno());

			ps.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteFileByBoardno(Board board) {
		
		conn = DBConn.getConnection();

		String sql = "";
		sql += "DELETE FROM boardfile";
		sql += " WHERE boardno=?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());

			ps.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteBoardByboardno(Board board) {
		
		conn = DBConn.getConnection();

		String sql = "";
		sql += "DELETE FROM board";
		sql += " WHERE boardno=?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());

			ps.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}





}
