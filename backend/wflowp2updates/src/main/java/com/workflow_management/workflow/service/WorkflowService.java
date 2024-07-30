package com.workflow_management.workflow.service;

import com.workflow_management.workflow.dto.WorkflowDTO;
import com.workflow_management.workflow.model.Workflow;
import com.workflow_management.workflow.model.WorkflowStep;
import com.workflow_management.workflow.repository.WorkflowRepository;
import com.workflow_management.workflow.repository.WorkflowStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkflowService {

    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private WorkflowStepRepository workflowStepRepository;
    //This ensures saving the workflow and itd steps are perfomed in single transaction Basically NO partial data will be saved
    @Transactional
    public Workflow saveWorkflow(Workflow workflow) {
        // Save the workflow first
        Workflow savedWorkflow = workflowRepository.save(workflow);

        // Set the saved workflow to each step and save steps
        for (WorkflowStep step : workflow.getSteps()) {
            step.setWorkflow(savedWorkflow);
            workflowStepRepository.save(step);
        }

        return savedWorkflow;
    }

    public List<Workflow> findAll() {
        return workflowRepository.findAll();
    }
    public List<WorkflowDTO.WorkflowSummary> getWorkflowSummaries() {
        List<Workflow> workflows = workflowRepository.findAll();
        return workflows.stream()
                .map(workflow -> new WorkflowDTO.WorkflowSummary(workflow.getId(), workflow.getName()))
                .collect(Collectors.toList());
    }
}