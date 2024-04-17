package entities;

import DtoIn.ParkingDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parking")
public class ParkingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_parking", nullable = false)
    private Integer id;

    @Size(max = 75)
    @NotNull
    @Column(name = "parking", nullable = false, length = 75)
    private String parking;

    //Contructs

    public ParkingEntity() {
    }

    public ParkingEntity(ParkingDto parkingDto) {
        this.parking = parkingDto.getParking();
    }


    // Methods

    public void insertNewValues(ParkingDto parkingDto) {
        this.parking = parkingDto.getParking();
    }
}