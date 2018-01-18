package com.spanishinquisition.functions;

/**
 * Created by madrimas on 06.12.2017.
 */
public interface IAuth {
    /**
     *
     * @param username name of the new user
     * @param password password of the new user
     * @return null
     */
    String login(String username, String password);

    /**
     * Token authorizatiom
     * @param token token of user
     * @return true or false
     */
    boolean authorize(String token);

}
