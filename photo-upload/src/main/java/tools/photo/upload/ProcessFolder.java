package tools.photo.upload;

import petrichor.entities.*;
import petrichor.network.GServiceProvider;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Gregoire on 07/04/2018.
 */
public class ProcessFolder {
    private final static Logger log = Logger.getLogger(ProcessFolder.class.getName());
    public static final String TABLE_NAME = "Photos";



    public static void main( String[] args ) throws Exception {
        IPetrichorUploader p = GServiceProvider.provide(IPetrichorUploader.class,"http://localhost:8080/web/rest/","a","a");
        TableEntity te = p.getTable(TABLE_NAME);
        if(true){
            if(te==null){
                te = p.createTable(TABLE_NAME);
            }else{
                p.deleteTable(TABLE_NAME);
                te = p.getTable(TABLE_NAME);
                if(te!=null) throw new Exception("Table should be destroyed");
                te = p.createTable(TABLE_NAME);
            }
            TableFieldRequest tfr = new TableFieldRequest(PhotoEntity.FIELD_NOM, PetrichorType.STRING);
            te = p.addField(tfr, te.name);
            tfr = new TableFieldRequest(PhotoEntity.FIELD_CHEMIN,PetrichorType.STRING);
            te = p.addField(tfr,te.name);
            tfr = new TableFieldRequest(PhotoEntity.FIELD_DATE_CREATION,PetrichorType.DATE);
            te = p.addField(tfr,te.name);
            tfr = new TableFieldRequest(PhotoEntity.FIELD_FICHIER,PetrichorType.FILE);
            te = p.addField(tfr, te.name);
            tfr = new TableFieldRequest(PhotoEntity.FIELD_LARGEUR, PetrichorType.LONG);
            te = p.addField(tfr, te.name);
            tfr = new TableFieldRequest(PhotoEntity.FIELD_HAUTEUR, PetrichorType.LONG);
            te = p.addField(tfr, te.name);
        }

        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.fileName="Photo test 1";
        photoEntity.chemin="C:\\totro\\";
        photoEntity.dateCreation= new GregorianCalendar().getTime();
        photoEntity.hauteur=12;
        photoEntity.largeur=24;

        p.addBusinessObject(photoEntity,te.name);

        File file2upload = new File(System.getProperty("user.dir")+"\\photo-upload\\src\\resources\\pdf-sample.pdf");
        FileEntity fe =  p.upload(file2upload);



        Collection<PhotoEntity> photo = p.getObjectList(TABLE_NAME,PhotoEntity.class);




    }
}
