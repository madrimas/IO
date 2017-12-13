package com.spanishinquisition.functions;

import static com.spanishinquisition.functions.Authorize.tokenList;

/**
 * Created by madrimas on 13.12.2017.
 */
class TokenFactory {

    TokenFactory(){}

    Token createToken(String username, int userId, int role){
        Token token = new Token(username, userId, role);
        tokenList.add(token);
        return token;
    }
}
