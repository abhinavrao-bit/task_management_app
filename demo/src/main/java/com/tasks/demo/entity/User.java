package com.tasks.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=100)
	private String name;
	
	@Column(nullable=false,unique=true,length=255)
	private String email;
	
	@Column(nullable=false,length=255)
	private String password;
	
	@Column(nullable=false,length=50)
	private String role="user";
	
	
	@Column(name="created_at",nullable=false,updatable=false)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy="manager")
	private List<Project> managedProjects=new ArrayList<>();
	
	@ManyToMany(mappedBy="members")
	private List<Project> projects=new ArrayList<>();
	
	@OneToMany(mappedBy="assignee")
	private List<Task> assignedTasks=new ArrayList<>();
	
	@OneToMany(mappedBy="createdBy")
	private List<Task> createdTasks=new ArrayList<>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Comment> comments=new ArrayList<>();
	
	@PrePersist
	protected void create()
	{
		this.createdAt=LocalDateTime.now();
	}
	
	
	public User() {
	}
	
	public User(String name,String email,String password,String role)
	{
		this.name=name;
		this.email=email;
		this.password=password;
		this.role=role==null?"user":role;
		
	}
	
	// Getters and Setters
	public Long getId() {
		return this.id;
	}
	
	public Long setId(Long id) {
		this.id=id;
	}
	
	public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public List<Project> getManagedProjects() { return managedProjects; }
    public void setManagedProjects(List<Project> managedProjects) { this.managedProjects = managedProjects; }

    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

    public List<Task> getAssignedTasks() { return assignedTasks; }
    public void setAssignedTasks(List<Task> assignedTasks) { this.assignedTasks = assignedTasks; }

    public List<Task> getCreatedTasks() { return createdTasks; }
    public void setCreatedTasks(List<Task> createdTasks) { this.createdTasks = createdTasks; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

	
	
	
}
