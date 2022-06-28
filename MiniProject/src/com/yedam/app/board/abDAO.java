package com.yedam.app.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;
import com.yedam.app.content.Content;

public class abDAO extends DAO {

	private static abDAO abDAO = null;

	private abDAO() {
	}

	public static abDAO getInstance() {
		if (abDAO == null) {
			abDAO = new abDAO();
		}
		return abDAO;
	}

	// 글 출력

	public List<Board> anonyPrint() {

		List<Board> list = new ArrayList<>();
		try {
			connect();

			String sql = "SELECT * FROM anony_board ORDER BY board_id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Board board = new Board();
				board.setBoardId(rs.getInt("board_id"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setMemberId(rs.getString("member_id"));
				board.setBoardDate(rs.getString("aboard_date"));

				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 생성
	public void insert(Board board) {
		try {
			connect();

			String sql = "INSERT INTO anony_board (board_id, member_id, board_title) VALUES(aboard_id_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getMemberId());
			pstmt.setString(2, board.getBoardTitle());

			int result = pstmt.executeUpdate();
			if (result > 0) {
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
	public void update(Board board) {
//		Board board = new Board();
		try {

			connect();
			String sql = "UPDATE anony_board SET board_title = ? WHERE board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setInt(2, board.getBoardId());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("변경이 완료되었습니다.");
			} else {
				System.out.println("변경 실패하였습니다.");
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

			String sql = "DELETE FROM anony_board WHERE board_id=" + boardId;
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
				board.setBoardDate(rs.getString("aboard_date"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}

	public int selectOne() {
		int boardid = 0;
		try {
			connect();

			String sql = "SELECT MAX(board_id) FROM anony_board";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				boardid = rs.getInt("MAX(board_id)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return boardid;
	}

}
