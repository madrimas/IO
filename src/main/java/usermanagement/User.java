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

    /**
     * Create a new user
     * @param username name of the user
     * @param permissionLevel permission level
     * @param ID user ID
     */
    public User(String username, int permissionLevel, int ID) {

        this.username = username;
        this.password = "password";
        this.userID = ID;
        this.permissionLevel = permissionLevel;
    }

    /**
     *
     * @return userID id of the user
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @return username name of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return password password of the user
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return permission permision level of the user
     */
    public int getPermissionLevel() {
        return permissionLevel;
    }
}
