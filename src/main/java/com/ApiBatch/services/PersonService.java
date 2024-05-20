package com.ApiBatch.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ApiBatch.entities.Person;
import com.ApiBatch.repositories.IPersonRepository;

@Service
public class PersonService {

	private IPersonRepository repo;
	
	public void saveAllPersons(List<Person> listPerson) {
		repo.saveAll(listPerson);
	}
}
