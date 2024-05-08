
package com.healthSystemAPI.classes;

public class Patient extends Person {
    private String gender;
    private String dob;
    private String medicalHistory;
    private String currentHealthStatus;
    
    
    public Patient() {
    }

    public Patient(int personId, String name, String contactNo, String address, String medicalHistory, String currentHealthStatus, String gender, String dob) {
        super(personId, name, contactNo, address);
        this.gender = gender;
        this.dob = dob;
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }  
    
    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}

