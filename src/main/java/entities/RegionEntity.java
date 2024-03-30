package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "region")
public class RegionEntity {
    @Id
    @Column(name = "id_region", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nom_region", nullable = false, length = 50)
    private String nomRegion;

    @Size(max = 50)
    @NotNull
    @Column(name = "slug_region", nullable = false, length = 50)
    private String slugRegion;

}