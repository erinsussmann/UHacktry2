/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhack.service;

import com.sendgrid.*;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uhack.model.Group;
import uhack.model.User;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static uhack.service.PairUp.pairUp;
/**
 *
 * @author Ted
 */
@WebServlet(name = "pairup", value = "/pairup")
public class EmailServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(EmailServlet.class.getName());
    private static final String EMAIL_KEY = "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";

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = getBody(req);
        Gson g = new Gson();
        Group group= g.fromJson(body, Group.class);

        System.err.print(g.toJson(group));

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


    private static void emailGroup(Group group) {
        List<User> user = group.getUsers();
       for(int i = 0; i< user.size(); i++){
           sendEmail(user.get(i), user.get(i).getSecretSanta());
       }
    
    }
    
    private static void sendEmail(User giver, User reciever){
        
        
         Email from = new Email("admin@secretsanta-186421.appspotmail.com");
    String subject = "Your Secret Santa";
    Email to = new Email(giver.getEmail());
    String giverEmail = giver.getEmail();
       String giverName = giver.getName();
       String recieverName = reciever.getName();
    Content content = new Content("text/plain", "Hello" + giverName + ", \n Your Secret Santa is " + recieverName + ".\n Thank you, \n Santa's Elves.");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(EMAIL_KEY);
    Request request = new Request();
    
    //-------------------------------------
    try {
        
        request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      
      //----------
    
    }   catch (IOException ex) {
              log.severe(ex.getMessage());
        }
       
    }
}
