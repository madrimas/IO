package com.spanishinquisition.functions;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import usermanagement.IUserData;
import usermanagement.IUserManagement;
import usermanagement.User;
import usermanagement.UserManagement;

import java.util.List;
import java.util.Random; //only for test


/**
 * Created by madrimas on 06.12.2017.
 */
public interface IAuth {

    default String login(String username, String password){
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

                if(hc.toString().equals(user.getPassword())) { //if hashes match
                    //if(password.equals(user.getPassword())) {
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
