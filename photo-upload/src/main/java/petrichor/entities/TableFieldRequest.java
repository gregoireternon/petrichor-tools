package petrichor.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import petrichor.network.IGEntity;

/**
 * Created by Gregoire on 23/05/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableFieldRequest implements IGEntity {

    public TableFieldRequest(String name, PetrichorType type){
        this.name = name;

        this.type = type.getName();
    }
    public String name;
    public String type;
}
