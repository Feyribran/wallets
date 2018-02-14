package io.pax.cryptos.ws;

import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;
import io.pax.cryptos.domain.jdbc.FullWallet;
import io.pax.cryptos.jpa.JpaWalletDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */

@Path("wallets") //pour avoir /crypto/api/wallets
                // (api car il n'y a qu'un @ApplicationPath pour
                //toute l'application dans le package)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletWs {

    @GET
    public List<Wallet> getWallets() throws SQLException{
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

    @Path("{id}")
    public Wallet getWallet(@PathParam("id") int walletId){
       return new JpaWalletDao().getWallet(walletId);
    }

    @POST
    /* create Wallet with id */
    public FullWallet createWallet(FullWallet wallet /* sent wallet, has no idea*/) throws SQLException {
        User user = wallet.getUser();

        if (user == null){
            //400x : navigator sent wrong informations
            throw new NotAcceptableException("No user id sent");
        }



        if (wallet.getName().length()<2){
            throw new NotAcceptableException("406: wallet name must have at least 2 letters");
        }

        try {
            System.out.println(wallet.getId());
            System.out.println(wallet.getName());
            int id = new WalletDao().createWallet(user.getId(),wallet.getName());

            return new FullWallet(id ,wallet.getName(), wallet.getUser());
        } catch (SQLException e) {
            // Breaks POLA
         throw e;
        }

    }


    @DELETE
    public void deleteWallet(FullWallet deletedWallet) throws SQLException {

        try {
            WalletDao dao = new WalletDao();
            int id = deletedWallet.getId();
            dao.deleteWallet(id);
        } catch (SQLException e) {
           throw e;
        }
    }

}
