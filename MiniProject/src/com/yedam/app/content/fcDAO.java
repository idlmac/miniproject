package com.yedam.app.content;

import java.sql.SQLException;

import com.yedam.app.common.DAO;

public class fcDAO extends DAO {

	private static fcDAO fcDAO = null;

	private fcDAO() {
	}

	public static fcDAO getInstance() {
		if (fcDAO == null) {
			fcDAO = new fcDAO();
		}
		return fcDAO;
	}

	// 생성
	public void insert(Content content) {
		try {
			connect();

			String sql = "INSERT INTO free_content VALUES(fcont_id_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content.getContent());
			pstmt.setInt(2, content.getBoardId());

			int result = pstmt.executeUpdate();
			if (result > 0) {
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 수정
	public void update(Content content) {
		try {
			connect();
			String sql = "UPDATE free_content SET content = ? WHERE board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content.getContent());
			pstmt.setInt(2, content.getBoardId());

			int result = pstmt.executeUpdate();
			if (result > 0) {
			} else {
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 삭제
	public void delete(int boardId) {
		try {
			connect();

			String sql = "DELETE FROM free_content WHERE board_id=" + boardId;
			stmt = conn.createStatement();

			int result = stmt.executeUpdate(sql);

			if (result > 0) {
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 단건조회
	public Content selectOne(int boardId) {
		Content content = null;
		try {
			connect();

			String sql = "SELECT * FROM free_content WHERE board_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				content = new Content();
				content.setContentId(rs.getInt("content_id"));
				content.setContent(rs.getString("content"));
				content.setBoardId(boardId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return content;
	}
}
