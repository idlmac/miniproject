package com.yedam.app.content;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.board.Board;
import com.yedam.app.common.DAO;

public class ncDAO extends DAO {
	private static ncDAO ncDAO = null;

	private ncDAO() {
	}

	public static ncDAO getInstance() {
		if (ncDAO == null) {
			ncDAO = new ncDAO();
		}
		return ncDAO;
	}

	// 생성
	public void insert(Content content) {
		try {
			connect();

			String sql = "INSERT INTO notice_content VALUES(acont_id_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content.getContent());
			pstmt.setInt(2, content.getBoardId());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("글이 등록되었습니다.");
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
	public void update(Content content) {
		try {
			connect();
			String sql = "UPDATE notice_content SET content = ? WHERE board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content.getContent());
			pstmt.setInt(2, content.getBoardId());

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

	// 삭제
	public void delete(int boardId) {
		try {
			connect();

			String sql = "DELETE FROM notice_content WHERE board_id =" + boardId;
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

	// 단건조회
	public Content selectOne(int boardId) {
		Content content = null;
		try {
			connect();

			String sql = "SELECT * FROM notice_content WHERE board_id =?";

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
