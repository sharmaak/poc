package com.amitcodes.rest.minimal;

import java.util.TreeMap;

public class EmployeeDAO {

    /*******************************************************
     * DISCLAIMER:
     * This is just a make-believe data-store.
     * Do not expect concurrency and stuff like that.
     * The aim of this project is to exhibit how to develop
     * a RESTful webservice.
     *******************************************************/
    private TreeMap<Long, Employee> makeBelieveDataStore;
    private static EmployeeDAO instance;

    public static EmployeeDAO getInstance() {

        if (instance == null) {
            synchronized (EmployeeDAO.class) {
                if (instance == null) {
                    instance = new EmployeeDAO();
                }
            }
        }

        return instance;
    }

    private EmployeeDAO() {
        makeBelieveDataStore = new TreeMap<>();

        Employee e = new Employee(0L, "Amit", "Sharma", 32);
        makeBelieveDataStore.put(e.getId(), e);
    }

    public Employee addEmployee(Employee employee) {

        Long employeeId = makeBelieveDataStore.isEmpty() ? 1L : makeBelieveDataStore.lastKey() + 1;
        employee.setId(employeeId);
        makeBelieveDataStore.put(employeeId, employee);
        return employee;
    }

    public void updateEmployee(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("employee.id cannot be null");
        }

        makeBelieveDataStore.put(employee.getId(), employee);
    }

    public Employee fetchEmployee(Long id) {
        return makeBelieveDataStore.get(id);
    }

    public void deleteEmployee(Long id) {
        makeBelieveDataStore.remove(id);
    }

}
