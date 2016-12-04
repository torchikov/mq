package com.torchikov.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sergei on 04.12.16.
 */
@XmlRootElement
public class OrganizationRequest {
    private long organizationId;
    private String organizationName;
    private String organizationItn;

    public long getOrganizationId() {
        return organizationId;
    }

    @XmlElement
    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    @XmlElement
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationItn() {
        return organizationItn;
    }

    @XmlElement
    public void setOrganizationItn(String organizationItn) {
        this.organizationItn = organizationItn;
    }
}
