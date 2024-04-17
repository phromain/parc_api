package DtoIn;


import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ParcDto {
    @NotNull(message = "Le nom du parc ne peut pas être null")
    @NotBlank(message = "Le nom du parc ne peut pas être vide")
    @Size(max = 50, message = "Le nom du parc doit avoir une longueur de 50 caractères max")
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Seules les lettres, les espaces et les tirets sont autorisés")
    private String nomParc;

    @NotNull(message = "La presentation du parc ne peut pas être nulle")
    @NotBlank(message = "La presentation du parc ne peut pas être vide")
    @Lob
    private String presentation;

    @NotNull(message = "Le point Restauration du parc ne peut pas être null")
    @NotBlank(message = "Le point Restauration du parc ne peut pas être vide")
    private Boolean pointRestauration = false;

    @NotNull(message = "La boutique du parc ne peut pas être nulle")
    @NotBlank(message = "La boutique du parc ne peut pas être vide")
    private Boolean boutique = false;

    @NotNull(message = "L'adresse du parc ne peut pas être nulle")
    @NotBlank(message = "L'adresse du parc ne peut pas être vide")
    @Size(max = 50, message = "L'adresse du parc doit avoir une longueur de 50 caractères max")
    private String adresse;

    @NotNull(message = "La longitude du parc ne peut pas être nulle")
    @NotBlank(message = "La longitude du parc ne peut pas être vide")
    @Size(max = 50, message = "La longitude nom du parc doit avoir une longueur de 50 caractères max")
    @Pattern(regexp = "^-?(180(\\\\.0+)?|((1[0-7]\\\\d)|([1-9]?\\\\d))(\\\\.\\\\d+)?)", message = "Longitude incorrecte")
    private String longitudeParc;

    @NotNull(message = "La lattitude du parc ne peut pas être nulle")
    @NotBlank(message = "La lattitude du parc ne peut pas être vide")
    @Size(max = 50, message = "La lattitude du parc doit avoir une longueur de 50 caractères max")
    @Pattern(regexp = "^-?([1-8]?\\\\d(\\\\.\\\\d+)?|90(\\\\.0+)?)", message = "Lattitude incorrecte")
    private String lattitudeParc;

    @NotNull(message = "Le site Internet  du parc ne peut pas être nulle")
    @NotBlank(message = "Le siteInternet du parc ne peut pas être vide")
    private String siteInternet;

    @NotNull(message = "Le numero tél du parc ne peut pas être nulle")
    @NotBlank(message = "La numero Tel  du parc ne peut pas être vide")
    @Size(max = 14, message = "Le numero Tel Parc du parc doit avoir une longueur de 14 caractères max")
    @Pattern(regexp = "^(?:(?:\\+|00)33|0)\\s*1-9{4}$", message = "Numero téléphone incorrecte +33, 00 33 ,0")
    private String numeroTelParc;

    @Size(max = 250)
    private String urlPlanParc;

    @NotNull(message = "L'url de présentation du parc ne peut pas être nulle")
    @NotBlank(message = "L'url de présentation du parc ne peut pas être vide")
    private String urlImgParcPrez;

    @NotNull(message = "Le séjour ne peut pas être nulle")
    private Boolean sejour = false;

    @Size(max = 100)
    @NotNull(message = "L'heure Ouverture du parc ne peut pas être nulle")
    @NotBlank(message = "L'heure Ouverture du parc ne peut pas être vide")
    private String heureOuverture;

    @Size(max = 100)
    @NotNull(message = "L'heure Fermeture du parc ne peut pas être nulle")
    @NotBlank(message = "L'heure Fermeture du parc ne peut pas être vide")
    private String heureFermeture;

    @Pattern(regexp = "^(?:(?:\\+|00)33|0)\\s*1-9{4}$", message = "Format de la date AAAA-MM-JJ ")
    private LocalDate dateOuverture;

    @Pattern(regexp = "^(?:(?:\\+|00)33|0)\\s*1-9{4}$", message = "Format de la date AAAA-MM-JJ ")
    private LocalDate dateFermeture;

    @NotNull(message = "L'url de Calendrier du parc ne peut pas être nulle")
    @NotBlank(message = "L'url de Calendrier du parc ne peut pas être vide")
    private String urlCalendrier;

    @Size(max = 250)
    private String urlAffiliation;

    @NotNull(message = "Le prix Adulte du parc ne peut pas être nulle")
    @NotBlank(message = "Le prix Adulte du parc ne peut pas être vide")
    @Size(max = 50, message = "Le prix Adulte du parc doit avoir une longueur de 50 caractères max")
    private String prixAdulte;

    @NotNull(message = "Le prix Enfant du parc ne peut pas être null")
    @NotBlank(message = "Le prix Enfant du parc ne peut pas être vide")
    @Size(max = 50, message = "Le prix Enfant du parc doit avoir une longueur de 50 caractères max")
    private String prixEnfant;

    @Size(max = 75, message = "La gratuité du parc doit avoir une longueur de 75 caractères max")
    private String gratuite;

    private Boolean transportCommun;

    // Contructs

    public ParcDto() {
    }

    public ParcDto(String nomParc, String presentation, Boolean pointRestauration, Boolean boutique, String adresse, String longitudeParc, String lattitudeParc, String siteInternet, String numeroTelParc, String urlPlanParc, String urlImgParcPrez, Boolean sejour, String heureOuverture, String heureFermeture, LocalDate dateOuverture, LocalDate dateFermeture, String urlCalendrier, String urlAffiliation, String prixAdulte, String prixEnfant, String gratuite, Boolean transportCommun) {
        this.nomParc = nomParc;
        this.presentation = presentation;
        this.pointRestauration = pointRestauration;
        this.boutique = boutique;
        this.adresse = adresse;
        this.longitudeParc = longitudeParc;
        this.lattitudeParc = lattitudeParc;
        this.siteInternet = siteInternet;
        this.numeroTelParc = numeroTelParc;
        this.urlPlanParc = urlPlanParc;
        this.urlImgParcPrez = urlImgParcPrez;
        this.sejour = sejour;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.dateOuverture = dateOuverture;
        this.dateFermeture = dateFermeture;
        this.urlCalendrier = urlCalendrier;
        this.urlAffiliation = urlAffiliation;
        this.prixAdulte = prixAdulte;
        this.prixEnfant = prixEnfant;
        this.gratuite = gratuite;
        this.transportCommun = transportCommun;
    }
}
