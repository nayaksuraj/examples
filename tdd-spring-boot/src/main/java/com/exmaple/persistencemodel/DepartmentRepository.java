package com.exmaple.persistencemodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
	
	DepartmentEntity findFirst1ByName(String name); 

}
