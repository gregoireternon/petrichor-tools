package petrichor.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Gregoire on 23/05/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableField {
    public String tableFieldName;
    public String type;
    public int index;
    public boolean key;
}
