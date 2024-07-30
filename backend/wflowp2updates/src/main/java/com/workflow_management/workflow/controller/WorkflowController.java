// controller/WorkflowController.java
package com.workflow_management.workflow.controller;

import com.workflow_management.workflow.dto.WorkflowDTO;
import com.workflow_management.workflow.model.Workflow;
import com.workflow_management.workflow.model.WorkflowStep;
import com.workflow_management.workflow.service.WorkflowService;
//importing Autowired
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//WorkflowController handles the API Call for creation of the workflow basically involves the activities or the steps performed by the user

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @PostMapping("/create")
    //Workflow DTO class handles and stores all the steps
    public Workflow createWorkflow(@RequestBody WorkflowDTO workflowDTO) {
        Workflow workflow = new Workflow();
        workflow.setName(workflowDTO.getName());

        List<WorkflowStep> steps = workflowDTO.getSteps().stream().map(dto -> {
            WorkflowStep step = new WorkflowStep();
            step.setTask(dto.getTask());
            step.setTaskCondition(dto.getTaskCondition());
            step.setSubTask(dto.getSubTask());
            return step;
        }).collect(Collectors.toList());

        workflow.setSteps(steps);
        return workflowService.saveWorkflow(workflow);
    }
    @GetMapping("/list")
    public List<Workflow> listWorkflows() {
        return workflowService.findAll();
    }
    @GetMapping("/summary")
    public List<WorkflowDTO.WorkflowSummary> getWorkflowSummaries() {
        return workflowService.getWorkflowSummaries();
    }
}
