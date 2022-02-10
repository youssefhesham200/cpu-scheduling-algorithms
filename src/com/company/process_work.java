package com.company;

import java.util.ArrayList;
import java.util.Comparator;

class process_work {
    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
    public String getColor(){
        return color;
    }
    public String name,color;
    public int start, end;
    public process_work(String name,String color, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.color=color;
    }

    @Override
    public String toString() {
        return name + " "+ color +" "+start+" "+end;
    }
}


class process {
    private String name;
    private final String color;
    private int arrival_Time;
    private int burst_Time, wait_time, trun_around, termination, Quantum, AGAT_Factor;
    private final int fake_burst;
    private int priority_number;
    public boolean non_pree = true;

    public process(String name, String color, int arrival_Time, int burst_Time, int priority_number, int Quantum) {
        this.name = name;
        this.color = color;
        this.arrival_Time = arrival_Time;
        this.burst_Time = burst_Time;
        fake_burst = burst_Time;
        this.Quantum = Quantum;
        this.priority_number = priority_number;
        wait_time = 0;
        trun_around = 0;
        termination = 0;
        this.AGAT_Factor = 0;
    }


    public String getname() {
        return name;
    }

    public String getcolor() {
        return color;
    }

    public int get_arrival_Time() {
        return arrival_Time;
    }

    public int get_burst_Time() {
        return burst_Time;
    }

    public int get_wait_time() {
        return wait_time;
    }

    public int get_trun_around() {
        return trun_around;
    }

    public int get_priority_number() {
        return priority_number;
    }

    public void set_wait_time(int wait_time) {
        this.wait_time = wait_time;
    }

    public void set_trun_around(int trun_around) {
        this.trun_around = trun_around;
    }

    public void setTermination(int termination) {
        this.termination = termination;
    }

    public int getTermination() {
        return termination;
    }

    public void setBurst_Time(int burst_Time) {
        this.burst_Time = burst_Time;
    }

    public int getFake_burst() {
        return fake_burst;
    }

    public int getQuantum() {
        return Quantum;
    }

    public int getAGAT_Factor() {
        return AGAT_Factor;
    }

    public void setAGAT_Factor(int AGAT_Factor) {
        this.AGAT_Factor = AGAT_Factor;
    }

    public void setQuantum(int Quantum) {
        this.Quantum = Quantum;
    }

    public void equall(process p) {
        this.AGAT_Factor = p.AGAT_Factor;
        this.priority_number = p.priority_number;
        this.Quantum = p.Quantum;
        this.arrival_Time = p.arrival_Time;
        this.burst_Time = p.burst_Time;
        this.name = p.name;
        this.non_pree = p.non_pree;
    }

    @Override
    public String toString() {
        return name + " " + color + " " + priority_number+" "+wait_time+" "+trun_around;
    }
}

class print {
    static float avg_wait = 0;
    static float avg_trun = 0;
    static void print(ArrayList<process> g) {
        for (process process : g) {
            process.set_trun_around(process.getTermination() - process.get_arrival_Time());
            process.set_wait_time(process.get_trun_around() - process.getFake_burst());
            avg_trun += process.get_trun_around();
            avg_wait += process.get_wait_time();
            //System.out.println(process.getname() + " " + process.get_wait_time() + " " + process.get_trun_around());
        }
        avg_trun = avg_trun / g.size();
        avg_wait = avg_wait / g.size();
        //System.out.println("avg_waiting_Time " + avg_wait + " avg_trun_around " + avg_trun);
    }

    static public void sorted1(ArrayList<process> processes) {
        Comparator<process> byarrive = Comparator.comparing(process::get_arrival_Time);
        Comparator<process> bypri = Comparator.comparing(process::get_burst_Time);
        processes.sort(byarrive.thenComparing(bypri));
    }

    public static float getAvg_wait() {
        return avg_wait;
    }

    public static float getAvg_trun() {
        return avg_trun;
    }
}