package repositories;


import entities.LieuEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class LieuRepository implements PanacheRepositoryBase<LieuEntity, Integer> {




}
