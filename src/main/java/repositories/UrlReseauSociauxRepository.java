package repositories;


import entities.UrlReseauSociauxEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UrlReseauSociauxRepository implements PanacheRepositoryBase<UrlReseauSociauxEntity, Integer> {
}








