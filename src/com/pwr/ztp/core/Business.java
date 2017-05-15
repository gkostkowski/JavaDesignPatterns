package com.pwr.ztp.core;

import java.time.LocalTime;

/**
 * Created by Grzesiek on 2017-05-14.
 */
public class Business {

    private LocalTime timeOfRequest;
    private Office.Activity areaOfCase;
    private boolean isDedicatedBusiness;
    private Worker particularWorker;
    private boolean isSolved=false;

    public Business(Office.Activity areaOfCase, LocalTime timeOfRequest, boolean isDedicatedBusiness, Worker particularWorker) {
        this.areaOfCase = areaOfCase;
        this.timeOfRequest = timeOfRequest;
        this.isDedicatedBusiness = isDedicatedBusiness;
        this.particularWorker = particularWorker;
    }

    public Business(Office.Activity areaOfCase, LocalTime timeOfRequest) {
        this(areaOfCase, timeOfRequest, false, null);
    }

    public LocalTime getTimeOfRequest() {
        return timeOfRequest;
    }

    public Office.Activity getAreaOfCase() {
        return areaOfCase;
    }

    public boolean isDedicatedBusiness() {
        return isDedicatedBusiness;
    }

    public Worker getParticularWorker() {
        return particularWorker;
    }

    public void solveBusiness() {
        isSolved = true;
    }

    public boolean isSolved() {
        return isSolved;
    }

    @Override
    public String toString() {
        return "Business: "+areaOfCase.toString()+",   request time: "+timeOfRequest.toString();
    }

    public boolean isRelevant(Worker worker) {
        return !isDedicatedBusiness || isDedicatedBusiness && particularWorker.equals(worker);
    }
}
