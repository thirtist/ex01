package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/return/*")
@Log4j
public class ReturnController {

	@RequestMapping("/ex1")
	public String method1() {
		log.info("method1");
		return "returnView1";
	}
	
	@RequestMapping("/ex2")
	public String method2() {
		log.info("method2");
		return "redirect:/sample/";
	}

	@RequestMapping("/ex3")
	public @ResponseBody String method3() {
		log.info("method3");
		return "returnValue3";
	}
	
	@RequestMapping("/ex4")
	public void method4() {
		log.info("method4");
	}
	
	@RequestMapping("/ex5")
	public @ResponseBody Member method5() {
		
		Member member = new Member();
		member.setName("donald");
		member.setAge(33);
		// JSON은 완전히 자바스크립트 오브젝트랑 같지는 않음
		// attribute이름을 ""로 감쌈
		// JS객체 {name:"donald", age:33};
		// JSON객체 {"name":"donald", "age":33} (앞에 ""추가)
		
		// json이 중간다리역할을 함
		// java object -> json -> javascript -> html
		// javascript -> json -> javaobject
		return member;
	}
}
