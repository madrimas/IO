package com.spanishinquisition.controller;

import com.spanishinquisition.functions.Token;

/**
 * Created by madrimas on 06.12.2017.
 */
public interface IAuth {

    String login(String login, String password);
    boolean authorize(String token);

}
