package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;

	 @Transactional(readOnly = true)
	    public List<CityDTO> findAll() {
	        List<City> list = repository.findAllByOrderByNameAsc();
	        return list.stream().map(city -> new CityDTO(city)).toList();
	    }

	 @Transactional
	 public CityDTO insert(CityDTO dto) {
	     City entity = new City();
	     entity.setName(dto.getName());   // só seta o nome!
	     
	     entity = repository.save(entity);  // aqui o JPA preenche o id
	     
	     return new CityDTO(entity);        // aqui o id não pode ser null
	 }

	 
	@Transactional(propagation = Propagation.SUPPORTS) 
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
		try {
			repository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}


}
