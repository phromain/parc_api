package repositories;

import entities.ParkingEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ParkingRepository implements PanacheRepositoryBase<ParkingEntity, Integer>{
}
