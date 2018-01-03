package com.spanishinquisition.functions;


public class Main{

    public static void main(String[] args) {

        
        Auth auth = new Auth();


       //String token = auth.login("root", "password");
       // System.out.println(token);

        String token = auth.login("user1", "password");
        System.out.println(token);


        if(auth.authorize(token)) {
            System.out.println("Access granted!");
        }
        else
            System.out.println("Access denied!");


        for (Token x: Auth.tokenList) {
            System.out.println(x.asJson());
        }

    }

}
