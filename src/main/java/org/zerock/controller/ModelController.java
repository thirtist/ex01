package org.zerock.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/model/*")
@Log4j
public class ModelController {
	
	@RequestMapping("/ex1")
	public void method1(Model model) {
		log.info("method1");
		
//		request.setAttribute("abc",abc);
		model.addAttribute("name","java");
		//spring에서 제공하는 model이 알아서 request에 옮겨줌
		//jsp에서 꺼낼때 el로 꺼낼수 있음 ${name}
		
	}
	
	@RequestMapping("/ex2")
	public void method2(@ModelAttribute("name") String name) {
		log.info("method2");
		
		//model.addAttribute("name",name);
	}

	@RequestMapping("/ex3")
	public void method3(@ModelAttribute("name") String name, @ModelAttribute("age") int age) {
		log.info("method3");
		log.info(name);
		log.info(age);
	}
	
	@RequestMapping("/ex4")
	public void method4(@ModelAttribute("member") Member member){
		log.info("method4");
		log.info(member);
		
		//model.addAttribute("member",member);
	}

	@RequestMapping("/ex5")
	// model attribute의 이름은 소문자로 바꾼 type명으로 결정됨
	public void method5(@ModelAttribute Member member){
		log.info("method5");
		log.info(member);
		
		//model.addAttribute("member",member);
	}
	
	@RequestMapping("/ex6")
	// model attribute의 이름은 소문자로 바꾼 type명으로 결정됨
	public String method6(Member member){
		log.info("method6");
		log.info(member);
		
		return "model/ex4";
	}
	
	@RequestMapping("/ex7")
	public String method7(Model model, HttpSession session, RedirectAttributes rattr) {
		log.info("method7");
		model.addAttribute("myattr1", "myvalue1"); //model객체는 request를 사용하므로 redirect에선 못넘어감
		session.setAttribute("myAttr2", "myValue2"); //이전방법처럼 세션으로 넘어가짐(세션에 남아있으므로 넘기고 바로 지우는게 깔끔했었음)
		rattr.addFlashAttribute("myAttr3","myValue3"); //Spring이 제공하는 RedirectAttribute객체에
						//add(((Flash)))Attribute로 보내면 내용은 model에 남아있음(세션아님)
		return "redirect:ex8";
		//http://localhost:8080/model/ex7
	}
	@RequestMapping("/ex8")
	public String method8(Model model) {
		log.info("method8");
		Map<String, Object> map = model.asMap();
		log.info(map.get("myattr1")); //없음(리다이렉트로하면 모델공유안됨)
		log.info(map.get("myAttr2")); //없음(모델이 아니고 세션에 넣었으니)
		log.info(map.get("myAttr3")); //model에 남아있음
		return "redirectex1";
	}
}
