package com.hatio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hatio.entity.Project;

public interface ProjectRepo extends JpaRepository< Project, Integer> {

}
