package DtoOut;

import entities.UrlReseauSociauxEntity;
import lombok.Data;

@Data
public class UrlReseauSociauxInfoDto {

    private Integer id;
    private String urlReseau;
    private Integer idParcEntity;
    private String nomParc;
    private Integer idReseauSociaux;
    private String nomReseauSociaux;

    public UrlReseauSociauxInfoDto(UrlReseauSociauxEntity urlReseauSociaux) {
        this.id = urlReseauSociaux.getId();
        this.urlReseau = urlReseauSociaux.getUrlReseau();
        this.idParcEntity = urlReseauSociaux.getIdParcEntity().getId();
        this.nomParc = urlReseauSociaux.getIdParcEntity().getNomParc();
        this.idReseauSociaux = urlReseauSociaux.getIdReseauSociauxEntity().getId();
        this.nomReseauSociaux = urlReseauSociaux.getIdReseauSociauxEntity().getLibReseau();
    }

}
