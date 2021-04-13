package addition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Utility {

    public static boolean getYesorNo(String message){
        Scanner uScanner = new Scanner(System.in);
        System.out.print("\n"+message + " (y/n) ");
        String userInput = uScanner.next();
        if(userInput.equalsIgnoreCase("y")){
            return true;
        }

        while(!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n")){
            System.out.print("Sorry your input's is not (y) or (n)\nPlease input (y/n) ");
            userInput = uScanner.next();
               
        }

        if(userInput.equalsIgnoreCase("y")){
            return true;
        }

        return false;
    }

    public static void clearScreen()throws IOException{
        try{
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else{
                System.out.println("\033\143");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    protected static boolean cekDatabase(String [] keywords, boolean isDisplay) throws IOException {
        FileReader fileReader = new FileReader("database.txt");
        BufferedReader bufferInput = new BufferedReader(fileReader);
        
        String data= bufferInput.readLine();
        long number = 0;
        StringTokenizer token;
        boolean isExist = true;
        boolean notFound = true;

        if(isDisplay){
            String [] field = {"No.","Name","Rank","Type","Attack","Combination"};
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.printf("| %4s |  %-25s |  %-10s |  %-10s |  %-7s |  %s\n",field[0],field[1],field[2],field[3],field[4],field[5]);
            System.out.println("------------------------------------------------------------------------------------------------");
        }

        while(data != null){
            
            isExist = true;
            number++;

            for(String key : keywords){    
                isExist = isExist && data.toLowerCase().contains(key.toLowerCase());
            }

            if(isExist){
                if(isDisplay){
                    token = new StringTokenizer(data, ",");
                    String combination = token.nextToken();
                    System.out.printf("| %4d ", number);
                    System.out.printf("|  %-25s ", token.nextToken());
                    System.out.printf("|  %-10s ", token.nextToken());
                    System.out.printf("|  %-10s ", token.nextToken());
                    System.out.printf("|  %-7s ", token.nextToken());
                    System.out.println("|  "+ combination);       
                }else{
                    return isExist;
                }
                notFound = false;
            }

            data = bufferInput.readLine();
        }

        if(isDisplay && notFound){
            System.out.println("  Your data is not found");
            boolean add = getYesorNo("Are you want to add new data?");
            if(add){
                Fungtion.addWeapon();
            }
        }

        fileReader.close();
        bufferInput.close();
        return isExist;
    }

}
