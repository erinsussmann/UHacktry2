/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhack.service;

import java.util.List;
import uhack.model.User;

/**
 *
 * @author Ted
 */
public class PairUp {
    
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
    
}
