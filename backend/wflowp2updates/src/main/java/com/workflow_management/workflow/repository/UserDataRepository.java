// repository/UserDataRepository.java
package com.workflow_management.workflow.repository;

import com.workflow_management.workflow.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    UserData findTopByOrderByIdDesc();
}
