package DtoOut;

import entities.ClasserEntity;
import entities.ParcEntity;
import entities.TypeParcEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ParcDetailDto {

    private Integer id;
    private String libelleTypeParc;
    private String nomParc;
    private String presentation;
    private String slug;
    private Boolean pointRestauration;
    private Boolean boutique;
    private Boolean sejour;
    private String adresse;
    private String codePostal;
    private String ville;
    private String nomRegion;
    private String siteInternet;
    private String numeroTelParc;
    private String heureOuverture;
    private String heureFermeture;
    private LocalDate dateOuverture;
    private LocalDate dateFermeture;
    private String urlPlanParc;
    private String urlImgParcPrez;
    private String urlCalendrier;
    private String urlAffiliation;
    private String prixAdulte;
    private String prixEnfant;
    private String gratuite;
    private String parking;


    public ParcDetailDto(ParcEntity parc) {
        this.id = parc.getId();
        Set<ClasserEntity> classerEntities = parc.getClasserEntities();
        for (ClasserEntity classerEntity : classerEntities) {
            TypeParcEntity type = classerEntity.getIdType();
            this.libelleTypeParc = type.getLibelleTypeParc();
            break;
        }
        this.nomParc = parc.getNomParc();
        this.presentation = parc.getPresentation();
        this.slug = parc.getSlug();
        this.pointRestauration = parc.getPointRestauration();
        this.boutique = parc.getBoutique();
        this.sejour = parc.getSejour();
        this.adresse = parc.getAdresse();
        this.codePostal = parc.getIdLieuEntity().getCodePostal();
        this.ville = parc.getIdLieuEntity().getVille();
        this.nomRegion = parc.getIdLieuEntity().getIdRegionEntity().getNomRegion();
        this.siteInternet = parc.getSiteInternet();
        this.numeroTelParc = parc.getNumeroTelParc();
        this.heureOuverture = parc.getHeureOuverture();
        this.heureFermeture = parc.getHeureFermeture();
        this.dateOuverture = parc.getDateOuverture();
        this.dateFermeture = parc.getDateFermeture();
        this.urlPlanParc = parc.getUrlPlanParc();
        this.urlImgParcPrez = parc.getUrlImgParcPrez();
        this.urlCalendrier = parc.getUrlCalendrier();
        this.prixAdulte = parc.getPrixAdulte();
        this.prixEnfant = parc.getPrixEnfant();
        this.gratuite = parc.getGratuite();
        this.parking = parc.getIdParkingEntity().getParking();
    }
}
