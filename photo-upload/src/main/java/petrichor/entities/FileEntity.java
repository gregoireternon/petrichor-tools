package petrichor.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

/**
 * Created by Gregoire on 12/07/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileEntity {
    public UUID reference;
    public String filename;
}
