package Model;

import java.util.List;

public class JobSchedule {
	    private double maxProfit;
	    private List<Integer> sequence;
	    
	    public double getMaxProfit() {
			return maxProfit;
		}

		public void setMaxProfit(double maxProfit) {
			this.maxProfit = maxProfit;
		}

		public List<Integer> getSequence() {
			return sequence;
		}

		public void setSequence(List<Integer> sequence) {
			this.sequence = sequence;
		}

		public JobSchedule(double profit, List<Integer> order) {
        this.maxProfit = profit;
        this.sequence = order;
	    }
}
