package com.amitcodes.uservices.resources;

import com.amitcodes.uservices.transform.DocumentTransformer;
import com.amitcodes.uservices.dao.JongoDocumentDao;
import com.amitcodes.uservices.dto.DocumentDTO;
import com.amitcodes.uservices.dto.SearchResultDTO;
import com.amitcodes.uservices.model.Document;
import com.codahale.metrics.annotation.Timed;

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
import java.util.List;

//NOTE: Resource classes are used concurrently my multiple threads.
//      Hence should be stateless and thread-safe;
@Path("/documents")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class DocumentResource
{

    // default response-body if request was processed successfully
    private static final String OK = "{\"response\": \"ok\"}";

    private JongoDocumentDao dao;
    private DocumentTransformer transformer;

    public DocumentResource(JongoDocumentDao dao, DocumentTransformer transformer)
    {
        this.dao = dao;
        this.transformer = transformer;
    }

    @POST
    @Timed
    // Http Response Codes: Success = 200, Exception = 500
    public Response insert(DocumentDTO documentDTO)
    {
        Document document = transformer.toDocument(documentDTO);
        dao.insert(document);
        return Response.status(Response.Status.OK).entity(OK).build();
    }

    @GET
    @Timed
    @Path("{documentId}")
    // Http Response Codes: Success = 200, Not Found = 404, Exception = 500
    public Response get(@PathParam("documentId") String documentId)
    {
        Document document = dao.get(documentId);
        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DocumentDTO dto = transformer.toDocumentDTO(document);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @PUT
    @Timed
    // Http Response Codes: Success = 200, Exception = 500
    public Response update(DocumentDTO documentDTO)
    {
        Document document = transformer.toDocument(documentDTO);
        dao.update(document);
        return Response.status(Response.Status.OK).entity(OK).build();
    }

    @DELETE
    @Timed
    @Path("{documentId}")
    // Http Response Codes: Success = 200, Exception = 500
    public Response delete(@PathParam("documentId") String documentId)
    {
        dao.delete(documentId);
        return Response.status(Response.Status.OK).entity(OK).build();
    }

    @GET
    @Timed
    @Path("/search/{criteria}/{limit}")
    // Http Response Codes: Success = 200, No results = 404, Exception = 500
    public Response find(@PathParam("criteria") String criteria,
                         @PathParam("limit") int limit)
    {
        List<Document> result = dao.find(criteria, limit);
        Response.Status status = result.isEmpty() ? Response.Status.NOT_FOUND : Response.Status.OK;

        List<DocumentDTO> dtos = transformer.toDocumentDTO(result);
        SearchResultDTO searchResult = new SearchResultDTO(dtos.size(), dtos);
        return Response.status(status).entity(searchResult).build();
    }
}
