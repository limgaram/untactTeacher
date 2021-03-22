package com.sbs.untactTeacher.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untactTeacher.dao.BoardDao;
import com.sbs.untactTeacher.dto.Article;
import com.sbs.untactTeacher.dto.Board;
import com.sbs.untactTeacher.dto.Member;
import com.sbs.untactTeacher.dto.ResultData;
import com.sbs.untactTeacher.util.Util;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private MemberService memberService;

	public Board getForPrintBoard(int id) {

		return boardDao.getForPrintBoard(id);
	}

	public List<Board> getForPrintBoards() {

		return boardDao.getForPrintBoards();
	}

	public Board getBoard(int id) {

		return boardDao.getBoard(id);
	}

	public ResultData deleteBoard(int id) {
		boardDao.deleteBoard(id);

		return new ResultData("S-1", "삭제하였습니다.", "id", id);
	}

	public ResultData modifyBoard(Map<String, Object> param) {
		boardDao.modifyBoard(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "게시판을 수정하였습니다.", "id", id);
	}

	public ResultData getActorCanModifyRd(Board board, Member actor) {
		if (board.getMemberId() == actor.getId()) {
			return new ResultData("S-1", "가능합니다.");
		}

		if (memberService.isAdmin(actor)) {
			return new ResultData("S-2", "가능합니다.");
		}

		return new ResultData("F-1", "권한이 없습니다.");
	}

	public ResultData getActorCanDeleteRd(Board board, Member actor) {
		return getActorCanModifyRd(board, actor);
	}

}
