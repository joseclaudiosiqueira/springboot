package com.joseclaudiosiqueira.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseclaudiosiqueira.springboot.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}