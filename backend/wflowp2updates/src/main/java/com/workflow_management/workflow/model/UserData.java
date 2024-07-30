    // model/UserData.java
    package com.workflow_management.workflow.model;

    import jakarta.persistence.*;

    @Entity
    public class UserData {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String dob;

        @Column(nullable = false)
        private String gender;

        @Column(nullable = false)
        private String pincode;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDob() { return dob; }
        public void setDob(String dob) { this.dob = dob; }

        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }

        public String getPincode() { return pincode; }
        public void setPincode(String pincode) { this.pincode = pincode; }
    }
