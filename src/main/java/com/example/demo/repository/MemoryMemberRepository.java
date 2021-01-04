package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Member;

//@Repository
public class MemoryMemberRepository implements MemberRepository
{
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	// 회원 저장
	@Override
	public Member save(Member member)
	{
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id)
	{
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name)
	{
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
		
		// 람다식
		// 루프 돌기. member.getName()이 파라미터로 넘어온 name과 같은지 확인
		// 하나 찾으면 그친구를 반환
	}

	// 모든 회원 조회
	@Override
	public List<Member> findAll()
	{
		return new ArrayList<>(store.values());
		// store안에있는 values는 member를 의미함. 그 member를 모두 반환한다.
	}
	
	public void clearStore()
	{
		store.clear();
	}

}
