package resources;

import Dto.ParcDetailDto;
import Dto.ParcInfoDto;
import entities.*;
import entrant.UrlReseauSociaux;
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
import repositories.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/parc")
public class ParcResource {

    @Inject
    ParcRepository parcRepository;
    @Inject
    TypeParcRepository typeParcRepository;
    @Inject
    RegionRepository regionRepository;
    @Inject
    UrlReseauSociauxRepository urlReseauSociauxRepository;
    @Inject
    ReseauSociauxRepository reseauSociauxRepository;



        @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des parcs", description = "Retourne une liste de parc")
    @APIResponse(responseCode = "200", description = " Liste Parc")
    public Response getListParc (){
        List<ParcEntity> listParc = parcRepository.listAll();
        List<ParcInfoDto> listParcDto = new ArrayList<>();
        for (ParcEntity parc : listParc) {
            ParcInfoDto parcInfoDto = new ParcInfoDto(parc);
            listParcDto.add(parcInfoDto);
        }
        return Response.ok(listParcDto).build();
    }

    @GET
    @Path("{idParc}")
    @Produces({MediaType.APPLICATION_JSON, (MediaType.TEXT_PLAIN)})
    @Operation(summary = "le détail d'un parc par son Id", description = "Retourne le détail d'un parc")
    @APIResponse(responseCode = "200", description = " Détail Parc")
    @APIResponse(responseCode = "404", description = "Parc non trouvé")
    public Response getParcById (@PathParam("idParc") int idParc) {
        ParcEntity parc = parcRepository.findById(idParc);
        if (parc == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Cet identifiant n'existe pas !")
                    .build();
        }
            ParcDetailDto parcDetail = new ParcDetailDto(parc);
            return Response.ok(parcDetail).build();
        }


    @GET
    @Path("/{idtype}/{idregion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des parcs par type et region (0 en idtype ou region = tous)", description = "Retourne une liste de parc par type et region (0 en idtype ou region = tous)")
    @APIResponse(responseCode = "200", description = " Liste Parc")
    @APIResponse(responseCode = "404", description = "Region non trouvé")
    @APIResponse(responseCode = "404", description = "Type non trouvé")
    public Response getListParcByTypeRegion (@PathParam("idtype") int idType, @PathParam("idregion") int idRegion){
        TypeParcEntity type = typeParcRepository.findById(idType);
        RegionEntity region = regionRepository.findById(idRegion);

        if (type == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Type non trouvé")
                    .build();
        }
        if (region == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Region non trouvé")
                    .build();
        }
        List<ParcEntity> listParc;
        if (idType == 0 && idRegion == 0) {
            listParc = parcRepository.listAll();
        } else {
            // Sinon, filtrer les parcs par type et région
            listParc = parcRepository.findByTypeIdAndRegionId(idType, idRegion);
        }
        List<ParcInfoDto> listParcDto = new ArrayList<>();
        for (ParcEntity parc : listParc) {
            ParcInfoDto parcInfoDto = new ParcInfoDto(parc);
            listParcDto.add(parcInfoDto);
        }
        return Response.ok(listParcDto).build();
    }


    @Transactional
    @POST
    @Path("/{idParc}/{idReseauSocial}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crée un lien url pour un reseau social pour un parc", description = "Crée un lien url pour un reseau social pour un parc")
    @APIResponse(responseCode = "201", description = " Lien url Créer")
    @APIResponse(responseCode = "400", description = "Erreur indiquer ")
    @APIResponse(responseCode = "404", description = "Cet identifiant n'existe pas de parc ou type ")
    @APIResponse(responseCode = "500", description = "Une erreur interne est survenue")
    public Response createUrlByReseauSocialByIdParc (@PathParam("idParc") Integer idParc, @PathParam("idReseauSocial") Integer idReseauSocial, @Valid UrlReseauSociaux urlReseauSociaux, @Context UriInfo uriInfo) {
        try {
            ParcEntity parc = parcRepository.findById(idParc);
            System.out.println(parc);
            if (parc == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Cet identifiant de parc n'existe pas !")
                        .build();
            }
            ReseauSociauxEntity reseauSociaux = reseauSociauxRepository.findById(idReseauSocial);
            System.out.println(reseauSociaux);
            if (reseauSociaux == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_PLAIN)
                        .entity("Cet identifiant de reseau social n'existe pas !")
                        .build();
            }
            UrlReseauSociauxEntity urlReseauSociauxEntity = new UrlReseauSociauxEntity(urlReseauSociaux,parc,reseauSociaux);
            urlReseauSociauxRepository.persist(urlReseauSociauxEntity);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(urlReseauSociauxEntity.getId())).build();
            return Response.created(uri).entity("Url " + reseauSociaux.getLibReseau()  +" Créer pour "+ parc.getNomParc())
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




}
