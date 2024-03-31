package entrant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Lieu {
    // Attributs

    @Size(max = 250)
    @NotBlank(message = "La ville ne peut pas être vide")
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Seules les lettres, les espaces et les tirets sont autorisés")
    @NotNull(message = "La ville ne peut pas être nulle")
    private String ville;

    @NotNull(message = "Le code postal ne peut pas être nul")
    @NotBlank(message = "Le code postal ne peut pas être vide")
    @Pattern(regexp = "^(0[1-9]|[1-4][0-9]|5[0-2]|6[0-9]|7[0-5]|8[0-9]|9[0-5])[0-9]{3}$"
            , message = "Le code postal n'est pas correct et il doit contenir uniquement 5 chiffres")
    @Size(max = 5, message = "Le code postal doit avoir une longueur de 5 caractères")
    private String codePostal;



    @NotNull(message = "Le code Insee ne peut pas être nul")
    @NotBlank(message = "Le code Insee ne peut pas être vide")
    @Pattern(regexp = "^[0-9]{2}[0-9A-Z]{3}$", message = "Le code postal n'est pas correct et il doit contenir uniquement 5 caractères")
    @Size(max = 5, message = "Le code postal doit avoir une longueur de 5 caractères")
    private String codeInsee;


    // Contructs
    public Lieu(String ville, String codePostal, String codeInsee) {
        this.ville = ville;
        this.codePostal = codePostal;
        this.codeInsee = codeInsee;
    }

    public Lieu() {
    }



}
