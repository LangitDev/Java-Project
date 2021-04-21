package com.warung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.Scanner;
import java.util.StringTokenizer;

public class History {
    File file;
    private FileReader fileReader;
    private BufferedReader bReader;
    private Scanner uScanner;
    
    History() throws IOException{
        
        boolean isRoll = true;
        do{
            Utility.clearScreen();
            System.out.println("========================");
            System.out.println("[1] History Harian");
            System.out.println("[2] History Bulanan");
            System.out.println("[3] History Tahunan");
            System.out.println("========================");

            System.out.print("Pilihan Anda: ");
            uScanner = new Scanner(System.in);
            String user = uScanner.next();
            switch(user){
                case "1":
                harian();
                isRoll = Utility.getYesorNo("Apakah Anda ingin melakukan pengecekan history lainnya?");
                break;
                case "2":
                bulanan();
                isRoll = Utility.getYesorNo("Apakah Anda ingin melakukan pengecekan history lainnya?");
                break;
                case "3":
                tahunan();
                isRoll = Utility.getYesorNo("Apakah Anda ingin melakukan pengecekan history lainnya?");
                break;
                default:
                
            }
        }while(isRoll);
        fileReader.close();
        bReader.close();
    }



    void harian()throws IOException{
            file = new File("history.txt");
            fileReader = new FileReader(file);
            bReader = new BufferedReader(fileReader);
            
            String waktu = Utility.waktu();

            System.out.println(" ");

            
            String data = bReader.readLine();
            StringTokenizer token;

            while(data != null){

                if(data.toLowerCase().contains(waktu.toLowerCase())){
                token = new StringTokenizer(data,",");
     //           token.nextToken();
                System.out.printf("%-17s ", token.nextToken());
                System.out.printf(" %-40s ", token.nextToken());
                System.out.printf(" %15s ", token.nextToken());
                System.out.printf(" %3s ", token.nextToken());
                System.out.printf(" %3s", token.nextToken()+"\n");
                }
                
                data = bReader.readLine();
            }

    }    

    void bulanan()throws IOException{
        file = new File("history.txt");
        fileReader = new FileReader(file);
        bReader = new BufferedReader(fileReader);
        
        String waktu = Utility.bulanan();

        System.out.println(" ");
        
        String data = bReader.readLine();
        StringTokenizer token;

        while(data != null){

            if(data.toLowerCase().contains(waktu.toLowerCase())){
            token = new StringTokenizer(data,",");
 //           token.nextToken();
            System.out.printf("%-17s ", token.nextToken());
            System.out.printf(" %-40s ", token.nextToken());
            System.out.printf(" %15s ", token.nextToken());
            System.out.printf(" %3s ", token.nextToken());
            System.out.printf(" %3s", token.nextToken()+"\n");
            }
            
            data = bReader.readLine();
        }

}    

    void tahunan()throws IOException{
            file = new File("history.txt");
            fileReader = new FileReader(file);
            bReader = new BufferedReader(fileReader);
            
            uScanner = new Scanner(System.in);
            boolean isWrong = false;
            String waktu = "2021";
    
            do{
                System.out.print("Masukkan Tahun : ");
                waktu = uScanner.next();
                try{
                    Year.parse(waktu);
                }catch(Exception ex){
                    isWrong = true;
                    System.out.println("Format tahun Anda salah");
                }
            }while(isWrong);
            
            System.out.println(" ");
            
            String data = bReader.readLine();
            StringTokenizer token;

            while(data != null){

                if(data.toLowerCase().contains(waktu.toLowerCase())){
                token = new StringTokenizer(data,",");
     //           token.nextToken();
                System.out.printf("%-17s ", token.nextToken());
                System.out.printf(" %-40s ", token.nextToken());
                System.out.printf(" %15s ", token.nextToken());
                System.out.printf(" %3s ", token.nextToken());
                System.out.printf(" %3s", token.nextToken()+"\n");
                }
                
                data = bReader.readLine();
            }

            

    }    

}
