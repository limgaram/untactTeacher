package com.sbs.untactTeacher.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untactTeacher.dto.Member;

@Mapper
public interface MemberDao {
	void join(Map<String, Object> param);

	Member getMember(@Param("id") int id);
	
	Member getMemberByLoginId(@Param("loginId") String loginId);

	void modifyMember(Map<String, Object> param);
}
