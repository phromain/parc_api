package entrant;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReseauSociaux {


    @Size(max = 250)
    @NotNull(message = "Le nom du réseau social ne peut pas être null")
    @NotBlank(message = "Le nom du réseau social ne peut pas être vide")
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Seules les lettres, les espaces et les tirets sont autorisés")
    private String libReseau;

    //Contructs

    public ReseauSociaux() {
    }

    public ReseauSociaux(String libReseau) {
        this.libReseau = libReseau;
    }
}
