package DtoIn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TypeDto {

    @NotNull(message = "Le type ne peut pas être nulle")
    @NotBlank(message = "Le type ne peut pas être vide")
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Seules les lettres, les espaces et les tirets sont autorisés")
    private String libelleTypeParc;

    public TypeDto(String libelleTypeParc) {
        this.libelleTypeParc = libelleTypeParc;
    }

    public TypeDto() {
    }



}


