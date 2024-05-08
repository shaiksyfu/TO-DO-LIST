package com.hatio.service;

import java.util.List;

import com.hatio.entity.Task;

public interface TaskService {
	
	public void createTask(Task task);

	public void updateTask(int id,Task  task);
	
	public void deleteTaskById(int id);
	
	public Task findById(int id);
	
	public List<Task> findAll();
	
    public List<Task> findByProjectId(int projectId);

}
