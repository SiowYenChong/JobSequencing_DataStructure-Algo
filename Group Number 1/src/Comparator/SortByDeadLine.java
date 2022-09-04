package Comparator;
import java.util.Comparator;

import Model.Job;

public class SortByDeadLine implements Comparator<Job>{
    public int compare(Job j1, Job j2){
        return j1.getEnd() - j2.getEnd();
    }   
}