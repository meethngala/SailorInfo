/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sailorinfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class for generating reports
 * @author Meeth
 */
public class Reports {
    
    /**
     * generates all the reports
     * @throws IOException
     * @throws SQLException
     */
    public void generateReport() throws IOException, SQLException{
       
        Scanner sc= new Scanner(System.in);
        JDBCConnect j1= new JDBCConnect();
        
        boolean flag= true;
        try{
        while(flag){
            
            System.out.println("Please enter your choice to generate the following reports");
        System.out.println("1. A list of top earning sailor in each position");
        System.out.println("2. Sailors earning least, highest and above average salaries");
        System.out.println("3. A list of Sailors whose Lastname starts with ‘R’ and earn more than $58543");
        int choice = sc.nextInt();
        switch(choice){
            case 1: j1.report1();
                break;
            case 2: j1.report2();
                break;
            case 3: j1.report3();
                break;
                
        }
            System.out.println("Enter 1 to contine OR 0 to exit");
            int c= sc.nextInt();
            if(c==1){
                flag= true;
            }
            else if(c==0){
                flag=false;
            }
            else
            {
                System.out.println("invalid input, try again");
            }
        }
            
            
        }
        catch(InputMismatchException e){
                System.out.println("Please enter valid integer");
               
                generateReport();
                }
        
    }
}
