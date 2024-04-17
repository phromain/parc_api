package DtoIn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UrlReseauSociauxDto {

    @NotNull(message = "Le type ne peut pas être nulle")
    @NotBlank(message = "Le type ne peut pas être vide")
    private String urlReseau;

    //Contructs

    public UrlReseauSociauxDto() {
    }
    public UrlReseauSociauxDto(String urlReseau) {
        this.urlReseau = urlReseau;
    }
}
