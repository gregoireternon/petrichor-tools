package petrichor.network;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import petrichor.entities.Token;
import petrichor.network.method.IGMethod;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Gregoire on 24/05/2018.
 */
public class GNetworkServiceImplementation implements InvocationHandler {
    private final static Logger log = Logger.getLogger(GNetworkServiceImplementation.class.getName());

    private String _rootUrl;
    private Token _token=null;
    private String _login;
    private String _password;
    Client _client = null;

    public GNetworkServiceImplementation(String rootUrl,String login, String password){

        _rootUrl = rootUrl.endsWith("/")?rootUrl.substring(0,rootUrl.length()-1):rootUrl;
        _login = login;
        _password = password;
        _client= ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("toString")){
            return this.toString();
        }
        if(_token==null){
            authenticate();
        }

        IGMethod restMethod = GNetworkHelper.buildMethod(method);
        Class returnType = method.getReturnType();


        int collectionReturnTypeIndex = -1;
        Class collectionType = null;
        if(Collection.class.isAssignableFrom(returnType)){
            Annotation[][] annotations = method.getParameterAnnotations();
            int paramIndex=0;
            for (Annotation[] ann : annotations) {
                if(ann.length>0 && ann[0].annotationType() == GCollectionType.class){
                    collectionType = (Class)args[paramIndex];
                    collectionReturnTypeIndex = paramIndex;
                    break;
                }
                paramIndex++;
            }
        }else{
            Annotation[][] annotations = method.getParameterAnnotations();
            int paramIndex=0;
            Object arg = null;
            for (Annotation[] ann : annotations) {
                if(ann.length>0 && ann[0].annotationType() == GReturnType.class){
                    arg = args[paramIndex];
                    break;
                }
                paramIndex++;
            }
            if(arg!=null){
                returnType = arg.getClass();
            }
        }
        final Object[] mesArgs;
        if(collectionReturnTypeIndex>=0){
            List t = new ArrayList();
            t.addAll(Arrays.asList(args));
            t.remove(collectionReturnTypeIndex);
            mesArgs = t.toArray();
        }else{
            mesArgs = args;
        }

        Response response = restMethod.apply(()->{
            WebTarget target = _client.target(new URI(_rootUrl+"/"+GNetworkHelper.getMethodPath(method,GNetworkHelper.getQueryParams(mesArgs))));
            Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
            builder.header("Authorization", _token.getValue());
            return builder;
        }, args);

        if(returnType==void.class) return null;

        return getResultFromResponse(response, returnType, collectionType);

    }



    private void authenticate() throws Exception {
        log.finest("Retrieving Token");
        WebTarget target = _client.target(new URI(_rootUrl+"/token"));
        MultivaluedMap<String,String> params = new MultivaluedHashMap<String,String>();
        params.add("login",_login);
        params.add("password",_password);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
        Response response = builder.post(Entity.form(params));
        ObjectMapper om = new ObjectMapper();
        Token t = om.readValue(response.readEntity(String.class),Token.class);
        log.fine("token retrieved:"+t.getValue());
        _token = t;
    }

    private <T> T getResultFromResponse(Response response, Class<T> targetType, Type genericReturnType) throws IOException {
        T resp = null;


        if(response.getStatusInfo().getFamily()==Response.Status.Family.SUCCESSFUL){
            String respS = response.readEntity(String.class);
            ObjectMapper om = new ObjectMapper();

            if(genericReturnType!=null){
                JavaType jt = om.constructType(genericReturnType);
                CollectionType ct = om.getTypeFactory().constructCollectionType((Class<? extends Collection>) targetType,jt);
                resp = om.readValue(respS,ct);
            }else{
                resp = om.readValue(respS,targetType);
            }
        }else{
            String msg = null;
            try {
                msg = response.readEntity(String.class);
                log.info(msg);
            }catch(Exception e){
                log.severe(e.toString());
            }
        }
        return resp;
    }


}
