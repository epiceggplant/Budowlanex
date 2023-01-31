package com.budowlanex.budowlanex;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface WorkerRepository extends CrudRepository<Worker, Integer> {
 // Obliczenie łacznej kwoty wypłat
  @Query(value = "SELECT SUM(salary) FROM worker", nativeQuery = true)
  Integer getTotalSalary(); 
}
