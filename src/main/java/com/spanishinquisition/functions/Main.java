package com.spanishinquisition.functions;

import usermanagement.User;
import usermanagement.UserManagement;

import java.util.List;

/**
 * Maven Project based on : https://maven.apache.org/
 * All project structure based on Factory method pattern : Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides (1994). Design Patterns: Elements of Reusable Object-Oriented Software. Addison Wesley. pp. 107ff. ISBN 0-201-63361-2 s. 121-132
 */
public class Main {

    /**
     * Only for presenting module in action.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        UserManagement ud = UserManagement.getInstance();
        List<User> userList = ud.getUserList();

        // Print database content.
        System.out.println("Database:");
        for (User user : userList) {
            System.out.println(user.getUsername() + " " + user.getPassword());
        }
        System.out.println("\n");

        Auth auth = Auth.getInstance();

        // Try to log in with correct credentials.
        String token = auth.login("user1", "password");

        System.out.println("Przed spaniem");

        // Print the token.
        System.out.println(token);

        // Try to authorize with the token.
        if (auth.authorize(token)) {
            System.out.println("Access granted!");
        } else
            System.out.println("Access denied!");

        // Sleep for a while...
        System.out.println("Spanie 5s");
        Thread.sleep(5000);

        // Check if the token is out-of-date.
        System.out.println("Po spaniu");
        if (auth.authorize(token)) {
            System.out.println("Access granted!");
        } else
            System.out.println("Access denied!");

        // Print active tokens.
        System.out.println("\nTokens in cache:");
        for (Token x : Auth.tokenList) {
            System.out.println(x.asJson());
        }

    }

}
