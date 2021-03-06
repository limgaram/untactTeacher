package com.sbs.untactTeacher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.sbs.untactTeacher.dto.Article;
import com.sbs.untactTeacher.dto.Board;
import com.sbs.untactTeacher.dto.GenFile;
import com.sbs.untactTeacher.dto.Member;
import com.sbs.untactTeacher.dto.ResultData;
import com.sbs.untactTeacher.service.BoardService;
import com.sbs.untactTeacher.util.Util;

@Controller
public class AdmBoardController extends BaseController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/adm/board/detail")
	@ResponseBody
	public ResultData showDetail(Integer id) {
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		Board board = boardService.getForPrintBoard(id);

		if (board == null) {
			return new ResultData("F-2", "존재하지 않는 게시물번호 입니다.");
		}

		return new ResultData("S-1", "성공", "board", board);
	}

	@RequestMapping("/adm/board/list")
	public String showList(HttpServletRequest req) {

		List<Board> boards = boardService.getForPrintBoards();

		req.setAttribute("boards", boards);

		return "adm/board/list";
	}

	@RequestMapping("/adm/board/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id, HttpServletRequest req) {

		Member loginedMember = (Member) req.getAttribute("loginedMemeber");

		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		Board board = boardService.getBoard(id);

		if (board == null) {
			return new ResultData("F-2", "해당 게시판은 존재하지 않습니다.");
		}

		ResultData actorCanDeleteRd = boardService.getActorCanDeleteRd(board, loginedMember);

		if (actorCanDeleteRd.isFail()) {
			return actorCanDeleteRd;
		}

		return boardService.deleteBoard(id);
	}

	@RequestMapping("/adm/board/modify")
	public String showModify(Integer id, HttpServletRequest req) {
		if (id == null) {
			return msgAndBack(req, "id를 입력해주세요.");
		}

		Board board = boardService.getForPrintBoard(id);

		if (board == null) {
			return msgAndBack(req, "존재하지 않는 게시판번호 입니다.");
		}

		return "adm/board/modify";
	}

	@RequestMapping("/adm/board/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		int id = Util.getAsInt(param.get("id"), 0);

		if (id == 0) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		if (Util.isEmpty(param.get("title"))) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}

		if (Util.isEmpty(param.get("body"))) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}

		Board board = boardService.getBoard(id);

		if (board == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}

		ResultData actorCanModifyRd = boardService.getActorCanModifyRd(board, loginedMember);

		if (actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}

		return boardService.modifyBoard(param);
	}
}