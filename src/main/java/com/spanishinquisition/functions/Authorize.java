package com.spanishinquisition.functions;

import java.util.ArrayList;
import java.util.List;

class Authorize {

    static List<Token> tokenList = new ArrayList<>();

    Authorize(){
        tokenList = getTokenList();
    }

    List<Token> getTokenList(){
        return tokenList;
    }

    void setTokenInList(Token token){
        tokenList.add(token);
    }

    int setTokenGetId(Token token){
        int id = tokenList.size();
        tokenList.add(id, token);
        return id;
    }

    Token getToken(int id) {
        return tokenList.get(id);
    }
}
