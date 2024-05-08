package com.healthSystemAPI.resources;

import com.healthSystemAPI.classes.Appointment;
import com.healthSystemAPI.dao.AppointmentDAO;
import com.healthSystemAPI.resources.Exception.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;


@Path("/appointments")
public class AppointmentsResource {
    // Initializes the DAO layer for appointments
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    //Logger intialization
    private static final Logger w1953568LOGGER = Logger.getLogger(AppointmentsResource.class.getName());

    //Retriving all Appointments and return it as JASON format, Used Produces MIME as APPILICATION JASON.
    @GET
    @Path("/getAllAppointments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            if (appointments.isEmpty()) {
            throw new NotFoundException("No appointments found.");
            }
            w1953568LOGGER.log(Level.INFO, "Retrieved All Appoitnemnts successfully!");
            return Response.ok().entity(appointments).build();
        } catch (Exception ex) {
            throw new NotFoundException("No appointments found.");
        }
    }

    // Retrieves a specific appointment by its ID and returns it as a JSON response
    @GET
    @Path("/{appointmentId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentsbyID(@PathParam("appointmentId") int appId) throws Exception {
        try {
            Appointment appointment = appointmentDAO.getAppointmentsbyID(appId);
            if (appointment != null) {
                w1953568LOGGER.log(Level.INFO, "Retrieved appointment with ID: " + appId + " successfully");
                return Response.ok().entity(appointment).build();
            } else {
                throw new UserNotFoundException("Appointment with ID " + appId + " not found.");
            }
        } catch (Exception ex) {
            throw new Exception("Failed to fetch the ID : "+ appId);
        } 
    }
    
    // Adds a new appointment to the database and returns a success message
    @POST
    @Path("/addAppointments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointments(Appointment appointment) throws Exception {
        try {
            if (appointment == null ||
            appointment.getPatient() == null ||
            appointment.getPatient().getPersonId() <= 0 ||
            appointment.getPatient().getName() == null || appointment.getPatient().getName().isEmpty() ||
            appointment.getDoctor() == null ||
            appointment.getDoctor().getPersonId() <= 0 ||
            appointment.getDoctor().getName() == null || appointment.getDoctor().getName().isEmpty() ||
            appointment.getDate() == null || appointment.getDate().isEmpty() ||
            appointment.getTime() == null || appointment.getTime().isEmpty()) {
            throw new BadRequestException("Invalid update data. Please check the request.");
        }
            appointmentDAO.addAppointments(appointment);
            w1953568LOGGER.log(Level.INFO, "Added new appointment successfully!");
            return Response.ok().entity("Appointment successfully added.").build();
        } catch (BadRequestException ex) {
            throw new BadRequestException("Invalid data provided.\nPlease check the JASON body  or appopintment attribute is missing.");
        } catch (Exception ex) {
            throw new Exception("Failed to add appointment. Please try again later.");
        }
    }


    //Update the particular Appointment ID and return the success message.
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") String appId, Appointment appointment) throws Exception {
        try {
            //if the appID is NULL that's not a valid request to update, handles that below 
            int id = Integer.parseInt(appId);
            Appointment existingAppointment = appointmentDAO.getAppointmentsbyID(id);
            if (existingAppointment == null) {
                 throw new UserNotFoundException("Appointment with ID " + id + " not found.");
            }

            //if JASON body is consist with NULL or missing parsmeters checking
            if (appointment == null ||
                appointment.getPatient() == null ||
                appointment.getPatient().getPersonId() <= 0 ||
                appointment.getPatient().getName() == null || appointment.getPatient().getName().isEmpty() ||
                appointment.getDoctor() == null ||
                appointment.getDoctor().getPersonId() <= 0 ||
                appointment.getDoctor().getName() == null || appointment.getDoctor().getName().isEmpty() ||
                appointment.getDate() == null || appointment.getDate().isEmpty() ||
                appointment.getTime() == null || appointment.getTime().isEmpty()) {
                throw new BadRequestException("Invalid update data. Please check the request.");
            }

            appointmentDAO.updateAppointment(appointment);
            w1953568LOGGER.log(Level.INFO, "Appointment with ID " + appId + " successfully updated!");
            return Response.ok().entity("Appointment " + appId + " successfully updated.").build();

        } catch (NumberFormatException num) {
            throw new NumberFormatException("Appointment ID must be a valid integer.\nWhat you have passed is: " + appId);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException("Appointment with ID " + appId + " not found.");
        } catch (BadRequestException ex) {
            throw new BadRequestException("Invalid update data for appointment ID " + appId + ".\nplease do check the JASONbody or feild is missing");
        } catch (Exception ex) {
            throw new Exception("Error occurred while updating appointment with ID " + appId + ".", ex);
        }
    }

    
    //Deleting the particular Appoitment by ID.
    @DELETE
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAppointment(@PathParam("appointmentId") String appointmentId) throws Exception {
        try {
            int appId = Integer.parseInt(appointmentId); 
            Appointment existingAppointment = appointmentDAO.getAppointmentsbyID(appId);
            if (existingAppointment == null) {
                throw new NotFoundException("Appointment with ID " + appId + " not found.");
            }
            appointmentDAO.deleteAppointment(appId);
            w1953568LOGGER.log(Level.INFO, "Appointment deleted successfully!");
            return Response.ok().entity("Appointment successfully deleted.").build();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Appointment ID must be a valid integer,\nWhat you have passed is: "+appointmentId);
        } catch (NotFoundException ex) {
            throw new UserNotFoundException("Appointment with ID " + appointmentId + " not found.");
        } catch (Exception ex) {
            throw new Exception("Error occurred while deleting the appointment" + appointmentId);
        }
    }

    
    // Handles requests to non-existent endpoints by throwing a NotFoundException,
    // This may cause if user mistakley entered a diffrent path parameter in the URI
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleIncorrectPath(@PathParam("path") String path) {
        throw new NotFoundException("Endpoint "+ path +" does not exist.\nUse /appointments/.....valid_endpoint.....");
    }
}
