package Dto;

import entities.LieuEntity;
import lombok.Data;

@Data
public class LieuInfoDto {
    private Integer id;
    private String ville;
    private String codePostal;
    private String codeInsee;
    private String region;


    public LieuInfoDto(LieuEntity lieu) {
        this.id = lieu.getId();
        this.ville = lieu.getVille();
        this.codePostal = lieu.getCodePostal();
        this.codeInsee = lieu.getCodeInsee();
        this.region = lieu.getIdRegionEntity().getNomRegion();
    }

}
