package petrichor.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import petrichor.network.IGEntity;

import java.util.Date;

/**
 * Created by Gregoire on 06/06/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoEntity implements IGEntity{
    public static final String FIELD_CHEMIN = "Chemin";
    public static final String FIELD_NOM = "Nom";
    public static final String FIELD_DATE_CREATION = "Date de creation";
    public static final String FIELD_FICHIER = "Fichier";
    public static final String FIELD_LARGEUR = "Largeur";
    public static final String FIELD_HAUTEUR = "Hauteur";


    @JsonProperty(FIELD_NOM)
    public String fileName;

    @JsonProperty(FIELD_CHEMIN)
    public String chemin;

    @JsonProperty(FIELD_DATE_CREATION)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public Date dateCreation;

    @JsonProperty(FIELD_LARGEUR)
    public long largeur;

    @JsonProperty(FIELD_HAUTEUR)
    public long hauteur;

    @JsonProperty(FIELD_FICHIER)
    public FileEntity fichier;


}
