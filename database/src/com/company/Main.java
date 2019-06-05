package com.company;

import com.company.db.OracleDAO;
import com.company.entity.Employee;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Список действий: ");
        System.out.println("1 - найти сотрудника по номеру; " +
                "\n2 - добавить нового сотрудника; " +
                "\n3 - удалить сотрудника по номеру; " +
                "\n4 - завершить работу.");
        boolean handleRequest = true;
        Scanner scanner = new Scanner(System.in);

        while (handleRequest) {
            System.out.println("Выберите действие");
            int userAnswer = scanner.nextInt();
            switch (userAnswer) {
                case 1:
                    System.out.println("Информация о сотруднике ");
                    System.out.println("Введите номер сотрудника (максимум 4-значное число): ");
                    int empno = scanner.nextInt();
                    Employee employee = OracleDAO.getInstance().getEmployeeByEmpno(empno);
                    if (employee != null) {
                        System.out.println(employee.toStringWithExtraOptions());
                    } else {
                        System.out.println("Сотрудника с таким номером нет.");
                    }
                    break;
                case 2:
                    Double comm = null;
                    Integer mgr = null;
                    Integer deptno = null;

                    System.out.println("Добавление сотрудника.");

                    System.out.println("Введите номер сотрудника (максимум 4-значное число): ");
                    int inEmpno = scanner.nextInt();

                    System.out.println("Введите имя сотрудника: ");
                    String ename = scanner.next();

                    System.out.println("У сотрудника есть менеджер? (true/false)");
                    boolean hasMgr = scanner.nextBoolean();
                    if (hasMgr) {
                        System.out.println("Введите номер менеджера сотрудника (максимум 4-значное число): ");
                        mgr = scanner.nextInt();
                    }

                    System.out.println("Введите должность сотрудника: ");
                    String job = scanner.next();

                    System.out.println("Введите зарплату сотрудника (дробная часть - через запятую): ");
                    double sal = scanner.nextDouble();

                    System.out.println("У сотрудника есть премия? (true/false)");
                    boolean hasComm = scanner.nextBoolean();
                    if (hasComm) {
                        System.out.println("Введите премию сотрудника (дробная часть - через запятую): ");
                        comm = scanner.nextDouble();
                    }

                    System.out.println("У сотрудника есть отдел? (true/false)");
                    boolean hasDept = scanner.nextBoolean();
                    if (hasDept) {
                        System.out.println("Введите отдел сотрудника (максимум 3-значное число): ");
                        deptno = scanner.nextInt();
                    }

                    OracleDAO.getInstance().createEmployee(
                            inEmpno,
                            ename,
                            job,
                            mgr,
                            new Date(),
                            sal,
                            comm,
                            deptno
                    );
                    break;
                case 3:
                    System.out.println("Удаление сотрудника.");
                    System.out.println("Введите номер сотрудника для удаления: ");
                    int delEmpno = scanner.nextInt();
                    OracleDAO.getInstance().deleteEmployeeByEmpno(delEmpno);
                    break;
                case 4:
                    System.out.println("Завершение работы.");
                    handleRequest = false;
                    break;
                default:
                    System.out.println("Неправильное действие. Попробуйте снова.");
                    break;
            }
        }
    }
}
