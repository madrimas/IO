package com.spanishinquisition.functions;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;

import java.util.Date;

/**
 * Hashing based on tutorial in documentatnion : https://github.com/google/guava/wiki/HashingExplained
 * Gson documentation : https://sites.google.com/site/gson/gson-user-guide
 */
class Token {
    private String username;
    private int userId;
    private int role;
    private String hashCode;
    private transient Date date;

    /**
     * Unmarshals JSON String into temporary Token object
     * and copies its attributes to the current instance.
     *
     * @param json marshaled token object
     */
    protected Token(String json) {
        Gson gson = new Gson();
        Token token = gson.fromJson(json, Token.class);
        this.username = token.getUsername();
        this.userId = token.getUserId();
        this.hashCode = token.getHashCode();
        this.role = token.getRole();
    }

    /**
     * Creates a token with username, user id and role, then
     * generates hashcode based on these attributes and timestamp
     * using sha256 hash function.
     *
     * @param username name of the user
     * @param userId ID of the user
     * @param role role of the user
     */
    Token(String username, int userId, int role) {
        this.username = username;
        this.userId = userId;
        this.role = role;
        this.date = new Date();
        HashFunction hf = Hashing.sha256();
        HashCode hc = hf.newHasher()
                .putInt(userId)
                .putString(username, Charsets.UTF_8)
                .putInt(role)
                .putString(date.toString(), Charsets.UTF_8)
                .hash();
        this.hashCode = hc.toString();
    }

    /**
     * @return hash code
     */
    private String getHashCode() {
        return hashCode;
    }


    /**
     * Marshals token object into JSON String.
     *
     * @return JSON String
     */
    String asJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     *
     * @return username from the token
     */
    private String getUsername() {
        return username;
    }

    /**
     *
     * @return user identifier from the token
     */
    private int getUserId() {
        return userId;
    }

    /**
     *
     * @return user role from the token.
     */
    private int getRole() {
        return role;
    }

    /**
     *
     * @return creation timestamp from the token.
     */
    public Date getDate() {
        return date;
    }
}
