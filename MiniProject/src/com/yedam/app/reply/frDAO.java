package com.yedam.app.reply;

import java.sql.SQLException;

import com.yedam.app.common.DAO;

public class frDAO extends DAO {
	private static frDAO frDAO = null;

	private frDAO() {
	}

	public static frDAO getInstance() {
		if (frDAO == null) {
			frDAO = new frDAO();
		}
		return frDAO;
	}

	// 댓글작성
	public void insert(Reply reply) {
		try {
			connect();

			String sql = "INSERT INTO free_reply (rp_id, member_id, content, board_id) VALUES (?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getRpId());
			pstmt.setString(2, reply.getMemberId());
			pstmt.setString(3, reply.getContent());
			pstmt.setDate(4, reply.getRpDate());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("댓글이 등록되었습니다.");
			} else {
				System.out.println("정상등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 수정
	public void update(Reply reply) {
		try {
			connect();

			String sql = "UPDATE free_reply SET content=? WHERE board_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getBoardId());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 댓글 삭제
	public void delete(int boardId) {
		try {
			connect();

			String sql = "DELETE FROM free_reply WHERE board_id" + boardId;

			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);

			if (result > 0) {
				System.out.println("삭제 완료되었습니다.");
			} else {
				System.out.println("삭제 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

}
