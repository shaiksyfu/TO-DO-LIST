package com.hatio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hatio.entity.Project;
import com.hatio.service.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping("/createProject")
	public String getCreateProjectForm() {
		return "createProject";
	}

	@PostMapping("/saveProject")
	public String saveProjectEntity(@RequestParam("projectTitle") String title,
			@RequestParam("projectDescription") String description) {

		LocalDate currentDate = LocalDate.now();

		// Define a date formatter for the desired format (DD/MM/YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Format the current date using the formatter
		String formattedDate = currentDate.format(formatter);

		Project project = new Project(title, description, formattedDate);

		projectService.createProject(project);

		return "redirect:/viewprojects";

	}

	@GetMapping("/viewprojects")
	public String getAllProjects(Model model) {

		List<Project> projects = projectService.findAll();

		model.addAttribute("projects", projects);

		return "viewAllProjects";
	}

	@RequestMapping("/editProject")
	public String editProject(@RequestParam("projectId") int id, Model model) {

		Project project = projectService.findById(id);

		System.out.println(project);

		model.addAttribute("project", project);

		return "editProjectForm";
	}

	@PostMapping("/updateProjectDetails")
	public String updateProjectEntity(@RequestParam("projectId") int id,
			@RequestParam("projectTitle") String projectTitle, @RequestParam("projectDescription") String description) {
		
		projectService.updateById(id, projectTitle,description);
		
		return "redirect:/viewprojects";
	}

}
