package com.hatio.service;

import java.util.List;

import com.hatio.entity.Project;

public interface ProjectService {
	
	public void createProject(Project project);
	
	public List<Project> findAll();
	

	public Project findById(int id);
	
	public void updateById(int id, String title, String description);
}
