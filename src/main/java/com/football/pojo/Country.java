package com.football.pojo;

public class Country {
	private String country_id;
	@Override
	public String toString() {
		return "Country [country_id=" + country_id + ", country_name=" + country_name + "]";
	}

	private String country_name;

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
}
