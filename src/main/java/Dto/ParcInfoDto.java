package Dto;


import entities.ClasserEntity;
import entities.ParcEntity;
import entities.TypeParcEntity;
import lombok.Data;

import java.util.Set;

@Data
public class ParcInfoDto {

    private int idParc;
    private String nomParc;
    private String slugParc;
    private String presentationMax250;
    private String libelleTypeParc;
    private String nomRegion;
    private String urlImgPrez;

    public ParcInfoDto(ParcEntity parc) {
        this.idParc = parc.getId();
        this.nomParc = parc.getNomParc();
        this.slugParc = parc.getSlug();
        this.presentationMax250 = parc.getPresentation().length() > 250 ? parc.getPresentation().substring(0, 250) + "..." : parc.getPresentation();
        this.nomRegion = parc.getIdLieuEntity().getIdRegionEntity().getNomRegion();
        Set<ClasserEntity> classerEntities = parc.getClasserEntities();
        for (ClasserEntity classerEntity : classerEntities) {
            TypeParcEntity type = classerEntity.getIdType();
            this.libelleTypeParc = type.getLibelleTypeParc();
            break;
        }
        this.urlImgPrez = parc.getUrlImgParcPrez();
    }

}
