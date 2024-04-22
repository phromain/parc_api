package repositories;


import entities.ParcEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class ParcRepository implements PanacheRepositoryBase<ParcEntity, Integer> {

    public List<ParcEntity> findByTypeIdAndRegionId(int idType, int idRegion) {
        if (idType == 0 && idRegion != 0) {
            // Si idType est 0 et idRegion est différent de 0, retourner les parcs de la région spécifiée
            return list("idLieuEntity.idRegionEntity.id", idRegion);
        } else if (idType != 0 && idRegion == 0) {
            // Si idType est différent de 0 et idRegion est 0, retourner les parcs du type spécifié
            return list("SELECT p FROM ParcEntity p JOIN p.classerEntities c WHERE c.idType.id = ?1", idType);
        } else {
            // Sinon, filtrer les parcs par type et région
            return list("SELECT p FROM ParcEntity p JOIN p.classerEntities c WHERE c.idType.id = ?1 and p.idLieuEntity.idRegionEntity.id = ?2", idType, idRegion);
        }
    }

public ParcEntity findParcBySlug (String slugParc) {
        ParcEntity parc = find("slug", slugParc).firstResult();
                return parc;
}



}
