package com.healthSystemAPI.resources;

import com.healthSystemAPI.classes.Patient;
import com.healthSystemAPI.dao.PatientDAO;
import com.healthSystemAPI.resources.Exception.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/patients")
public class PatientResource {
    private PatientDAO patientDAO = new PatientDAO();
    private static final Logger w1953568LOGGER = Logger.getLogger(PatientResource.class.getName());

    @GET
    @Path("/getAllPatients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientbyId() {
        try {
            List<Patient> patient = patientDAO.getAllPatients();
            if (patient.isEmpty()) {
            throw new NotFoundException("No such Patient found.");
            }
            w1953568LOGGER.log(Level.INFO, "Retrieved All Patient successfully!");
            return Response.ok().entity(patient).build();
        } catch (Exception ex) {
            throw new NotFoundException("No Patient found.");
        }
    }


    @GET
    @Path("/{patientsId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientbyId(@PathParam("patientsId") int patientID) throws Exception {
        try {
            Patient patient = patientDAO.getPatientById(patientID);
            if (patient != null) {
                w1953568LOGGER.log(Level.INFO, "Retrieved Patient with ID: " + patientID + " successfully");
                return Response.ok().entity(patient).build();
            } else {
                throw new UserNotFoundException("Patient with ID " + patientID + " not found.");
            }
        } catch (Exception ex) {
            throw new Exception("Failed to fetch the ID : "+ patientID);
        } 
    }
    

    @POST
    @Path("/addPatients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) throws Exception {
        try {
            // Validate the input
            if (patient == null ||
                patient.getPersonId() <= 0 || 
                patient.getName() == null || patient.getName().isEmpty() ||
                patient.getContactNo() == null || patient.getContactNo().isEmpty() ||
                patient.getAddress() == null || patient.getAddress().isEmpty() ||
                patient.getMedicalHistory() == null || patient.getMedicalHistory().isEmpty() ||
                patient.getCurrentHealthStatus() == null || patient.getCurrentHealthStatus().isEmpty() ||
                patient.getGender() == null || patient.getGender().isEmpty() ||
                patient.getDob() == null || patient.getDob().isEmpty()) {
                throw new BadRequestException("Invalid data provided. Please check the request.");
            }
            // Perform the addition
            patientDAO.addPatient(patient);
            w1953568LOGGER.log(Level.INFO, "Added new Patient successfully!");
            return Response.ok().entity("Patient successfully added.").build();
        } catch (BadRequestException bd) {
            throw new BadRequestException("Invalid data provided.\nPlease check the JSON body or Patient attribute is missing.");
        } catch (Exception ex) {
            throw new Exception("Failed to add Patient. Please try again later.", ex);
        }
    }



    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatients(@PathParam("patientId") String patientId, Patient patient) throws Exception {
        try {
            // Validate patientId as integer
            int id = Integer.parseInt(patientId);
            Patient existingPatient = patientDAO.getPatientById(id);
            if (existingPatient == null) {
                throw new UserNotFoundException("Patient with ID " + id + " not found.");
            }

            // Validate the input
            if (patient == null ||
                patient.getPersonId() != id || // Ensure the ID matches
                patient.getName() == null || patient.getName().isEmpty() ||
                patient.getContactNo() == null || patient.getContactNo().isEmpty() ||
                patient.getAddress() == null || patient.getAddress().isEmpty() ||
                patient.getMedicalHistory() == null || patient.getMedicalHistory().isEmpty() ||
                patient.getCurrentHealthStatus() == null || patient.getCurrentHealthStatus().isEmpty() ||
                patient.getGender() == null || patient.getGender().isEmpty() ||
                patient.getDob() == null || patient.getDob().isEmpty()) {
                throw new BadRequestException("Invalid update data. Please check the request.");
            }

            // Perform the update
            patientDAO.updatePatient(patient);
            w1953568LOGGER.log(Level.INFO, "Patient with ID " + patientId + " successfully updated!", id);
            return Response.ok().entity("Patient " + patientId + " successfully updated.").build();

        } catch (NumberFormatException num) {
            throw new NumberFormatException("Patient ID must be a valid integer. What you have passed is: " + patientId);
        } catch (UserNotFoundException en) {
            throw new UserNotFoundException("Patient with ID " + patientId + " not found.");
        } catch (BadRequestException bd) {
            throw new BadRequestException("Invalid update data for patient ID " + patientId + ". Please check the JSON body or fields are missing.");
        }
}


    @DELETE
    @Path("/{patientsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePatient(@PathParam("patientsId") String patientID) throws Exception {
        try {
            int psId = Integer.parseInt(patientID); 
            Patient existingPatient = patientDAO.getPatientById(psId);
            if (existingPatient == null) {
                throw new NotFoundException("Patient with ID " + psId + " not found.");
            }
            patientDAO.deletePatient(psId);
            w1953568LOGGER.log(Level.INFO, "Patient deleted successfully!");
            return Response.ok().entity("Patient successfully deleted.").build();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Patient ID must be a valid integer,\nwhat you have passed is: "+patientID);
        } catch (NotFoundException ex) {
            throw new UserNotFoundException("Patient with ID " + patientID + " not found.");
        } catch (Exception ex) {
            throw new Exception("Error occurred while deleting the Patient" + patientID);
        }
    }

    
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleIncorrectPath(@PathParam("path") String path) {
        throw new NotFoundException("Endpoint "+ path +" does not exist.\nUse /patients/.....valid_endpoint.....");
    }
}

