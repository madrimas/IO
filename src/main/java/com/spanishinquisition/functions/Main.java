package com.spanishinquisition.functions;


public class Main implements IAuth{

    public static void main(String[] args) {

        IAuth iAuth = new IAuth() {};
        Authorize authorize = new Authorize();

        String token = iAuth.login("madrimas", "wolololo");
        System.out.println(token);
        token = iAuth.login("elo", "wolololo");
        System.out.println(token);
        token = iAuth.login("mordo", "wolololo");
        System.out.println(token);
        token = iAuth.login("morenka", "wolololo");
        System.out.println(token);
        token = iAuth.login("ryuchanie", "wolololo");
        System.out.println(token);
        if(iAuth.authorize(token)) {
            System.out.println("Access granted!");
        }
        else
            System.out.println("Access denied!");

        for (Token x:Authorize.tokenList
             ) {System.out.println(x.asJson());
        }

    }

}
