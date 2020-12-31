package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
	@GetMapping("hello")
	public String hello(Model model)
	{
		model.addAttribute("data", "spring!!");
		return "hello";
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model)
	{
		model.addAttribute("name", name);
		
		return "hello-template";
	}
	
	@GetMapping("hello-string")
	@ResponseBody		// html에 나오는 body 태그가 아니라, http 통신의 response body 부분에 이 데이터를 직접 넣어줌
	public String helloString(@RequestParam("name") String name)
	{
		return "hello " + name;		// "hello spring"
	}
	
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name)
	{
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
		// 이렇게 객체를 반환하고 @ResponseBody로 하면 Spring은 기본적으로 JSON으로 반환한다
	}
	
	static class Hello
	{
		private String name;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
		
		
	}

}	
