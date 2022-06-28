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
				selectOne();
			} else if (menuNo == 4) {
				// 제목 수정
				updateTitle();
			} else if (menuNo == 9) {
				back();
				break;
			} else {
				error();
			}
		}
	}

	public void menuPrint() {
		System.out.println(" =========================================================");
		System.out.println("| 1. 글 등록 | 2. 글 삭제 | 3. 글 조회 | 4. 제목수정 | 9. 뒤로가기 |");
		System.out.println(" =========================================================");
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
			board.setMemberId("super");
			if (loginInfo.getRole() == 1) {
				nDAO.insert(board);
				content.setBoardId(nDAO.selectOne());
				ncDAO.getInstance().insert(content);
			} else {
				System.out.println("작성 권한이 없습니다.");
			}
		} else if (fDAO != null) {
			board.setMemberId(loginInfo.getMemberId());
			fDAO.insert(board);
			content.setBoardId(fDAO.selectOne());
			fcDAO.getInstance().insert(content);
		}
	}

	private Board inputWrite() {
		Board board = new Board();

		System.out.print("제목>>");
		board.setBoardTitle(sc.nextLine());
		Member loginInfo = new Member();
		loginInfo.setMemberId("anony");
		board.setMemberId(loginInfo.getMemberId());
		return board;
	}

	// 삭제
	private void delete() {
		// 글번호입력
		Board board = null;
		int boardId = inputId();

		// 작성자 확인
		if (aDAO != null) {
			board = aDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("작성된 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					acDAO.getInstance().delete(boardId);
					aDAO.delete(boardId);
				} else {
					System.out.println("계정이 일치하지 않습니다.");
				}
			}
		} else if (nDAO != null) {
			board = nDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("작성된 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					ncDAO.getInstance().delete(boardId);
					nDAO.delete(boardId);
				} else {
					System.out.println("삭제 권한이 없습니다.");
				}
			}

		} else if (fDAO != null) {
			board = fDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("작성된 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					fcDAO.getInstance().delete(boardId);
					fDAO.delete(boardId);
				} else {
					System.out.println("계정이 일치하지 않습니다.");
				}
			}
		}
	}

	private void selectOne() {
		Board board = new Board();
		Content content = new Content();
		int boardId = inputId();

		if (aDAO != null) {
			board = aDAO.selectOne(boardId);
			content = acDAO.getInstance().selectOne(boardId);
			List<Reply> list = arDAO.getInstance().selectAll(boardId);
			printAll(nDAO, board, content, list);
		} else if (nDAO != null) {
			board = nDAO.selectOne(boardId);
			content = ncDAO.getInstance().selectOne(boardId);
			List<Reply> list = nrDAO.getInstance().selectAll(boardId);
			printAll(nDAO, board, content, list);
		} else if (fDAO != null) {
			board = fDAO.selectOne(boardId);
			content = fcDAO.getInstance().selectOne(boardId);
			List<Reply> list = frDAO.getInstance().selectAll(boardId);
			printAll(fDAO, board, content, list);
		}
	}

	private void printAll(DAO dao, Board board, Content content, List<Reply> list) {
		if (board == null) {
			System.out.println("글이 존재하지 않습니다.");
			return;
		}
		System.out.println(board);
		System.out.println(content);
		System.out.println("\n------------------------------댓글------------------------------\n");
		list.forEach(x -> System.out.println(x));
		new ReplyManagement(loginInfo, dao, board);
	}

	private int inputId() {
		int num = -1;
		try {
			System.out.print("글번호>");
			num = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("다시 입력해주세요.");
		}
		return num;
	}

	private Content content() {
		Content content = new Content();
		System.out.print("내용 : ");
		content.setContent(sc.nextLine());

		return content;
	}

	private void updateTitle() {
		Board board1 = new Board(); // 변경할 내용 담긴 게시판
		Board board = new Board(); // 로그인 정보랑 비교할 게시판
		int boardId = inputId();
		board1.setBoardTitle(changeTitle());
		board1.setBoardId(boardId);
		if (aDAO != null) {
			board = aDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("작성된 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					abDAO.getInstance().update(board1);
				} else {
					System.out.println("계정이 일치하지 않습니다.");
				}
			}
		} else if (nDAO != null) {
			board = nDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("작성된 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					nbDAO.getInstance().update(board1);
				} else {
					System.out.println("계정이 일치하지 않습니다.");
				}
			}
		} else if (fDAO != null) {
			board = fDAO.selectOne(boardId);
			if (board == null) {
				System.out.println("작성된 글이 없습니다.");
			} else {
				if (board.getMemberId().equals(loginInfo.getMemberId()) || loginInfo.getRole() == 1) {
					fbDAO.getInstance().update(board1);
				} else {
					System.out.println("계정이 일치하지 않습니다.");
				}
			}
		}
	}

	private String changeTitle() {
		System.out.print("변경할 제목>");
		return sc.nextLine();
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
