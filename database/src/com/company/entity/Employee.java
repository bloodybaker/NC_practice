package com.company.entity;

import java.util.Date;

public class Employee {
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hireDate;
    private double sal;
    private double comm;
    private int deptno;

    //Допольнительные параметры при запросе
    private String loc;
    private String dname;
    private int grade;

    public Employee(int empno,
                    String ename,
                    String job,
                    int mgr,
                    Date hireDate,
                    double sal,
                    double comm,
                    int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Employee(int empno,
                    String ename,
                    String job,
                    int mgr,
                    Date hireDate,
                    double sal,
                    double comm,
                    int deptno,
                    String loc,
                    String dname,
                    int grade) {
        this(empno, ename, job, mgr, hireDate, sal, comm, deptno);
        this.loc = loc;
        this.dname = dname;
        this.grade = grade;
    }

    public Employee() {
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (empno != employee.empno) return false;
        if (mgr != employee.mgr) return false;
        if (Double.compare(employee.sal, sal) != 0) return false;
        if (Double.compare(employee.comm, comm) != 0) return false;
        if (deptno != employee.deptno) return false;
        if (ename != null ? !ename.equals(employee.ename) : employee.ename != null) return false;
        if (job != null ? !job.equals(employee.job) : employee.job != null) return false;
        return hireDate != null ? hireDate.equals(employee.hireDate) : employee.hireDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = empno;
        result = 31 * result + (ename != null ? ename.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + mgr;
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        temp = Double.doubleToLongBits(sal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(comm);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + deptno;
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }


    public String toStringWithExtraOptions() {
        return "Employee{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                ", loc='" + loc + '\'' +
                ", dname='" + dname + '\'' +
                ", grade=" + grade +
                '}';
    }
}
