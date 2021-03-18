package com.sbs.untactTeacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untactTeacher.dao.BoardDao;
import com.sbs.untactTeacher.dto.Board;
import com.sbs.untactTeacher.dto.ResultData;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

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

}
