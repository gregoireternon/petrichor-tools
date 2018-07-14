package petrichor.network.method;

import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Gregoire on 11/07/2018.
 */
public class FileUploadMethod extends AbstractMethod{
    @Override
    protected Response applyMethod(Invocation.Builder builder, Object[] args) {
        File myFile = (File) args[0];

        try {
            MultiPart multipart = new MultiPart();
            FileDataBodyPart fileBodyPart = new FileDataBodyPart("file",myFile,MediaType.APPLICATION_OCTET_STREAM_TYPE);
            multipart.bodyPart(fileBodyPart);
            Response resp = builder.post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA_TYPE));
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
