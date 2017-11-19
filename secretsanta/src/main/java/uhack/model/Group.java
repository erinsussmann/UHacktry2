/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhack.model;

import java.util.List;

/**
 *
 * @author Ted
 */
public class Group {

    private List<User> users;
    private String groupName;

    public Group(List<User> users, String groupName) {

        this.users = users;
        this.groupName = groupName;

    }

    public List<User> getUsers() {
        return this.users;
    }

    public String getGroupName(String groupName) {
        return groupName;
    }

    public void setUsers(List<User> pairUp) {
        this.users = pairUp;
    }
}
