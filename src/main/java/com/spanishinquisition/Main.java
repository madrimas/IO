package com.spanishinquisition;

import com.spanishinquisition.implementation.IAuthImpl;

public class Main {

    public static void main(String[] args) {
        IAuthImpl auth = new IAuthImpl();

        System.out.println(auth.login("staho", "fadada"));

    }

}
