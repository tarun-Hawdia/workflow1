    // service/UserDataService.java
    package com.workflow_management.workflow.service;

    import com.workflow_management.workflow.model.UserData;
    import com.workflow_management.workflow.repository.UserDataRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class UserDataService {

        @Autowired
        private UserDataRepository userDataRepository;

        public UserData saveUserData(UserData userData) {
            return userDataRepository.save(userData);
        }

        public UserData getLatestUserData() {
            return userDataRepository.findTopByOrderByIdDesc();
        }
    }
