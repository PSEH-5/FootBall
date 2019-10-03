package com.football.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.football.Exception.FootBallException;
import com.football.config.FootBallDataConfiguration;
import com.football.pojo.Country;

@Component
public class CountryRepository {

	@Autowired
	FootBallDataConfiguration config;
	/**
	 *
	 */
	
	public ResponseEntity<Country[]>  getCountryId(String countryName) throws FootBallException {

		RestTemplate resttemplate = new RestTemplate();
		ResponseEntity<Country[]> responsecountryList = resttemplate.getForEntity(config.getGetCountriesUrl(),
				Country[].class);
	return responsecountryList;

	}
}

