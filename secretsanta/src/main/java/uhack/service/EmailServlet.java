/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhack.service;

/**
 *
 * @author Ted
 */
public class EmailServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String body = getBody(req);
        JSONObect json = getJSON(body);
        Group group = getGroup(json);
        List<User> users = group.getUsers();
        List<User> hat = new ArrayList<User>(users);
        Collections.shuffle(hat);
        group.setUsers(pairUp(hat, users, new ArrayList<User>()));
        emailGroup(group);
    }

    private static String getBody(HttpServletRequest req) {
        StringBuilder buffer = new StringBuilder();
        BufferReader reader = request.getReader();
        String line;
        while ((line = reader.readline()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();

        return data;
    }

    public static List<User> pairUp(List<User> hat, List<User> users, List<User> paired) {
        if (hat.size() == 0 && users.size() == 0) {
            return paired;
        } else if (hat.size() == 1 && users.size() == 1
                && (hat.get(0)
                        .getEmail()
                        .equals(users.get(0).getEmail()))  {

            User u = paired.get(0);
            pairUp(hat, users, paired);
        } else {

            pairUp(hat, users, paired);
        }

    }
}
