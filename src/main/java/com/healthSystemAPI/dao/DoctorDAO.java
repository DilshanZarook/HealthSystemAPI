package com.healthSystemAPI.dao;

import com.healthSystemAPI.classes.Doctor;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO extends PersonDAO {
    //Store Doctors in an ArrayList
    private static List<Doctor> doctorsList = new ArrayList<>();

    static {
        doctorsList.add(new Doctor(003, "Dr.Bhathiya", "07730310100", "1247/s parakkarma mawatha, hunupityaa wattala", "Sergeon"));
        doctorsList.add(new Doctor(005, "Dr,katukurunada", "07730310100", "123/d Rajagiriya, colombo 10  ", "Dentist"));
    }

    //Method to retriive All Doctors Details
    public static List<Doctor> getAllDoctors() {
        return doctorsList;
    }

    //Method to retriive doctors by ID, browsing throw entire doctorsList
    public Doctor getDoctorById(int id) throws NotFoundException {
        for (Doctor doctor : doctorsList) {
            if (doctor.getPersonId() == id) {
                return doctor;
            }
        }
        throw new NotFoundException("Doctor not found with ID: " + id);
    }

    // Creating the new doctor Detail in the(doctorsList)
    public void addDoctor(Doctor doctor) {
        int newDoctorId = getNextDoctorId();
        doctor.setPersonId(newDoctorId);
        doctorsList.add(doctor);
    }

    // Updating the Exsisitng Doctors by an ID in the (doctorsList)
    public void updateDoctor(Doctor updatedStudent) {
        for (int i = 0; i < doctorsList.size(); i++) {
            Doctor doctor = doctorsList.get(i);
            if (doctor.getPersonId() == updatedStudent.getPersonId()) {
                doctorsList.set(i, updatedStudent);
                System.out.println("Student updated: " + updatedStudent);
                return;
            }
        }
    }

    // Deleting the Exsisitng Doctors by an ID in the (doctorsList)
    public void deleteDoctor(int id) throws NotFoundException {
        doctorsList.removeIf(doctor1 -> doctor1.getPersonId() == id);
    }
    
    // The below method act as To maintain the Unique ID for doctors.
    public int getNextDoctorId() {
        int maxDoctorId = Integer.MIN_VALUE;

        for (Doctor doctor : doctorsList) {
            int userId = doctor.getPersonId();
            if (userId > maxDoctorId) {
                maxDoctorId = userId;
            }
        }
        return maxDoctorId + 1;
    }
}
