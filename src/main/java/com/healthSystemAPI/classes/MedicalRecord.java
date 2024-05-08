package com.healthSystemAPI.classes;

public class MedicalRecord {
    private int medicalRecordId;
    private int patientId; 
    private String medicalDiagnoses;
    private String medicalTreatments;
   

    public MedicalRecord() {
    }

    public MedicalRecord(int medicalRecordId, int patientId, String medicalDiagnoses, String medicalTreatments) {
        this.medicalRecordId = medicalRecordId;
        this.patientId = patientId;
        this.medicalDiagnoses = medicalDiagnoses;
        this.medicalTreatments = medicalTreatments;
    }

    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMedicalDiagnoses() {
        return medicalDiagnoses;
    }

    public void setMedicalDiagnoses(String medicalDiagnoses) {
        this.medicalDiagnoses = medicalDiagnoses;
    }

    public String getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(String medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }
}

