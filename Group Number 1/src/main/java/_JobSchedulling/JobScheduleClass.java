package _JobSchedulling;
import java.util.*;
public class JobScheduleClass {
	public static class WeightedJob {
	    public int startTime;
	    public int endTime;
	    public int profit;
	    public int id;
	    WeightedJob(int startTime, int endTime, int profit, int id) {
	        this.startTime = startTime;
	        this.endTime = endTime;
	        this.profit = profit;
	        this.id = id;
	    }
	 }

	 public static class GreedyJob {
	    public int deadline;
	    public int profit;
	    public int id;
	    GreedyJob(int deadline, int profit, int id) {
	        this.deadline = deadline;
	        this.profit = profit;
	        this.id = id;
	    }
	  }

	  public static class JobSchedule {
		    int maxProfit;
		    List<Integer> sequence;
	    public JobSchedule(int profit, List<Integer> order) {
	        this.maxProfit = profit;
	        this.sequence = order;
	    }
	   }
	   
}