package Model;
public class Job{
    private int start, end;
    private double profit;
    private String name;

    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public double getProfit(){
        return profit;
    }
    public void setProfit(double value){
        profit = value;
    }
    public String getName(){
        return name;
    }
	public Job(int start, int end, double profit, String name)
	{
        this.start = start;
		this.end = end;
        this.profit = profit;
        this.name = name;
	}
}
