package entities;

import DtoIn.ParcDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "parc")
public class ParcEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_parc", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nom_parc", nullable = false, length = 50)
    private String nomParc;

    @NotNull
    @Lob
    @Column(name = "presentation", nullable = false)
    private String presentation;

    @Size(max = 50)
    @NotNull
    @Column(name = "slug", nullable = false, length = 50)
    private String slug;

    @NotNull
    @Column(name = "point_restauration", nullable = false)
    private Boolean pointRestauration = false;

    @NotNull
    @Column(name = "boutique", nullable = false)
    private Boolean boutique = false;

    @Size(max = 50)
    @NotNull
    @Column(name = "adresse", nullable = false, length = 50)
    private String adresse;

    @Size(max = 50)
    @NotNull
    @Column(name = "longitude_parc", nullable = false, length = 50)
    private String longitudeParc;

    @Size(max = 50)
    @NotNull
    @Column(name = "lattitude_parc", nullable = false, length = 50)
    private String lattitudeParc;

    @Size(max = 250)
    @NotNull
    @Column(name = "site_internet", nullable = false, length = 250)
    private String siteInternet;

    @Size(max = 14)
    @NotNull
    @Column(name = "numero_tel_parc", nullable = false, length = 14)
    private String numeroTelParc;

    @Size(max = 250)
    @Column(name = "url_plan_parc", length = 250)
    private String urlPlanParc;

    @Size(max = 250)
    @NotNull
    @Column(name = "url_img_parc_prez", nullable = false, length = 250)
    private String urlImgParcPrez;

    @NotNull
    @Column(name = "sejour", nullable = false)
    private Boolean sejour = false;

    @Size(max = 100)
    @NotNull
    @Column(name = "heure_ouverture", nullable = false, length = 100)
    private String heureOuverture;

    @Size(max = 100)
    @NotNull
    @Column(name = "heure_fermeture", nullable = false, length = 100)
    private String heureFermeture;

    @Column(name = "date_ouverture")
    private LocalDate dateOuverture;

    @Column(name = "date_fermeture")
    private LocalDate dateFermeture;

    @Size(max = 250)
    @NotNull
    @Column(name = "url_calendrier", nullable = false, length = 250)
    private String urlCalendrier;

    @Size(max = 250)
    @Column(name = "url_affiliation", length = 250)
    private String urlAffiliation;

    @Size(max = 50)
    @NotNull
    @Column(name = "prix_adulte", nullable = false, length = 50)
    private String prixAdulte;

    @Size(max = 50)
    @NotNull
    @Column(name = "prix_enfant", nullable = false, length = 50)
    private String prixEnfant;

    @Size(max = 75)
    @Column(name = "gratuite", length = 75)
    private String gratuite;

    @Column(name = "transport_commun")
    private Boolean transportCommun;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_lieu", nullable = false)
    private LieuEntity idLieuEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_parking", nullable = false)
    private ParkingEntity idParkingEntity;

    @OneToMany(mappedBy = "idParcEntity")
    private Set<ClasserEntity> classerEntities = new HashSet<>();


    // Contructs
    public ParcEntity() {
    }

    public ParcEntity(ParcDto parcDto, LieuEntity lieuEntity, ParkingEntity parkingEntity ) {
        this.nomParc = parcDto.getNomParc();
        this.presentation = parcDto.getPresentation();
        this.slug = toSlug(parcDto.getNomParc());
        this.pointRestauration = parcDto.getPointRestauration();
        this.boutique = parcDto.getBoutique();
        this.adresse = parcDto.getAdresse();
        this.longitudeParc = parcDto.getLongitudeParc();
        this.lattitudeParc = parcDto.getLattitudeParc();
        this.siteInternet = parcDto.getSiteInternet();
        this.numeroTelParc = parcDto.getNumeroTelParc();
        this.urlPlanParc = parcDto.getUrlPlanParc();
        this.urlImgParcPrez = parcDto.getUrlImgParcPrez();
        this.sejour = parcDto.getSejour();
        this.heureOuverture = parcDto.getHeureOuverture();
        this.heureFermeture = parcDto.getHeureFermeture();
        this.dateOuverture = parcDto.getDateOuverture();
        this.dateFermeture = parcDto.getDateFermeture();
        this.urlCalendrier = parcDto.getUrlCalendrier();
        this.urlAffiliation = parcDto.getUrlAffiliation();
        this.prixAdulte = parcDto.getPrixAdulte();
        this.prixEnfant = parcDto.getPrixEnfant();
        this.gratuite = parcDto.getGratuite();
        this.transportCommun = parcDto.getTransportCommun();
        this.idLieuEntity = lieuEntity;
        this.idParkingEntity = parkingEntity;
    }


    // Methods
    public static String toSlug(String input) {
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
        String accentRemoved = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String lowerCased = accentRemoved.toLowerCase();
        String nonAlphanumericRemoved = lowerCased.replaceAll("[^a-z0-9]", "-");
        String slug = nonAlphanumericRemoved.replaceAll("-+", "-");
        return slug;
    }


}