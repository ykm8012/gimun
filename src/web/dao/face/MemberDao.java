package web.dao.face;

import web.dto.Member;

public interface MemberDao {

	int selectCntMemberByUserid(Member member);
	
	Member selectMemberByUserid(Member member);

	void insert(Member member);
}
