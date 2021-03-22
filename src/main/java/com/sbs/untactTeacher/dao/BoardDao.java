package com.sbs.untactTeacher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untactTeacher.dto.Board;

@Mapper
public interface BoardDao {

	Board getForPrintBoard(@Param("id") int id);

	List<Board> getForPrintBoards();

	Board getBoard(@Param("id") int id);

	void deleteBoard(@Param("id") int id);

	void modifyBoard(Map<String, Object> param);

}
