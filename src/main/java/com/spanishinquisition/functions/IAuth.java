package com.spanishinquisition.functions;

/**
 * Created by madrimas on 06.12.2017.
 */
public interface IAuth {
    /**
     * Login specified user with username and password.
     *
     * @param username name of the new user
     * @param password password of the new user
     * @return token as JSON (String) on success, null otherwise
     */
    String login(String username, String password);

    /**
     * Token authorization based on current memory content.
     *
     * @param token token of user
     * @return true or false
     */
    boolean authorize(String token);

}
