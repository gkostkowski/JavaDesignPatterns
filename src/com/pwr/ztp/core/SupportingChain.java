package com.pwr.ztp.core;

/**
 * Created by Grzesiek on 2017-05-14.
 */
public class SupportingChain {
    Worker solver;
    SupportingChain nextSolver;

    public SupportingChain(Worker worker, SupportingChain sc) {
        solver = worker;
        nextSolver = sc;
    }

    void setNextSolver(SupportingChain sc) {
        nextSolver = sc;
    }

    void support(Business business) {
        if (!business.isSolved()) {
            if (solver.isEligible(business))
                solver.solveBusiness(business);
            else if (nextSolver != null)
                nextSolver.support(business);
            else
                System.out.println("No one was able to solve: \n\t" + business);
        }

    }
}
