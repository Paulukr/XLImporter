package com.journaldev.java.ssh;
 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.sql.Connection;
 
 
public class MySqlConnOverSSH {
 
    /**
     * Java Program to connect to remote database through SSH using port forwarding
     * @author Pankaj@JournalDev
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
 
        int lport=5656;
        //String rhost="secure.journaldev.com";
        String rhost="138.68.72.237";
        //String host="secure.journaldev.com";
        String host="127.0.0.1";
        int rport=3306;
        //String user="sshuser";
        String user="root";
//        String password="sshpassword";
        String password="5gBiQu96VRig";
//        String dbuserName = "mysql";
        String dbuserName = "root";
//        String dbpassword = "mysql123";
        String dbpassword = "5gBiQu96VRig";
        String url = "jdbc:mysql://localhost:"+lport+"/mydb";
        String driverName="com.mysql.jdbc.Driver";
        Connection conn = null;
        Session session= null;
        try{
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            System.out.println("Port Forwarded");
             
            //mysql database connectivity
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
            System.out.println ("Database connection established");
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(conn != null && !conn.isClosed()){
                System.out.println("Closing Database Connection");
                conn.close();
            }
            if(session !=null && session.isConnected()){
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }
    }
 
}