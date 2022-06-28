package com.yedam.app.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yedam.app.board.Board;
import com.yedam.app.board.abDAO;
import com.yedam.app.content.ContentManagement;
import com.yedam.app.content.acDAO;
import com.yedam.app.content.fcDAO;
import com.yedam.app.content.ncDAO;
import com.yedam.app.members.Member;
import com.yedam.app.members.MembersDAO;

public class LoginControl {
	Scanner sc = new Scanner(System.in);
	MembersDAO mDAO = MembersDAO.getInstance();
	protected acDAO aDAO = acDAO.getInstance();
	protected fcDAO fDAO = fcDAO.getInstance();
	protected ncDAO nDAO = ncDAO.getInstance();
	private static Member loginInfo = null;

	public static Member getLoginInfo() {
		return loginInfo;
	}

	public LoginControl() {
		while (true) {
			menuPrint();

			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 회원가입
				join();
			} else if (menuNo == 2) {
				// 로그인
				login();
			} else if (menuNo == 3) {
				// 익명 게시판
				anonyBoard();
			} else if (menuNo == 9) {
				// 종료
				exit();
				break;
			} else {
				error();
			}
		}
	}

	private void menuPrint() {
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■■■■■■■■엉스티즈에 오신걸 환영합니다람쥐■■■■■■■■■■■■");
		System.out.println("■■■■■■■■■■■■■■■■■■(〃･ิ‿･ิ)ゞ■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■■■■■■□■■■■■■■■■■■■■■■■■■■■■■■■■■■■□■■■■■■■■■");
		System.out.println("■■■■■■■■■□■■■■■■■■■■■■■■■■■■■■■■■■□■■■■■■■■■■■");
		System.out.println("■■■■■■■■■■■□■■■■■■■■■■■■■■■■■■■■□■■■■■■■■■■■■■");
		System.out.println("■■■■■■■■■■■■■□■■■■■■■■■■■■■■■■□■■■■■■■■■■■■■■■");
		System.out.println("■■■■■■■■■■■■■■■□□□□□□□□□□□□□□■■■■■■■■■■■■■■■■■");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println(" ============================================");
		System.out.println("| 1. 회원가입 | 2. 로그인 | 3. 익명게시판 | 9. 종료 |");
		System.out.println(" ============================================");
		System.out.print("메뉴 선택>> ");
	}

	private int menuSelect() {
		int menuNo = -1;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자 형식으로 입력해주세요.");
		}
		return menuNo;
	}

	private void join() {
		System.out.print("ID>");
		String id = sc.nextLine();
		List<String> list = mDAO.allId();
		for (String x : list) {
			if (x.equals(id)) {
				System.out.println("이미 존재하는 ID입니다.");
				return;
			}
		}
		System.out.print("PW>");
		String pw = sc.nextLine();
		Member member = new Member();
		member.setMemberId(id);
		member.setMemberPw(pw);
		mDAO.insert(member);
		System.out.println("회원가입 성공 !");
	}

	private void login() {
		// 아이디와 비밀번호 입력
		Member inputInfo = inputMember();
		// 로그인 시도
		loginInfo = MembersDAO.getInstance().selectOne(inputInfo);
		// 실패할 경우 메뉴로 BACK
		if (loginInfo == null)
			return;

		new MainManagement(loginInfo);
	}

	private void anonyBoard() {
		abDAO aDAO = abDAO.getInstance();
		List<Board> list = new ArrayList<>();
		list = aDAO.anonyPrint();
		for (Board show : list) {
			System.out.println(show);

		}
		Member inputInfo = new Member();
		inputInfo.setMemberId("anony");
		inputInfo.setMemberPw("anony");
		new ContentManagement(inputInfo, aDAO);
	}

	private Member inputMember() {
		Member info = new Member();
		System.out.print("ID> ");
		info.setMemberId(sc.nextLine());
		System.out.print("PW> ");
		info.setMemberPw(sc.nextLine());
		return info;
	}

	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	private void error() {
		System.out.println("다시 입력해주세요.");
	}

}
