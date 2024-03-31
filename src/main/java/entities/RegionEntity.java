package entities;

import entrant.Region;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "region")
public class RegionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_region", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nom_region", nullable = false, length = 50)
    private String nomRegion;

    @Size(max = 50)
    @NotNull
    @Column(name = "slug_region", nullable = false, length = 50)
    private String slugRegion;


    // contructs

    public RegionEntity(Region region) {
        this.nomRegion = region.getNomRegion();
        this.slugRegion = toSlug(region.getNomRegion());
    }

    public RegionEntity() {
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

    public void insertNewValues(Region region) {
        this.nomRegion = region.getNomRegion();
        this.slugRegion = toSlug(region.getNomRegion());
    }


}