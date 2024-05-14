package com.ApiBatch.services;

import java.util.List;

import com.ApiBatch.entities.Person;
import com.ApiBatch.repositories.IPersonRepository;

public class PersonService {

	IPersonRepository repo;
	
	public void saveAllPersons(List<Person> listPerson) {
		repo.saveAll(listPerson);
	}
}
