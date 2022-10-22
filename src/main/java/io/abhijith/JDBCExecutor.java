package io.abhijith;

import io.abhijith.utils.OrderDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

//          CustomerDao execution
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Adams");
            customer.setEmail("jadams@wh.gov");
            customer.setPhone("(555) 555-9845");
            customer.setAddress("1234 Main St");
            customer.setCity("Arlington");
            customer.setState("VA");
            customer.setZipCode("01234");
            Customer dbCustomer = customerDao.create(customer);
            System.out.println(dbCustomer);
            dbCustomer = customerDao.findById(dbCustomer.getId());
            System.out.println(dbCustomer);
            dbCustomer.setEmail("john.adams@wh.gov");
            dbCustomer = customerDao.update(dbCustomer);
            System.out.println(dbCustomer);
            customerDao.delete(dbCustomer.getId());
            System.out.println();

            List<Customer> customers = customerDao.findAllSorted(20);
            for(Customer customer1 : customers) {
                System.out.println(customer1);
            }
            System.out.println();

//          OrderDao execution
            OrderDao orderDao = new OrderDao(connection);
            Order order = orderDao.findById(1000);
            System.out.println(order);
            System.out.println();

            List<Order> orderList = orderDao.getOrdersForCustomer(789);
            for(Order order1: orderList) {
                System.out.println(order1);
            }
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
