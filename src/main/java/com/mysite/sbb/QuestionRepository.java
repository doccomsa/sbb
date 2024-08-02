package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA 리포지터리 : 테이블에 대하여 삽입,삭제, 수정, 조회 작업을 가능하게 해주는 인터페이스
// JpaRepository 인터페이스 : CRUD 작업을 처리하는 메서드들이 내장되어 있다.
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	//추상메서드명 네이밍규칙이 존재한다.
	
	// findBy + 필드명의 첫문자를 대문자로
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
}
