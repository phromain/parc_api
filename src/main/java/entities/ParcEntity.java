package entities;

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
}