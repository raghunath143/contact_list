package com.databaseDesign.projectOne.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.databaseDesign.projectOne.Views.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="Date")
public class DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @JsonView(Views.Public.class)
    private Integer dateId;

    @JsonView(Views.Public.class)
    private String dateType;

    @JsonFormat(pattern="MM/dd/yyyy")
    @JsonView(Views.Public.class)
    private Date date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="contact_id")
    private Contact contact;

    public DateEntity() {

    }

    public DateEntity(String dateType, java.util.Date date) {
        this.setDateType(dateType);
        this.setDate(date);
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}