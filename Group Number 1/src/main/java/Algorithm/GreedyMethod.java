package Algorithm;
import java.util.*;

import Comparator.SortByProfit;
import Model.Job;
public class GreedyMethod {
    private Queue<Job> jobSequence = new LinkedList<Job>();
    private Job[] arr;
    private int n;

    public GreedyMethod(Job[] arr){
        this.arr = arr;
        this.n = arr.length;
    }
    //method
    public Queue<Job> SortJobScheduling() {
        int maxDeadLine = arr[0].getEnd();
        for (Job i : arr){
            if (i.getEnd() > maxDeadLine){
                maxDeadLine = i.getEnd();
            }
        }

        Arrays.sort(arr, new SortByProfit());

        Job[] slot = new Job[maxDeadLine];

        for(int i=0; i<n; i++) {
            int slotNumber = findSlot(slot, arr[i]);
            if(slotNumber != -1) {
                slot[slotNumber] = arr[i];            
            }
        }
        for(int i=0;i<slot.length;i++) {
            if(slot[i] != null){
                jobSequence.add(slot[i]);
            }
        }
        return jobSequence;
    }
    private int findSlot(Job[] slot, Job job) {
        int tempSlot = job.getEnd() - 1;
        for(int i=tempSlot; i>=0; i--) {
            if(slot[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
