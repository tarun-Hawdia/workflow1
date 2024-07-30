package com.workflow_management.workflow.model;
//To handle circular reference
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//IMport all the JPA (Java Persistence Api classes used for    ORM)
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SubTask {
    //Annotation to denote primary key
    @Id
    //Generate id in autoincrement way
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //THis property cannot be null
    @Column(nullable = false)
    private String detail;
    //Many to one means many subtasks are associated with Task COnditions
    @ManyToOne
    @JoinColumn(name = "condition_id")
    private TaskCondition taskCondition;


    @OneToMany(mappedBy = "subTask", cascade = CascadeType.ALL)
    private List<WorkflowStep> steps;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public TaskCondition getTaskCondition() {
        return taskCondition;
    }

    public void setTaskCondition(TaskCondition taskCondition) {
        this.taskCondition = taskCondition;
    }

    public List<WorkflowStep> getSteps() {
        return steps;
    }

    public void setSteps(List<WorkflowStep> steps) {
        this.steps = steps;
    }
}