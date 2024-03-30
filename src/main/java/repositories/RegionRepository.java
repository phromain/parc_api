package repositories;


import entities.RegionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RegionRepository implements PanacheRepositoryBase<RegionEntity, Integer> {



}
