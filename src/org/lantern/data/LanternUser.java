package org.lantern.data;

import java.util.Date;
import java.util.Set;

import javax.persistence.Id;

public class LanternUser {
    @Id
    private String id;
    
    private boolean validated;
    
    private long directBytes;
    
    private long bytesProxied;
    
    private long directRequests;
    
    private long requestsProxied;
    
    private int numContacts;
    
    private Date created = new Date();
    
    private Set<String> countryCodes;

    public LanternUser() {
        super();
    }

    public LanternUser(final String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setValidated(final boolean validated) {
        this.validated = validated;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return created;
    }

    public long getBytesProxied() {
        return bytesProxied;
    }

    public void setBytesProxied(final long bytesProxied) {
        this.bytesProxied = bytesProxied;
    }

    public Set<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(final Set<String> countryCodes) {
        this.countryCodes = countryCodes;
    }
    
    public long getDirectBytes() {
        return directBytes;
    }

    public void setDirectBytes(long directBytes) {
        this.directBytes = directBytes;
    }

    public long getDirectRequests() {
        return directRequests;
    }

    public void setDirectRequests(long directRequests) {
        this.directRequests = directRequests;
    }

    public long getRequestsProxied() {
        return requestsProxied;
    }

    public void setRequestsProxied(long requestsProxied) {
        this.requestsProxied = requestsProxied;
    }

    public void setNumContacts(int numContacts) {
        this.numContacts = numContacts;
    }

    public int getNumContacts() {
        return numContacts;
    }
}