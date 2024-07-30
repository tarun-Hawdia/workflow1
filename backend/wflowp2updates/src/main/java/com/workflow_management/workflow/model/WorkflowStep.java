package com.workflow_management.workflow.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WorkflowStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_condition_id")
    private TaskCondition taskCondition;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_task_id")
    private SubTask subTask;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Workflow getWorkflow() { return workflow; }
    public void setWorkflow(Workflow workflow) { this.workflow = workflow; }

    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    public TaskCondition getTaskCondition() { return taskCondition; }
    public void setTaskCondition(TaskCondition taskCondition) { this.taskCondition = taskCondition; }

    public SubTask getSubTask() { return subTask; }
    public void setSubTask(SubTask subTask) { this.subTask = subTask; }
}