package resources;

import Dto.ParkingInfoDto;
import entities.ParkingEntity;
import entrant.Parking;
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
import repositories.ParkingRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/parking")

public class ParkingResource {
    @Inject
    ParkingRepository parkingRepository;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des parkings", description = "Retourne une liste des parkings")
    @APIResponse(responseCode = "200", description = " Liste Parking")
    public Response getListParking (){
        List<ParkingEntity> listParking = parkingRepository.listAll();
        List<ParkingInfoDto> listParkingInfoDto = new ArrayList<>();
        for (ParkingEntity parking : listParking) {
            ParkingInfoDto parkingInfoDto = new ParkingInfoDto(parking);
            listParkingInfoDto.add(parkingInfoDto);
        }
        return Response.ok(listParkingInfoDto).build();
    }
    @GET
    @Path("{idParking}")
    @Produces({MediaType.APPLICATION_JSON, (MediaType.TEXT_PLAIN)})
    @Operation(summary = "le détail d'un parking par son Id", description = "Retourne le détail d'un parking")
    @APIResponse(responseCode = "200", description = " Détail Parking")
    @APIResponse(responseCode = "404", description = "Parking non trouvé")
    public Response getParkingById (@PathParam("idParking") int idParking) {
        ParkingEntity parking = parkingRepository.findById(idParking);
        if (parking == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
        ParkingInfoDto parkingInfoDto = new ParkingInfoDto(parking);
        return Response.ok(parkingInfoDto).build();
    }

    @Transactional
    @POST
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crée un parking", description = "Crée un parking")
    @APIResponse(responseCode = "201", description = " Parking Créer")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response createParking (@Valid Parking parking, @Context UriInfo uriInfo) {
        try {
            ParkingEntity parkingEntity = new ParkingEntity(parking);
            parkingRepository.persist(parkingEntity);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(parkingEntity.getId())).build();
            return Response.created(uri).entity("Parking Créer")
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
    @Operation(summary = "Modifie un parking", description = "Modifie un parking")
    @APIResponse(responseCode = "200", description = " Parking mis à jour")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "404", description = "Parking non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response updateParking (@PathParam("id") Integer id, @Valid Parking parking) {
        try {
            ParkingEntity parkingEntity = parkingRepository.findById(id);
            if (parkingEntity == null){
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Parking non trouvé")
                        .build();
            }
            parkingEntity.insertNewValues(parking);
            return Response.ok().entity("Parking mis à jour")
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
    @Operation(summary = "Supprime un parking", description = "Supprime un parking")
    @APIResponse(responseCode = "200", description = " Parking Supprimé")
    @APIResponse(responseCode = "404", description = "Parking non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response deleteParking (@PathParam("id") Integer id) {
        ParkingEntity parking = parkingRepository.findById(id);
        if (parking == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Parking non trouvé")
                    .build();
        }
        try {
            parkingRepository.deleteById(id);
            return Response.ok().entity("Suppression d'un parking")
                    .build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }




}
