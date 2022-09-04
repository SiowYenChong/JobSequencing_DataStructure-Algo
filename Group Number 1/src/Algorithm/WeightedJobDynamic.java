package Algorithm;
import java.util.*;

import Comparator.SortByDeadLine;
import Model.Job;

public class WeightedJobDynamic {
    private int n;
    private Job[] arr;
    private double maxProfit;

    public WeightedJobDynamic(Job[] arr){ 
        this.arr = arr;
        this.n = arr.length;
    }
    public double getMaxProfit(){
        return maxProfit;
    }
    private  int latestNonConflict(Job arr[], int i){
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
    private Queue<Job> DynamicSort(){
        Stack<Queue<Job>> sequence = new Stack<Queue<Job>>();
        double[] total = new double[n];
        total[0] = arr[0].getProfit();
        sequence.add(new LinkedList<Job>(Arrays.asList(arr[0])));

        for (int i = 1; i < n; i++) {
            Queue<Job> chosenJob = new LinkedList<Job>();
            double inclProf = arr[i].getProfit();
            int l = latestNonConflict(arr, i);
            if (l != -1){
                inclProf += total[l];
                try{
                    chosenJob.addAll(sequence.get(l));
                } catch (IndexOutOfBoundsException ex){
                    //do nothing
                }
            }
            chosenJob.add(arr[i]);
            if (inclProf >= total[i-1]){
                total[i] = inclProf;
                sequence.add(chosenJob);
            }
            else{
                total[i] = total[i-1];
                sequence.add(sequence.peek());
            }	
        }
        maxProfit = total[n-1];
        return sequence.pop();
    }
    public Queue<Job> findMaxProfit(){
        Arrays.sort(arr,new SortByDeadLine());
        Queue<Job> result = DynamicSort();
        return result;
    }
}