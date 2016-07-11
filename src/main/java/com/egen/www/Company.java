package com.egen.www;

/**
 * Created by Ram on 07/09/16.
 */
public class Company {

    private String name;
    private String website;

    public Company(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public Company() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
