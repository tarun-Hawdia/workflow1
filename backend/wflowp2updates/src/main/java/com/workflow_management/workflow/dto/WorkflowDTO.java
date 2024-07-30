package com.workflow_management.workflow.dto;

import com.workflow_management.workflow.model.Task;
import com.workflow_management.workflow.model.TaskCondition;
import com.workflow_management.workflow.model.SubTask;

import java.util.List;

public class WorkflowDTO {
    private Long id;
    private String name;
    //List to store all the workflow steps
    private List<WorkflowStepDTO> steps;

    // Getters and Setters
    //Generating unique id for each user
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    //Getting the name of the user
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    //Applying Getter and Setter on the List for storing steps

    public List<WorkflowStepDTO> getSteps() { return steps; }
    public void setSteps(List<WorkflowStepDTO> steps) { this.steps = steps; }

    //Now creating getter and setter for all the class like Task,TaskCondition,SubTask
    public static class WorkflowStepDTO {
        private Long id;
        private Task task;
        private TaskCondition taskCondition;
        private SubTask subTask;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Task getTask() { return task; }
        public void setTask(Task task) { this.task = task; }

        public TaskCondition getTaskCondition() { return taskCondition; }
        public void setTaskCondition(TaskCondition taskCondition) { this.taskCondition = taskCondition; }

        public SubTask getSubTask() { return subTask; }
        public void setSubTask(SubTask subTask) { this.subTask = subTask; }
    }
    public static class WorkflowSummary {
        private Long id;
        private String name;

        public WorkflowSummary(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

}