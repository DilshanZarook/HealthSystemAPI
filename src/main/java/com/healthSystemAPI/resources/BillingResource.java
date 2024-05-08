
package com.healthSystemAPI.resources;

import com.healthSystemAPI.classes.Billing;
import com.healthSystemAPI.dao.BillingDAO;
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


@Path("/billings")
public class BillingResource {
    private BillingDAO billingDAO = new BillingDAO();
    private static final Logger w1953568LOGGER = Logger.getLogger(BillingResource.class.getName());

    @GET
    @Path("/getAllBillings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingbyID() {
        try {
            List<Billing> bills = billingDAO.getAllBillings();
            if (bills.isEmpty()) {
            throw new NotFoundException("No bills found.");
            }
            w1953568LOGGER.log(Level.INFO, "Retrieved All Bills successfully!");
            return Response.ok().entity(bills).build();
        } catch (Exception ex) {
            throw new NotFoundException("No Bills found.");
        }
    }


    @GET
    @Path("/{billingId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingbyID(@PathParam("billingId") int billingId) throws Exception {
        try {
            Billing bill = billingDAO.getBilling(billingId);
            if (bill != null) {
                w1953568LOGGER.log(Level.INFO, "Retrieved Bills with ID: " + billingId + " successfully");
                return Response.ok().entity(bill).build();
            } else {
                throw new UserNotFoundException("Bill with ID " + billingId + " not found.");
            }
        } catch (NotFoundException ex) {
            throw new NotFoundException("Failed to fetch the ID : "+ billingId);
        } 
    }
    

    @POST
    @Path("/addBillings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBillings(Billing newBilling) throws Exception {
        try{
            billingDAO.addBilling(newBilling);
            w1953568LOGGER.log(Level.INFO, "Added new Bill successfully!");
            return Response.ok().entity("Bill successfully updated.").build();
        } catch (Exception ex){
            throw new Exception("Failed to add Bill. Please try again later.");
        }
    }


    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("billingId") String billingId, Billing bills) throws Exception {
        try {
            int id = Integer.parseInt(billingId);
            Billing existingBills = billingDAO.getBilling(id);
            if (existingBills == null) {
                throw new UserNotFoundException("Bill with ID " + id + " not found.");
            }

            if (bills == null || bills.getBillingId() != id) {
                throw new BadRequestException("Invalid update data. Billing ID in the update does not match the path.");
            }
            billingDAO.updateBilling(bills);
            w1953568LOGGER.log(Level.INFO, "Bill with ID "+billingId+" successfully updated!", id);
            return Response.ok().entity("Bill"+ billingId +" successfully updated.").build();

            } catch (NumberFormatException num) {
                throw new NumberFormatException("Bill ID must be a valid integer,\nwhat you have passed is: "+billingId);
            } catch (BadRequestException | UserNotFoundException ex) {
                throw new BadRequestException("Error updating Bill: " +billingId);
            } catch (Exception ex) {
                 throw new Exception("Error occurred while updating Bills." + billingId);
        }
    }

    @DELETE
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBilling(@PathParam("billingId") String billingId) throws Exception {
        try {
            int billId = Integer.parseInt(billingId); 
            Billing existingbill = billingDAO.getBilling(billId);
            if (existingbill == null) {
                throw new NotFoundException("Bill with ID " + billId + " not found.");
            }
            billingDAO.deleteBills(billId);
            w1953568LOGGER.log(Level.INFO, "Bill deleted successfully!");
            return Response.ok().entity("Bill successfully deleted.").build();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Bill ID must be a valid integer,\nwhat you have passed is: "+billingId);
        } catch (NotFoundException ex) {
            throw new UserNotFoundException("Bill with ID " + billingId + " not found.");
        } catch (Exception ex) {
            throw new Exception("Error occurred while deleting the bill" + billingId);
        }
    }

    
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleIncorrectPath(@PathParam("path") String path) {
        throw new NotFoundException("Endpoint "+ path +" does not exist.\nUse /billings/.....valid_endpoint.....");
    }
}
