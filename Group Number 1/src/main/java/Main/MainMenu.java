package Main;
import java.time.LocalDate;
import java.util.*;

import Algorithm.GreedyMethod;
import Algorithm.NaiveRecursiveTest;
import Algorithm.WeightedJobDynamic;
import Model.Job;
import Model.JobSchedule;

import java.time.temporal.ChronoUnit;

public class MainMenu {
    public static Scanner input = new Scanner(System.in);

    public static Job[] getJobDetails(){
        Job job;

        int jobNum = 0;
        double profit = 0.0;// get user input profit
        int deadlineStart = 0; // get user input deadline
        int deadlineEnd = 0;

        String name = "";
        boolean check = false;// loop condition
    
        do {
            System.out.println("How many jobs do you have? ");
            try {
                String jobNumStr = input.nextLine();
                jobNum = Integer.parseInt(jobNumStr);
                check = true;
            } catch (Exception invalidInput) {
                System.out.println("Please enter numeric value.");
            }
        } while (!check);
        
        Job arr[] = new Job[jobNum];
        
        check = false;

        for (int i=0; i<jobNum; i++){
            System.out.printf("\nJob number %d:\n", i+1);
            // get job month value
            do {
                String startTimeStr;
                String endTimeStr;

                try {
                	System.out.printf("Please enter start time: ");
                	startTimeStr = input.nextLine(); 
                    deadlineStart = Integer.parseInt(startTimeStr);
                    System.out.printf("Please enter end time: ");
                    endTimeStr = input.nextLine(); 
                    deadlineEnd = Integer.parseInt(endTimeStr);

                    try {
                        if (deadlineStart >= 0) {
                              check = true;
                        } else {
                            System.out.print("Deadline Start can't be smaller than 0");
                        }
                    } catch (Exception invalidInput) {
                        System.out.println("Please enter numeric input");
                    }
                    
                    try {
                        if (deadlineEnd > deadlineStart) {
                              check = true;
                        } else {
                            System.out.print("Deadline End can't be smaller than or equal to Deadline Start");
                        }
                    } catch (Exception invalidInput) {
                        System.out.println("Please enter numeric input");
                    }
                }catch (Exception invalidInput) {
                	System.out.println("Please enter numeric input");
                }
            }while (!check);
            
            check = false;
            do {
                System.out.printf("Please enter profit: ");
                String profitStr = input.nextLine();

                try {
                    profit = Double.parseDouble(profitStr);
                    check = true;
                } catch (Exception inputInvalid) {
                    System.out.println("Please enter numeric value");
                }

            } while (!check);

            // get job id
            System.out.printf("Please enter name: ");
            name = input.nextLine();

            job = new Job(deadlineStart, deadlineEnd, profit, name);

           
            arr[i] = job;
        }
        return arr;
    }

    public static Job[] getGreedyJobDetails(){
        Job job;

        int jobNum = 0;
        double profit = 0.0;// get user input profit
        String name = "";
        boolean check = false;// loop condition
        int deadline = 0;
    
        do {
            System.out.println("How many jobs do you have? ");
            try {
                String jobNumStr = input.nextLine();
                jobNum = Integer.parseInt(jobNumStr);
                check = true;
            } catch (Exception invalidInput) {
                System.out.println("Please enter numeric value.");
            }
        } while (!check);
        
        Job arr[] = new Job[jobNum];
        
        check = false;

        for (int i=0; i<jobNum; i++){
            System.out.printf("\nJob number %d:\n", i+1);
            // get job month value
            do {
                String deadlineStr;
                try {
                	System.out.printf("Please enter the deadline: ");
                	deadlineStr = input.nextLine(); 
                    deadline = Integer.parseInt(deadlineStr);

                    try {
                        if (deadline > 0) {
                              check = true;
                        } else {
                            System.out.print("Deadline can't be smaller than 1");
                        }
                    } catch (Exception invalidInput) {
                        System.out.println("Please enter numeric input");
                    }
                }catch (Exception invalidInput) {
                	System.out.println("Please enter numeric input");
                }
            }while (!check);
            
            check = false;
            do {
                System.out.printf("Please enter profit: ");
                String profitStr = input.nextLine();

                try {
                    profit = Double.parseDouble(profitStr);
                    check = true;
                } catch (Exception inputInvalid) {
                    System.out.println("Please enter numeric value");
                }

            } while (!check);

            // get job id
            System.out.printf("Please enter job ID: ");
            name = input.nextLine();
            job = new Job(0, deadline, profit, name);
            arr[i] = job;
        }
        return arr;
    }


