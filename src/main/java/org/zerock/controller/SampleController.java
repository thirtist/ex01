package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		log.info("basic......");

	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("basic get......");
	}

	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get......");
	}

	@RequestMapping("/ex02")
	public String ex02(String name, int age) {

		log.info("sample/ex02");
		log.info(name);
		log.info(age);

		return "ex02";
	}

	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: " + Arrays.toString(ids));

		return "ex02Array";
	}

	@GetMapping("/ex2List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids: " + ids);

		return "ex02List";
	}

	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		return "ex01";
	}

	// 134쪽
	// http://localhost:8080/sample/ex02Bean?list[0].name=aaa&list[1].name=bbb
	// http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {

		log.info("list dtos: " + list);
		return "ex02Bean";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/ex03")
	public String ex03Bean(TodoDTO todo) {
		// http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01
		log.info("todo: " + todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04Bean(SampleDTO dto, @ModelAttribute("page") int page) {
		//http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
		//기본타입은 @ModelAttribute생략불가 + 같이쓸때 뒷괄호도 생략불가?
		log.info("sampleDTO :" + dto);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06....");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload......");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("----------------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
			
		});
	}

}
