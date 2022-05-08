package com.databaseDesign.projectOne.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.databaseDesign.projectOne.Views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="Phone")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private Integer phoneId;

    @JsonView(Views.Public.class)
    private String phoneType;

    @Column(length = 3)
    @JsonView(Views.Public.class)
    private String areaCode;

    @JsonView(Views.Public.class)
    private String number;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="contact_id")
    private Contact contact;

    public PhoneEntity() {

    }

    public PhoneEntity(String phoneType, String areaCode, String number) {
        this.setPhoneType(phoneType);
        this.setAreaCode(areaCode);
        this.setNumber(number);
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}