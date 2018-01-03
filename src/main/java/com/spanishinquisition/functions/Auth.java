package com.spanishinquisition.functions;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import usermanagement.User;
import usermanagement.UserManagement;

import java.util.ArrayList;
import java.util.List;

class Auth implements IAuth {

    static List<Token> tokenList = new ArrayList<>();

    Auth(){
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


    @Override
     public String login(String username, String password){
        //first at all check if user is in database
        UserManagement ud = UserManagement.getInstance();

        for(User user : ud.getUserList()) {
            if(user.getUsername().equals(username)) {
                //then check password
                System.out.println("User found");
                HashFunction hf = Hashing.sha256();
                HashCode hc = hf.newHasher()
                        .putString(password, Charsets.UTF_8)
                        .hash();

                //if(hc.toString().equals(user.getPassword())) {
                if(password.equals(user.getPassword())) {
                    //if all passes, making the token based on privileges of user
                    int id = user.getUserID();
                    int role = user.getPermissionLevel();
                    TokenFactory tokenFactory = new TokenFactory();
                    Token token = tokenFactory.createToken(username, id, role);

                    return token.asJson();

                } else {
                    System.out.println("Incorrect password.");
                    break;
                }
            }
        }

        return null;
    }


    @Override
    public boolean authorize(String token){
        //check in cache if token is active
        //if it is, return true

        for(Token tkn : Auth.tokenList) {
            if (tkn.asJson().equals(token)) {
                return true;
            }
        }

        return false;
    }
}
