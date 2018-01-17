package com.spanishinquisition.functions;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import usermanagement.User;
import usermanagement.UserManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auth implements IAuth {

    static List<Token> tokenList = new ArrayList<>();

    private static Auth auth;

    /**
     * fill token list
     */
    private Auth() {
        tokenList = getTokenList();
    }

    /**
     * Creates 1 inscance of object
     * @return object auth
     */
    public static Auth getInstance() {
        if (Auth.auth == null) {
            auth = new Auth();
            return auth;
        }

        return auth;
    }

    List<Token> getTokenList() {
        return tokenList;
    }

    void setTokenInList(Token token) {
        tokenList.add(token);
    }

    int setTokenGetId(Token token) {
        int id = tokenList.size();
        tokenList.add(id, token);
        return id;
    }

    Token getToken(int id) {
        return tokenList.get(id);
    }

    /**
     * Login specific user using name and password
     * @param username name of the user
     * @param password password of the user
     * @return null
     */
    @Override
    public String login(String username, String password) {
        //first at all check if user is in database
        UserManagement ud = UserManagement.getInstance();

        for (User user : ud.getUserList()) {
            if (user.getUsername().equals(username)) {
                //then check password
                HashFunction hf = Hashing.sha256();
                HashCode hc = hf.newHasher()
                        .putString(password, Charsets.UTF_8)
                        .hash();

                if (hc.toString().equals(user.getPassword())) {
                    //if(password.equals(user.getPassword())) {
                    //if all passes, making the token based on privileges of user
                    int id = user.getUserID();
                    int role = user.getPermissionLevel();
                    TokenFactory tokenFactory = new TokenFactory();
                    Token token = tokenFactory.createToken(username, id, role);

                    return token.asJson();

                } else {
                    System.out.println("Incorrect password.");
                    return null;
                }
            }
        }

        System.out.println("User not found.");

        return null;
    }

    /**
     * Check in cache if token is active
     * If it is, return true
     * @param token token of the user
     * @return true or false
     */
    @Override
    public boolean authorize(String token) {


        for (Token tkn : Auth.tokenList) {
            if (tkn.asJson().equals(token)) {

                if (getDateDiff(tkn.getDate(), new Date()) < 3600) {

                    return true;
                } else {
                    Auth.tokenList.remove(tkn);
                    return false;
                }

            }
        }

        return false;
    }

    /**
     * Returning date difference
     * @param then
     * @param now
     * @return difference
     */
    private long getDateDiff(Date then, Date now) {
        return (now.getTime() - then.getTime()) / 1000;
    }
}
