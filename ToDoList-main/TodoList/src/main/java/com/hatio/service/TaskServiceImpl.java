package com.hatio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.dao.TaskRepo;
import com.hatio.entity.Task;


@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepo taskRepo;
	
	@Override
	public void createTask(Task task) {
		
		taskRepo.save(task);
		
	}

	@Override
	public void updateTask(int id, Task task) {
	    Optional<Task> optionalTask = taskRepo.findById(id);
	    
	    if (optionalTask.isPresent()) {
	        Task existingTask = optionalTask.get();
	        existingTask.setTaskTitle(task.getTaskTitle());
	        existingTask.setTaskDescription(task.getTaskDescription());
	        existingTask.setTaskCreationDate(task.getTaskCreationDate());
	        existingTask.setTaskUpdatedDate(task.getTaskUpdatedDate());
	        existingTask.setTaskStatus(task.isTaskStatus());
	        
	        taskRepo.save(existingTask);
	    } 
	}
	@Override
	public void deleteTaskById(int id) {
		taskRepo.deleteById(id);
	}

	@Override
	public Task findById(int id) {
		Optional<Task> task = taskRepo.findById(id);
		return task.orElse(null); // Return the project if found, otherwise return null

	}

	@Override
	public List<Task> findAll() {
		List<Task> tasks = taskRepo.findAll();
		return tasks;
	}
	
    public List<Task> findByProjectId(int projectId) {
        return taskRepo.findByProject_ProjectId(projectId);
    }


}
