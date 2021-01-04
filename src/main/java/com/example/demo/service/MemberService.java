package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

//@Service	// 이때도 매개변수로 받는 MemoryMemberRepository(구현체)에 @Repository 어노테이션을 등록해야 함
public class MemberService
{
	// 이건 다른 인스턴스
	//private final MemberRepository memberRepository = new MemoryMemberRepository();

	// 이건 같은 인스턴스 
	private final MemberRepository memberRepository;
	
	// 직접 생성하는게 아니라 외부에서 넣어주도록 바꾸는 것
	//@Autowired
	public MemberService(MemberRepository memberRepository)
	{
		this.memberRepository = memberRepository;
	}

	// 회원 가입
	public Long join(Member member)
	{
		// 같은 이름이 있는 중복 회원 X 
		// 이렇게 바로 꺼내는것을 권장하지는 않는다
		//Optional<Member> result = memberRepository.findByName(member.getName());
		/*
		memberRepository.findByName(member.getName())	// findByName을 한 결과는 Optinal<Member>이기 때문에 바로 아래처럼 사용 가능
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
		// member에 값이 있으면 예외 발생
		*/
		validateDuplicateMember(member);	// 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	// 중복 회원 검증 메소드
	private void validateDuplicateMember(Member member)
	{
		memberRepository.findByName(member.getName())	// findByName을 한 결과는 Optinal<Member>이기 때문에 바로 아래처럼 사용 가능
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	// 전체 회원 조회
	public List<Member> findMembers()
	{
		return memberRepository.findAll();
	}
	
	// 회원 한명 조회
	public Optional<Member> findOne(Long memberId)
	{
		return memberRepository.findById(memberId);			
	}

}
