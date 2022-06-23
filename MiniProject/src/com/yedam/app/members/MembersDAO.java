package com.yedam.app.members;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;

public class MembersDAO extends DAO {
	// 싱글톤
	private static MembersDAO dao = null;

	private MembersDAO() {
	}

	public static MembersDAO getInstance() {
		if (dao == null) {
			dao = new MembersDAO();
		}
		return dao;
	}

	// 회원가입
	public void insert(Member member) {
		try {
			connect();

			String sql = "INSERT INTO members (member_id, member_pw) VALUES (?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원가입되었습니다.");
			} else {
				System.out.println("이미 존재하는 ID입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// ID조회
	public List<String> allId() {

		List<String> list = new ArrayList<String>();

		try {
			connect();

			String sql = "SELECT member_id FROM members ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getString("member_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// CRUD
	public Member selectOne(Member member) {
		Member loginInfo = null;
		try {
			connect();

			String sql = "SELECT * FROM members WHERE member_id= '" + member.getMemberId() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				// 아이디 존재
				if (rs.getString("member_pw").equals(member.getMemberPw())) {
					// 비밀번호 일치
					// -> 로그인 성공
					loginInfo = new Member();
					loginInfo.setMemberId(rs.getString("member_id"));
					loginInfo.setMemberPw(rs.getString("member_pw"));
					loginInfo.setRole(rs.getInt("role"));
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("비밀번호 오류");
				member = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return loginInfo;
	}
}
