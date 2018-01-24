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
     * Private constructor for a single instance of
     * this class. The instance also gets a reference to the
     * static tokenList.
     */
    private Auth() {
        tokenList = getTokenList();
    }

    /**
     * Creates single instance of this class or
     * returns existing one.
     *
     * @return instance of this class
     */
    public static Auth getInstance() {
        if (Auth.auth == null) {
            auth = new Auth();
        }

        return auth;
    }

    /**
     * @return static list of active tokens.
     */
    List<Token> getTokenList() {
        return tokenList;
    }

    /**
     * Appends the tokenList with a token.
     *
     * @param token
     */
    void setTokenInList(Token token) {
        tokenList.add(token);
    }

    /**
     * Appends the tokenList with a token, returns its id.
     *
     * @param token
     * @return index (id) of the token in tokenList
     */
    int setTokenGetId(Token token) {
        int id = tokenList.size();
        tokenList.add(id, token);
        return id;
    }

    /**
     * @param id index of the token
     * @return token of specified index (id)
     */
    Token getToken(int id) {
        return tokenList.get(id);
    }

    /**
     * Login specified user with username and password.
     *
     * @param username name of the user
     * @param password password of the user
     * @return token as JSON (String) on success, null otherwise
     */
    @Override
    public String login(String username, String password) {
        // First at all check if user is in database.
        UserManagement ud = UserManagement.getInstance();

        for (User user : ud.getUserList()) {
            if (user.getUsername().equals(username)) {
                // Then check the password
                HashFunction hf = Hashing.sha256();
                HashCode hc = hf.newHasher()
                        .putString(password, Charsets.UTF_8)
                        .hash();

                if (hc.toString().equals(user.getPassword())) {
                    // If all passes, create the token based on privileges of user.
                    int id = user.getUserID();
                    int role = user.getPermissionLevel();
                    TokenFactory tokenFactory = new TokenFactory();
                    Token token = tokenFactory.createToken(username, id, role);

                    return token.asJson();

                } else {
                    // Incorrect password.
                    return null;
                }
            }
        }

        // User not found.
        return null;
    }

    /**
     * Checks in the memory if the token is active. Removes token from tokenList
     * if it is older than specified time and returns false. Otherwise returns true.
     *
     * @param token token of the user
     * @return true if token is active, false otherwise
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
     * @param then Previous date object
     * @param now Current date object
     * @return Difference between two dates
     */
    private long getDateDiff(Date then, Date now) {
        return (now.getTime() - then.getTime()) / 1000;
    }
}
