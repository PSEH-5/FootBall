package com.football.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.football.Exception.FootBallException;
import com.football.config.FootBallDataConfiguration;
import com.football.pojo.League;

public class LeagueRepostory {

	@Autowired
	FootBallDataConfiguration config;
	
	public ResponseEntity<League[]> getLeagueId(String country_Id) throws FootBallException {

		RestTemplate resttemplate = new RestTemplate();

		String leagueUrl = UriComponentsBuilder.fromUriString(config.getGetleaguesurl())
				.replaceQueryParam("country_id", country_Id).toUriString();

		ResponseEntity<League[]> responseLeagueList = resttemplate.getForEntity(leagueUrl, League[].class);
		return responseLeagueList;
	}
}
