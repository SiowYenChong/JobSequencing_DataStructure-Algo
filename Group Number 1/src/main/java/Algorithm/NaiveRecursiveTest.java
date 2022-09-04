package Algorithm;
import java.util.*;

import Comparator.SortByDeadLine;
import Model.Job;

//O(n* 2^n) -> Worst & Best (Exponential)
//Make in DP solution (memoisation/top-down) -> O(n^2)
//Binary search + DP 						 -> O(nlogn)

//Space complexity: O(n)

public class NaiveRecursiveTest{
    private int n;
    private Job[] arr;

    public NaiveRecursiveTest(Job[] arr){
        this.arr = arr;
        this.n = arr.length;
    }
    private static int latestNonConflict(Job arr[], int i){
    	for (int j = i - 1; j >= 0; j--)
        {
            if (arr[j].getEnd() <= arr[i - 1].getStart())
                return j;
        }
        return -1;
    }
    public Stack<Job> RecursiveSort(int i){
        if (i == 1){
            Stack<Job> returnList = new Stack<Job>();
            returnList.push(arr[0]);
            //returnList.push(new Job(0, 0, arr[0].getProfit(), "total"));
            return returnList;
        }
        double includedProfit = arr[i-1].getProfit();
        Stack<Job> includedJob = new Stack<Job>(); //O(n): stack complexity - space - height of stack at most n
        //includedJob.push(arr[i-1]);
        int j = latestNonConflict(arr, i-1);
        if (j != -1){
            includedJob.addAll(RecursiveSort(j+1));
			includedProfit += includedJob.peek().getProfit();
			includedJob.push(arr[i-1]);
			includedJob.peek().setProfit(includedProfit);
        }else {
        	includedJob.push(arr[i-1]);
        }
        Stack<Job> excludedJob = RecursiveSort(i-1); //O(n): stack complexity - space - height of stack at most n
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
