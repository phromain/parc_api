package resources;

import Dto.ParcDetailDto;
import Dto.ParcInfoDto;
import entities.ParcEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.ParcRepository;

import java.util.ArrayList;
import java.util.List;

@Path("/parc")
public class ParcResource {

    @Inject
    ParcRepository parcRepository;

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
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "le détail d'un parc par son Id", description = "Retourne le détail d'un parc")
    @APIResponse(responseCode = "200", description = " Détail Parc")
    public Response getParcById (@PathParam("idParc") int idParc){
        ParcEntity parc = parcRepository.findById(idParc);
        ParcDetailDto parcDetail = new ParcDetailDto(parc);
        return Response.ok(parcDetail).build();
    }

    @GET
    @Path("/{idtype}/{idregion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Affiche la liste des parcs par type et region", description = "Retourne une liste de parc par type")
    @APIResponse(responseCode = "200", description = " Liste Parc")
    public Response getListParcByTypeRegion (@PathParam("idtype") int idType, @PathParam("idregion") int idRegion){
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
