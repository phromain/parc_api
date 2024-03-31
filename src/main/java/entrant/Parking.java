package entrant;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Parking {

    @Size(max = 75)
    @NotNull(message = "Le parking ne peut pas être null")
    @NotBlank(message = "Le parking ne peut pas être vide")
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Seules les lettres, les espaces et les tirets sont autorisés")
    private String parking;

    //Contructs
    public Parking() {
    }

    public Parking(String parking) {
        this.parking = parking;
    }
}
