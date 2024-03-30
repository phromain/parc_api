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
@Table(name = "type_parc")
public class TypeParcEntity {
    @Id
    @Column(name = "id_type", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "libelle_type_parc", nullable = false, length = 20)
    private String libelleTypeParc;

    @Size(max = 20)
    @NotNull
    @Column(name = "slug_type", nullable = false, length = 20)
    private String slugType;

}