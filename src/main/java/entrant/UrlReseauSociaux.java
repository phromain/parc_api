package entrant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UrlReseauSociaux {

    @NotNull(message = "Le type ne peut pas être nulle")
    @NotBlank(message = "Le type ne peut pas être vide")
    private String urlReseau;

    //Contructs

    public UrlReseauSociaux() {
    }
    public UrlReseauSociaux(String urlReseau) {
        this.urlReseau = urlReseau;
    }
}
