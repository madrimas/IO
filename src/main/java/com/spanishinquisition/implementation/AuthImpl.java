package com.spanishinquisition.implementation;

import com.spanishinquisition.functions.Token;

public class AuthImpl implements com.spanishinquisition.interfaces.IAuth{

    @Override
    public String login(String username, String password){
        //first at all check if user is in database
        //then check password
        //if all passes, making the token based on privileges of user
        int id = 0;
        int role = 4;

        Token token = new Token(username, id, role);

        return token.asJson();

    }

    @Override
    public boolean authorize(String token){
        //check in cache if token is active
        //if it is, return true

        return true;

    }


}
