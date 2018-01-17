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
    private transient Date date;
    private transient long lifeSpan;

    /**
     * Create token using json
     * @param json
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
     * Create token using sha256
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
     * Get Hash code
     * @return hash code
     */
    private String getHashCode() {
        return hashCode;
    }

    private void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    String asJson() {
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

    public Date getDate() {
        return date;
    }
}
