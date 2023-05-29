package com.dp.attendance.repository;

import com.dp.attendance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query(value = "update employee set sign_out = ?1, total_hours= ?2 where id = ?3", nativeQuery = true)
    public int updateEmployee(LocalDateTime localDateTime, Long total_hours, int id);

}
