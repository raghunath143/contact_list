package com.databaseDesign.projectOne.Entities;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.databaseDesign.projectOne.Views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="Address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private Integer addressId;

    @JsonView(Views.Public.class)
    private String addressType;

    @JsonView(Views.Public.class)
    private String address = "";

    @JsonView(Views.Public.class)
    private String city = "";

    @JsonView(Views.Public.class)
    private String state = "";

    @JsonView(Views.Public.class)
    private String zip = "";

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name= "contact_id")
    private Contact contact;

    public AddressEntity() {

    }

    public AddressEntity(String addressType, String address, String city, String state, String zip) {
        this.setAddressType(addressType);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setZip(zip);
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        if (addressType.equals("")) this.addressType = "Home";
        else this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.equals("")) this.address = "NO_ADDRESS";
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}