package com.dp.attendance.service;

import com.dp.attendance.model.Employee;
import com.dp.attendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    public Employee login(Employee emp){
        Optional<Employee> employee = repository.findById(emp.getId());
        if(employee.isPresent()){
            return employee.get();
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        emp.setSign_in(localDateTime);
        return repository.save(emp);
    }

    public String logout(int id){
        Optional<Employee> employee = repository.findById(id);
        if(!employee.isPresent()){
            return "There is no login for this employee";
        }
        else {
            LocalDateTime sign_in = employee.get().getSign_in();
            LocalDateTime sign_out = LocalDateTime.now();
            Long total_hours = Duration.between(sign_in, sign_out ).toHours();
            Long total_hours_mins = Duration.between(sign_in, sign_out ).toMinutes();
            System.out.println(total_hours_mins);
            System.out.println(Duration.between(sign_in,sign_out).toMinutesPart());
            System.out.println(Duration.between(sign_in,sign_out).toHoursPart());
            int value = repository.updateEmployee(sign_out, total_hours, id);
            System.out.println(value);
            return "employee logged out at " + sign_out;
        }
    }
}
