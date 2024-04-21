package DtoOut;


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
    private Integer idRegion;
    private String urlImgPrez;
    private boolean parkingGratuit;
    private String parkingString;
    private boolean restauration;
    private boolean boutique;
    private boolean sejour;
    private String prixAdulte;
    private String prixEnfant;


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
        this.idRegion = parc.getIdLieuEntity().getIdRegionEntity().getId();
        this.parkingGratuit = "Gratuit".equals(parc.getIdParkingEntity().getParking());
        this.parkingString = parc.getIdParkingEntity().getParking();
        this.restauration = parc.getPointRestauration();
        this.boutique = parc.getBoutique();
        this.sejour = parc.getSejour();
        this.prixAdulte = parc.getPrixAdulte();
        this.prixEnfant = parc.getPrixEnfant();
    }

}
