package com.yedam.app.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;

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
	public List<Board> print() {
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
				board.setBoardDate(rs.getDate("board_date"));

				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	
}
