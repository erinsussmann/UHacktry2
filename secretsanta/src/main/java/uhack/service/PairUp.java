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
        if (hat.isEmpty() && users.isEmpty()) {
            return paired;
        } else if (hat.size() == 1 && users.size() == 1
                && userEqual(hat.get(0), users.get(0)))  {
        	
        	User u  = getAndRemove(users, 0);
        	hat.remove(0);
            
            u.setSecretSanta(u);
            
            swapSecretSantas(paired.get(0), u);
            paired.add(u);
            return paired;
        } else {
            User u1;
            User hatUser;
            if(userEqual(hat.get(0), users.get(0))) {
                u1 = getAndRemove(users, 0);
            	
                hatUser  = getAndRemove(hat, 1);
                
                u1.setSecretSanta(hatUser);

                paired.add(u1);
            }
            else {
            	u1 = getAndRemove(users, 0);
            	
            	hatUser = getAndRemove(hat, 0);
                
                u1.setSecretSanta(hatUser);
                
                paired.add(u1);
                
            } 
            return pairUp(hat, users, paired);
        }

    }
    
    private static boolean userEqual(User u1, User u2) {
        return u1.getEmail().equals(u2.getEmail());
    }
    
    private static User getAndRemove(List<User> list, int index) {
        User user = list.get(index);
        list.remove(index);
        return user;
    }

    private static void swapSecretSantas(User u1, User u2) {
    	User tmp = u2.getSecretSanta();
    	u2.setSecretSanta(u1.getSecretSanta());
    	u1.setSecretSanta(tmp);
    }
}
