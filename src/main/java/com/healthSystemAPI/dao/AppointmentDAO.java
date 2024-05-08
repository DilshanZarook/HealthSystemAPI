package com.healthSystemAPI.dao;

import com.healthSystemAPI.classes.Appointment;
import com.healthSystemAPI.classes.Doctor;
import com.healthSystemAPI.classes.Patient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;


public class AppointmentDAO {
    //Store Appoitnemtns in an ArrayList
    private static List<Appointment> appointmentsList = new ArrayList<>();


    static {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String formattedDate = date.format(dateFormatter);
        String formattedTime = time.format(timeFormatter);

        List<Patient> allPatients = PatientDAO.getAllPatients();
        List<Doctor> allDoctors = DoctorDAO.getAllDoctors();

        appointmentsList.add(new Appointment(1, allPatients.get(0), allDoctors.get(0), formattedDate, formattedTime));
        appointmentsList.add(new Appointment(2, allPatients.get(1), allDoctors.get(1), formattedDate, formattedTime));
    }
    
    
    //Method to retriive All appoitnemnts
     public List<Appointment> getAllAppointments() {
        return appointmentsList;
    }
     
     //Method to retriive appoitnemnts by ID, browsing throw entire appointmentList
      public Appointment getAppointmentsbyID(int appointmentId) {
        
        for (Appointment appointment : appointmentsList) {
            if (appointment.getAppId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }
      
    // Creating the new Appoitment in the Appoitments.(appointmentList)
    public void addAppointments(Appointment appointment) {
        int newUserId = getNextAppointmentId();
        appointment.setAppId(newUserId);
        appointmentsList.add(appointment);
    }

   

    // Updating the Exsisitng Appointment by an ID in the (appointmentList)
    public void updateAppointment(int appointmentId, Appointment updatedAppointment) throws NotFoundException {
        Appointment appointment = getAppointmentsbyID(appointmentId);
        appointment.setDate(updatedAppointment.getDate());
    }
    
      public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointmentsList.size(); i++) {
            Appointment appointment = appointmentsList.get(i);
            if (appointment.getAppId() == updatedAppointment.getAppId()) {
                appointmentsList.set(i, updatedAppointment);
                System.out.println("Student updated: " + updatedAppointment);
                return;
            }
        }
    }
      
    // Deleting the Exsisitng Appointment by an ID in the (appointmentList)
    public void deleteAppointment(int appointmentId) throws NotFoundException {
        appointmentsList.removeIf(appoin -> appoin.getAppId() == appointmentId);
    }
    
    
    // The below method act as To maintain the Unique ID for appointments.
    public int getNextAppointmentId() {
        int maxUserId = Integer.MIN_VALUE;
        for (Appointment apointment : appointmentsList) {
            int AppointmentId = apointment.getAppId();
            if (AppointmentId > maxUserId) {
                maxUserId = AppointmentId;
            }
        }
        return maxUserId + 1;
    }
}