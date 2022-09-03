package _JobSchedulling;
import java.util.*;

import _JobSchedulling.JobScheduleClass.GreedyJob;
import _JobSchedulling.JobScheduleClass.JobSchedule;
import _JobSchedulling.JobScheduleClass.WeightedJob;
import GreedyMethod.GreedyMethodFunc;
import WeightedProblemWithDP.WeightedProbWithDPFunc;

public class TestProgram {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String cont = "Y";
		
		while (!cont.toUpperCase().equals("N")){
		//Menu
			System.out.println("-------Welcome to CCFL Printing Service-------");
			System.out.println("Your best schedulling of printing task and max profit");
			System.out.println();
			System.out.print("Do you have a dateline? (Y/N): ");
			String response1 = sc.next();
			System.out.print("Do you have a start/end date? (Y/N): ");
			String response2 = sc.next();
			System.out.println();
		
		//------------------------------------------------------------------------------------ 
		//1. Weighted prob with Dynamic Programming
		
		
		
	        List<WeightedJob> jobs_weighted = new ArrayList<>();
	
	        
	        if((response1.equals("N") || response1.equals("n")) && (response2.equals("Y") || response2.equals("y")) ) {
		        // n -> # of jobs
		        System.out.print("Insert number of jobs: ");
		        int n = sc.nextInt();
		        // startTime, endTime, profit, jodId
		        for(int i=0;i<n;i++) {
		
		        	System.out.print("Insert start time for Job "+(i+1)+": ");
		            int startTime = sc.nextInt();
		            System.out.print("Insert end time for Job "+(i+1)+": ");
		            int endTime = sc.nextInt();
		            System.out.print("Insert profit for Job "+(i+1)+": ");
		            int profit = sc.nextInt();
		            System.out.print("Insert job id for Job "+(i+1)+": ");
		            int jobId = sc.nextInt();
		            System.out.println();
		
		            WeightedJob weightedJob = new WeightedJob(startTime,endTime,profit,jobId);
		
		            jobs_weighted.add(weightedJob);
		        }
		
		        JobSchedule weightedAns = WeightedProbWithDPFunc.printJobScheduling_weighted(jobs_weighted,n);
		
		        System.out.println("Weighted Job Scheduling:");
		        System.out.println("MaxProfit: "+ weightedAns.maxProfit);
		        System.out.println("Job Sequence:");
		        for(int i=0;i<weightedAns.sequence.size();i++) {
		            System.out.print(weightedAns.sequence.get(i)+ " , ");
		        }
		        System.out.println();
		        System.out.print("Report ended. Continue? (Y/N): ");
		        cont = sc.next();
		        
		        System.out.println();
		        System.out.println();
		}
	
	        
	    //------------------------------------------------------------------------------------   
		//2. Greedy Algorithm
	        List<GreedyJob> jobs_greedy = new ArrayList<>();
	
	        if((response1.equals("Y") || response1.equals("y")) && (response2.equals("N") || response2.equals("n"))) {
		        // m -> # of jobs
		        System.out.print("Insert number of jobs: ");
		        int m = sc.nextInt();
		        // deadline, profit, jodId
		        for(int i=0;i<m;i++) {
		
		        	System.out.print("Insert deadline for Job "+(i+1)+": ");
		            int deadline = sc.nextInt();
		            System.out.print("Insert profit for Job "+(i+1)+": ");
		            int profit = sc.nextInt();
		            System.out.print("Insert job id for Job "+(i+1)+": ");
		            int jobId = sc.nextInt();
		            System.out.println();
		
		            GreedyJob greedyJob = new GreedyJob(deadline,profit,jobId);
		
		            jobs_greedy.add(greedyJob);
		        }
		
		        JobSchedule greedyAns = GreedyMethodFunc.printJobScheduling_greedy(jobs_greedy,m);
		
		        System.out.println("Greedy Job Scheduling:");
		        System.out.println("MaxProfit: "+ greedyAns.maxProfit);
		        System.out.println("Job Sequence:");
		        for(int i=0;i<greedyAns.sequence.size();i++) {
		            System.out.print(greedyAns.sequence.get(i)+ " , ");
		        }
		        System.out.println();
		        System.out.print("Report ended. Continue? (Y/N): ");
		        cont = sc.next();
		    }
	        if((response1.equals("Y") || response1.equals("y")) && (response2.equals("Y") || response2.equals("y"))) {
	        	System.out.println("Our machine currently can accept either dateline/ start-end date only");
	        	System.out.println();
	        }
	        
	        
		}
	}
}
