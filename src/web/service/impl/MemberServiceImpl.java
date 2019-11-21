package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.MemberDao;
import web.dao.impl.MemberDaoImpl;
import web.dto.Member;
import web.service.face.MemberService;

public class MemberServiceImpl implements MemberService {
	
	MemberDao memberDao = new MemberDaoImpl();

	@Override
	public Member getLoginMember(HttpServletRequest req) {

		String id = req.getParameter("userid");
		String pw = req.getParameter("userpw");
		
		Member member = new Member();
		
		member.setUserid(id);
		member.setUserpw(pw);
		
		return member;
	}

	@Override
	public boolean login(Member member) {

		if(memberDao.selectCntMemberByUserid(member) == 1) {
			
			return true;
		}
		
		return false;
	}

	@Override
	public Member getMemberByUserid(Member member) {
		
		Member mem = memberDao.selectMemberByUserid(member);
		
		return mem;
	}

	@Override
	public void join(Member member) {
		
		memberDao.insert(member);
		
	}

}
