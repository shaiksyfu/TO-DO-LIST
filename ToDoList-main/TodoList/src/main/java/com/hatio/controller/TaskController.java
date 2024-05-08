package com.hatio.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hatio.dao.TaskRepo;
import com.hatio.entity.Project;
import com.hatio.entity.Task;
import com.hatio.service.ProjectService;
import com.hatio.service.TaskService;

@Controller
public class TaskController {

	@Autowired
	ProjectService projectService;

	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepo taskRepo;

	@PostMapping("/createTask/{id}")
	public String getTaskCreationForm(@PathVariable("id") int id, Model model) {

		Project project = projectService.findById(id);

		model.addAttribute("project", project);
		return "createTask";
	}

	@PostMapping("/addTask/{taskId}")
	public String submitNewTask(@PathVariable("taskId") int id, @RequestParam("taskTitle") String taskTitle,
			@RequestParam("taskDescription") String taskDescription) {
		Project project = projectService.findById(id);

		LocalDateTime currentDateTime = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

		String formattedDateTime = currentDateTime.format(formatter);

		Task task = new Task(project, taskTitle, taskDescription, formattedDateTime, null, false);

		taskService.createTask(task);

		return "redirect:/viewAllTasks/" + id;
	}

	@PostMapping("/editRecord/{taskId}")
	public String getTaskEditForm(@PathVariable("taskId") int id, @RequestParam("projectId") int projectId,
			Model model) {

		Project project = projectService.findById(projectId);

		Task task = taskService.findById(id);

		model.addAttribute("task", task);
		model.addAttribute("project", project);

		return "editTaskForm";

	}
	
	public static int getNumberOfCompletedTasks(List<Task> tasks) {
		int count=0;
		for(Task task:tasks) {
			if(task.isTaskStatus()==true) {
				count++;
			}
		}
		
		return count;
	}

	@RequestMapping("/viewAllTasks/{projectId}")
	public String viewAllTasks(@PathVariable("projectId") int projectId, Model model) {

		List<Task> tasks = taskService.findByProjectId(projectId);

		Project project = projectService.findById(projectId);
		
		int completedTasks = getNumberOfCompletedTasks(tasks);
		
		model.addAttribute("completedTasks", completedTasks);

		model.addAttribute("project", project);

		model.addAttribute("tasks", tasks);
		
		

		return "viewAllTasks";
	}
	
	@PostMapping("/incomplete/{taskId}")
	public String markAsCompleteTask(@PathVariable("taskId") int taskId, @RequestParam("projectId") int projectId) {
		
		Task task = taskService.findById(taskId);
		task.setTaskStatus(false);
		taskRepo.save(task);
		
		return "redirect:/viewAllTasks/" + projectId;

		
	}
	@PostMapping("/complete/{taskId}")
	public String markAsInCompleteTask(@PathVariable("taskId") int taskId, @RequestParam("projectId") int projectId) {
		
		
		LocalDateTime currentDateTime = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

		String formattedDateTime = currentDateTime.format(formatter);

		
		
		Task task = taskService.findById(taskId);
		task.setTaskStatus(true);
		
		task.setTaskUpdatedDate(formattedDateTime);
		
		
		taskRepo.save(task);
		
		return "redirect:/viewAllTasks/" + projectId;
		
		
	}

	@PostMapping("/updateTask")
	public String updateTaskDetails(@RequestParam("taskId") int taskId, @RequestParam("taskTitle") String taskTitle,
			@RequestParam("taskDescription") String taskDescription, @RequestParam("projectId") int projectId) {
		Task task = taskService.findById(taskId);
		task.setTaskTitle(taskTitle);
		task.setTaskDescription(taskDescription);
		taskRepo.save(task);
		return "redirect:/viewAllTasks/" + projectId;
	}

	@PostMapping("/deleteRecord/{taskId}")
	public String deleteTaskDetails(@RequestParam("projectId") int projectId, @PathVariable("taskId") int taskId) {
		taskService.deleteTaskById(taskId);
		return "redirect:/viewAllTasks/" + projectId;
	}

}
