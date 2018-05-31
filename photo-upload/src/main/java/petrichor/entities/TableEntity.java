package petrichor.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by Gregoire on 22/05/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableEntity{
    public String name;
    public String tableName;
    public int entityId;

    public Map<String,TableField> fields;

}
