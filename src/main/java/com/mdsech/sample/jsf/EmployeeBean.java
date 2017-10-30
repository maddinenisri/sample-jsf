package com.mdsech.sample.jsf;

import org.fluttercode.datafactory.impl.DataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class EmployeeBean {
    private final Logger log = LoggerFactory.getLogger(EmployeeBean.class);
    private List<Employee> employeeList = new ArrayList<>();

    @PostConstruct
    private void postConstruct () {
        log.info("Initializing EmployeeBean");
        DataFactory dataFactory = new DataFactory();
        for (int i = 1; i < 20; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setName(dataFactory.getName());
            employee.setPhoneNumber(String.format("%s-%s-%s", dataFactory.getNumberText(3),
                    dataFactory.getNumberText(3),
                    dataFactory.getNumberText(4)));
            employee.setAddress(dataFactory.getAddress() + "," + dataFactory.getCity());
            employeeList.add(employee);
            LogEvent.getInstance().<Employee>sendEvent(employee);
            log.info("Completed - Initializing EmployeeBean");
        }
    }

    public List<Employee> getEmployeeList () {
        log.info("Completed - getEmployeeList");
        return employeeList;
    }
}

