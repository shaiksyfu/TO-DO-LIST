package com.hatio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.dao.ProjectRepo;
import com.hatio.entity.Project;
import com.hatio.entity.Task;

@Service
public class ProjectServiceImpl implements ProjectService{

	
	@Autowired
	ProjectRepo projectRepo;
	
	@Override
	public void createProject(Project project) {
		
		projectRepo.save(project);
		
	}
	

	@Override
	public List<Project> findAll() {
		List<Project> projects = projectRepo.findAll();
		return projects;
	}

	@Override
	public Project findById(int id) {
	    Optional<Project> optionalProject = projectRepo.findById(id);
	    return optionalProject.orElse(null); // Return the project if found, otherwise return null
	}


	@Override
	public void updateById(int id,String title, String description) {
		
		Optional<Project> optionalProject = projectRepo.findById(id);
	    if (optionalProject.isPresent()) {
	        Project existingTask = optionalProject.get();
	        
	        existingTask.setProjectTitle(title);
	        existingTask.setProjectDescription(description);
	        
	        projectRepo.save(existingTask);
	        
	    } 

		
	}

}
