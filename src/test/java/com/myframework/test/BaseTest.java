package com.myframework.test;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.sql.*;

public class BaseTest {
    public String theBaseUri="";

    @BeforeTest
    public void initialize() throws SQLException {
        String myTestEnv=System.getProperty("Env");

        String relativePath="src/main/java/database/TestNgRestAssured.db";
        String dbPath=new File(relativePath).getAbsolutePath();
        String myConnectionUrl="jdbc:sqlite:"+dbPath;
        Connection connection= DriverManager.getConnection(myConnectionUrl);

        connection.setAutoCommit(false);
        Statement myStatement=connection.createStatement();

        String myQuery="SELECT * FROM RestServices WHERE Environment='"+myTestEnv+"' AND ServiceType='Booking'";
        ResultSet myResult=myStatement.executeQuery(myQuery);

        int myCount;

        for (myCount=1;myResult.next();myCount++)
        {
            theBaseUri=myResult.getString("Url");
        }

        myResult.close();
        myStatement.close();
        connection.close();
    }

}
