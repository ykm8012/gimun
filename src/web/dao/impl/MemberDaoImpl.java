package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBConn;
import web.dao.face.MemberDao;
import web.dto.Member;

public class MemberDaoImpl implements MemberDao{
	
	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL수행 결과 객체

	int result;
	
	@Override
	public int selectCntMemberByUserid(Member member) {
		
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE userid=? AND userpw=?";
		
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			
			rs = ps.executeQuery();
			
			rs.next();
			
			result = rs.getInt("count(*)");
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

	@Override
	public Member selectMemberByUserid(Member member) {
		
		conn = DBConn.getConnection(); //DB연결
		
		Member mem = new Member();
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT userid, userpw, usernick FROM member";
		sql += " WHERE userid=?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getUserid());
			
			rs = ps.executeQuery();
			
			rs.next();
			
			mem.setUserid(rs.getString("userid"));
			mem.setUserpw(rs.getString("userpw"));
			mem.setUsernick(rs.getString("usernick"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mem;
	}

	@Override
	public void insert(Member member) {

		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "INSERT INTO member(userid, userpw, usernick)";
		sql += " VALUES(?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			ps.setString(3, member.getUsernick());
			
			ps.executeQuery();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}

}
