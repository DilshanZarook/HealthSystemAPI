package com.healthSystemAPI.classes;

public class Prescription{
    
    private Patient patient;
    private Doctor doctor;
    private String medicineDetails;
    private String prescribDosage;
    
    
    public Prescription(){
    }
    
    public Prescription(Patient patient, Doctor doctor, String medicineDetails, String prescribDosage) {
        this.patient = patient;
        this.doctor = doctor;
        this.medicineDetails = medicineDetails;
        this.prescribDosage = prescribDosage;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMedicineDetails() {
        return medicineDetails;
    }

    public void setMedicineDetails(String medicineDetails) {
        this.medicineDetails = medicineDetails;
    }

    public String getPrescribDosage() {
        return prescribDosage;
    }

    public void setPrescribDosage(String prescribDosage) {
        this.prescribDosage = prescribDosage;
    }
}
