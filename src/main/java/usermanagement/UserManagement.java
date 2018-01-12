/*
 * Created by IKS DE, IS WIMiIP AGH 2017
 */
package usermanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class UserManagement {

    private static UserManagement userManagement = null;
    private List<User> userList;
    private File file = new File("userBase.xd");

    private UserManagement() {
        userList = new ArrayList<>();

        if (file.isFile()) {
            readUserListFromBase();
        } else {
            System.err.println("Database not found!");
        }
    }

    public static UserManagement getInstance() {
        if (userManagement == null) {
            userManagement = new UserManagement();
        }
        return userManagement;
    }

    private void readUserListFromBase() {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userList = (List<User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    public List<User> getUserList() {
        return userList;
    }

}
