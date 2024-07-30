
package com.workflow_management.workflow.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TaskCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String conditionText;

    @OneToMany(mappedBy = "taskCondition", cascade = CascadeType.ALL)
    private List<WorkflowStep> steps;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getConditionText() { return conditionText; }
    public void setConditionText(String conditionText) { this.conditionText = conditionText; }

    public List<WorkflowStep> getSteps() { return steps; }
    public void setSteps(List<WorkflowStep> steps) { this.steps = steps; }
}
