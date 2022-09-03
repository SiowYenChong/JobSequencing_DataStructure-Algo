
package GreedyMethod;

import _JobSchedulling.JobScheduleClass.GreedyJob;
import _JobSchedulling.JobScheduleClass.JobSchedule;
import _JobSchedulling.JobScheduleClass.WeightedJob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreedyMethodFunc {
	 // Worst Case: O(n^2) , Worst Case Optimized: O(nlogn) - heap + binarySearch
    public static JobSchedule printJobScheduling_greedy(List<GreedyJob> job, int n) {

        Map<Integer,GreedyJob> map = new HashMap<>();

        for(int i=0;i<job.size();i++) {
            map.put(job.get(i).id,job.get(i));
        }

        int maxSlot = max(job);

        // Greedy
        Collections.sort(job,(a,b)->b.profit-a.profit); // O(nlogn)

        int[] slot = new int[maxSlot];

        Arrays.fill(slot,-1);

        // O(n^2) - linear Search // O(nlogn) - binarySearch
        for(int i=0;i<job.size();i++) {

            int slotNumber = findSlot(slot,job.get(i));

            if(slotNumber != -1) {
                slot[slotNumber] = job.get(i).id;            
            }
        }

        int profit = 0;
        List<Integer> sequence = new ArrayList<>();

        for(int i=0;i<slot.length;i++) {
            if(slot[i]!=-1) {
                profit+=map.get(slot[i]).profit;
                sequence.add(slot[i]);
            }
        }
        return new JobSchedule(profit,sequence);
    }

    // O(n) - linear search // if we use binarySearch to findSlot then it will be O(logn)
    static int findSlot(int slot[], GreedyJob job) {
        int tempSlot = job.deadline-1;
        // find a empty slot
        for(int i=tempSlot;i>=0;i--) {
            if(slot[i] == -1) {
                return i;
            }
        }
        // no empty slot found
        return -1;
    }

    static int max(List<GreedyJob> job) {
        int max = 0;
        for(int i=0;i<job.size();i++) {
            max = Math.max(max,job.get(i).deadline);
        } 
        return max;
    }
}
