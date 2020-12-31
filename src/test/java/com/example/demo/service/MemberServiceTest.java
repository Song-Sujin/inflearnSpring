package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.Member;

class MemberServiceTest
{
	MemberService memberService = new MemberService();
	
	@Test
	void join()		// void 회원가입()이라고 한글로 적어도 됨
	{
		// given
		Member member = new Member();
		member.setName("hello");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		//Assertions
		
	}
	
	@Test
	void findMembers()
	{
		
	}

	@Test
	void findOne()
	{
		
	}
}
