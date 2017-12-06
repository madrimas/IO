package com.spanishinquisition;

import com.spanishinquisition.functions.Token;

public class Main {

    public static void main(String[] args) {
        Token token = new Token("Lala", 12, 4);
        System.out.println(token.asJson());
    }
}
