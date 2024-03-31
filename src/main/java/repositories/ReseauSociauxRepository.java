package repositories;

import entities.ReseauSociauxEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ReseauSociauxRepository implements PanacheRepositoryBase<ReseauSociauxEntity, Integer> {
}


