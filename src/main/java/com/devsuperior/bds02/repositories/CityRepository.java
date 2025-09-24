package com.devsuperior.bds02.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.bds02.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{

	List<City> findAllByOrderByNameAsc();
}
