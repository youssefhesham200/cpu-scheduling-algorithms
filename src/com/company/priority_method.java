package com.company;

import java.util.ArrayList;
import java.util.Comparator;

class priority_method {
    public ArrayList<process> sorted1(ArrayList<process> processes) {
        Comparator<process> byarrive = Comparator.comparing(process::get_arrival_Time);
        Comparator<process> bypri = Comparator.comparing(process::get_priority_number);
        processes.sort(byarrive.thenComparing(bypri));
        return processes;
    }

    ArrayList<process> p;
    ArrayList<process> g = new ArrayList<process>();

    int context, minp, n;

    priority_method(ArrayList<process> p, int context) {
        this.p = p;
        this.context = context;
    }

    public ArrayList<process> solve(){
        p = this.sorted1(p);
        p.get(0).setTermination(p.get(0).get_arrival_Time() + p.get(0).get_burst_Time() + context);
        g.add(p.get(0));
        p.remove(0);
        n = p.size();

        for (int i = 0; i < n; i++) {
            minp = Integer.MAX_VALUE;
            for (process process : p) {
                if (g.get(g.size() - 1).getTermination() >= process.get_arrival_Time()) {
                    minp = Math.min(minp, process.get_priority_number());
                }
            }

            for (int j = 0; j < p.size(); j++) {
                if (p.get(j).get_priority_number() == minp) {
                    p.get(j).setTermination(g.get(g.size() - 1).getTermination() + p.get(j).get_burst_Time() + context);
                    g.add(p.get(j));
                    p.remove(j);
                    break;
                }
            }
        }

        return g;
    }
}

