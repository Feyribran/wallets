package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.domain.jdbc.SimpleUser;
import io.pax.cryptos.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
@Path("users") //pour avoir /crypto/api/users
// (api car il n'y a qu'un @ApplicationPath pour
//toute l'application dans le package)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}") // this is a PathParam
    public User getUser(@PathParam("id") int userId) throws SQLException {
        return new UserDao().findUserWithWallets(userId);
    }

    @POST
    public SimpleUser createUser(SimpleUser user) throws SQLException {
        if (user.getName().length() < 2){
            throw new NotAcceptableException("406: user name must have at least 2 letters");
        }

        try {
            System.out.println(user.getName());
            int id = new UserDao().createUser(user.getName());
            return new SimpleUser(id, user.getName());
        } catch (SQLException e) {
           throw e;
        }

    }
}
