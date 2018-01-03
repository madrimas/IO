package com.spanishinquisition.functions;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;

import java.util.Date;

class Token {
    private String username;
    private int userId;
    private int role;
    private String hashCode;

    protected Token(String json){
        Gson gson = new Gson();
        Token token = gson.fromJson(json, Token.class);
        this.username = token.getUsername();
        this.userId = token.getUserId();
        this.hashCode = token.getHashCode();
        this.role = token.getRole();
    }

    Token(String username, int userId, int role){
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

    private String getHashCode() {
        return hashCode;
    }

    private void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

   String asJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private int getRole() {
        return role;
    }

    protected void setRole(int role) {
        this.role = role;
    }
}
