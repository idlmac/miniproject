package com.yedam.app.reply;

import java.util.Scanner;

import com.yedam.app.board.Board;
import com.yedam.app.board.abDAO;
import com.yedam.app.board.fbDAO;
import com.yedam.app.board.nbDAO;
import com.yedam.app.common.DAO;
import com.yedam.app.content.Content;
import com.yedam.app.content.acDAO;
import com.yedam.app.content.fcDAO;
import com.yedam.app.content.ncDAO;
import com.yedam.app.members.Member;

public class ReplyManagement {
	Scanner sc = new Scanner(System.in);
	private Member loginInfo = null;
	private arDAO arpdao = null;
	private frDAO frpdao = null;
	private nrDAO nrpdao = null;
	private abDAO aDAO = abDAO.getInstance();
	private nbDAO nDAO = nbDAO.getInstance();
	private fbDAO fDAO = fbDAO.getInstance();

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
			} else if (menuNo == 9) {
				// 뒤로가기
				back();
				break;
			} else {
				error();
			}
		}
	}

	public void menuPrint() {
		System.out.println(" ===========================================================");
		System.out.println("| 1. 댓글달기 | 2. 글 수정 | 3. 댓글 수정 | 4. 댓글 삭제| 9. 뒤로가기 |");
		System.out.println(" ===========================================================");
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
		Board board = null;
		Content ct = new Content();
		int boardId = inputId();
		ct.setBoardId(boardId);
		ct.setContent(inputCont());
		if (arpdao != null) {
			board = aDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("해당 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId())) {
					acDAO.getInstance().update(ct);
				} else {
					System.out.println("수정권한이 없습니다.");
				}
			}
		} else if (nrpdao != null) {
			board = nDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("해당 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId()) && loginInfo.getRole() == 1) {
					ncDAO.getInstance().update(ct);
				} else {
					System.out.println("수정권한이 없습니다.");
				}
			}
		} else if (frpdao != null) {
			board = fDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("해당 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId())) {
					fcDAO.getInstance().update(ct);

				} else {
					System.out.println("수정권한이 없습니다.");
				}
			}
		}
	}

	private void updaterp() {
		Reply rp = new Reply();
		int rpId = inputId();
		String str = inputCont();
		rp.setRpId(rpId);
		rp.setContent(str);

		if (arDAO.getInstance().selectOne(rpId) == null) {
			System.out.println("작성된 글이 아닙니다.");
		}

		if (arpdao != null) {
			rp.setMemberId(arDAO.getInstance().selectOne(rpId).getMemberId());
			if (rp.getMemberId() == null) {
				System.out.println("없는 댓글입니다.");
				return;
			}
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				arDAO.getInstance().update(rp);
			}
		} else if (nrpdao != null) {
			rp.setMemberId(nrDAO.getInstance().selectOne(rpId).getMemberId());
			if (rp.getMemberId() == null) {
				System.out.println("없는 댓글입니다.");
				return;
			}
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				nrDAO.getInstance().update(rp);
			}
		} else if (frpdao != null) {
			rp.setMemberId(frDAO.getInstance().selectOne(rpId).getMemberId());
			if (rp.getMemberId() == null) {
				System.out.println("없는 댓글입니다.");
				return;
			}
			if (rp.getMemberId().equals(loginInfo.getMemberId())) {
				frDAO.getInstance().update(rp);
			}
		}

	}

	private String inputCont() {
		System.out.print("변경할 내용>");
		return sc.nextLine();
	}

	private void deleterp() {
		Reply rp = null;
		int rpId = inputId();
		if (arpdao != null) {
			rp = arpdao.selectOne(rpId);
			if (rp == null) {
				System.out.println("해당하는 댓글이 없습니다.");
			} else {
				if (rp.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					arDAO.getInstance().delete(rpId);
				}
			}
		} else if (nrpdao != null) {
			rp = nrpdao.selectOne(rpId);
			if (rp == null) {
				System.out.println("해당하는 댓글이 없습니다.");
			} else {
				if (rp.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					nrDAO.getInstance().delete(rpId);
				}
			}
		} else if (frpdao != null) {
			rp = frpdao.selectOne(rpId);
			if (rp == null) {
				System.out.println("해당하는 댓글이 없습니다.");
			} else {
				if (rp.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					frDAO.getInstance().delete(rpId);
				}
			}
		} else {
			System.out.println("번호를 다시 입력해주세요.");
		}

	}

	private int inputId() {
		System.out.print("번호>");
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
