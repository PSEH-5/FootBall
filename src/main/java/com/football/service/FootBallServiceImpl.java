package com.football.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.football.Exception.FootBallException;
import com.football.config.FootBallDataConfiguration;
import com.football.pojo.Country;
import com.football.pojo.FootballResponse;
import com.football.pojo.League;
import com.football.repository.CountryRepository;
import com.football.repository.LeagueRepostory;

public class FootBallServiceImpl implements FootBallService{

	@Autowired
	FootBallDataConfiguration config;

	@Autowired
	CountryRepository countryRepo;
	
	@Autowired
	LeagueRepostory lrepo;
	
	@Override
	public String getCountryId(String countryName) throws FootBallException {
		String country_Id = "";
		ResponseEntity<Country[]> responsecountryList = countryRepo.getCountryId(countryName);
		
		
		for (Country country : responsecountryList.getBody()) {
			System.out.println("country == " + country.toString());
			if (countryName.equalsIgnoreCase(country.getCountry_name())) {
				country_Id = country.getCountry_id();
			}

		}
		
		if (StringUtils.isEmpty(country_Id)) {
			throw new FootBallException("Country does not exist in league.");
		}
		return country_Id;
	}

	@Override
	public String getLeagueId(String leagueName) throws FootBallException {
		String league_Id = "";
		ResponseEntity<League[]> responseLeagueList = lrepo.getLeagueId(leagueName);
		
		for (League league : responseLeagueList.getBody()) {
			System.out.println("league list == " + league.toString());
			league_Id = league.getLeague_id();
		}
		
		if (StringUtils.isEmpty(league_Id)) {
			throw new FootBallException("League does not exist.");
		}
		
		return league_Id;
	}

	@Override
	public List<FootballResponse> getFootBallStandingsData(String countryName, String leagueName, String teamName,
			String leaguePosition) throws FootBallException {
		
			RestTemplate resttemplate = new RestTemplate();
			
			String country_Id=getCountryId(countryName);
			String league_Id=getLeagueId(country_Id);

			
			// get standings
			String standingUrl = UriComponentsBuilder.fromUriString(config.getGetstandingsurl())
					.replaceQueryParam("league_id", league_Id).toUriString();

			ResponseEntity<FootballResponse[]> standingresponse = resttemplate.getForEntity(standingUrl,
					FootballResponse[].class);

			List<FootballResponse> list = new ArrayList<>();
			FootballResponse leagueResponse = null;
			
			
			for (FootballResponse standings : standingresponse.getBody()) {
				System.out.println("standings ==  " + standings.toString());
				if (standings.getLeague_name().equalsIgnoreCase(leagueName)
						&& standings.getCountry_name().equalsIgnoreCase(countryName)
						&& standings.getTeam_name().equalsIgnoreCase(teamName) && standings.getOverall_league_position().equalsIgnoreCase(leaguePosition)) {
					leagueResponse = new FootballResponse();
					leagueResponse.setCountry_id(country_Id);
					leagueResponse.setCountry_name(standings.getCountry_name());
					leagueResponse.setLeague_name(standings.getLeague_name());
					leagueResponse.setLegaue_id(league_Id);
					leagueResponse.setTeam_id(standings.getTeam_id());
					leagueResponse.setTeam_name(standings.getTeam_name());
					leagueResponse.setOverall_league_position(standings.getOverall_league_position());
					list.add(leagueResponse);
				}
			return list;
		}
			return list;
	}

}
