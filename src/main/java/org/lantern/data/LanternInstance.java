package org.lantern.data;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Id;

import org.lantern.state.Mode;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class LanternInstance {
    private static long METRIC_BUCKET_DURATION_IN_MILLIS = 60 * 60 * 1000;
    private static long NUMBER_OF_BUCKETS_TO_KEEP = 24;
    
    @Id
    private String id;

    @Parent
    private Key<LanternUser> parent;

    private boolean available;

    private Date lastUpdated = new Date();

    private String user;

    private String countries = "";

    private String currentCountry;
    
    //@formatter:off
    @Embedded
    private Metric processCpuUsage = new Metric(METRIC_BUCKET_DURATION_IN_MILLIS,
                                                NUMBER_OF_BUCKETS_TO_KEEP);
    
    @Embedded
    private Metric systemCpuUsage = new Metric(METRIC_BUCKET_DURATION_IN_MILLIS,
                                               NUMBER_OF_BUCKETS_TO_KEEP);
    
    @Embedded
    private Metric systemLoadAverage = new Metric(METRIC_BUCKET_DURATION_IN_MILLIS,
                                                  NUMBER_OF_BUCKETS_TO_KEEP);
    
    @Embedded
    private Metric memoryUsageInBytes = new Metric(METRIC_BUCKET_DURATION_IN_MILLIS,
                                                   NUMBER_OF_BUCKETS_TO_KEEP);
    
    @Embedded
    private Metric numberOfOpenFileDescriptors = new Metric(METRIC_BUCKET_DURATION_IN_MILLIS,
                                                            NUMBER_OF_BUCKETS_TO_KEEP);
    //@formatter:on

    /* The most recent resource id we have seen for this instance. */
    private String resource;

    private Mode mode;

    public LanternInstance() {
        super();
    }

    public LanternInstance(final String id, final Key<LanternUser> parent) {
        super();
        this.id = id;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(final boolean available) {
        this.available = available;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setLastUpdated(final Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void addSeenFromCountry(String countryCode) {
        if (countries.contains(countryCode + ".")) {
            return;
        }
        countries += countryCode + ".";
    }

    public boolean getSeenFromCountry(String countryCode) {
        return countries.contains(countryCode + ".");
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
    
    public Metric getProcessCpuUsage() {
        return processCpuUsage;
    }

    public void setProcessCpuUsage(Metric processCpuUsage) {
        this.processCpuUsage = processCpuUsage;
    }

    public Metric getSystemCpuUsage() {
        return systemCpuUsage;
    }

    public void setSystemCpuUsage(Metric systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage;
    }

    public Metric getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(Metric systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public Metric getMemoryUsageInBytes() {
        return memoryUsageInBytes;
    }

    public void setMemoryUsageInBytes(Metric memoryUsageInBytes) {
        this.memoryUsageInBytes = memoryUsageInBytes;
    }

    public Metric getNumberOfOpenFileDescriptors() {
        return numberOfOpenFileDescriptors;
    }

    public void setNumberOfOpenFileDescriptors(Metric numberOfOpenFileDescriptors) {
        this.numberOfOpenFileDescriptors = numberOfOpenFileDescriptors;
    }

    public boolean isCurrent() {
        long now = new Date().getTime();
        long age = now - lastUpdated.getTime();
        return age < 1000L * 60 * 15;
    }

}
