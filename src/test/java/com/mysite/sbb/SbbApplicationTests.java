package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  // 스프링부트의 테스트용 클래스라는 것을 의미
class SbbApplicationTests {

	@Autowired  // 롬복 어노테인션 사용하면 안됨.
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Test  // 아래 메서드는 JUnit 환경에서 테스트하기위한 메서드이다.
	void testJpa() {
		/*
		1)
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1); // 첫번째 질문데이타 저장. 
		
		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); // 두번째 질문데이타 저장. 
		
		2)
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());  //  assertEquals(기대값, 실제값)
		
		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		
		3)
		Optional<Question> oq = this.questionRepository.findById(3);
		
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
		
		4)
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(3, q.getId());
		
		5)
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(3, q.getId());
		6)
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent()); // false 반화되면, 에러발생
		Question q = oq.get(); // 수정하고자 하는 데이타를 참조.
		q.setSubject("수정한 제목");
		this.questionRepository.save(q); // 삽입(insert), 수정(update)시 save()메서드 사용
		
		4)
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
		
		5)
		Optional<Question> oq = this.questionRepository.findById(4);
		assertTrue(oq.isPresent());
		Question q = oq.get(); // 질문글 참조.
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.2");
		a.setQuestion(q); // 어떤 질문글의 답변글인지에 정보작업. mybatis에서는 부모의 일련번호를 사용하지만, JPA에서는 다르다.
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		*/
		Optional<Answer> oa = this.answerRepository.findById(1); // left join
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(4, a.getQuestion().getId()); // 질문글의 3번글을 보고 답변글을 저장.
		
	}

}
