// controller/UserDataController.java
package com.workflow_management.workflow.controller;

import com.workflow_management.workflow.model.UserData;
import com.workflow_management.workflow.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//UserDataCOntroller handles the API call when user tries to store the json data
@RestController
@RequestMapping("/api/userdata")
@CrossOrigin(origins = "http://localhost:3000")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/create")
    public UserData createUserData(@RequestBody UserData userData) {
        return userDataService.saveUserData(userData);
    }

    @GetMapping("/latest")
    public UserData getLatestUserData() {
        return userDataService.getLatestUserData();
    }
}
