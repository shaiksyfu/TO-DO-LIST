package com.hatio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hatio.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>{

    List<Task> findByProject_ProjectId(int projectId);

}
