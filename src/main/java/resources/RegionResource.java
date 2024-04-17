package resources;

import DtoOut.RegionInfoDto;
import entities.LieuEntity;
import entities.RegionEntity;
import DtoIn.LieuDto;
import DtoIn.RegionDto;
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
import repositories.LieuRepository;
import repositories.RegionRepository;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/region")

public class RegionResource {

    @Inject
    RegionRepository regionRepository;

    @Inject
    LieuRepository lieuRepository;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des regions", description = "Retourne une liste de region")
    @APIResponse(responseCode = "200", description = " Liste Region")
    public Response getListRegion (){
        List<RegionEntity> listRegion = regionRepository.listAll();
        List<RegionInfoDto> listRegionDto = new ArrayList<>();
        for (RegionEntity region : listRegion) {
            RegionInfoDto regionInfoDto = new RegionInfoDto(region);
            listRegionDto.add(regionInfoDto);
        }
        return Response.ok(listRegionDto).build();
    }
    @GET
    @Path("{idRegion}")
    @Produces({MediaType.APPLICATION_JSON, (MediaType.TEXT_PLAIN)})
    @Operation(summary = "le détail d'une region par son Id", description = "Retourne le détail d'une region")
    @APIResponse(responseCode = "200", description = " Détail Region")
    @APIResponse(responseCode = "404", description = "Region non trouvée")
    public Response getRegionById (@PathParam("idRegion") int idRegion) {
        RegionEntity region = regionRepository.findById(idRegion);
        if (region == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
        RegionInfoDto regionDto = new RegionInfoDto(region);
        return Response.ok(regionDto).build();
    }

    @Transactional
    @POST
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crée une region", description = "Crée une region")
    @APIResponse(responseCode = "201", description = " Région Créer")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response createRegion (@Valid RegionDto regionDto, @Context UriInfo uriInfo) {
        try {
            RegionEntity regionEntity = new RegionEntity(regionDto);
            regionRepository.persist(regionEntity);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(regionDto.getNomRegion())).build();
            return Response.created(uri).entity("Région Créer")
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
    @POST
    @Path("/{id}/lieu")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crée un lieu par un IDregion", description = "Crée un lieu par un IDregion")
    @APIResponse(responseCode = "201", description = " Lieu Créer")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "404", description = "Cet identifiant n'existe pas ")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response createLieuByIdRegion (@PathParam("id") Integer idRegion, @Valid LieuDto lieuDto, @Context UriInfo uriInfo) {
        try {
            RegionEntity region = regionRepository.findById(idRegion);
            if (region == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Cet identifiant n'existe pas !")
                        .build();
            }
            LieuEntity lieuEntity = new LieuEntity(lieuDto, region);
            lieuRepository.persist(lieuEntity);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(lieuEntity.getId())).build();
            return Response.created(uri).entity("Lieu Créer")
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
    @PUT
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Modifie une region", description = "Modifie une region")
    @APIResponse(responseCode = "200", description = " Région mis à jour")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "404", description = "Région non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response updateRegion (@PathParam("id") Integer id, @Valid RegionDto regionDto) {
        try {
            RegionEntity regionEntity = regionRepository.findById(id);
            if (regionEntity == null){
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Région non trouvé")
                        .build();
            }
            regionEntity.insertNewValues(regionDto);
            return Response.ok().entity("Région mise à jour")
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
    @Operation(summary = "Supprime une region", description = "Supprime une region")
    @APIResponse(responseCode = "200", description = " Région Supprimée")
    @APIResponse(responseCode = "404", description = "Région non trouvée")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response deleteRegion (@PathParam("id") Integer id) {
        RegionEntity region = regionRepository.findById(id);
        if (region == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Région non trouvé")
                    .build();
        }
        try {
            regionRepository.deleteById(id);
            return Response.ok().entity("Suppression d'une region")
                    .build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }




}
