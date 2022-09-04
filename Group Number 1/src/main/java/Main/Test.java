package Main;
import java.util.*;

import Algorithm.GreedyMethod;
import Algorithm.WeightedJobDynamic;
import Algorithm.WeightedJobRecursive;
import Model.Job;

public class Test {
    public static void main(String args[]){
        int n = 5;
        Job arr[] = new Job[n];
        arr[0] = new Job(1, 2, 100, "Job 1");
        arr[1] = new Job(6, 19, 19, "Job 2");
        arr[2] = new Job(3, 5, 27, "Job 3");
        arr[3] = new Job(20, 100, 25, "Job 4");
        arr[4] = new Job(2, 100, 50, "Job 5");

        System.out.printf("The result of Weighted Job Scheduling with Dynamic Sort:\n");
        WeightedJobDynamic dynamic = new WeightedJobDynamic(arr.clone());
        Queue<Job> dynamicResult = dynamic.findMaxProfit();
        double dynamicProfit = dynamic.getMaxProfit();
        while(!dynamicResult.isEmpty()){
            Job i = dynamicResult.remove();
            System.out.printf("%s ", i.getName());
        }
        System.out.printf("\nThe profit is: %.2f\n", dynamicProfit);

        System.out.printf("\nThe result of Weighted Job Scheduling with Recursive Sort:\n");
        WeightedJobRecursive recursive = new WeightedJobRecursive(arr.clone());
        Stack<Job> recursiveResult = recursive.findMaxProfit();
        double recursiveProfit = recursiveResult.pop().getProfit();
        while(!recursiveResult.isEmpty()){
            Job job = recursiveResult.pop();
            System.out.printf("%s ", job.getName());
        }
        System.out.printf("\nThe profit is: %.2f\n", recursiveProfit);



        int m = 5;
        Job[] arr1 = new Job[m];
        arr1[0] = new Job(0, 2, 100, "Job 1");
        arr1[1] = new Job(0, 1, 19, "Job 2");
        arr1[2] = new Job(0, 2, 27, "Job 3");
        arr1[3] = new Job(0, 1, 25, "Job 4");
        arr1[4] = new Job(0, 3, 15, "Job 5");
        GreedyMethod greedy = new GreedyMethod(arr1);
        double profit = 0;
        Queue<Job> greedyResult = greedy.SortJobScheduling();
        System.out.printf("\nThe result of Unweighted Job Scheduling with Greedy Method:\n");
        while (!greedyResult.isEmpty()){
            Job job = greedyResult.remove();
            System.out.printf("%s, ", job.getName());
            profit += job.getProfit();
        }
        System.out.printf("\nThe profit is: %.2f\n", profit);
    }
}
