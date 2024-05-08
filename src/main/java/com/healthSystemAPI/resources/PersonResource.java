
package com.healthSystemAPI.resources;

import com.healthSystemAPI.classes.Person;
import com.healthSystemAPI.dao.PersonDAO;
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


@Path("/persons")
public class PersonResource {
    private PersonDAO personsDAO = new PersonDAO();
    private static final Logger w1953568LOGGER = Logger.getLogger(PersonResource.class.getName());

//    @GET
//    @Path("/getAllPersons")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getPersonbyID() {
//        try {
//            List<Person> persons = personsDAO.getPerson(0);
//            if (persons.isEmpty()) {
//            throw new NotFoundException("No such Person found.");
//            }
//            w1953568LOGGER.log(Level.INFO, "Retrieved All Person successfully!");
//            return Response.ok().entity(persons).build();
//        } catch (Exception ex) {
//            throw new NotFoundException("No person found.");
//        }
//    }


    @GET
    @Path("/{personsId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonbyID(@PathParam("personsId") int personId) throws Exception {
        try {
            Person bill = personsDAO.getPerson(personId);
            if (bill != null) {
                w1953568LOGGER.log(Level.INFO, "Retrieved Person with ID: " + personId + " successfully");
                return Response.ok().entity(bill).build();
            } else {
                throw new UserNotFoundException("Person with ID " + personId + " not found.");
            }
        } catch (Exception ex) {
            throw new Exception("Failed to fetch the ID : "+ personId);
        } 
    }
    

    @POST
    @Path("/addPersons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(Person personList) throws Exception {
        try{
            personsDAO.addAllPerson(personList);
            w1953568LOGGER.log(Level.INFO, "Added new Person successfully!");
            return Response.ok().entity("Person successfully updated.").build();
        } catch (Exception ex){
            throw new Exception("Failed to add Person. Please try again later.");
        }
    }


    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") String personId, Person personList) throws Exception {
        try {
            int id = Integer.parseInt(personId);
            Person existingPerson = personsDAO.getPerson(id);
            if (existingPerson == null) {
                throw new UserNotFoundException("Person with ID " + id + " not found.");
            }

            if (personList == null || personList.getPersonId() != id) {
                throw new BadRequestException("Invalid update data. Person ID in the update does not match the path.");
            }
            personsDAO.updatePerson(id,personList);
            w1953568LOGGER.log(Level.INFO, "Person with ID "+personId+" successfully updated!", id);
            return Response.ok().entity("Person "+ personId +" successfully updated.").build();

            } catch (NumberFormatException num) {
                throw new NumberFormatException("Person ID must be a valid integer,\nwhat you have passed is: "+personId);
            } catch (BadRequestException | UserNotFoundException ex) {
                throw new BadRequestException("Error updating Person: " +personId);
            } catch (Exception ex) {
                 throw new Exception("Error occurred while updating Person." + personId);
        }
    }

    @DELETE
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("personId") String personId) throws Exception {
        try {
            int psId = Integer.parseInt(personId); 
            Person existingPerson = personsDAO.getPerson(psId);
            if (existingPerson == null) {
                throw new NotFoundException("Person with ID " + psId + " not found.");
            }
            personsDAO.deletePerson(psId);
            w1953568LOGGER.log(Level.INFO, "Person deleted successfully!");
            return Response.ok().entity("Person successfully deleted.").build();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Person ID must be a valid integer,\nwhat you have passed is: "+personId);
        } catch (NotFoundException ex) {
            throw new UserNotFoundException("Person with ID " + personId + " not found.");
        } catch (Exception ex) {
            throw new Exception("Error occurred while deleting the Person" + personId);
        }
    }

    
    @GET
    @Path("{path:.*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleIncorrectPath(@PathParam("path") String path) {
        throw new NotFoundException("Endpoint "+ path +" does not exist.\nUse /persons/.....valid_endpoint.....");
    }
}
