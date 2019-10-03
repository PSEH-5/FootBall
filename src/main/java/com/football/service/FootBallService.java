package com.football.service;

import java.util.List;

import com.football.Exception.FootBallException;
import com.football.pojo.FootballResponse;

public interface FootBallService {
	String getCountryId(String countryName) throws FootBallException;

	String getLeagueId(String leagueName) throws FootBallException;

	List<FootballResponse> getFootBallStandingsData(String countryName, String leagueName, String teamName,String leaguePosition)
			throws FootBallException;
}
