package com.pwr.ztp;

import com.pwr.ztp.core.Business;
import com.pwr.ztp.core.Office;
import com.pwr.ztp.core.SupportingChain;
import com.pwr.ztp.core.Worker;

import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        Office taxOffice = new Office();
        Worker[] workers = new Worker[]{
                new Worker.WorkerBuilder("Piotr", "Brzozowski")
                        .address("Jagodowa 21")
                        .position("Stażysta")
                        .competency(Office.Activity.FORM_FILLING)
                        .build(),
                new Worker.WorkerBuilder("Jan", "Kowalski")
                        .position("Urzędnik")
                        .roomNbr(32)
                        .workingHours(LocalTime.of(10, 30), LocalTime.of(18, 30))
                        .competency(
                                Office.Activity.CONSULTING_SERVICE,
                                Office.Activity.FORM_FILLING,
                                Office.Activity.SIGNING_DOCUMENT)
                        .build(),
                new Worker.WorkerBuilder("Adam", "Brzoza")
                        .workingHours(LocalTime.of(7, 15), LocalTime.of(15, 15))
                        .competency(
                                Office.Activity.FORM_FILLING,
                                Office.Activity.CONSULTING_SERVICE,
                                Office.Activity.PAYMENT)
                        .build(),
                new Worker.WorkerBuilder("Anna", "Nowak")
                        .position("Samodzielny referent")
                        .competency(
                                Office.Activity.PAYMENT,
                                Office.Activity.SIGNING_DOCUMENT,
                                Office.Activity.FORM_FILLING,
                                Office.Activity.CONSULTING_SERVICE)
                        .workingHours(LocalTime.of(9, 15), LocalTime.of(17, 0))
                        .build(),
                new Worker.WorkerBuilder("Józef", "Górny")
                        .position("Kierownik")
                        .roomNbr(1)
                        .competency(taxOffice.getOffer())
                        .build()
        };
        taxOffice.addWorkers(workers);

        taxOffice.setPathOfSupporting(new SupportingChain(workers[0],
                new SupportingChain(workers[1],
                        new SupportingChain(workers[2],
                                new SupportingChain(workers[3],
                                        new SupportingChain(workers[4], null
                                        ))))));

        Business[] cases = new Business[]{
                new Business(Office.Activity.FORM_FILLING, LocalTime.of(12, 3)),
                new Business(Office.Activity.FORM_FILLING, LocalTime.of(16, 12)),
                new Business(Office.Activity.FORM_FILLING, LocalTime.of(19, 12)),
                new Business(Office.Activity.CONSULTING_SERVICE, LocalTime.of(12, 55)),
                new Business(Office.Activity.CONSULTING_SERVICE, LocalTime.of(9, 55)),
                new Business(Office.Activity.SIGNING_DOCUMENT, LocalTime.of(11, 55)),
                new Business(Office.Activity.FORM_FILLING, LocalTime.of(11, 12)),
                new Business(Office.Activity.FORM_FILLING, LocalTime.of(11, 13), true, workers[3]),
                new Business(Office.Activity.RECEIVING_VALIDATION, LocalTime.of(11, 15)),
                new Business(Office.Activity.RECEIVING_VALIDATION, LocalTime.of(7, 58))
        };

        for (Business business : cases)
            taxOffice.supportCase(business);

    }
}
