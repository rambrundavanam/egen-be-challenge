package com.egen.www;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by Ram on 07/09/16.
 */
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Date dateCreated = new Date();
    private Company company;
    private String profilePic;
    private boolean done;
    
    public User(){
    	
    }
    public User(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.firstName = dbObject.getString("firstName");
        this.lastName = dbObject.getString("lastName");
        this.email = dbObject.getString("email");
        this.address = ((Address) dbObject.get("address"));
        this.dateCreated = dbObject.getDate("dateCreated");
        this.company = ((Company) dbObject.get("company"));
        this.profilePic = dbObject.getString("profilePic");
        this.done = dbObject.getBoolean("done");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
