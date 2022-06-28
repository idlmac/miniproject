package com.yedam.app.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yedam.app.board.Board;
import com.yedam.app.board.abDAO;
import com.yedam.app.board.fbDAO;
import com.yedam.app.board.nbDAO;
import com.yedam.app.content.ContentManagement;
import com.yedam.app.members.Member;

public class MainManagement {
	private Scanner sc = new Scanner(System.in);
	private Member loginInfo = null;

	public MainManagement(Member loginInfo) {
		this.loginInfo = loginInfo;
		while (true) {
			menuPrint();

			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 공지사항 게시판
				showNotice();
			} else if (menuNo == 2) {
				// 자유게시판
				showFree();
			} else if (menuNo == 3) {
				// 익명게시판
				showAnony();
			} else if (menuNo == 9) {
				back();
				break;
			} else {
				error();
			}
		}
	}

	public void menuPrint() {
		System.out.println("  ======================================================");
		System.out.println(" | 1. 공지사항게시판 | 2. 자유게시판 | 3. 익명게시판 | 9. 로그아웃 |");
		System.out.println("  ======================================================");
		System.out.print("메뉴선택> ");
	}

	public int menuSelect() {
		int menu = -1;
		try {
			menu = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("다시 선택해주세요.");
		}
		return menu;
	}

	private void showNotice() {
		nbDAO nDAO = nbDAO.getInstance();
		List<Board> list = new ArrayList<>();
		list = nDAO.noticePrint();
		for (Board show : list) {
			System.out.println(show);

		}
		new ContentManagement(loginInfo, nDAO);
	}

	private void showFree() {
		fbDAO fDAO = fbDAO.getInstance();
		List<Board> list = new ArrayList<>();
		list = fDAO.freePrint();
		for (Board show : list) {
			System.out.println(show);
		}
		new ContentManagement(loginInfo, fDAO);
	}

	private void showAnony() {
		abDAO aDAO = abDAO.getInstance();
		List<Board> list = new ArrayList<>();
		list = aDAO.anonyPrint();
		for (Board show : list) {
			System.out.println(show);
		}
		new ContentManagement(loginInfo, aDAO);
	}

	private void back() {
		System.out.println("뒤로갑니다.");
	}

	private void error() {
		System.out.println("다시 선택해주십시오.");
	}

}