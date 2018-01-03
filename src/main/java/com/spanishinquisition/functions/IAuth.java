package com.spanishinquisition.functions;
/**
 * Created by madrimas on 06.12.2017.
 */
public interface IAuth {

   String login(String username, String password);

   boolean authorize(String token);

}
