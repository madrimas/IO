import com.spanishinquisition.functions.Auth;
import org.junit.AfterClass;
import org.junit.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Wienio on 2018-01-15.
 */

public class Test {

    private static Auth auth = Auth.getInstance();

    /**
     * Test if access for user1 has passed correctly, then
     * change user access token, which should cause a test failure.
     */
    @org.junit.Test
    public void testAccess() {
        String token = auth.login("user1", "password");
        Assert.assertEquals(true, auth.authorize(token));
        token += "random_word";
        Assert.assertEquals(false, auth.authorize(token));
    }

    /**
     * Test if login of user1 returns valid Token object (not Null).
     */
    @org.junit.Test
    public void testCorrectLogin() {
        Assert.assertNotNull(auth.login("user1", "password"));
    }

    /**
     * Test if wrong_user with incorrect password can pass login. This should return null
     * (opposite of correct login), not Token.
     */
    @org.junit.Test
    public void testIncorrectLogin() {
        Assert.assertNull(auth.login("wrong_user", "wrong_password"));
    }

    /**
     * Check whether Token constructor won't pass for any random text
     * wrongToken constructor will throw exception
     */
    @org.junit.Test
    public void test() {
        try {
            Class clazz = Class.forName("com.spanishinquisition.functions.Token");
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            Object goodToken = constructor.newInstance(auth.login("user1", "password"));
            Assert.assertNotNull(goodToken);
            Object wrongToken = constructor.newInstance("random_text");
        } catch (Exception e) {
            Assert.assertEquals(InvocationTargetException.class, e.getClass());
        }
    }

    /**
     * Check if tokenList isn't empty after login, size after all tests should equals 3.
     */
    @AfterClass
    public static void checkTokenList() {
        try {
            Field field = auth.getClass().getDeclaredField("tokenList");
            field.setAccessible(true);
            Object value = field.get(auth);
            List<Object> list = cast(value);
            Assert.assertEquals(false, list.isEmpty());
            Assert.assertEquals(3, list.size());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helpful method for testing if tokens are on token list.
     *
     * @param obj our object to cast to list
     * @param <T> any type for cast
     * @return list of object T
     */
    @SuppressWarnings("unchecked")
    public static <T extends List<?>> T cast(Object obj) {
        return (T) obj;
    }

}