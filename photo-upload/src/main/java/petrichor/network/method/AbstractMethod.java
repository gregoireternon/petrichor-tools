package petrichor.network.method;

import petrichor.network.IGEntity;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

/**
 * Created by Gregoire on 30/05/2018.
 */
public abstract class AbstractMethod implements IGMethod  {



    public Response apply(MethodPrepare methodePrepare, Object[] args) throws Exception {
        Invocation.Builder builder = methodePrepare.prepare();
        return applyMethod(builder, args);
    }

    protected abstract Response applyMethod(Invocation.Builder builder, Object[] args);


    protected IGEntity findEntity(Object[] args) {
        for (Object o :args) {
            if(o instanceof IGEntity) return (IGEntity)o;
        }
        return null;
    }

}
