package Dto;

import entities.RegionEntity;
import lombok.Data;

import java.util.Set;

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
