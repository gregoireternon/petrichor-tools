package petrichor.network;

import petrichor.network.method.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregoire on 30/05/2018.
 */
public class GNetworkHelper {

    public static String getMethodPath(Method method) throws Exception {
        GPath path = method.getAnnotation(GPath.class);
        if(path!=null){
            String res = path.value();
            if(res.startsWith("/")) res = res.substring(1);
            if(res.endsWith("/")) res = res.substring(0,res.length()-1);
            return res;
        }
        else{
            throw new Exception("Missing GPath annotation on method "+method.getName());
        }
    }



    public static String getQueryParams(Object[] args) {
        List<String> p = new ArrayList<String>();
        if(args==null) return null;
        for (Object o :args
                ) {
            if(!(o instanceof IGEntity)){
                p.add(urlEncode(o.toString()));
            }
        }
        return String.join("/",p);
    }

    public static String urlEncode(String param){
        try {
            return URLEncoder.encode(param, "ISO-8859-1").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static IGMethod buildMethod(Method method) throws Exception {
        GMethod m = method.getAnnotation(GMethod.class);
        GMethod.MethodName mn = m.value();
        switch (mn){
            case GET:
                return new GetMethod();
            case POST:
                return new PostMethod();
            case DELETE:
                return new DeleteMethod();
            case PUT:
                return new PutMethod();
            default:
                throw new Exception("No restmethod found on method");
        }
    }

    public static String getTypeName(Class c){
        String[] o = c.getName().split("\\.");
        return o[o.length-1];
    }

}
