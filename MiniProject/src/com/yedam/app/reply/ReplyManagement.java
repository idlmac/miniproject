package com.yedam.app.reply;

import java.util.Scanner;

import com.yedam.app.board.Board;
import com.yedam.app.board.abDAO;
import com.yedam.app.board.fbDAO;
import com.yedam.app.board.nbDAO;
import com.yedam.app.common.DAO;
import com.yedam.app.content.Content;
import com.yedam.app.members.Member;

public class ReplyManagement {
	Scanner sc = new Scanner(System.in);
	private Member loginInfo = null;
	private arDAO arpdao = null;
	private frDAO frpdao = null;
	private nrDAO nrpdao = null;
	private abDAO aDAO = null;
	private nbDAO nDAO = null;
	private fbDAO fDAO = null;

	public ReplyManagement(Member loginInfo, DAO dao, Board board) {
		this.loginInfo = loginInfo;
		check(dao);
		while (true) {
			menuPrint();

			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 댓글달기
				writerp(board);
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
		System.out.println(" ========================================================");
		System.out.println("| 1. 댓글달기 | 2. 글 수정 | 3. 댓글 수정 | 4. 댓글 삭제| 0. 뒤로가기 |");
		System.out.println(" ========================================================");
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

	private void writerp(Board board) {
		// 댓글내용, 작성자, 게시판ID
		Reply rp = inputWrite(board);
		// DB저장
		if (arpdao != null) {
			arDAO.getInstance().insert(rp);
		} else if (nrpdao != null) {
			nrDAO.getInstance().insert(rp);
		} else if (frpdao != null) {
			frDAO.getInstance().insert(rp);
		}

	}

	private Reply inputWrite(Board board) {
		Board bd = board;
		Reply rp = new Reply();
		System.out.print("댓글내용> ");
		rp.setContent(sc.nextLine());
		rp.setMemberId(loginInfo.getMemberId());
		rp.setBoardId(bd.getBoardId());
		return rp;

	}

	private void updatecont() {
		Content ct = null;
		int bid = inputId();
		String cont = inputcont();
		if (arpdao != null) {
			rp = arpdao.selectOne(rpId);
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				arDAO.getInstance().delete(rpId);
			}
		} else if (nrpdao != null) {
			rp = nrpdao.selectOne(rpId);
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				nrDAO.getInstance().delete(rpId);
			}
		} else if (frpdao != null) {
			rp = frpdao.selectOne(rpId);
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				frDAO.getInstance().delete(rpId);
			}
		}

	}

	private void updaterp() {
		Reply rp = new Reply();
		int rpId = inputId();
		String str = inputcont();
		rp.setRpId(rpId);
		rp.setContent(str);

		if (arpdao != null) {
			rp.setMemberId(arDAO.getInstance().selectOne(rpId).getMemberId());
			if (rp.getMemberId() == null) {
				System.out.println("없는 rp입니다.");
				return;
			}
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				arDAO.getInstance().update(rp);
			}
		} else if (nrpdao != null) {
			rp.setMemberId(nrDAO.getInstance().selectOne(rpId).getMemberId());
			if (rp.getMemberId() == null) {
				System.out.println("없는 rp입니다.");
				return;
			}
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				nrDAO.getInstance().update(rp);
			}
		} else if (frpdao != null) {
			rp.setMemberId(frDAO.getInstance().selectOne(rpId).getMemberId());
			if (rp.getMemberId() == null) {
				System.out.println("없는 rp입니다.");
				return;
			}
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				frDAO.getInstance().update(rp);
			}
		}

	}

	private String inputcont() {
		System.out.print("변경할 내용>");
		return sc.nextLine();
	}

	private void deleterp() {
		Board board = null;
		Reply rp = null;
		int rpId = inputId();
		rp = frpdao.selectOne(rpId);
		if (arpdao != null) {
			rp = arpdao.selectOne(rpId);
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				arDAO.getInstance().delete(rpId);
			}
		} else if (nrpdao != null) {
			rp = nrpdao.selectOne(rpId);
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				nrDAO.getInstance().delete(rpId);
			}
		} else if (frpdao != null) {
			rp = frpdao.selectOne(rpId);
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				frDAO.getInstance().delete(rpId);
			}
		}

	}

	private int inputId() {
		System.out.print("댓글번호>");
		return Integer.parseInt(sc.nextLine());
	}

	public void back() {
		System.out.println("뒤로 이동합니다.");
	}

	private void error() {
		System.out.println("다시 선택해주십시오.");
	}

	public void check(DAO dao) {
		if (dao instanceof abDAO) {
			arpdao = arDAO.getInstance();
		}
		if (dao instanceof nbDAO) {
			nrpdao = nrDAO.getInstance();
		}
		if (dao instanceof fbDAO) {
			frpdao = frDAO.getInstance();
		}
	}
}
