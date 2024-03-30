package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lieu")
public class LieuEntity {
    @Id
    @Column(name = "id_lieu", nullable = false)
    private Integer id;

    @Size(max = 250)
    @NotNull
    @Column(name = "ville", nullable = false, length = 250)
    private String ville;

    @Size(max = 5)
    @NotNull
    @Column(name = "code_postal", nullable = false, length = 5)
    private String codePostal;

    @Size(max = 5)
    @NotNull
    @Column(name = "code_insee", nullable = false, length = 5)
    private String codeInsee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_region", nullable = false)
    private RegionEntity idRegionEntity;

}