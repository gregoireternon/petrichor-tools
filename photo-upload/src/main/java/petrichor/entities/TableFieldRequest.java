package petrichor.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import petrichor.network.GNetworkHelper;
import petrichor.network.IGEntity;

/**
 * Created by Gregoire on 23/05/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableFieldRequest implements IGEntity {

    public TableFieldRequest(String name, Class type){
        this.name = name;
        this.type = GNetworkHelper.getTypeName(type);
    }
    public String name;
    public String type;
}
