package com.company.db;

import com.company.entity.Employee;

import java.util.Date;

public interface DAO {
    void connect();

    void createEmployee(int empno,
                        String ename,
                        String job,
                        Integer mgr,
                        Date hireDate,
                        double sal,
                        Double comm,
                        Integer deptno);

    void deleteEmployeeByEmpno(int empno);

    Employee getEmployeeByEmpno(int empno);

    void disconnect();



}
