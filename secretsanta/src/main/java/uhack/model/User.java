/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhack.model;

/**
 *
 * @author Ted
 */
public class User {

    private String email;
    private String name;
    private User secretSanta;

    public User(String email, String name) {

        this.email = email;
        this.name = name;

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setSecretSanta(User secretSanta) {
        this.secretSanta = secretSanta;
    }

}
