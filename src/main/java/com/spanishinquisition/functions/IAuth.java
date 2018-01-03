package com.spanishinquisition.functions;

import com.google.gson.Gson;
import usermanagement.User;

import java.util.Random; //only for test


/**
 * Created by madrimas on 06.12.2017.
 */
public interface IAuth {

    default String login(String username, String password){
        //first at all check if user is in database
        //then check password
        //if all passes, making the token based on privileges of user



        Random random = new Random(); //only for test
        int id = random.nextInt(); //get from data storage
        int role = random.nextInt(); //get from data storage

        TokenFactory tokenFactory = new TokenFactory();
        Token token = tokenFactory.createToken(username, id, role);

        return token.asJson();
    }

    default boolean authorize(String token){
        //check in cache if token is active
        //if it is, return true


        for(Token tkn : Authorize.tokenList) {
            if (tkn.asJson().equals(token)) {
                return true;
            }
        }

        return false;
    }
}
