package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ClasserPK implements Serializable {
    private static final long serialVersionUID = 4155588571726469275L;
    @NotNull
    @Column(name = "id_parc", nullable = false)
    private Integer idParc;

    @NotNull
    @Column(name = "id_type", nullable = false)
    private Integer idType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClasserPK entity = (ClasserPK) o;
        return Objects.equals(this.idType, entity.idType) &&
                Objects.equals(this.idParc, entity.idParc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idType, idParc);
    }

}