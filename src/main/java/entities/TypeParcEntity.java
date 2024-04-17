package entities;

import DtoIn.TypeDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "type_parc")
public class TypeParcEntity {

    // Attributs
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "libelle_type_parc", nullable = false, length = 20)
    private String libelleTypeParc;

    @Size(max = 20)
    @NotNull
    @Column(name = "slug_type", nullable = false, length = 20)
    private String slugType;

    // contructs

    public TypeParcEntity(TypeDto type) {
        this.libelleTypeParc = type.getLibelleTypeParc();
        this.slugType = toSlug(type.getLibelleTypeParc());
    }

    public TypeParcEntity() {
    }

    // Methods
    public static String toSlug(String input) {
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
        String accentRemoved = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String lowerCased = accentRemoved.toLowerCase();
        String nonAlphanumericRemoved = lowerCased.replaceAll("[^a-z0-9]", "-");
        String slug = nonAlphanumericRemoved.replaceAll("-+", "-");
        return slug;
    }

    public void insertNewValues(TypeDto type) {
        this.libelleTypeParc = type.getLibelleTypeParc();
        this.slugType = toSlug(type.getLibelleTypeParc());
    }


}