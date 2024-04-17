package DtoOut;


import entities.ReseauSociauxEntity;
import lombok.Data;

@Data
public class ReseauSociauxInfoDto {
    private Integer id;
    private String libReseau;


    public ReseauSociauxInfoDto(ReseauSociauxEntity reseauSociaux) {
        this.id = reseauSociaux.getId();
        this.libReseau = reseauSociaux.getLibReseau();
    }
}
