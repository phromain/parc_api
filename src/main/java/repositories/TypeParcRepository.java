package repositories;



import entities.TypeParcEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TypeParcRepository implements PanacheRepositoryBase<TypeParcEntity, Integer> {



}
