package petrichor.network.method;

import petrichor.network.IGEntity;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

/**
 * Created by Gregoire on 30/05/2018.
 */
public class PostMethod extends AbstractMethod {
    @Override
    protected Response applyMethod(Invocation.Builder builder, Object[] args) {
        IGEntity entity = findEntity(args);
        return builder.post(Entity.json(entity));
    }
}
