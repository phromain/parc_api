package repositories;


import entities.ParcEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

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

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<Object[]> getUrlAndSocialNetworkNameByParcId(Integer parcId) {
        String query = "SELECT u.urlReseau, r.libReseau " +
                "FROM UrlReseauSociauxEntity u " +
                "JOIN u.idReseauSociauxEntity r " +
                "WHERE u.idParcEntity.id = :parcId";

        return entityManager.createQuery(query, Object[].class)
                .setParameter("parcId", parcId)
                .getResultList();
    }

}
