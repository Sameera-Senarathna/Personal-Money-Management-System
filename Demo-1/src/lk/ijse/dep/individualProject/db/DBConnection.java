package lk.ijse.dep.individualProject.db;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private static Connection connection ;

    public static Connection getConnection() throws SQLException {

        if(connection == null){

            try {
                File file = new File("resource/application.properties");
                FileReader fileReader = new FileReader(file);
                Properties dbProp = new Properties();
                dbProp.load(fileReader);

                String ip = dbProp.getProperty("ip");
                String port = dbProp.getProperty("port");
                String db = dbProp.getProperty("database");
                String username = dbProp.getProperty("username");
                String password = dbProp.getProperty("password");

                String url = "jdbc:mysql://" + ip + ":" + port + "/" + db;

                connection = DriverManager.getConnection(url, username, password);
            }catch (Exception e){
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

         //   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_money_management_system","ssfs","123456");

        }

        return connection;
    }

}
