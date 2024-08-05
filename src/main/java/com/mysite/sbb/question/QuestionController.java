package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 생성자 메서드를 만들어주소, 생성자 주입방식이 진행된다.
@Controller
public class QuestionController {

	// 기본구조는 QuestionService 를 사용해야 하지만, 현재는 직접 리포지터를 사용한다.
	private final QuestionService questionService;
	
	@GetMapping("/question/list")
//	@ResponseBody
	public String list(Model model) {
		
		List<Question> questionList = this.questionService.getList(); // 페이징기능이 없는 메서드임.
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	// 주소   /question/detail/1
	@GetMapping(value = "/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		
		Question question = this.questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_detail";
	}
	
	
	
	
	
	
	
	
}
