package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class srtf {
    ArrayList<process> p;
    ArrayList<process> g = new ArrayList<process>();

    srtf(ArrayList<process> p) {
        this.p = p;
    }

    float minp;

    public ArrayList<process> solve() {
        Set<String> ready = new HashSet<>();
        print.sorted1(p);
        p.get(0).setTermination(p.get(0).get_arrival_Time() + 1);
        p.get(0).setBurst_Time(p.get(0).get_burst_Time() - 1);
        g.add(p.get(0));
        int n = p.size();

        while (ready.size() != n) {
            minp = Integer.MAX_VALUE;

            for (process process : p) {
                if (g.get(g.size() - 1).getTermination() >= process.get_arrival_Time()) {
                    ready.add(process.getname());
                    minp = Math.min(minp, process.get_burst_Time());
                }
            }

            for (int j = 0; j < p.size(); j++) {
                if (p.get(j).get_burst_Time() == minp) {
                    p.get(j).setTermination(g.get(g.size() - 1).getTermination() + 1);
                    p.get(j).setBurst_Time(p.get(j).get_burst_Time() - 1);
                    g.add(p.get(j));
                    if (p.get(j).get_burst_Time() == 0) {
                        p.remove(j);
                    }
                    break;
                }
            }
        }

        n = p.size();
        for (int i = 0; i < n; i++) {
            minp = Integer.MAX_VALUE;
            boolean f = true;
            for (process process : p) {
                if (g.get(g.size() - 1).getTermination() >= process.get_arrival_Time()) {
                    f = false;
                    minp = Math.min(minp, process.get_burst_Time());
                } else {
                    minp = process.get_burst_Time();
                }
            }

            for (int j = 0; j < p.size(); j++) {
                if (p.get(j).get_burst_Time() == minp) {
                    if (!f) {
                        p.get(j).setTermination(p.get(j).get_burst_Time() + g.get(g.size() - 1).getTermination());
                    } else {
                        p.get(j).setTermination((p.get(j).get_arrival_Time() - g.get(g.size() - 1).getTermination()) + p.get(j).get_burst_Time() + g.get(g.size() - 1).getTermination());
                    }
                    g.add(p.get(j));
                    p.remove(j);
                    break;
                }
            }
        }
        return g;
    }
}