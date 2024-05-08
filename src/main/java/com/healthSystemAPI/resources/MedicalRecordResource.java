package com.healthSystemAPI.resources;



import com.healthSystemAPI.classes.MedicalRecord;
import com.healthSystemAPI.dao.MedicalRecordDAO;
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



@Path("/medicalRecords")
public class MedicalRecordResource {
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private static final Logger w1953568LOGGER = Logger.getLogger(MedicalRecordResource.class.getName());

    @GET
    @Path("/getAllMedicalRecords")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordsbyId() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getAllMedicalRecords();
            if (medicalRecords.isEmpty()) {
            throw new NotFoundException("No such Medical Record is found.");
            }
            w1953568LOGGER.log(Level.INFO, "Retrieved All Medical Records successfully!");
            return Response.ok().entity(medicalRecords).build();
        } catch (Exception ex) {
            throw new NotFoundException("No Medical Record found.");
        }
    }


    @GET
    @Path("/{medicalRecordsId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordsbyId(@PathParam("medicalRecordsId") int medicalRecordID) throws Exception {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecord(medicalRecordID);
            if (medicalRecord != null) {
                w1953568LOGGER.log(Level.INFO, "Retrieved Medical Records with ID: " + medicalRecordID + " successfully");
                return Response.ok().entity(medicalRecord).build();
            } else {
                throw new UserNotFoundException("Medical Records with ID " + medicalRecordID + " not found.");
            }
        } catch (Exception ex) {
            throw new Exception("Failed to fetch the ID : "+ medicalRecordID);
        } 
    }
    

    @POST
    @Path("/addMedicalRecords")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalRecords(MedicalRecord medicalRecordsList) throws Exception {
        try{
            medicalRecordDAO.addMedicalRecord(medicalRecordsList);
            w1953568LOGGER.log(Level.INFO, "Added new Medical Record successfully!");
            return Response.ok().entity("Medical Record successfully updated.").build();
        } catch (Exception ex){
            throw new Exception("Failed to add Medical Record. Please try again later.");
        }
    }


//    @PUT
//    @Path("/{medicalRecordsId}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateMedicalRecords(@PathParam("medicalRecordsId") String medicalRecordID, MedicalRecord medicalRecordsList) throws Exception {
//        try {
//            int id = Integer.parseInt(medicalRecordID);
//            MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecord(id);
//            if (existingMedicalRecord == null) {
//                throw new UserNotFoundException("Medical Record with ID " + id + " not found.");
//            }
//
//            if (medicalRecordsList == null || medicalRecordsList.getMedicalRecordId() != id) {
//                throw new BadRequestException("Invalid update data. Medical Record ID in the update does not match the path.");
//            }
//            medicalRecordDAO.updateMedicalRecord(medicalRecordID, medicalRecordsList);
//            w1953568LOGGER.log(Level.INFO, "Medical Record with ID "+medicalRecordID+" successfully updated!", id);
//            return Response.ok().entity("Medical Record "+ medicalRecordID +" successfully updated.").build();
//
//            } catch (NumberFormatException num) {
//                throw new NumberFormatException("Medical Record ID must be a valid integer,\nwhat you have passed is: "+medicalRecordID);
//            } catch (BadRequestException | UserNotFoundException ex) {
//                throw new BadRequestException("Error updating Medical Record: " +medicalRecordID);
//            } catch (Exception ex) {
//                 throw new Exception("Error occurred while updating Medical Record." + medicalRecordID);
//        }
//    }

    @DELETE
    @Path("/{medicalRecordsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMedicalRecord(@PathParam("medicalRecordsId") String medicalRecordID) throws Exception {
        try {
            int mDId = Integer.parseInt(medicalRecordID); 
            MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecord(mDId);
            if (existingMedicalRecord == null) {
                throw new NotFoundException("Medical Record with ID " + mDId + " not found.");
            }
            medicalRecordDAO.deleteMedicalRecord(mDId);
            w1953568LOGGER.log(Level.INFO, "Medical Record deleted successfully!");
            return Response.ok().entity("Medical Record successfully deleted.").build();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Medical Record ID must be a valid integer,\nwhat you have passed is: "+medicalRecordID);
        } catch (NotFoundException ex) {
            throw new UserNotFoundException("Medical Record with ID " + medicalRecordID + " not found.");
        } catch (Exception ex) {
            throw new Exception("Error occurred while deleting the Medical Record" + medicalRecordID);
        }
    }

    
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleIncorrectPath(@PathParam("path") String path) {
        throw new NotFoundException("Endpoint "+ path +" does not exist.\nUse /medicalRecords/.....valid_endpoint.....");
    }
}

