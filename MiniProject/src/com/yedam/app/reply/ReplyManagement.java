package com.yedam.app.reply;

import java.util.Scanner;

import com.yedam.app.common.DAO;
import com.yedam.app.members.Member;

public class ReplyManagement {
	Scanner sc = new Scanner(System.in);
	private Member loginInfo = null;

	public ReplyManagement(Member loginInfo, DAO dao) {
		this.loginInfo = loginInfo;
		while (true) {
			menuPrint();

			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 댓글달기
				writerp();
			} else if (menuNo == 2) {
				// 글 수정
				updatecont();
			} else if (menuNo == 3) {
				// 댓글수정
				updaterp();
			} else if (menuNo == 4) {
				// 댓글삭제
				deleterp();
			} else if (menuNo == 0) {
				// 뒤로가기
				back();
				break;
			} else {
				error();
			}
		}
	}

	public void menuPrint() {
		System.out.println(" =============================================");
		System.out.println("| 1. 댓글달기 | 2. 글 수정 | 3. 댓글 수정 | 0. 뒤로가기 |");
		System.out.println(" =============================================");
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

	private void writerp() {

	}

	private void updatecont() {

	}

	private void updaterp() {

	}

	private void deleterp() {

	}

	public void back() {
		System.out.println("뒤로 이동합니다.");
	}

	private void error() {
		System.out.println("다시 선택해주십시오.");
	}
}