    public static void greedyMethod(){
        Job[] arr = getGreedyJobDetails();
        GreedyMethod greedy = new GreedyMethod(arr);
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
    public static void dynamicPrograming(){
        Job[] arr = getJobDetails();
        System.out.printf("The result of Weighted Job Scheduling with Dynamic Sort:\n");
        WeightedJobDynamic dynamic = new WeightedJobDynamic(arr);
        JobSchedule dynamicResult = dynamic.generateJobScheduling();
        double dynamicProfit = dynamicResult.getMaxProfit();
        List<Integer> sequence = dynamicResult.getSequence();
        
        for(int job: sequence){
            System.out.printf("%s ", job);
        }
        System.out.printf("\nThe profit is: %.2f\n", dynamicProfit);
    }
    public static void recursiveMethod(){
        Job[] arr = getJobDetails();
        System.out.printf("\nThe result of Weighted Job Scheduling with Recursive Sort:\n");
        NaiveRecursiveTest recursive = new NaiveRecursiveTest(arr);
        Stack<Job> recursiveResult = recursive.findMaxProfit();
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
        System.out.printf("\nThe profit is: %.2f\n", recursiveProfit);
    }

    public static void main(String[] args) {
        int selection;

        System.out.printf("Welcome to Printing Job Scheduling Program\n");
        System.out.printf("\nInsert the details of jobs to get a sequence with max profit\n");
        System.out.printf("\nPease select Dynamic Programing or Naive Recursive Method approach for weighted jobs.\n");
        System.out.printf("Please select Greedy Method approach for unweigthed jobs.\n");
        System.out.printf("\n----------------------------------------------------------------------------------\n");
        while(true){
            while(true){
                try{
                    System.out.printf("\nPlease choose one of the approach to schedule the jobs:\n");
                    System.out.printf("1. Greedy Method (Unweighted Job)\n");
                    System.out.printf("2. Dynamic Programing (Weighted Job)\n");
                    System.out.printf("3. Naive Recursive Method (Weighted Job)\n");
                    System.out.printf(">>> ");
                    selection = Integer.parseInt(input.nextLine());
                    if (selection >= 1 && selection <= 3){
                        break;
                    }
                    else{
                        System.out.printf("Please insert a valid input\n");
                    }
                } catch(NumberFormatException ex){
                    System.out.printf("Invalid input\n");
                }
            }
            if (selection == 1){
                greedyMethod();
            }
            else if (selection == 2){
                dynamicPrograming();
            }
            else {
                recursiveMethod();
            }

            boolean repeat;
            while(true){
                System.out.printf("\nDo you wish schedule another list of jobs?\n");
                System.out.printf("Yes / No (y/n): ");
                String choice = input.nextLine();
                if (choice.toLowerCase().equals("y") || choice.toLowerCase().equals("yes")){
                    repeat = true;
                    break;
                }
                else if(choice.toLowerCase().equals("n") || choice.toLowerCase().equals("no")){
                    repeat = false;
                    break;
                }
                else{
                    System.out.printf("Please insert a valid input\n");
                }
            }
            if (!repeat){
                System.out.printf("Thank you for using our program\n");
                System.out.printf("Press enter to continue...");
                input.nextLine();
                break;
            }
        }
        input.close();
    }
}