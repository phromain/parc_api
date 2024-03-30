package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reseau_sociaux")
public class ReseauSociauxEntity {
    @Id
    @Column(name = "id_reseau_sociaux", nullable = false)
    private Integer id;

    @Size(max = 250)
    @Column(name = "lib_reseau", length = 250)
    private String libReseau;

}