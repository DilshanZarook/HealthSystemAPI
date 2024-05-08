
package com.healthSystemAPI.dao;


import com.healthSystemAPI.classes.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PatientDAO extends PersonDAO {
    //store patient data in a list
    private static List<Patient> patientsList = new ArrayList<>();
    
     static {
        patientsList.add(new Patient(1, "Mr.Dilshan", "0773010100", "Hunupitiya,wattala", "new Patient", "Feverish and bodyache", "male", "1999-01-23"));
        patientsList.add(new Patient(2, "Mr.Bunty", "0112310100", "matara,galle", "Acciedent", "Fracture in bones ", "male", "1997-09-09"));
    }
     
    //get all patient data 
    public static List<Patient> getAllPatients() {
        return patientsList;
    }
    
    //get specific patient by an ID 
     public Patient getPatientById(int id) {
        for (Patient patient : patientsList) {
            if (patient.getPersonId() == id) {
                return patient;
            }
        }
        return null;
    }

    // Create new patient 
    public void addPatient(Patient patient) {
        int newpatientId = getNextPatientId();
        patient.setPersonId(newpatientId);
        addPatient(patient);
        patientsList.add(patient);
    }

   
    //update exsisiting patient details 
    public void updatePatient(Patient updatedPatient) {
        for (int i = 0; i < patientsList.size(); i++) {
            Patient patient = patientsList.get(i);
            if (patient.getPersonId() == updatedPatient.getPersonId()) {
                patientsList.set(i, updatedPatient);
                System.out.println("Student updated: " + updatedPatient);
                return;
            }
        }
    }

    // Delete exsiting patient by ID 
    public void deletePatient(int id) throws NotFoundException {
        patientsList.removeIf(patientt -> patientt.getPersonId() == id);
    }
    
    //giving a unique ID fro the patient.
     public int getNextPatientId() {
        int maxpatientId = Integer.MIN_VALUE;
        for (Patient patient : patientsList) {
            int userId = patient.getPersonId();
            if (userId > maxpatientId) {
                maxpatientId = userId;
            }
        }
        return maxpatientId + 1;
    }
}


    



