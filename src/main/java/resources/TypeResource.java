package resources;

import Dto.TypeParcInfoDto;
import entities.TypeParcEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import repositories.TypeParcRepository;

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


}
