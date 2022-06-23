package com.yedam.app.board;

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
	
}
