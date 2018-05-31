package tools.photo.upload;

import petrichor.entities.TableEntity;
import petrichor.entities.TableFieldRequest;
import petrichor.network.GServiceProvider;

import java.util.logging.Logger;

/**
 * Created by Gregoire on 07/04/2018.
 */
public class ProcessFolder {
    private final static Logger log = Logger.getLogger(ProcessFolder.class.getName());

    public static void main( String[] args ) throws Exception {
        IPetrichorUploader p = GServiceProvider.provide(IPetrichorUploader.class,"http://localhost:8080/web/rest/","a","a");
        TableEntity te = p.getTable("aze rrr");
        if(te==null){
            te = p.createTable("aze rrr");
        }else{
            p.deleteTable("aze rrr");
            te = p.getTable("aze rrr");
            if(te!=null) throw new Exception("Table should be destroyed");
            te = p.createTable("aze rrr");
        }
        TableFieldRequest tfr = new TableFieldRequest("Nom",String.class);
        te = p.addField(tfr, te.name);





    }
}
