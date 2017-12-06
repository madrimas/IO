package com.spanishinquisition;

import com.spanishinquisition.functions.Token;
import com.spanishinquisition.implementation.IAuth;

public class Main {

    public static void main(String[] args) {
        IAuth auth = new IAuth();

        System.out.println(auth.login("staho", "fadada"));

    }

}
