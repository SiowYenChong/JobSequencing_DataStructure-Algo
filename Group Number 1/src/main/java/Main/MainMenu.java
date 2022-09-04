package Main;
import java.time.LocalDate;
import java.util.*;

import Algorithm.GreedyMethod;
import Algorithm.WeightedJobDynamic;
import Algorithm.WeightedJobRecursive;
import Model.Job;

import java.time.temporal.ChronoUnit;

public class MainMenu {
    public static Scanner input = new Scanner(System.in);

    public static Job[] getJobDetails(boolean weight){
        Job job;

        LocalDate today = LocalDate.now();
        LocalDate startDate = null;
        LocalDate endDate = null; // get user input date obj

        int jobNum = 0;
        int startMonth = 0; // user input month
        int startDay = 0;// user input day
        int endMonth = 0; // user input month
        int endDay = 0;// user input day
        double profit = 0.0;// get user input profit
        int deadlineStart = 0; // get user input deadline
        int deadlineEnd = 0;
        String name = "";// get user input name
        int year = 0;

        boolean check = false;// loop condition
        // assume same year for end and start date
        do {
            System.out.printf("Enter year: ");
            String yearStr = input.nextLine();

            try {
                year = Integer.parseInt(yearStr);

                // assume year after 2060 is invalid
                if (year > 2060 || year < today.getYear()) {
                    System.out.println("Please enter a proper year");
                }
                else
                    check = true;

            } catch (Exception invalidInput) {
                System.out.println("Please enter numeric value");
            }
        } while (!check);

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
                String startMonthStr;
                if (weight){
                    System.out.printf("Please enter start month (1-12): ");
                    startMonthStr = input.nextLine();
                }
                else{
                    startMonthStr = String.format("%s", today.getMonthValue());
                }

                try {
                    startMonth = Integer.parseInt(startMonthStr);

                    if (startMonth >= 1 && startMonth <= 12) {

                        if (startMonth >= today.getMonthValue()) {

                            System.out.printf("Please enter end month (1-12): ");
                            String endMonthStr = input.nextLine();

                            try {
                                endMonth = Integer.parseInt(endMonthStr);

                                if (endMonth >= 1 && endMonth <= 12) {
                                    if (endMonth >= today.getMonthValue()) {
                                        if (endMonth >= startMonth) {
                                            check = true;
                                        } else {
                                            System.out.print("End month can't be greater than start month");
                                        }
                                    } else
                                        System.out.println("End month must greater than or equals to current month!");

                                } else {
                                    System.out.println("End month must be 1 - 12 only");
                                }
                            } catch (Exception invalidInput) {
                                System.out.println("Please enter numeric input");
                            }
                        } else {
                            System.out.println("Start month must be greater than or equals to current month");
                        }
                    } else {
                        System.out.println("Start month must be 1 - 12 only");
                    }
                } catch (Exception invalidInput) {
                    System.out.println("Please enter numeric value.");
                }
            } while (!check);

            check = false;

            // get job date value
            do {
                String startDayStr;
                if (weight){
                    System.out.printf("Please enter start day: ");
                    startDayStr = input.nextLine();
                }
                else{
                    startDayStr = "0";
                }

                try {
                    startDay = Integer.parseInt(startDayStr);
                    System.out.printf("Please enter end day: ");
                    String endDayStr = input.nextLine();
                    
                    endDay = Integer.parseInt(endDayStr);
                    
                    // calculate days between
                    endDate = LocalDate.of(year, endMonth, endDay);
                    long deadlineEndLong = ChronoUnit.DAYS.between(today, endDate);
                    deadlineEnd = (int) deadlineEndLong;

                    if (weight){
                        startDate = LocalDate.of(year, startMonth, startDay);
                        long deadlineStartLong = ChronoUnit.DAYS.between(today, startDate);
                        deadlineStart = (int) deadlineStartLong;
                        if(deadlineStartLong > 0 && deadlineEndLong > 0)
                        {
                            if(deadlineEnd > deadlineStart)
                            {
                                check = true;
                            }
                            else
                            {
                                System.out.println("End date must be greater than start date");
                            }
                        }
                        else{
                            System.out.println("Start date or End date must be greater than today.");
                        }
                    }
                    else{
                        if (deadlineEndLong > 0){
                            check = true;
                        }
                        else{
                            System.out.println("End date must be greater than today.");
                        }
                    }
                } catch (Exception invalidInput) {
                    System.out.println("Please enter numeric value.");
                }
            } while (!check);
            
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

            // get job name
            System.out.printf("Please enter name: ");
            name = input.nextLine();
            if (weight){
                job = new Job(deadlineStart, deadlineEnd, profit, name);
            }
            else{
                job = new Job(0, deadlineEnd, profit, name);
            }
            arr[i] = job;
        }
        return arr;
    }
    public static void greedyMethod(){
        Job[] arr = getJobDetails(false);
        GreedyMethod greedy = new GreedyMethod(arr);
        double profit = 0;
        Queue<Job> greedyResult = greedy.SortJobScheduling();
        System.out.printf("\nThe result of Unweighted Job Scheduling with Greedy Method:\n");
        while (!greedyResult.isEmpty()){
            Job job = greedyResult.remove();
            System.out.printf("%s, ", job.getName());
            profit += job.getProfit();
        }
        System.out.printf("\nThe profit is: %.2f\n", profit);
        System.out.printf("Total Time taken is %d\n", greedy.getTime());
    }
    public static void dynamicPrograming(){
        Job[] arr = getJobDetails(true);
        System.out.printf("The result of Weighted Job Scheduling with Dynamic Sort:\n");
        WeightedJobDynamic dynamic = new WeightedJobDynamic(arr);
        Queue<Job> dynamicResult = dynamic.findMaxProfit();
        double dynamicProfit = dynamic.getMaxProfit();
        while(!dynamicResult.isEmpty()){
            Job i = dynamicResult.remove();
            System.out.printf("%s ", i.getName());
        }
        System.out.printf("\nThe profit is: %.2f\n", dynamicProfit);
        System.out.printf("Total Time taken is %d\n", dynamic.getTime());
    }
    public static void recursiveMethod(){
        Job[] arr = getJobDetails(true);
        System.out.printf("\nThe result of Weighted Job Scheduling with Recursive Sort:\n");
        WeightedJobRecursive recursive = new WeightedJobRecursive(arr);
        Stack<Job> recursiveResult = recursive.findMaxProfit();
        double recursiveProfit = recursiveResult.pop().getProfit();
        while(!recursiveResult.isEmpty()){
            Job i = recursiveResult.pop();
            System.out.printf("%s ", i.getName());
        }
        System.out.printf("\nThe profit is: %.2f\n", recursiveProfit);
        System.out.printf("Total Time taken is %d\n", recursive.getTime());
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
