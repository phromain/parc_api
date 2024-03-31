package resources;

import Dto.TypeParcInfoDto;
import entities.TypeParcEntity;
import entrant.Type;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.TypeParcRepository;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/type")

public class TypeResource {


    @Inject
    TypeParcRepository typeParcRepository;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des typeParcs", description = "Retourne une liste de typeParc")
    @APIResponse(responseCode = "200", description = " Liste TypeParc")
    public Response getListTypeParc (){
        List<TypeParcEntity> listTypeParc = typeParcRepository.listAll();
        List<TypeParcInfoDto> listTypeParcDto = new ArrayList<>();
        for (TypeParcEntity typeParc : listTypeParc) {
            TypeParcInfoDto typeParcInfoDto = new TypeParcInfoDto(typeParc);
            listTypeParcDto.add(typeParcInfoDto);
        }
        return Response.ok(listTypeParcDto).build();
    }
    @GET
    @Path("{idType}")
    @Produces({MediaType.APPLICATION_JSON, (MediaType.TEXT_PLAIN)})
    @Operation(summary = "le type par son Id", description = "Retourne le type")
    @APIResponse(responseCode = "200", description = "Type trouvé")
    @APIResponse(responseCode = "404", description = "Type non trouvé")
    public Response getParcById (@PathParam("idType") int idType) {
        TypeParcEntity type = typeParcRepository.findById(idType);
        if (type == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
        TypeParcInfoDto typeParcInfoDto = new TypeParcInfoDto(type);
        return Response.ok(typeParcInfoDto).build();
    }



    @Transactional
    @POST
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crée un type", description = "Crée un type")
    @APIResponse(responseCode = "201", description = " Type Créer")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response createType (@Valid Type type, @Context UriInfo uriInfo) {
        try {
            TypeParcEntity typeParc = new TypeParcEntity(type);
            typeParcRepository.persist(typeParc);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(typeParc.getLibelleTypeParc())).build();
            return Response.created(uri).entity("Type Créer")
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
    @Operation(summary = "Modifie un type", description = "Modifie un type")
    @APIResponse(responseCode = "200", description = " Type mis à jour")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "404", description = "Type non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response updateTypeParc (@PathParam("id") Integer id, @Valid Type type) {
        try {
            TypeParcEntity typeParc = typeParcRepository.findById(id);
            if (typeParc == null){
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Type non trouvé")
                        .build();
            }
            typeParc.insertNewValues(type);
            return Response.ok().entity("Type mise à jour")
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
    @Operation(summary = "Supprime un type", description = "Supprime un type")
    @APIResponse(responseCode = "200", description = " Type Supprimé")
    @APIResponse(responseCode = "404", description = "Type non trouvé")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response deleteType (@PathParam("id") Integer id) {
        TypeParcEntity typeParc = typeParcRepository.findById(id);
        if (typeParc == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Type non trouvé")
                    .build();
        }
        try {
            typeParcRepository.deleteById(id);
            return Response.ok().entity("Suppression du type")
                    .build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Une erreur est survenue ")
                    .build();
        }
    }



}
