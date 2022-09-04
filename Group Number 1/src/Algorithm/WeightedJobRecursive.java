package Algorithm;
import java.util.*;

import Comparator.SortByDeadLine;
import Model.Job;

public class WeightedJobRecursive{
    private int n;
    private Job[] arr;

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
        else{
            includedJob.push(new Job(0, 0, arr[i-1].getProfit(), "total"));
        }
        Stack<Job> excludedJob = RecursiveSort(i-1);
        double excludedProfit = excludedJob.peek().getProfit();

        if (includedProfit > excludedProfit){
            return includedJob;
        }
        else{
            return excludedJob;
        }
    }

    public Stack<Job> findMaxProfit(){
        Arrays.sort(arr,new SortByDeadLine());
        Stack<Job> result = RecursiveSort(n);
        return result;
    }
}