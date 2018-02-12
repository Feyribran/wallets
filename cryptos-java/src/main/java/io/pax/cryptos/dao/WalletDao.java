package io.pax.cryptos.dao;

import io.pax.cryptos.domain.SimpleWallet;
import io.pax.cryptos.domain.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public class WalletDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Wallet> listWallets() throws SQLException{

        List<Wallet> wallets = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM wallet");

        while (rs.next()){
            String name = rs.getString("name");
            int id = rs.getInt("id");
            wallets.add(new SimpleWallet(id, name));
        }

        rs.close();
        stmt.close();
        conn.close();

        return wallets;
    }


    public int createWallet(int userId, String name) throws SQLException{
        //most import stuff of my life : Never ever string concatenation in jdbc => no sql injection
        String query = "INSERT INTO wallet (name, user_id) VALUES (?,?)";
        //query = "INSERT INTO wallet (name, user_id) VALUES(`test`, 2)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,name);
        statement.setInt(2,userId);

        statement.executeUpdate();

        /*if (rows != 1){
            // Permet de sortir de la fonction (comme return) en cas d'erreur
            throw new SQLException("Something wrong happened with :" +query);
        }*/

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        statement.close();
        conn.close();

        return id;
    }


    public void deleteWallet(int walletId) throws SQLException{
        String query = "DELETE FROM wallet WHERE id=?";
        //query = "INSERT INTO wallet (name, user_id) VALUES('test', 2)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,walletId);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }


    public void deleteByName(String name) throws SQLException{
        String query = "DELETE FROM wallet WHERE name=?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }


    public List<Wallet> findByName(String extract) throws SQLException{
        String query = "SELECT * FROM wallet WHERE name LIKE ?";
        List<Wallet> wallets = new ArrayList<>();
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, extract + "%");

        ResultSet keys = statement.executeQuery();


        while (keys.next()){
            int id = keys.getInt("id");
            String name = keys.getString("name");
            wallets.add(new SimpleWallet(id, name));
        }

        statement.execute();
        statement.close();
        conn.close();

        System.out.println(wallets);
        return wallets;
    }


    public void updateWallet(int walletId, String newName) throws SQLException{
        String query = "UPDATE wallet SET name=? WHERE id=?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,newName);
        statement.setInt(2,walletId);

        statement.executeUpdate();



        statement.close();
        conn.close();
    }

    /**
     * Delete all wallet from a User
     */
    public void deleteAll (int userId) throws SQLException {
        String query = "DELETE FROM wallet WHERE user_id=?";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,userId);

        statement.executeUpdate();
        statement.close();
        conn.close();
    }



    public static void main(String[] args) throws SQLException {
        WalletDao dao = new WalletDao();
        //dao.deleteWallet(25);
        //
        //
        int id = dao.createWallet(9, "Some gtest");
        //System.out.println(id);
        //dao.createUser("ArnaudV");
       // dao.updateWallet(35,"Okay");
        dao.deleteAll(1);
        //List<Wallet> list = dao.findByName("Some");
        //System.out.println(list.size());
    }
}
