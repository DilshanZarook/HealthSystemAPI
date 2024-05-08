package com.healthSystemAPI.classes;

public class Appointment {

    private int    appId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;
    

    public Appointment(){
    } 
    
    public Appointment(int appId,Patient patient,Doctor doctor,String date, String time){
        this.appId = appId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }
    
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
