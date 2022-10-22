package io.abhijith;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCExecutor {

    public static void main(String[] args) {
        System.out.println("Learning JDBC started!");
        String filePath = args[0];
        Properties properties = getProperties(filePath);

        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager("localhost", "learnjdbc", properties);
        try {
            Connection connection = connectionManager.getConnection();
            CustomerDao customerDao = new CustomerDao(connection);
            Customer customer = new Customer();
            customer.setFirstName("George");
            customer.setLastName("Washington");
            customer.setEmail("george.washington@wh.gov");
            customer.setPhone("(555) 555-6543");
            customer.setAddress("1234 Main St");
            customer.setCity("Mount Vernon");
            customer.setState("VA");
            customer.setZipCode("22121");
            customerDao.create(customer);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Gets the propertes
     * @param filePath from where the properties should be picked up
     * @return Properties instance
     */
    private static Properties getProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(JDBCExecutor.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
