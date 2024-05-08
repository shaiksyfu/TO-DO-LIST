package com.hatio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
        
    @Column(name = "task_title")
    private String taskTitle;
    
    @Column(name = "task_description")
    private String taskDescription;
    
    @Column(name = "task_creation_date")
    private String taskCreationDate;
    
    @Column(name = "task_updated_date")
    private String taskUpdatedDate;
    
    @Column(name = "task_status")
    private boolean taskStatus;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskCreationDate() {
        return taskCreationDate;
    }

    public void setTaskCreationDate(String taskCreationDate) {
        this.taskCreationDate = taskCreationDate;
    }

    public String getTaskUpdatedDate() {
        return taskUpdatedDate;
    }

    public void setTaskUpdatedDate(String taskUpdatedDate) {
        this.taskUpdatedDate = taskUpdatedDate;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    

    public Task(Project project, String taskTitle, String taskDescription, String taskCreationDate,
			String taskUpdatedDate, boolean taskStatus) {
		super();
		this.project = project;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskCreationDate = taskCreationDate;
		this.taskUpdatedDate = taskUpdatedDate;
		this.taskStatus = taskStatus;
	}

	public Task() {
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", project=" + project + ", taskTitle=" + taskTitle
                + ", taskDescription=" + taskDescription + ", taskCreationDate=" + taskCreationDate
                + ", taskUpdatedDate=" + taskUpdatedDate + ", taskStatus=" + taskStatus + "]";
    }
}
