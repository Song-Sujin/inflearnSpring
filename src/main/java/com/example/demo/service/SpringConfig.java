package com.example.demo.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.JdbcMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig
{
	DataSource dataSource;
	
	@Autowired
	public SpringConfig(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Bean	// 이걸 스프링에 등록하라고 직접 알려줌
	public MemberService memberService()
	{
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository()
	{
		// 직접 설정파일을 이렇게 운영할때는 아래 호출하는 repository 명만 바꿔주면 됨
		// 이게 가장 큰 장점이다
		//return new MemoryMemberRepository();
		
		return new JdbcMemberRepository(dataSource);
	}
	

}
