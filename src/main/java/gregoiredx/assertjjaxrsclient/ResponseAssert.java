package gregoiredx.assertjjaxrsclient;

import org.assertj.core.api.AbstractAssert;

import javax.ws.rs.core.Response;
import java.util.Objects;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response> {

    public ResponseAssert(Response actual) {
        super(actual, ResponseAssert.class);
    }

    public static ResponseAssert assertThat(Response actual) {
        return new ResponseAssert(actual);
    }

    public ResponseAssert isOk() {
        isNotNull();
        if (actual.getStatus() != Response.Status.OK.getStatusCode()) {
            failWithMessage("Expected status code to be <%s> but was <%s>", Response.Status.OK.getStatusCode(), actual.getStatus());
        }
        return this;
    }

    public ResponseAssert hasContent(CharSequence textEntity) {
        isNotNull();
        if (! Objects.equals(actual.getEntity(), textEntity)) {
            failWithMessage("Expected content to be <%s> but was <%s>", textEntity, actual.getEntity());
        }
        return null;
    }
}
