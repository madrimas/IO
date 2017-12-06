package com.spanishinquisition.functions;

import com.spanishinquisition.controller.IAuth;

public abstract class Login implements IAuth{

    @Override
    public Token login(String password, String login){
        Token token = new Token();
        return token;
    }


}
