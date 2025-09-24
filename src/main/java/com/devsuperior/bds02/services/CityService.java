package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

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


}
