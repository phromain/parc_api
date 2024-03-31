package resources;

import Dto.ParcDetailDto;
import Dto.ParcInfoDto;
import entities.ParcEntity;
import entities.RegionEntity;
import entities.TypeParcEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.ParcRepository;
import repositories.RegionRepository;
import repositories.TypeParcRepository;
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
    @Operation(summary = "Affiche la liste des parcs par type et region", description = "Retourne une liste de parc par type")
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






}
