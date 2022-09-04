package Comparator;
import java.util.Comparator;

import Model.Job;

public class SortByProfit implements Comparator<Job>{
    public int compare(Job j1, Job j2){
        if (j2.getProfit() > j1.getProfit()){
            return 1;
        }
        else if (j2.getProfit() == j1.getProfit()){
            return 0;
        }
        else{
            return -1;
        }
    }
}