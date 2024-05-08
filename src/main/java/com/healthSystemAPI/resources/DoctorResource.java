package com.healthSystemAPI.resources;

import com.healthSystemAPI.classes.Doctor;
import com.healthSystemAPI.dao.DoctorDAO;
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



@Path("/doctors")
public class DoctorResource {
    private DoctorDAO doctorsDAO = new DoctorDAO();
    private static final Logger w1953568LOGGER = Logger.getLogger(DoctorResource.class.getName());

    @GET
    @Path("/getAllDoctors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorsbyID() {
        try {
            List<Doctor> doctors = doctorsDAO.getAllDoctors();
            if (doctors.isEmpty()) {
            throw new NotFoundException("No such Doctors found.");
            }
            w1953568LOGGER.log(Level.INFO, "Retrieved All Doctors successfully!");
            return Response.ok().entity(doctors).build();
        } catch (Exception ex) {
            throw new NotFoundException("No Dcotors found.");
        }
    }


    @GET
    @Path("/{doctorsId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorsbyID(@PathParam("doctorsId") int doctorID) throws Exception {
            Doctor bill = doctorsDAO.getDoctorById(doctorID);
            if (bill != null) {
                w1953568LOGGER.log(Level.INFO, "Retrieved Doctors with ID: " + doctorID + " successfully");
                return Response.ok().entity(bill).build();
            } else {
                throw new UserNotFoundException("Doctors with ID " + doctorID + " not found.");
            }
        
    }
    
    
    @POST
    @Path("/addDoctors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDoctors(Doctor doctor) throws Exception {
        try {
            if (doctor == null ||
                doctor.getPersonId() <= 0 || 
                doctor.getName() == null || doctor.getName().isEmpty() ||
                doctor.getContactNo() == null || doctor.getContactNo().isEmpty() ||
                doctor.getAddress() == null || doctor.getAddress().isEmpty() ||
                doctor.getDoctorSpeciality() == null || doctor.getDoctorSpeciality().isEmpty()) {
                throw new BadRequestException("Invalid update data. Please check the request.");
            }
            doctorsDAO.addDoctor(doctor);
            w1953568LOGGER.log(Level.INFO, "Added new Doctor successfully!");
            return Response.ok().entity("Doctor successfully added.").build();
        } catch (BadRequestException ex) {
            throw new BadRequestException("Invalid data provided.\nPlease check the JASON body  or Doctor attribute is missing.");
        } catch (Exception ex) {
            throw new Exception("Failed to add appointment. Please try again later.");
        }
    }
    

    
    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") String doctorId, Doctor doctor) throws Exception {
        try {
            // Check if the doctorId is a valid integer
            int id = Integer.parseInt(doctorId);
            Doctor existingDoctor = doctorsDAO.getDoctorById(id);
            if (existingDoctor == null) {
                throw new UserNotFoundException("Doctor with ID " + id + " not found.");
            }

            // Validate the input
            if (doctor == null ||
                doctor.getPersonId() != id || // Ensure the ID matches
                doctor.getName() == null || doctor.getName().isEmpty() ||
                doctor.getContactNo() == null || doctor.getContactNo().isEmpty() ||
                doctor.getAddress() == null || doctor.getAddress().isEmpty() ||
                doctor.getDoctorSpeciality() == null || doctor.getDoctorSpeciality().isEmpty()) {
                throw new BadRequestException("Invalid update data. Please check the request.");
            }

            // Perform the update
            doctorsDAO.updateDoctor(doctor);
            w1953568LOGGER.log(Level.INFO, "Doctor with ID " + doctorId + " successfully updated!", id);
            return Response.ok().entity("Doctor " + doctorId + " successfully updated.").build();

        } catch (NumberFormatException num) {
            throw new NumberFormatException("Doctor ID must be a valid integer.\nWhat you have passed is: " + doctorId);
        } catch (UserNotFoundException ur) {
            throw new UserNotFoundException("Doctor with ID " + doctorId + " not found.");
        } catch (BadRequestException bd) {
            throw new BadRequestException("Invalid update data for doctor ID " + doctorId + ".\nPlease check the JSON body or fields are missing.");
        }
    }


    @DELETE
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBilling(@PathParam("doctorId") String doctorId) throws Exception {
        try {
            int docId = Integer.parseInt(doctorId); 
            Doctor existingdoctors = doctorsDAO.getDoctorById(docId);
            if (existingdoctors == null) {
                throw new NotFoundException("Doctor with ID " + docId + " not found.");
            }
            doctorsDAO.deleteDoctor(docId);
            w1953568LOGGER.log(Level.INFO, "Doctor deleted successfully!");
            return Response.ok().entity("Doctor successfully deleted.").build();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Doctor ID must be a valid integer,\nwhat you have passed is: "+doctorId);
        } catch (NotFoundException ex) {
            throw new UserNotFoundException("Doctor with ID " + doctorId + " not found.");
        } catch (Exception ex) {
            throw new Exception("Error occurred while deleting the Doctor" + doctorId);
        }
    }

    
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleIncorrectPath(@PathParam("path") String path) {
        throw new NotFoundException("Endpoint "+ path +" does not exist.\nUse /doctors/.....valid_endpoint.....");
    }
}

