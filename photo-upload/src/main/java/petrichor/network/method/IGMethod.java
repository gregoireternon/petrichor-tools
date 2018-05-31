package petrichor.network.method;

import javax.ws.rs.core.Response;

/**
 * Created by Gregoire on 30/05/2018.
 */
public interface IGMethod {

    Response apply(MethodPrepare methodePrepare, Object[] args) throws Exception;
}
