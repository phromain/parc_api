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
@Table(name = "parking")
public class ParkingEntity {
    @Id
    @Column(name = "id_parking", nullable = false)
    private Integer id;

    @Size(max = 75)
    @NotNull
    @Column(name = "parking", nullable = false, length = 75)
    private String parking;

}