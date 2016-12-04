package com.torchikov.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sergei on 04.12.16.
 */
@XmlRootElement
public class Organization {
    private long id;
    private String name;
    private String ITN;

    public long getId() {
        return id;
    }

    @XmlElement(required = true)
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getITN() {
        return ITN;
    }

    @XmlElement
    public void setITN(String ITN) {
        this.ITN = ITN;
    }
}
