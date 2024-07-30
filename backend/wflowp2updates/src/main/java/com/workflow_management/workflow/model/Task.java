
package com.workflow_management.workflow.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Task {
    @Id
    //Generate id in autoincrement way
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    //Setting One to many relation by mapping the task table
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<WorkflowStep> steps;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<WorkflowStep> getSteps() { return steps; }
    public void setSteps(List<WorkflowStep> steps) { this.steps = steps; }
}
