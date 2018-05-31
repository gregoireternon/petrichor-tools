package petrichor.network.method;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

/**
 * Created by Gregoire on 30/05/2018.
 */
public class GetMethod extends AbstractMethod {

    @Override
    protected Response applyMethod(Invocation.Builder builder, Object[] args) {
        return builder.get();
    }
}
