package com.hatio.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "project_title")
    private String projectTitle;
    
    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_creation_date")
    private String projectCreationDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectCreationDate() {
        return projectCreationDate;
    }

    public void setProjectCreationDate(String projectCreationDate) {
        this.projectCreationDate = projectCreationDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project(String projectTitle, String projectDescription, String projectCreationDate) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.projectCreationDate = projectCreationDate;
    }

    public Project() {
    }

    @Override
    public String toString() {
        return "Project [projectId=" + projectId + ", projectTitle=" + projectTitle + ", projectDescription="
                + projectDescription + ", projectCreationDate=" + projectCreationDate + "]";
    }
}
