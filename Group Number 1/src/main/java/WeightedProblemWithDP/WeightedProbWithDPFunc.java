package WeightedProblemWithDP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import _JobSchedulling.JobScheduleClass.JobSchedule;
import _JobSchedulling.JobScheduleClass.WeightedJob;

public class WeightedProbWithDPFunc {
	// O(n^2) -> Both worst & best
		  public static JobSchedule printJobScheduling_weighted(List<WeightedJob> job, int n) {

	        Collections.sort(job,(a,b)->a.endTime-b.endTime); // O(nlogn)

	        // J2 , J5 , J1 , J3, J4, J6

	        // <ArrayListIndex,Job>
	        HashMap<Integer,WeightedJob> map = new HashMap<>();
	        for(int i=0;i<n;i++) {
	            map.put(i,job.get(i));
	        }

	        int ans[] = new int[n];
	        int sequence[] = new int[n];

	        for(int i=0;i<job.size();i++) {
	            ans[i] = job.get(i).profit;
	        }

	        Arrays.fill(sequence,-1);

	        int maxProfit = ans[0];
	        int maxJobId = job.get(0).id;

	        // O(n^2)
	        for(int j=1;j<n;j++) {
	            for(int i=0;i<j;i++) {
	                if(canDoBothJobs(i,j,job,map)) {
	                    int currentProfit = ans[i] + job.get(j).profit;
	                    if(currentProfit > ans[j]) {
	                        ans[j] = currentProfit;
	                        sequence[j] = map.get(i).id;
	                    }
	                }
	            }
	            if(ans[j] > maxProfit) {
	                maxProfit = ans[j];
	                maxJobId = map.get(j).id;
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

		 static boolean canDoBothJobs(int i, int j, List<WeightedJob> job, HashMap<Integer,WeightedJob> map) {
		        return map.get(j).startTime >= map.get(i).endTime;
		 }
}
