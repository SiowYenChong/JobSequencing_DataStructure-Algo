package Main;
import java.util.*;

import Algorithm.GreedyMethod;
import Algorithm.NaiveRecursiveTest;
import Algorithm.WeightedJobDynamic;
import Model.Job;
import Model.JobSchedule;

public class Test {
    public static void main(String args[]){
        int n = 4;
        Job arr[] = new Job[n];
        /*
        arr[0] = new Job(1,3,5,"1");
        arr[1] = new Job(2,5,6,"2");
        arr[2] = new Job(4,6,5,"3");
        arr[3] = new Job(6,7,4,"4");
        arr[4] = new Job(5,8,11,"5");
        arr[5] = new Job(7,9,2,"6");
		*/

        arr[0] = new Job(1,2,50,"1");
        arr[1] = new Job(3,5,20,"2");
        arr[2] = new Job(6,19,100,"3");
        arr[3] = new Job(2,100,200,"4");

        System.out.printf("The result of Weighted Job Scheduling with Dynamic Sort:\n");
        WeightedJobDynamic dynamic = new WeightedJobDynamic(arr.clone());
        JobSchedule dynamicResult = dynamic.generateJobScheduling();
        double dynamicProfit = dynamicResult.getMaxProfit();
        List<Integer> sequence = dynamicResult.getSequence();
        
        for(int job: sequence){
            System.out.printf("%s ", job);
        }
       
        System.out.printf("\nThe profit is: %.2f\n", dynamicProfit);

        System.out.printf("\nThe result of Weighted Job Scheduling with Recursive Sort:\n");
        NaiveRecursiveTest recursive = new NaiveRecursiveTest(arr.clone());
        
        Stack<Job> recursiveResult = recursive.findMaxProfit();
        //double recursiveResult = recursive.findMaxProfitRec(n);
        //System.out.println(recursiveResult);
        
        double recursiveProfit = recursiveResult.peek().getProfit();
        
        List <String> seq = new ArrayList<>();
        
        
        while(!recursiveResult.isEmpty()){
            Job job = recursiveResult.pop();
            seq.add(job.getId());
        }
        Collections.reverse(seq);
        
        for(String job: seq) {
        	System.out.printf("%s ", job);
        }
        System.out.println();
        System.out.println("The profit is: "+recursiveProfit);
        /*
        recursive.printMaxProfit();
        recursive.printJobScheduling();
		*/

        /*
         *  // jobs_greedy.add(new GreedyJob(2,60,1));
        // jobs_greedy.add(new GreedyJob(1,100,2));
        // jobs_greedy.add(new GreedyJob(3,20,3));
        // jobs_greedy.add(new GreedyJob(2,40,4));
        // jobs_greedy.add(new GreedyJob(1,20,5));
         * */
        int m = 5;
        Job[] arr1 = new Job[m];
        arr1[0] = new Job(0, 2, 60, "Job 1");
        arr1[1] = new Job(0, 1, 100, "Job 2");
        arr1[2] = new Job(0, 3, 20, "Job 3");
        arr1[3] = new Job(0, 2, 40, "Job 4");
        arr1[4] = new Job(0, 1, 20, "Job 5");
        GreedyMethod greedy = new GreedyMethod(arr1);
        double profit = 0;
        Queue<Job> greedyResult = greedy.SortJobScheduling();
        System.out.printf("\nThe result of Unweighted Job Scheduling with Greedy Method:\n");
        while (!greedyResult.isEmpty()){
            Job job = greedyResult.remove();
            System.out.printf("%s, ", job.getId());
            profit += job.getProfit();
        }
        System.out.printf("\nThe profit is: %.2f\n", profit);
    }
}
