package com.pwr.ztp.core;

import javafx.util.Pair;

import java.time.LocalTime;
import java.util.*;

/**
 * Created by Grzesiek on 2017-05-14.
 */
public class Worker {
    private int id;
    private String name, surname, address,
            position;
    private String telephone,
            roomNbr = "1";
    private Pair<LocalTime, LocalTime> businessHours;

    private Set<Office.Activity> areaOfCompetency;

    private Worker(WorkerBuilder wb) {
        this.id = wb.id;
        this.name = wb.name;
        this.surname = wb.surname;
        this.address = wb.address;
        this.position = wb.position;
        this.telephone = wb.telephone;
        this.roomNbr = wb.roomNbr;
        this.businessHours = wb.businessHours;
        this.areaOfCompetency = wb.areaOfCompetency;
    }


    public boolean isEligible(Business business) {
        return isPresentAtOffice(business.getTimeOfRequest())
                && isCompetent(business)
                && business.isRelevant(this);
    }

    public boolean isPresentAtOffice(LocalTime time) {
        return time.isAfter(businessHours.getKey()) && time.isBefore(businessHours.getValue());
    }

    public boolean isCompetent(Business business) {
        return areaOfCompetency.contains(business.getAreaOfCase());
    }

    public void solveBusiness(Business business) {
        business.solveBusiness();
        System.out.println(name+" "+surname+" solved the business: "+business);
    }

    public static class WorkerBuilder {
        private static int lastId=0;
        private int id;
        String roomNbr;
        private String name, surname, address, position = "regular",
                telephone;
        private Set<Office.Activity> areaOfCompetency;
        private Pair<LocalTime, LocalTime> businessHours;


        public WorkerBuilder(int id, String name, String surname) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.position("Urzędnik");
            businessHours = new Pair<>(LocalTime.of(8, 0), LocalTime.of(16, 0));
        }

        public WorkerBuilder(String name, String surname) {
            this(++lastId, name, surname);
        }

            public Worker build() {
            return new Worker(this);
        }

        public WorkerBuilder roomNbr(String nbr) {
            roomNbr = nbr;
            return this;
        }

        public WorkerBuilder roomNbr(int nbr) {
            roomNbr = ""+nbr;
            return this;
        }

        public WorkerBuilder address(String addr) {
            address = addr;
            return this;
        }

        public WorkerBuilder position(String pos) {
            position = pos;
            return this;
        }

        public WorkerBuilder workingHours(LocalTime start, LocalTime end) {
            businessHours = new Pair<>(start, end);
            return this;
        }

        public WorkerBuilder competency(Office.Activity... duties) {
            if (this.areaOfCompetency == null)
                areaOfCompetency = new HashSet<>();
            areaOfCompetency.addAll(Arrays.asList(duties));
            return this;
        }


    }


}
