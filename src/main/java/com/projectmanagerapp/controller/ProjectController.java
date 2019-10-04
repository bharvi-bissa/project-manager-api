package com.projectmanagerapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.service.ProjectServiceImpl;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectServiceImpl projService;

	@PostMapping("")
	public ResponseEntity<?> createOrUpdateProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errMap = new HashMap<>();
			for (FieldError err : bindingResult.getFieldErrors()) {
				errMap.put(err.getField(), err.getDefaultMessage());
			}
			return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
		}
		Project createdProject = projService.createOrUpdateProject(project);
		return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<?> getProject(Long projectId) {
		Project project = projService.findById(projectId);
		return new ResponseEntity<>(project, HttpStatus.OK);
	}

	@GetMapping("/allProjects")
	public ResponseEntity<?> getAllProjects() {
		List<Project> projectList = projService.getAllProjects();
		return new ResponseEntity<>(projectList, HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteProject(Long projectId) {
		if (projService.deleteProjectById(projectId)) {
			return new ResponseEntity<>("Project Deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Someething went wrong", HttpStatus.OK);
	}
}
