package DtoOut;


import entities.ParkingEntity;
import lombok.Data;

@Data
public class ParkingInfoDto {
    private Integer id;
    private String parking;


    public ParkingInfoDto(ParkingEntity parking) {
        this.id = parking.getId();
        this.parking = parking.getParking();
    }
}
