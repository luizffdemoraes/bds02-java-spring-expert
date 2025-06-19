package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exception.DataBaseException;
import com.devsuperior.bds02.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService (CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    @Transactional
    public CityDTO insert(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        cityRepository.save(city);
        return new CityDTO(city);
    }

    public void delete(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }

        try {
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }
}
