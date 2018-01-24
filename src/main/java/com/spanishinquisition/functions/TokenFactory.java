package com.spanishinquisition.functions;

import static com.spanishinquisition.functions.Auth.tokenList;

/**
 * Created by madrimas on 13.12.2017.
 */
class TokenFactory {

    TokenFactory() {
    }
    /**
     * Creates a new token and appends it to
     * the static tokenList.
     *
     * @param username name of the user
     * @param userId ID of the user
     * @param role role of the user
     * @return token for the user
     */
    Token createToken(String username, int userId, int role) {
        Token token = new Token(username, userId, role);
        tokenList.add(token);
        return token;
    }
}
