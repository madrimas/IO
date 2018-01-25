package com.spanishinquisition.functions;

import static com.spanishinquisition.functions.Auth.tokenList;

/**
 * Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides (1994)
 * Design Patterns: Elements of Reusable Object-Oriented Software
 * Addison Wesley pp 107ff ISBN 0-201-63361-2 s 121-132
 */
class TokenFactory {

    TokenFactory() {
    }
    /**
     * Creates a new token with specified attributes
     * and appends it to the static tokenList.
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
