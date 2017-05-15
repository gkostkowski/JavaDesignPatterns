package com.pwr.ztp.core;


import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Grzesiek on 2017-05-14.
 */
public class Office {
    Collection<Worker> workers;
    Activity [] availableActivities = Activity.values();

    SupportingChain pathOfSupporting;

    public Activity[] getOffer() {
        return Activity.values();
    }

    public enum Activity {
        CONSULTING_SERVICE,
        FORM_FILLING,
        SIGNING_DOCUMENT,
        PAYMENT,
        HANDING_IN_DOCUMENT,
        RECEIVING_VALIDATION;
    }

    public Office(Collection<Worker> workers) {
        this.workers = workers;
    }
    public Office() {
        this(new HashSet<>());
    }

    public void addWorkers(Worker ... newWorkers) {
        for (Worker w:newWorkers)
            workers.add(w);
    }

    public void setPathOfSupporting(SupportingChain pathOfSupporting) {
        this.pathOfSupporting = pathOfSupporting;
    }

    public void supportCase(Business business) {
        pathOfSupporting.support(business);
    }

}
