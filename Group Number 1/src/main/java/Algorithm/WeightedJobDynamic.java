package Algorithm;
import java.util.*;

import Model.Job;
import Model.JobSchedule;

// O(n^2) -> Both worst & best
// O(nlogn) -> optimisation (binary search)

//Space complexity: O(n)

public class WeightedJobDynamic {
	Job[] jobs;
	public WeightedJobDynamic(Job[] jobs) {
		this.jobs = jobs;
	}
    
    public JobSchedule generateJobScheduling() {
	
    int n = jobs.length;
	
    Arrays.sort(jobs,(a,b)->a.getEnd()-b.getEnd()); // O(nlogn)

    // J2 , J5 , J1 , J3, J4, J6

    // <ArrayListIndex,Job>
    HashMap<Integer,Job> map = new HashMap<>();
    for(int i=0;i<n;i++) {
        map.put(i,jobs[i]);
    }

    double ans[] = new double[n]; //O(n) - space, size array
    int sequence[] = new int[n]; //O(n)  - space

    for(int i=0;i<jobs.length;i++) {
        ans[i] = jobs[i].getProfit();
    }

    Arrays.fill(sequence,-1);

    double maxProfit = ans[0];
    int maxJobId = Integer.parseInt(jobs[0].getId());

    
    // O(n^2)
    for(int j=1;j<n;j++) {
        for(int i=0;i<j;i++) {
            if(canDoBothJobs(i,j,map)) {
                double currentProfit = ans[i] + jobs[j].getProfit();
                if(currentProfit > ans[j]) {
                    ans[j] = currentProfit;
                    sequence[j] = Integer.parseInt(map.get(i).getId());
                }
            }
        }
        if(ans[j] > maxProfit) {
            maxProfit = ans[j];
            maxJobId = Integer.parseInt(map.get(j).getId());
        }
    }

    List<Integer> seq = new ArrayList<>();
    int jobId = maxJobId; // 5
    seq.add(jobId);

    // 5 <-- 2 <--- -1
    while(sequence[jobId-1] != -1) {
        seq.add(sequence[jobId-1]);
        jobId = sequence[jobId-1];
    }
    Collections.reverse(seq);
    return new JobSchedule(maxProfit,seq);
}

static boolean canDoBothJobs(int i, int j, HashMap<Integer,Job> map) {
    return map.get(j).getStart() >= map.get(i).getEnd();
}


}


//package Algorithm;
//import java.util.*;
//
//import Comparator.SortByDeadLine;
//import Model.Job;
//
//public class WeightedJobDynamic {
//    private int n;
//    private Job[] arr;
//    private double maxProfit;
//
//    public WeightedJobDynamic(Job[] arr){ 
//        this.arr = arr;
//        this.n = arr.length;
//    }
//    public double getMaxProfit(){
//        return maxProfit;
//    }
//    private  int latestNonConflict(Job arr[], int i){
//        int lo = 0, hi = i - 1;
//        while (lo <= hi)
//        {
//            int mid = (lo + hi) / 2;
//            if (arr[mid].getEnd() <= arr[i].getStart()){
//                if (arr[mid + 1].getEnd() <= arr[i].getStart()){
//                    lo = mid + 1;
//                }
//                else{
//                    return mid;
//                }
//            }
//            else{
//                hi = mid - 1;
//            }
//        }
//        return -1;
//    }
//    private Queue<Job> DynamicSort(){
//        Stack<Queue<Job>> sequence = new Stack<Queue<Job>>();
//        double[] total = new double[n];
//        total[0] = arr[0].getProfit();
//        sequence.add(new LinkedList<Job>(Arrays.asList(arr[0])));
//
//        for (int i = 1; i < n; i++) {
//            Queue<Job> chosenJob = new LinkedList<Job>();
//            double inclProf = arr[i].getProfit();
//            int l = latestNonConflict(arr, i);
//            if (l != -1){
//                inclProf += total[l];
//                try{
//                    chosenJob.addAll(sequence.get(l));
//                } catch (IndexOutOfBoundsException ex){
//                    //do nothing
//                }
//                chosenJob.add(arr[i]);
//            }
//            if (inclProf >= total[i-1]){
//                total[i] = inclProf;
//                sequence.add(chosenJob);
//            }
//            else{
//                total[i] = total[i-1];
//                sequence.add(sequence.peek());
//            }	
//        }
//        maxProfit = total[n-1];
//        return sequence.pop();
//    }
//    public Queue<Job> findMaxProfit(){
//        Arrays.sort(arr,new SortByDeadLine());
//        Queue<Job> result = DynamicSort();
//        return result;
//    }
//}
