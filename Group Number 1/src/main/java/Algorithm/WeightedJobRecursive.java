package Algorithm;
import java.util.*;

import Comparator.SortByDeadLine;
import Model.Job;

public class WeightedJobRecursive{
    private int n;
    public long time;
    private Job[] arr;

    public long getTime(){
        return time;
    }
    public WeightedJobRecursive(Job[] arr){
        this.arr = arr;
        this.n = arr.length;
    }
    private static int latestNonConflict(Job arr[], int i){
		int lo = 0, hi = i - 1;
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (arr[mid].getEnd() <= arr[i].getStart()){
                if (arr[mid + 1].getEnd() <= arr[i].getStart()){
                    lo = mid + 1;
                }
                else{
                    return mid;
                }
            }
            else{
                hi = mid - 1;
            }
        }
        return -1;
    }
    public Stack<Job> RecursiveSort(int i){
        if (i == 1){
            Stack<Job> returnList = new Stack<Job>();
            returnList.push(arr[0]);
            returnList.push(new Job(0, 0, arr[0].getProfit(), "total"));
            return returnList;
        }
        double includedProfit = arr[i-1].getProfit();
        Stack<Job> includedJob = new Stack<Job>();
        includedJob.push(arr[i-1]);
        int j = latestNonConflict(arr, i-1);
        if (j != -1){
            includedJob.addAll(RecursiveSort(j+1));
			includedProfit += includedJob.peek().getProfit();
			includedJob.peek().setProfit(includedProfit);
        }
        Stack<Job> excludedJob = RecursiveSort(i-1);
        double excludedProfit = excludedJob.peek().getProfit();

        if (includedProfit > excludedProfit){
            for (Job job : includedJob){
                System.out.printf("%s ", job.getName());
            }
            System.out.printf("\n");
            return includedJob;
        }
        else{
            for (Job job : excludedJob){
                System.out.printf("%s ", job.getName());
            }
            System.out.printf("\n");
            return excludedJob;
        }
    }

    public Stack<Job> findMaxProfit(){
        long startTime = System.nanoTime();
        Arrays.sort(arr,new SortByDeadLine());
        Stack<Job> result = RecursiveSort(n);
        time = System.nanoTime() - startTime;
        return result;
    }
}