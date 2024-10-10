package com.example.demo.controller;

import java.lang.System.LoggerFinder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TestEntity;
import com.example.demo.service.TestService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class TestApi {

	@Autowired
	private TestService testService;
	
	@GetMapping("/test/create")
	public void createTest() {
		testService.create("cuna", 28);
	}
	
	@PostMapping("/test/create")
	public void postCreate(
			@RequestBody CreateTestRequests request
			) { 
		log.info("################" + request.getName());
		System.out.println("/------------------" + request.getName() + "--------------/");
		testService.create(request.getName(), request.getAge());
	}
	
	@PutMapping("/test/update")
	public void update(
			@RequestParam("id") Long id,
			@RequestBody CreateTestRequests request
			) {
		testService.update(id, request.getName(), request.getAge());
	}
	
	@DeleteMapping("/test/delete/{id}")
	public void delete(
			@PathVariable("id") Long id
			) {
		testService.delete(id);
	}
	
	@GetMapping("/test")
	public ResponseEntity<List<TestEntity>> jsonData(){
		List<TestEntity> data = testService.findAll();
		return ResponseEntity.ok(data);
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class CreateTestRequests{
		private String name;
		private Integer age;
	}
}
