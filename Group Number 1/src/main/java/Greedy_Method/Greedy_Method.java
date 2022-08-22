package Greedy_Method;
import java.util.*;
public class Greedy_Method {
	 static Map<Integer,Job> map = new HashMap<>();

	    static class Job {
	    int deadline;
	    int profit;
	    int id;
	    Job(int deadline, int profit, int id) {
	        this.deadline = deadline;
	        this.profit = profit;
	        this.id = id;
	    }
	    }

	    static class JobSchedule {
	    int maxProfit;
	    List<Integer> sequence;
	    JobSchedule(int profit, List<Integer> order) {
	        this.maxProfit = profit;
	        this.sequence = order;
	    }
	    }

	static JobSchedule printJobScheduling(List<Job> job, int n) {

	    for(int i=0;i<job.size();i++) {
	        map.put(job.get(i).id,job.get(i));
	    }

	    int maxSlot = max(job);

	    // Greedy
	    Collections.sort(job,(a,b)->b.profit-a.profit);

	    int[] slot = new int[maxSlot];

	    Arrays.fill(slot,-1);

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

	static int findSlot(int slot[], Job job) {
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

	static int max(List<Job> job) {
	    int max = 0;
	    for(int i=0;i<job.size();i++) {
	        max = Math.max(max,job.get(i).deadline);
	    } 
	    return max;
	}
}
