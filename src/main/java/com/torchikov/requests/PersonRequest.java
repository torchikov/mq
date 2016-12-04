package com.torchikov.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sergei on 04.12.16.
 */
@XmlRootElement(name = "personRequest")
public class PersonRequest {
    private long personId;
    private String personName;
    private int personAge;


    public long getPersonId() {
        return personId;
    }

    @XmlElement
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    @XmlElement
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    @XmlElement
    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }
}
