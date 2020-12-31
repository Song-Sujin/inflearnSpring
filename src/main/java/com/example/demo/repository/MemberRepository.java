package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Member;

public interface MemberRepository
{
	// 회원 저장
	Member save(Member member);
	
	// id로 회원찾기
	Optional<Member> findById(Long id);			// 없으면 null일수 있으니 이때 Optional로 감싸기
	
	// name으로 회원찾기
	Optional<Member> findByName(String name);
	
	// 지금까지 저장된 모든 회원 리스트 반환
	List<Member> findAll();

}
