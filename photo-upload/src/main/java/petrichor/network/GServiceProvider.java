package petrichor.network;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Gregoire on 24/05/2018.
 */
public class GServiceProvider {

    public static <T> T provide(Class<T> serviceInterface,String rootUrl, String login, String password){
        InvocationHandler handler = new GNetworkServiceImplementation(rootUrl, login, password);
        T res = (T)Proxy.newProxyInstance(GServiceProvider.class.getClassLoader(),new Class[]{serviceInterface}, handler);
        return res;
    }
}
