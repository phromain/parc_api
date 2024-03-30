package resources;

import Dto.RegionInfoDto;
import entities.RegionEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.RegionRepository;


import java.util.ArrayList;
import java.util.List;

@Path("/region")

public class RegionResource {

    @Inject
    RegionRepository regionRepository;

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




}
