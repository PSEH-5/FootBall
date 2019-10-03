package com.football.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.football.pojo.FootballResponse;
import com.football.service.FootBallService;

@RestController
public class FootballController {
	
	@Autowired
	private FootBallService standingservice;
	
	
	@RequestMapping(path="/", produces = "application/json")
    public String getEmployees()
    {
        return "Dummy";
    }
	
	@RequestMapping(value = "/football/{countryName}/{leagueName}/{teamName}/{leaguePosition}", method = RequestMethod.GET)
	public ResponseEntity<List<FootballResponse>> getFootballTeamStanding(
			@PathVariable(value = "countryName", required = true) String countryName,
			@PathVariable(value = "leagueName", required = true) String leagueName,
			@PathVariable(value = "teamName", required = true) String teamName,
			@PathVariable(value = "leaguePosition", required = true) String leaguePosition)
			throws Exception {
		
		List<FootballResponse> responselist = standingservice.getFootBallStandingsData(countryName, leagueName,
				teamName, leaguePosition);

		return new ResponseEntity<List<FootballResponse>>(responselist, HttpStatus.OK);
	}

}
