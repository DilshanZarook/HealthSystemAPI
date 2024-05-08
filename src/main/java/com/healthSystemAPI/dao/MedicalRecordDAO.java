
package com.healthSystemAPI.dao;


import com.healthSystemAPI.classes.MedicalRecord;
import com.healthSystemAPI.classes.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;


public class MedicalRecordDAO {
    //Store medical details in an ArrayList
    private static List<MedicalRecord> medicalRecordsList = new ArrayList<>();
    
    static List<Patient> allRecords = PatientDAO.getAllPatients();
    
    static {
    
        medicalRecordsList.add(new MedicalRecord(1, allRecords.get(0).getPersonId(), "morning", "Daily"));
        medicalRecordsList.add(new MedicalRecord(2, allRecords.get(1).getPersonId(), "night", "clinic"));
        medicalRecordsList.add(new MedicalRecord(3, allRecords.get(2).getPersonId(), "morning", "Daily"));
        medicalRecordsList.add(new MedicalRecord(4, allRecords.get(3).getPersonId(), "night", "Clinic"));
    }
    
    //get all medical records 
      public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordsList;
    }
      
      
    //get specific medical record by an ID 
    public MedicalRecord getMedicalRecord(int id) {
        int i =0;
        for (MedicalRecord medicalRecord : medicalRecordsList) {
            if (medicalRecord.getPatientId() == id ) {
              
                return medicalRecord;  
            }
            i++;
        }
        return null;
    }
    
   
    //addig new medical record by ID 
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordsList.add(medicalRecord);
        
    }


    // Update exsisitng medical record by ID 
    public void updateMedicalRecord(int id, MedicalRecord updatedMedicalRecord) throws NotFoundException {
        MedicalRecord medicalRecord = getMedicalRecord(id);
       
    }

    // Delete exssiting medical record by ID 
    public void deleteMedicalRecord(int id) throws NotFoundException {
        MedicalRecord medicalRecord = getMedicalRecord(id);
        medicalRecordsList.remove(medicalRecord);
    }
}

    


