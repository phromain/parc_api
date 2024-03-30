package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "url_reseau_sociaux")
public class UrlReseauSociauxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_url_resau", nullable = false)
    private Integer id;

    @Size(max = 250)
    @NotNull
    @Column(name = "url_reseau", nullable = false, length = 250)
    private String urlReseau;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_parc", nullable = false)
    private ParcEntity idParcEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reseau_sociaux", nullable = false)
    private ReseauSociauxEntity idReseauSociauxEntity;

}