import com.spanishinquisition.functions.Auth;
import org.junit.Assert;

/**
 * Created by Wienio on 2018-01-15.
 */

public class Test {

    Auth auth = Auth.getInstance();

    @org.junit.Test
    public void testAccess() {
        String token = auth.login("user1", "password");
        Assert.assertEquals(true, auth.authorize(token));
        token += "random";
        Assert.assertEquals(false, auth.authorize(token));
    }

}