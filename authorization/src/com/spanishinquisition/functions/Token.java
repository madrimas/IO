package com.spanishinquisition.functions;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;

import java.util.Date;

public class Token {
    private String username;
    private int userId;
    private int role;
    private String hashCode;

    public Token(String json){
        Gson gson = new Gson();
        Token token = gson.fromJson(json, Token.class);
        this.username = token.getUsername();
        this.userId = token.getUserId();
        this.hashCode = token.getHashCode();
        this.role = token.getRole();

    }

    public Token(String username, int userId, int role){
        this.username = username;
        this.userId = userId;
        this.role = role;
        Date date = new Date();
        HashFunction hf = Hashing.sha256();
        HashCode hc = hf.newHasher()
                .putInt(userId)
                .putString(username, Charsets.UTF_8)
                .putInt(role)
                .putString(date.toString(), Charsets.UTF_8)
                .hash();
        this.hashCode = hc.toString();

    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String asJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
