package com.workflow_management.workflow.repository;

import com.workflow_management.workflow.model.WorkflowStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowStepRepository extends JpaRepository<WorkflowStep, Long> {
}
