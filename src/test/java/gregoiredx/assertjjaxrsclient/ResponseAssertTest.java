package gregoiredx.assertjjaxrsclient;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static gregoiredx.assertjjaxrsclient.ResponseAssert.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class ResponseAssertTest {

    @Test
    public void canTellIfOk() throws Exception {
        Response response = Response.ok().build();

        assertThat(response).isOk();
    }

    @Test
    public void canTellIfNotOk() throws Exception {
        Response response = Response.noContent().build();
        try {
            assertThat(response).isOk();
            failBecauseExceptionWasNotThrown(AssertionError.class);
        }catch (AssertionError e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Expected status code to be <200> but was <204>");
        }
    }

    @Test
    public void canAssertOnTextResponse() throws Exception {
        Response response = Response.ok("a text entity").build();

        assertThat(response).isOk().hasContent("a text entity");
    }

    @Test
    public void canAssertFalseOnTextResponse() throws Exception {
        Response response = Response.ok("another text").build();
        try {
            assertThat(response).isOk().hasContent("a text entity");
            failBecauseExceptionWasNotThrown(AssertionError.class);
        }catch (AssertionError e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Expected content to be <a text entity> but was <another text>");
        }
    }
}