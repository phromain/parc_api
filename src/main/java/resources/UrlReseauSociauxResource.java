package resources;

import DtoOut.UrlReseauSociauxInfoDto;
import entities.ParcEntity;
import entities.ReseauSociauxEntity;
import entities.UrlReseauSociauxEntity;
import DtoIn.UrlReseauSociauxDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.ParcRepository;
import repositories.ReseauSociauxRepository;
import repositories.UrlReseauSociauxRepository;

import java.util.ArrayList;
import java.util.List;

@Path("/urlReseauSociaux")

public class UrlReseauSociauxResource {

    @Inject
    UrlReseauSociauxRepository urlReseauSociauxRepository;
    @Inject
    ParcRepository parcRepository;
    @Inject
    ReseauSociauxRepository reseauSociauxRepository;


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des url des Reseaux Sociaux", description = "Retourne une liste des url des Reseaux Sociaux")
    @APIResponse(responseCode = "200", description = " Liste Url Reseaux Sociaux")
    public Response getListUrlReseauSociaux() {
        List<UrlReseauSociauxEntity> listUrlReseauSociaux = urlReseauSociauxRepository.listAll();
        List<UrlReseauSociauxInfoDto> listUrlReseauSociauxInfoDto = new ArrayList<>();
        for (UrlReseauSociauxEntity urlReseauSociaux : listUrlReseauSociaux) {
            UrlReseauSociauxInfoDto urlReseauSociauxInfoDto = new UrlReseauSociauxInfoDto(urlReseauSociaux);
            listUrlReseauSociauxInfoDto.add(urlReseauSociauxInfoDto);
        }
        return Response.ok(listUrlReseauSociauxInfoDto).build();
    }

    @GET
    @Path("{idUrlReseauSociaux}")
    @Produces({MediaType.APPLICATION_JSON, (MediaType.TEXT_PLAIN)})
    @Operation(summary = "le détail d'une url d'un Reseau Social par son Id", description = "Retourne le détail d'une url Reseau Social")
    @APIResponse(responseCode = "200", description = " Détail Url Reseau Social")
    @APIResponse(responseCode = "404", description = "Url Reseau Social non trouvée")
    public Response getUrlReseauSociauxById(@PathParam("idUrlReseauSociaux") int idUrlReseauSociaux) {
        UrlReseauSociauxEntity urlReseauSociaux = urlReseauSociauxRepository.findById(idUrlReseauSociaux);
        if (urlReseauSociaux == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
        UrlReseauSociauxInfoDto urlReseauSociauxInfoDto = new UrlReseauSociauxInfoDto(urlReseauSociaux);
        return Response.ok(urlReseauSociauxInfoDto).build();
    }



    @Transactional
    @PUT
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Modifie une url Reseau Social", description = "Modifie une url Reseau Social")
    @APIResponse(responseCode = "200", description = " Url Reseau Social mis à jour")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "404", description = "Url Reseau Social non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response updateUrlReseauSociaux (@PathParam("id") Integer id, @Valid UrlReseauSociauxDto urlReseauSociauxDto) {
        try {
            UrlReseauSociauxEntity urlReseauSociauxEntity = urlReseauSociauxRepository.findById(id);
            ParcEntity parcEntity = parcRepository.findById(urlReseauSociauxEntity.getIdParcEntity().getId());
            ReseauSociauxEntity reseauSociauxEntity = reseauSociauxRepository.findById(urlReseauSociauxEntity.getIdReseauSociauxEntity().getId());
            if (urlReseauSociauxEntity == null){
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Url Reseau Social non trouvé")
                        .build();
            }
            urlReseauSociauxEntity.insertNewValues(urlReseauSociauxDto, parcEntity, reseauSociauxEntity);
            return Response.ok().entity("Url Reseau Social mis à jour")
                    .build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Supprime une url Reseau Social", description = "Supprime une url Reseau Social")
    @APIResponse(responseCode = "200", description = " Url Reseau Social Supprimée")
    @APIResponse(responseCode = "404", description = "Url Reseau Social non trouvée")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response deleteUrlReseauSociaux (@PathParam("id") Integer id) {
        UrlReseauSociauxEntity urlReseauSociaux = urlReseauSociauxRepository.findById(id);
        if (urlReseauSociaux == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Url Reseau Social non trouvée")
                    .build();
        }
        try {
            urlReseauSociauxRepository.deleteById(id);
            return Response.ok().entity("Suppression d'une url Reseau Social")
                    .build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }

}