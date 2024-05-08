
package com.healthSystemAPI.dao;


import com.healthSystemAPI.classes.Doctor;
import com.healthSystemAPI.classes.Patient;
import com.healthSystemAPI.classes.Prescription;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PrescriptionDAO {
    //store all prescription 
    private static List<Prescription> prescriptionsList = new ArrayList<>();
    
    static {
        List<Patient> allPatients = PatientDAO.getAllPatients();
        List<Doctor> allDoctors = DoctorDAO.getAllDoctors();

        prescriptionsList.add(new Prescription(allPatients.get(0), allDoctors.get(0), "Amlodipine 5mg", "Take one tablet daily"));
        prescriptionsList.add(new Prescription(allPatients.get(1), allDoctors.get(1), "Amlodipine 5mg", "Take three tablet daily"));
    }
    
    //get all prescription
    public List<Prescription> getAllPrescriptions() {
        return prescriptionsList;
    }
    
    //get specific prescription by an ID 
     public Prescription getPrescription(int id) throws NotFoundException {
        for (Prescription prescription : prescriptionsList) {
            if (prescription.getPatient().getPersonId() == id) {
                return prescription;
            }
        }
        return null;
    }

    //add new prescription to the List 
    public void addPrescription(Prescription prescription) {
        prescriptionsList.add(prescription);
    }

    //update the exssiting prescription by ID 
     public void updatePrescription(Prescription updatedPrescription) {
        for (int i = 0; i < prescriptionsList.size(); i++) {
            Prescription prescription = prescriptionsList.get(i);
            if (prescription.getPatient().getPersonId() == updatedPrescription.getPatient().getPersonId()) {
                prescriptionsList.set(i, updatedPrescription);
                System.out.println("Student updated: " + updatedPrescription);
                return;
            }
        }
    }

    //deleting the exsisitng prescription by ID 
    public void deletePrescription(int id) throws NotFoundException {
        Prescription prescription = getPrescription(id);
        prescriptionsList.remove(prescription);
    }
}
