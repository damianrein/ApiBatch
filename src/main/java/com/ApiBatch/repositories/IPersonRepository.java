package com.ApiBatch.repositories;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApiBatch.entities.Person;

public interface IPersonRepository extends JpaRepository<Person, Long>{

	//void saveAll(List<Person> personList);
}
