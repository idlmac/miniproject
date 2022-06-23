package com.yedam.app.common;

import java.util.List;
import java.util.Scanner;

import com.yedam.app.members.Member;
import com.yedam.app.members.MembersDAO;

public class LoginControl {
	private static Member loginInfo = null;
	Scanner sc = new Scanner(System.in);
	MembersDAO mDAO = MembersDAO.getInstance();
	static boolean loginflag = false;

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
			} else if (menuNo == 0) {
				// 종료
				exit();
				break;
			} else {
				error();
			}
		}
	}

	private void menuPrint() {
		System.out.println(" =============================");
		System.out.println("| 1. 회원가입 | 2. 로그인 | 3. 종료 |");
		System.out.println(" =============================");
		System.out.print("메뉴 선택>> ");
	}

	private int menuSelect() {
		int menuNo = 0;
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

		// 성공할 경우 프로그램 실행

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
