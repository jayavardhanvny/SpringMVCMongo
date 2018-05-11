package org.letuslearn.service;

import java.util.List;

import org.letuslearn.model.mongo.Employee;

/**
 * @author Dinesh Rajput
 *
 */
public interface EmployeeService {

    public Boolean addEmployee(Employee employee);

    public List<Employee> listEmployeess();

    public Employee getEmployee(String empid);

    public Boolean deleteEmployee(String empid);
}