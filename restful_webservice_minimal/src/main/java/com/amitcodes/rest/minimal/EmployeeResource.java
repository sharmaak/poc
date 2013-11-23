package com.amitcodes.rest.minimal;

import com.sun.jersey.api.NotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/name")
public class EmployeeResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee addEmployee(Employee employee) {
        return EmployeeDAO.getInstance().addEmployee(employee);

        // curl -X POST -H "Content-Type: application/json" \
        //      -d '{"id":"100","firstName":"Anupam", "lastName":"Asthana", "age":"45"}' \
        //      http://localhost:8090/restful_webservice_minimal/rest/name
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") long id) {
        Employee employee = EmployeeDAO.getInstance().fetchEmployee(id);
        if(employee == null) {
            // return 404
            // throw new NotFoundException("Employee with id=" + id + " not found");
            return Response.status(Response.Status.NOT_FOUND).entity(null).build();
        }
        return Response.status(Response.Status.OK).entity(employee).build();

        // curl -i -X GET 'http://localhost:8090/restful_webservice_minimal/rest/name/{id}'
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) {
        EmployeeDAO.getInstance().updateEmployee(employee);
        return Response.noContent().build();
        // curl -X PUT -H "Content-Type: application/json" \
        //      -d '{"id":"100","firstName":"XXX", "lastName":"Asthana", "age":"45"}' \
        //      http://localhost:8090/restful_webservice_minimal/rest/name
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") Long id) {
        EmployeeDAO.getInstance().deleteEmployee(id);
        return Response.noContent().build();
        // curl -I -X DELETE 'http://localhost:8090/restful_webservice_minimal/rest/name/{id}'
    }
}
