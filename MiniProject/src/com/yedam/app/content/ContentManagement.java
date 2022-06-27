package com.yedam.app.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yedam.app.board.Board;
import com.yedam.app.board.abDAO;
import com.yedam.app.board.fbDAO;
import com.yedam.app.board.nbDAO;
import com.yedam.app.common.DAO;
import com.yedam.app.members.Member;
import com.yedam.app.reply.Reply;
import com.yedam.app.reply.ReplyManagement;
import com.yedam.app.reply.arDAO;
import com.yedam.app.reply.frDAO;
import com.yedam.app.reply.nrDAO;

public class ContentManagement {
	Scanner sc = new Scanner(System.in);
	private Member loginInfo = null;
	private abDAO aDAO = null;
	private nbDAO nDAO = null;
	private fbDAO fDAO = null;

	public ContentManagement(Member loginInfo, DAO dao) {
		this.loginInfo = loginInfo;
		check(dao);
		while (true) {

			menuPrint();

			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 글 등록
				write();
			} else if (menuNo == 2) {
				// 삭제
				delete();
			} else if (menuNo == 3) {
				// 조회
//				selectOne();
				new ReplyManagement(loginInfo, dao, selectOne());
			} else if (menuNo == 0) {
				back();
				break;
			} else {
				error();
			}
		}
	}

	public void menuPrint() {
		System.out.println(" ===========================================");
		System.out.println("| 1. 글 등록 | 2. 글 삭제 | 3. 글 조회 | 0. 뒤로가기 |");
		System.out.println(" ===========================================");
		System.out.print("선택>>");
	}

	public int menuSelect() {
		int menu = 0;
		try {
			menu = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("다시 선택해주세요.");
		}
		return menu;
	}

	private void write() {
		// 제목, 내용, 작성자
		Board board = inputWrite();
		Content content = content();
		// DB저장
		if (aDAO != null) {
			board.setMemberId("anony");
			aDAO.insert(board);
			content.setBoardId(aDAO.selectOne());
			acDAO.getInstance().insert(content);
		} else if (nDAO != null) {
			nDAO.insert(board);
			content.setBoardId(nDAO.selectOne());
			ncDAO.getInstance().insert(content);
		} else if (fDAO != null) {
			fDAO.insert(board);
			content.setBoardId(fDAO.selectOne());
			fcDAO.getInstance().insert(content);
		}
	}

	private Board inputWrite() {
		Board board = new Board();

		System.out.print("제목>>");
		board.setBoardTitle(sc.nextLine());
		board.setMemberId(loginInfo.getMemberId());
		return board;
	}

	private void delete() {
		// 글번호입력
		Board board = null;
		int boardId = inputId();

		// 작성자 확인
		if (aDAO != null) {
			board = aDAO.selectOne(boardId);
			if (board.getMemberId().equals(loginInfo.getMemberId())) {
				acDAO.getInstance().delete(boardId);
				aDAO.delete(boardId);
			} else {
				System.out.println("계정이 일치하지 않습니다.");
			}
		} else if (nDAO != null) {
			board = nDAO.selectOne(boardId);
			if (board.getMemberId().equals(loginInfo.getMemberId())) {
				ncDAO.getInstance().delete(boardId);
				nDAO.delete(boardId);
			} else {
				System.out.println("계정이 일치하지 않습니다.");
			}

		} else if (fDAO != null) {
			board = fDAO.selectOne(boardId);
			if (board.getMemberId().equals(loginInfo.getMemberId())) {
				fcDAO.getInstance().delete(boardId);
				fDAO.delete(boardId);
			} else {
				System.out.println("계정이 일치하지 않습니다.");
			}
		}
	}

	private Board selectOne() {
		Board board = null;
		Content content = null;
		int boardId = inputId();
		List<Reply> list1 = new ArrayList<>();

		if (aDAO != null) {
			board = aDAO.selectOne(boardId);
			content = acDAO.getInstance().selectOne(boardId);
			List<Reply> list = arDAO.getInstance().selectAll(boardId);
			list1 = list;
		} else if (nDAO != null) {
			board = nDAO.selectOne(boardId);
			content = ncDAO.getInstance().selectOne(boardId);
			List<Reply> list = nrDAO.getInstance().selectAll(boardId);
			list1 = list;
		} else if (fDAO != null) {
			board = fDAO.selectOne(boardId);
			content = fcDAO.getInstance().selectOne(boardId);
			List<Reply> list = frDAO.getInstance().selectAll(boardId);
			list1 = list;
		}
		System.out.println(board);
		System.out.println(content);
		System.out.println("\n------------------------------댓글------------------------------\n");

		for (Reply show : list1) {
			System.out.println(show);
		}
		return board;
	}

	private int inputId() {
		System.out.print("글번호>");
		return Integer.parseInt(sc.nextLine());
	}

	private Content content() {
		Content content = new Content();
		System.out.print("내용 : ");
		content.setContent(sc.nextLine());

		return content;
	}

	public void back() {
		System.out.println("뒤로 이동합니다.");
	}

	private void error() {
		System.out.println("다시 선택해주십시오.");
	}

	public void check(DAO dao) {
		if (dao instanceof abDAO) {
			aDAO = (abDAO) dao;
		}
		if (dao instanceof nbDAO) {
			nDAO = (nbDAO) dao;
		}
		if (dao instanceof fbDAO) {
			fDAO = (fbDAO) dao;
		}
	}
}
