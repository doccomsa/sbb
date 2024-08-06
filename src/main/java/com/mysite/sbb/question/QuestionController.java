package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor // 생성자 메서드를 만들어주소, 생성자 주입방식이 진행된다.
@Controller
public class QuestionController {

	// 기본구조는 QuestionService 를 사용해야 하지만, 현재는 직접 리포지터를 사용한다.
	private final QuestionService questionService;
	
	@GetMapping("/list")
//	@ResponseBody
	public String list(Model model) {
		
		List<Question> questionList = this.questionService.getList(); // 페이징기능이 없는 메서드임.
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	// 주소   /question/detail/1
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		
		Question question = this.questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_detail"; // 답변 폼이 존재하기때문에 파라미터로 AnswerForm answerForm 사용한다.
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult ) {
		
		
		if(bindingResult.hasErrors()) {
			System.out.println("폼입력에러");
			return "question_form";
		}
		
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		
		return "redirect:/question/list";
	}
	
	
	
	
	
	
	
	
	
}
