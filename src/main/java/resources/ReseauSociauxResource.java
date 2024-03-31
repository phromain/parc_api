package resources;

import Dto.ReseauSociauxInfoDto;
import entities.ReseauSociauxEntity;
import entrant.ReseauSociaux;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.ReseauSociauxRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/reseauSociaux")

public class ReseauSociauxResource {

    @Inject
    ReseauSociauxRepository reseauSociauxRepository;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des reseauSociauxs", description = "Retourne une liste des reseauSociauxs")
    @APIResponse(responseCode = "200", description = " Liste ReseauSociaux")
    public Response getListReseauSociaux() {
        List<ReseauSociauxEntity> listReseauSociaux = reseauSociauxRepository.listAll();
        List<ReseauSociauxInfoDto> listReseauSociauxInfoDto = new ArrayList<>();
        for (ReseauSociauxEntity reseauSociaux : listReseauSociaux) {
            ReseauSociauxInfoDto reseauSociauxInfoDto = new ReseauSociauxInfoDto(reseauSociaux);
            listReseauSociauxInfoDto.add(reseauSociauxInfoDto);
        }
        return Response.ok(listReseauSociauxInfoDto).build();
    }

    @GET
    @Path("{idReseauSociaux}")
    @Produces({MediaType.APPLICATION_JSON, (MediaType.TEXT_PLAIN)})
    @Operation(summary = "le détail d'un reseauSociaux par son Id", description = "Retourne le détail d'un reseauSociaux")
    @APIResponse(responseCode = "200", description = " Détail ReseauSociaux")
    @APIResponse(responseCode = "404", description = "ReseauSociaux non trouvé")
    public Response getReseauSociauxById(@PathParam("idReseauSociaux") int idReseauSociaux) {
        ReseauSociauxEntity reseauSociaux = reseauSociauxRepository.findById(idReseauSociaux);
        if (reseauSociaux == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
        ReseauSociauxInfoDto reseauSociauxInfoDto = new ReseauSociauxInfoDto(reseauSociaux);
        return Response.ok(reseauSociauxInfoDto).build();
    }

    @Transactional
    @POST
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crée un reseauSociaux", description = "Crée un reseauSociaux")
    @APIResponse(responseCode = "201", description = " ReseauSociaux Créer")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response createReseauSociaux(@Valid ReseauSociaux reseauSociaux, @Context UriInfo uriInfo) {
        try {
            ReseauSociauxEntity reseauSociauxEntity = new ReseauSociauxEntity(reseauSociaux);
            reseauSociauxRepository.persist(reseauSociauxEntity);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(reseauSociauxEntity.getId())).build();
            return Response.created(uri).entity("ReseauSociaux Créer")
                    .build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }

    @Transactional
    @PUT
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Modifie un reseauSociaux", description = "Modifie un reseauSociaux")
    @APIResponse(responseCode = "200", description = " ReseauSociaux mis à jour")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "404", description = "ReseauSociaux non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response updateReseauSociaux(@PathParam("id") Integer id, @Valid ReseauSociaux reseauSociaux) {
        try {
            ReseauSociauxEntity reseauSociauxEntity = reseauSociauxRepository.findById(id);
            if (reseauSociauxEntity == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("ReseauSociaux non trouvé")
                        .build();
            }
            reseauSociauxEntity.insertNewValues(reseauSociaux);
            return Response.ok().entity("ReseauSociaux mis à jour")
                    .build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Supprime un reseauSociaux", description = "Supprime un reseauSociaux")
    @APIResponse(responseCode = "200", description = " ReseauSociaux Supprimé")
    @APIResponse(responseCode = "404", description = "ReseauSociaux non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response deleteReseauSociaux(@PathParam("id") Integer id) {
        ReseauSociauxEntity reseauSociaux = reseauSociauxRepository.findById(id);
        if (reseauSociaux == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("ReseauSociaux non trouvé")
                    .build();
        }
        try {
            reseauSociauxRepository.deleteById(id);
            return Response.ok().entity("Suppression d'un reseauSociaux")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }

}



