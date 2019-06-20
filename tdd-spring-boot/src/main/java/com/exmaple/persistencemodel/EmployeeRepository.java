package com.exmaple.persistencemodel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
	List<EmployeeEntity> findByFirstName(String firstname);
	
	List<EmployeeEntity> findByDepartmentId(Long departmentId);
}
