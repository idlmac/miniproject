package com.yedam.app.content;

import java.sql.SQLException;

import com.yedam.app.board.Board;
import com.yedam.app.common.DAO;

public class acDAO extends DAO {
	private static acDAO acDAO = null;

	private acDAO() {
	}

	public static acDAO getInstance() {
		if (acDAO == null) {
			acDAO = new acDAO();
		}
		return acDAO;
	}

	// 생성
	public void insert(Board board) {
		try {
			connect();

			String sql = "INSERT INTO anony_board (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardId());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getMemberId());
			pstmt.setDate(4, board.getBoardDate());

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
	public void update() {
		Board board = new Board();
		Content content = new Content();
		try {
			connect();
			String sql = "UPDATE anony_board SET board_title = ?, content = ? WHERE board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, content.getContent());
			pstmt.setInt(3, board.getBoardId());

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

			String sql = "DELETE FROM anony_board WHERE board_id" + boardId;
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
	public Board selectOne(int boardId) {
		Board board = null;
		try {
			connect();

			String sql = "SELECT * FROM anony_board WHERE board_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setBoardId(rs.getInt("board_id"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setMemberId(rs.getString("member_id"));
				board.setBoardDate(rs.getDate("board_date"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}

}
