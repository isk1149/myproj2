package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import sql.exception.SelectQueryNoResultException;

public class MemberService {

	private MemberDao memberDao = new MemberDao();
	
	public Member searchMember(String id) {
		
		Member member = null;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			member = memberDao.selectById(conn, id);
			if (member == null)
				throw new SelectQueryNoResultException();
			return member;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
