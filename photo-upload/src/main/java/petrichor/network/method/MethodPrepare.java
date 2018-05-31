package petrichor.network.method;

import javax.ws.rs.client.Invocation;

/**
 * Created by Gregoire on 30/05/2018.
 */
@FunctionalInterface
public interface MethodPrepare {
    Invocation.Builder prepare() throws Exception;
}
