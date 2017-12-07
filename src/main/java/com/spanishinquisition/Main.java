package com.spanishinquisition;

import com.spanishinquisition.implementation.AuthImpl;

public class Main {

    public static void main(String[] args) {
        AuthImpl auth = new AuthImpl();

        System.out.println(auth.login("staho", "fadada"));

    }

}
