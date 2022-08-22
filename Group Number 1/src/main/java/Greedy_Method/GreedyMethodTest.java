package Greedy_Method;
import java.util.*;

import Greedy_Method.Greedy_Method.Job;
import Greedy_Method.Greedy_Method.JobSchedule;

public class GreedyMethodTest {

	public static void main(String[] args) {
		 List<Job> jobs = new ArrayList<>();
	        jobs.add(new Job(2,60,1));
	        jobs.add(new Job(1,100,2));
	        jobs.add(new Job(3,20,3));
	        jobs.add(new Job(2,40,4));
	        jobs.add(new Job(1,20,5));
	        JobSchedule ans = Greedy_Method.printJobScheduling(jobs,5);
	        System.out.println("MaxProfit: "+ ans.maxProfit);
	        for(int i=0;i<ans.sequence.size();i++) {
	            System.out.print(ans.sequence.get(i)+ "  ");
	        }

	}

}
