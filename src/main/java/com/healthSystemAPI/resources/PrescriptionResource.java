
package com.healthSystemAPI.resources;


import com.healthSystemAPI.classes.Prescription;
import com.healthSystemAPI.dao.PrescriptionDAO;
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


@Path("/prescriptions")
public class PrescriptionResource {
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private static final Logger w1953568LOGGER = Logger.getLogger(PrescriptionResource.class.getName());

    @GET
    @Path("/getAllPrescriptions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionbyID() {
        try {
            List<Prescription> prescription = prescriptionDAO.getAllPrescriptions();
            if (prescription.isEmpty()) {
            throw new NotFoundException("No such Prescription found.");
            }
            w1953568LOGGER.log(Level.INFO, "Retrieved All Prescription successfully!");
            return Response.ok().entity(prescription).build();
        } catch (Exception ex) {
            throw new NotFoundException("No Prescription found.");
        }
    }


    @GET
    @Path("/{prescriptionsId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionbyID(@PathParam("prescriptionsId") int prescriptionId) throws Exception {
        try {
            Prescription prep = prescriptionDAO.getPrescription(prescriptionId);
            if (prep != null) {
                w1953568LOGGER.log(Level.INFO, "Retrieved Prescription with ID: " + prescriptionId + " successfully");
                return Response.ok().entity(prep).build();
            } else {
                throw new UserNotFoundException("Prescription with ID " + prescriptionId + " not found.");
            }
        } catch (Exception ex) {
            throw new Exception("Failed to fetch the ID : "+ prescriptionId);
        } 
    }
    

    @POST
    @Path("/addPrescriptions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescriptionList) throws Exception {
        try{
            prescriptionDAO.addPrescription(prescriptionList);
            w1953568LOGGER.log(Level.INFO, "Added new Prescription successfully!");
            return Response.ok().entity("Prescription successfully updated.").build();
        } catch (Exception ex){
            throw new Exception("Failed to add Prescription. Please try again later.");
        }
    }


    @PUT
    @Path("/{prescriptionsId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionsId") String prescriptionId, Prescription prescriptionList) throws Exception {
        try {
            int id = Integer.parseInt(prescriptionId);
            Prescription existingPrescription = prescriptionDAO.getPrescription(id);
            if (existingPrescription == null) {
                throw new UserNotFoundException("Prescription with ID " + id + " not found.");
            }
            prescriptionDAO.updatePrescription(prescriptionList);
            w1953568LOGGER.log(Level.INFO, "Prescription with ID "+prescriptionId+" successfully updated!", id);
            return Response.ok().entity("Prescription "+ prescriptionId +" successfully updated.").build();

            } catch (NumberFormatException num) {
                throw new NumberFormatException("Prescription ID must be a valid integer,\nwhat you have passed is: "+prescriptionId);
            } catch (BadRequestException | UserNotFoundException ex) {
                throw new BadRequestException("Error updating Prescription: " +prescriptionId);
        }
    }

    @DELETE
    @Path("/{prescriptionsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePrescription(@PathParam("prescriptionsId") String prescriptionId) throws Exception {
        try {
            int pressId = Integer.parseInt(prescriptionId); 
            Prescription existingPerson = prescriptionDAO.getPrescription(pressId);
            if (existingPerson == null) {
                throw new NotFoundException("Prescription with ID " + pressId + " not found.");
            }
            prescriptionDAO.deletePrescription(pressId);
            w1953568LOGGER.log(Level.INFO, "Prescription deleted successfully!");
            return Response.ok().entity("Prescription successfully deleted.").build();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Prescription ID must be a valid integer,\nwhat you have passed is: "+prescriptionId);
        } catch (NotFoundException ex) {
            throw new UserNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        } catch (Exception ex) {
            throw new Exception("Error occurred while deleting the Prescription" + prescriptionId);
        }
    }

    
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleIncorrectPath(@PathParam("path") String path) {
        throw new NotFoundException("Endpoint "+ path +" does not exist.\nUse /prescriptions/.....valid_endpoint.....");
    }
}

