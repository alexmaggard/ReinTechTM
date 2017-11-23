/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.data;

import employee.main.Employee;
import employee.main.TimeClock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDB {
    
    public static int insert(Employee employee){

    }
    
    public static int update(Employee employee){

    }
    
    public static int delete(Employee employee){

    }
    
    public static Employee selectEmployee(int employeeID){

    }
  
    
    //TODO: WEEK 5-6 - get employee array list working
    public static ArrayList<Employee> selectEmployees(){
        // work here danny
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Employee";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Employee> employeeList = new ArrayList<Employee>();
                while (rs.next())
                {
                    Employee employee = new Employee();
                    employee.setEmployeeID(rs.getInt("EmloyyeeID"));
                    employee.setAuthLevel(rs.getInt(query));
                    employee.setPayRate(rs.getDouble(query));
                    employee.setFirstName(rs.getString("FirstName"));
                    employee.setLastName(rs.getString("LastName"));
                   //employee.setPassword(query);
                   //employee.setStatus(query);
                    employeeList.add(employee);
                }
            return employeeList ;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<TimeClock> selectTimeClocks(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Timeclock";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<TimeClock> timeClocks = new ArrayList<TimeClock>();
            while (rs.next())
            {
                TimeClock timeClock = new TimeClock();
                timeClock.setDay(rs.getDate("day"));
                timeClock.setStartTime(rs.getDate("startTime"));
                timeClock.setLunchOut(rs.getDate("lunchOut"));
                timeClock.setLunchIn(rs.getDate("lunchIn"));
                timeClock.setEndTime(rs.getDate("endTime"));
                
                timeClocks.add(timeClock);
            }
            return timeClocks;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
                
    }
}
