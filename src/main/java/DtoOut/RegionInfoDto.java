package DtoOut;

import entities.RegionEntity;
import lombok.Data;

@Data
public class RegionInfoDto {
    private Integer id;
    private String nomRegion;
    private String slugRegion;

    public RegionInfoDto(RegionEntity region) {
        this.id = region.getId();
        this.nomRegion = region.getNomRegion();
        this.slugRegion = region.getSlugRegion();
    }
}
