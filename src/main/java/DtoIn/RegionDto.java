package DtoIn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegionDto {

    // Attributs
    @NotNull(message = "Le nom de région ne peut pas être nulle")
    @NotBlank(message = "Le nom de région ne peut pas être vide")
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Seules les lettres, les espaces et les tirets sont autorisés")
    private String nomRegion;

    // Contructs

    public RegionDto(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public RegionDto() {
    }

    // Methods


}
