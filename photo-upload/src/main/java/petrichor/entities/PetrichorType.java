package petrichor.entities;

import java.util.Date;

/**
 * Created by Gregoire on 06/06/2018.
 */
public enum PetrichorType {
    STRING(String.class.getSimpleName()),
    LONG(Long.class.getSimpleName()),
    DATE(Date.class.getSimpleName()),
    FILE("PetFile"),
    BOOLEAN(Boolean.class.getSimpleName()),
    FLOAT(Float.class.getSimpleName());

    PetrichorType(String name){
        this.name = name;
    }

    String name;
    public String getName(){return name;}
}
