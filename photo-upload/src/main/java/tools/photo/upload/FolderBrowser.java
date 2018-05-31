package tools.photo.upload;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Gregoire on 07/04/2018.
 */
public class FolderBrowser {

    String rootFolder;
    private final static Logger log = Logger.getLogger(FolderBrowser.class.getName());
    private Map<String, Integer> metaSorts = new HashMap<String,Integer>();


    public FolderBrowser(String rootFolder){
        this.rootFolder=rootFolder;
    }

    public void processFolder() throws Exception {
        File f = new File(rootFolder);
        if(!f.exists()){
            throw new Exception(String.format("Providen folder %s doe not exists", rootFolder));
        }
        if(!f.isDirectory()){
            throw new Exception(String.format("Providen path %s must be a directory", rootFolder));
        }
        log.info("Processing folder "+f.getCanonicalPath());
        processFolder(f);
        ObjectMapper om = new ObjectMapper();

        try {
            log.info("sortes de metadatas: "+om.writeValueAsString(metaSorts));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void processFolder(File folder) throws Exception {
        for(File f:folder.listFiles()){
            if(f.isDirectory()){
                processFolder(f);
            }else{
                log.info(String.format("processing file %s",f.getCanonicalPath()));
                try {
                    Metadata metadata = ImageMetadataReader.readMetadata(f);

                    print(metadata,"basic usage");
                } catch (ImageProcessingException e) {
                    log.warning("Error processing image "+f);
                }catch(Exception e){
                    log.warning("Global exception processing image "+f);
                }

            }
        }
    }


    private void print(Metadata metadata, String method)
    {
        for (Directory directory : metadata.getDirectories()) {

            //
            // Each Directory stores values in Tag objects
            //
            Map<String,String> metadatas = new HashMap<String, String>();
            for (Tag tag : directory.getTags()) {
                metadatas.put(tag.getTagName(),tag.getDescription());
                Integer cpt = metaSorts.get(tag.getTagName());
                if(cpt==null){
                    metaSorts.put(tag.getTagName(),1);
                }else{
                    metaSorts.put(tag.getTagName(),cpt+1);
                }
            }
            ObjectMapper om = new ObjectMapper();
            if(metadatas.containsKey("X Resolution")){
                log.info(metadatas.get("X Resolution"));
            }

            /*try {
                log.info(om.writeValueAsString(metadatas));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
*/
            //
            // Each Directory may also contain error messages
            //
            for (String error : directory.getErrors()) {
                log.severe("ERROR: " + error);
            }
        }
    }

}
