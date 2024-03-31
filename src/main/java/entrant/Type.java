package entrant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Type {

    @NotNull(message = "Le type ne peut pas être nulle")
    @NotBlank(message = "Le type ne peut pas être vide")
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Seules les lettres, les espaces et les tirets sont autorisés")
    private String libelleTypeParc;


    public Type() {
    }

    public Type(String libelleTypeParc) {
        this.libelleTypeParc = libelleTypeParc;
    }


    public boolean typeValid() {
        return libelleTypeParc != null && !libelleTypeParc.isEmpty();
    }
}


