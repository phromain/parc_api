package resources;

import DtoOut.LieuInfoDto;
import entities.LieuEntity;
import entities.RegionEntity;
import DtoIn.LieuDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.LieuRepository;
import repositories.RegionRepository;

import java.util.ArrayList;
import java.util.List;

@Path("/lieux")

public class LieuResource {

    @Inject
    LieuRepository lieuRepository;

    @Inject
    RegionRepository regionRepository;


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des lieux", description = "Retourne une liste de lieux")
    @APIResponse(responseCode = "200", description = " Liste de Lieux")
    public Response getListLieu (){
        List<LieuEntity> listLieu = lieuRepository.listAll();
        List<LieuInfoDto> listLieuDto = new ArrayList<>();
        for (LieuEntity lieu : listLieu) {
            LieuInfoDto lieuInfoDto = new LieuInfoDto(lieu);
            listLieuDto.add(lieuInfoDto);
        }
        return Response.ok(listLieuDto).build();
    }
    @GET
    @Path("{idLieu}")
    @Produces({MediaType.APPLICATION_JSON, (MediaType.TEXT_PLAIN)})
    @Operation(summary = "le détail d'une lieu par son Id", description = "Retourne le détail d'une lieu")
    @APIResponse(responseCode = "200", description = " Détail Lieu")
    @APIResponse(responseCode = "404", description = "Lieu non trouvée")
    public Response getLieuById (@PathParam("idLieu") int idLieu) {
        LieuEntity lieu = lieuRepository.findById(idLieu);
        if (lieu == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
        LieuInfoDto lieuDto = new LieuInfoDto(lieu);
        return Response.ok(lieuDto).build();
    }




    @Transactional
    @PUT
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Modifie un lieu", description = "Modifie un lieu")
    @APIResponse(responseCode = "200", description = " Lieu mis à jour")
    @APIResponse(responseCode = "400", description = "informations manquantes ou vide")
    @APIResponse(responseCode = "404", description = "Lieu non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response updateLieu (@PathParam("id") Integer id, @Valid LieuDto lieuDto) {
        try {
            LieuEntity lieuEntity = lieuRepository.findById(id);
            RegionEntity regionEntity = regionRepository.findById(lieuEntity.getIdRegionEntity().getId());
            if (lieuEntity == null){
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Cet identifiant n'existe pas !")
                        .build();
            }
            lieuEntity.insertNewValues(lieuDto,regionEntity);
            return Response.ok().entity("Lieu mis à jour")
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
    @Operation(summary = "Supprime un lieu", description = "Supprime un lieu")
    @APIResponse(responseCode = "200", description = " Lieu Supprimé")
    @APIResponse(responseCode = "404", description = "Lieu non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response deleteLieu (@PathParam("id") Integer id) {
        LieuEntity lieu = lieuRepository.findById(id);
        if (lieu == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
        try {
            lieuRepository.deleteById(id);
            return Response.ok().entity("Suppression d'un lieu")
                    .build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }




}
