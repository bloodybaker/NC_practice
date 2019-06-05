package com.company.db;

import com.company.entity.Employee;
import com.company.parser.ConnectionParser;

import java.sql.*;
import java.util.Date;
import java.util.Map;

public class OracleDAO implements DAO {

    private static final OracleDAO instance = new OracleDAO();
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static OracleDAO getInstance() {
        return instance;
    }

    @Override
    public void connect() {
        Map<String, String> params = ConnectionParser.getConnectionParamFromXml();
        try {
            Class.forName(params.get("driverClass"));
            connection = DriverManager.getConnection(params.get("url"), params.get("userName"), params.get("password"));


//            Class.forName("oracle.jdbc.OracleDriver");
//            connection = DriverManager.getConnection("jdbc:oracle:thin:@176.108.235.133:1521:XE", "student", "student");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createEmployee(int empno,
                               String ename,
                               String job,
                               Integer mgr,
                               java.util.Date hireDate,
                               double sal,
                               Double comm,
                               Integer deptno) {
        connect();
        try {

            java.sql.Date sqlDate = new java.sql.Date(hireDate.getTime());
            preparedStatement = connection.prepareStatement("INSERT INTO EUG_CHYKALOV " +
                    "VALUES (?, ?, ?, ?, TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?)");
            preparedStatement.setInt(1, empno);
            preparedStatement.setString(2, ename);
            preparedStatement.setString(3, job);
            if (mgr != null) {
                preparedStatement.setInt(4, mgr);
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            preparedStatement.setDate(5, sqlDate);
            preparedStatement.setDouble(6, sal);
            if (comm != null) {
                preparedStatement.setDouble(7, comm);
            } else {
                preparedStatement.setNull(7, Types.DOUBLE);
            }
            if (deptno != null) {
                preparedStatement.setInt(8, deptno);
            } else {
                preparedStatement.setNull(8, Types.INTEGER);
            }
            if (!preparedStatement.execute()) {
                System.out.println("Вставка прошла успешно.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public void deleteEmployeeByEmpno(int empno) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM EUG_CHYKALOV WHERE empno = ?");
            preparedStatement.setInt(1, empno);
            int isOk = preparedStatement.executeUpdate();
            if (isOk != 0) {
                System.out.println("Удаление прошло успешно.");
            } else {
                if (isOk == 0) {
                    System.out.println("Сотрудника с таким номером нет.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    @Override
    public Employee getEmployeeByEmpno(int empno) {
        Employee employee = null;
        connect();
        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT e.*, d.loc, d.dname, s.grade "
                            + "FROM emp e "
                            + "JOIN dept d ON e.deptno = d.deptno "
                            + "JOIN salgrade s ON e.sal >= s.minsal AND e.sal <= s.hisal "
                            + "WHERE e.empno = ? ");
            preparedStatement.setInt(1, empno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = parseEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return employee;
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee parseEmployee(ResultSet resultSet) {
        Employee employee = null;
        try {
            int empno = resultSet.getInt("EMPNO");
            String ename = resultSet.getString("ENAME");
            String job = resultSet.getString("JOB");
            int mgr = resultSet.getInt("MGR");
            Date hireDate = resultSet.getDate("HIREDATE");
            double sal = resultSet.getDouble("SAL");
            double comm = resultSet.getDouble("COMM");
            int deptno = resultSet.getInt("DEPTNO");
            String loc = resultSet.getString("LOC");
            String dname = resultSet.getString("DNAME");
            int grade = resultSet.getInt("GRADE");
            employee = new Employee(
                    empno,
                    ename,
                    job,
                    mgr,
                    hireDate,
                    sal,
                    comm,
                    deptno,
                    loc,
                    dname,
                    grade
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
