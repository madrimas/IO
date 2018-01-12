/*
 * Created by IKS DE, IS WIMiIP AGH 2017
 */
package usermanagement;

import java.io.Serializable;

public class User implements Serializable {

    private int userID;
    private int permissionLevel;
    private String username;
    private String password;


    public User(String username, int permissionLevel, int ID) {

        this.username = username;
        this.password = "password";
        this.userID = ID;
        this.permissionLevel = permissionLevel;
    }


    public int getUserID() {
        return userID;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
