package entities;

import entrant.ReseauSociaux;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reseau_sociaux")
public class ReseauSociauxEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_reseau_sociaux", nullable = false)
    private Integer id;

    @Size(max = 250)
    @Column(name = "lib_reseau", length = 250)
    private String libReseau;

    //Contructs

    public ReseauSociauxEntity() {
    }

    public ReseauSociauxEntity(ReseauSociaux reseauSociaux) {
        this.libReseau = reseauSociaux.getLibReseau();
    }

    // Methods
    public void insertNewValues(ReseauSociaux reseauSociaux) {
        this.libReseau = reseauSociaux.getLibReseau();
    }
}