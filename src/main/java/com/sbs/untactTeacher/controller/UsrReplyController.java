package com.sbs.untactTeacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untactTeacher.dto.Article;
import com.sbs.untactTeacher.dto.Reply;
import com.sbs.untactTeacher.dto.ResultData;
import com.sbs.untactTeacher.service.ArticleService;
import com.sbs.untactTeacher.service.ReplyService;

@Controller
public class UsrReplyController {
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/reply/list")
	@ResponseBody
	public ResultData showList(String relTypeCode, Integer relId) {
		
		if ( relId == null ) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		if ( relTypeCode.equals("article") ) {
			Article article = articleService.getArticle(relId);
			
			if ( article == null ) {
				return new ResultData("F-1", "존재하지 않는 게시물 입니다.");
			}
		}

		List<Reply> replies = replyService.getForPrintReplies(relTypeCode, relId);

		return new ResultData("S-1", "성공", "replies", replies);
	}
}
