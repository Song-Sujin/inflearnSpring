package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;

// 스프링이 처음 로드될때 스프링 컨테이너라는 하나의 통이 생김
// 거기에 @Controller 어노테이션이 있으면 그걸 찾아서 MemberController 객체를 생성해서
// 스프링에 넣어둠. 스프링이 관리하게 됨
@Controller
public class MemberController
{
	// 생성자로 injection 할때
	private final MemberService memberService;
	
	// 생성자로 injection 할때
	// 조립되는 시점에 한번 세팅되고 끝
	@Autowired	// 이때 매개변수로 받는 MemberService에 @Service 어노테이션이 등록되어야 함
	public MemberController(MemberService memberService)
	{
		this.memberService = memberService;
	}

	// setter를 통한 injection
	//private MemberService memberService;

	// 단점 : 누군가가 MemberController를 호출했을때 이게 public으로 열려있어야 함
	// 근데 한번 세팅되고나면 중간에 바뀔일이 없는데 public하게 노출되어있는게 별루..
	// 아무나 호출할수 있도록 열려있으면 안됨
	/*
	@Autowired
	public void setMemberService(MemberService memberService)
	{
		this.memberService = memberService;
	}
	*/
	
	@GetMapping("/members/new")
	public String createForm()
	{
		return "members/createMemberForm";
	}
	
	// 데이터를 form에서 전달할때는 보통 Post. 등록할때
	@PostMapping("/members/new")
	public String create(MemberForm form)
	{
		Member member = new Member();
		member.setName(form.getName());
		
		System.out.println("member = " + member.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model)
	{
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
}
