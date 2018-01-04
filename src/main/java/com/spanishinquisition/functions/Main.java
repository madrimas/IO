package com.spanishinquisition.functions;


import usermanagement.User;
import usermanagement.UserManagement;

import java.util.List;

public class Main{

    public static void main(String[] args) {

        UserManagement ud = UserManagement.getInstance();
        List<User> userList = ud.getUserList();

        System.out.println("Database:");
        for(User user : userList) {
            System.out.println(user.getUsername() +" "+ user.getPassword());
        }
        System.out.println("\n");


        Auth auth = Auth.getInstance();

        String token = auth.login("user1", "password");
        System.out.println("Token: " + token + "\n");
        token = auth.login("user2", "dhjashdk");
        System.out.println("Token: " + token + "\n");
        token = auth.login("user2137", "dhjashdk");
        System.out.println("Token: " + token + "\n");
        token = auth.login("user3", "password");
        System.out.println("Token: " + token + "\n");


        if(auth.authorize(token)) {
            System.out.println("Access granted!");
        }
        else
            System.out.println("Access denied!");


        System.out.println("\nTokens in cache:");
        for (Token x: Auth.tokenList) {
            System.out.println(x.asJson());
        }
    }

}
