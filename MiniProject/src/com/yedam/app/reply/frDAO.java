package com.yedam.app.reply;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			String sql = "INSERT INTO free_reply (rp_id, member_id, content, board_id) VALUES (nrp_id_seq.nextval,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getMemberId());
			pstmt.setString(2, reply.getContent());
			pstmt.setInt(3, reply.getBoardId());

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

			String sql = "UPDATE free_reply SET content=? WHERE rp_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getRpId());
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
	public void delete(int rpId) {
		try {
			connect();

			String sql = "DELETE FROM free_reply WHERE rp_Id =" + rpId;

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

	public Reply selectOne(int rpId) {
		Reply rp = null;
		try {
			connect();

			String sql = "SELECT * FROM free_reply WHERE rp_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rpId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rp = new Reply();
				rp.setRpId(rs.getInt("rp_id"));
				rp.setMemberId(rs.getString("member_id"));
				rp.setContent(rs.getString("content"));
				rp.setRpDate(rs.getString("rp_date"));
				rp.setBoardId(rs.getInt("board_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rp;
	}

	public List<Reply> selectAll(int boardId) {
		List<Reply> list = new ArrayList<>();
		try {
			connect();

			String sql = "SELECT * FROM free_reply WHERE board_id = ? ORDER BY rp_id";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Reply rp = new Reply();
				rp.setRpId(rs.getInt("rp_id"));
				rp.setMemberId(rs.getString("member_id"));
				rp.setContent(rs.getString("content"));
				rp.setRpDate(rs.getString("rp_date"));
				rp.setBoardId(boardId);

				list.add(rp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
