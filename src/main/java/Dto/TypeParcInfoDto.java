package Dto;

import entities.TypeParcEntity;
import lombok.Data;

@Data
public class TypeParcInfoDto {

    private Integer id;
    private String libelleTypeParc;
    private String slugType;

    public TypeParcInfoDto(TypeParcEntity typeParc) {
        this.id = typeParc.getId();
        this.libelleTypeParc = typeParc.getLibelleTypeParc();
        this.slugType = typeParc.getSlugType();
    }
}
