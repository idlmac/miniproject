package com.yedam.app.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;
import com.yedam.app.content.Content;

public class fbDAO extends DAO {
	private static fbDAO fbDAO = null;

	private fbDAO() {
	}

	public static fbDAO getInstance() {
		if (fbDAO == null) {
			fbDAO = new fbDAO();
		}
		return fbDAO;
	}

	public List<Board> freePrint() {

		List<Board> list = new ArrayList<>();
		try {
			connect();

			String sql = "SELECT * FROM free_board ORDER BY board_id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Board board = new Board();
				board.setBoardId(rs.getInt("board_id"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setMemberId(rs.getString("member_id"));
				board.setBoardDate(rs.getString("board_date"));

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

			String sql = "INSERT INTO free_board (board_id, member_id, board_title) VALUES(fboard_id_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getMemberId());
			pstmt.setString(2, board.getBoardTitle());

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
			String sql = "UPDATE free_board SET board_title = ?, content = ? WHERE board_id=?";
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

			String sql = "DELETE FROM free_board WHERE board_id=" + boardId;
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

			String sql = "SELECT * FROM free_board WHERE board_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setBoardId(rs.getInt("board_id"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setMemberId(rs.getString("member_id"));
				board.setBoardDate(rs.getString("board_date"));
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

			String sql = "SELECT MAX(board_id) FROM free_board";

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
