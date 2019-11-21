package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Member;

public interface MemberService {

	Member getLoginMember(HttpServletRequest req);
	
	boolean login(Member member);
	
	Member getMemberByUserid(Member member);

	void join(Member member);

}
