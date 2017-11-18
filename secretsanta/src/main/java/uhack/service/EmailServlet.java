/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhack.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uhack.model.Group;
import uhack.model.User;


import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Ted
 */
@WebServlet(name = "pairup", value = "/pairup")
public class EmailServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = getBody(req);
        JSONObect json = getJSON(body);
        Group group = getGroup(json);
        List<User> users = group.getUsers();
        List<User> hat = new ArrayList<User>(users);
        Collections.shuffle(hat);
        group.setUsers(pairUp(hat, users, new ArrayList<User>()));
        emailGroup(group);
    }

    private static String getBody(HttpServletRequest req) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();

        return data;
    }

    public static List<User> pairUp(List<User> hat, List<User> users, List<User> paired) {
        if (hat.size() == 0 && users.size() == 0) {
            return paired;
        } else if (hat.size() == 1 && users.size() == 1
                && userEqual(hat.get(0), users.get(0)))  {

            User u = paired.get(0);
            return pairUp(hat, users, paired);
        } else {
            User u1;
            User hatUser;
            if(userEqual(hat.get(0), users.get(0))) {
                u1 = users.get(0);
                hatUser = hat.get(1);
                u1.setSecretSanta(hatUser);
                hat.remove(1);
                users.remove(0);
                paired.add(u1);
            }
            else {
                u1 = users.get(0);
                hatUser = hat.get(0);
                u1.setSecretSanta(hatUser);
                hat.remove(0);
                users.remove(0);
                paired.add(u1);
                
            } 
            return pairUp(hat, users, paired);
        }

    }
    
    private static <E> E  pop (List<E> list) {
        E e = null;
        if(list.size() > 0) {
            e = list.get(0);
            list.remove(0);
        }
        return e;
    }
    
    private static boolean userEqual(User u1, User u2) {
        return u1.getEmail().equals(u2.getEmail());
    }

    private static void emailGroup(Group group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static JSONObect getJSON(String body) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Group getGroup(JSONObect json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
