package com.warung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pendapatan {
    FileReader fileReader;
    BufferedReader bReader;
    Scanner uScanner;

    Pendapatan() throws IOException{
        
        boolean isRoll = true;
        do{
            Utility.clearScreen();
            System.out.println("========================");
            System.out.println("[1] Pendapatan Harian");
            System.out.println("[2] Pendapatan Bulanan");
            System.out.println("[3] Pendapatan Tahunan");
            System.out.println("========================");

            System.out.print("Pilihan Anda: ");
            uScanner = new Scanner(System.in);
            String user = uScanner.next();
            switch(user){
                case "1":
                harian();
                isRoll = Utility.getYesorNo("Apakah Anda ingin melakukan pengecekan pendapatan lainnya?");
                break;
                case "2":
                bulanan();
                isRoll = Utility.getYesorNo("Apakah Anda ingin melakukan pengecekan pendapatan lainnya?");
                break;
                case "3":
                tahunan();
                isRoll = Utility.getYesorNo("Apakah Anda ingin melakukan pengecekan pendapatan lainnya?");
                break;
                default:
                
            }
        }while(isRoll);
        fileReader.close();
        bReader.close();
    }

    void harian() throws IOException{
        try{
            fileReader = new FileReader("totalPenjualan.txt");
            bReader = new BufferedReader(fileReader);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        String waktu = Utility.waktu();
        String data = bReader.readLine();
        StringTokenizer token;
        int totalIncome = 0;
        System.out.println("-------------");
        System.out.println(waktu);
        System.out.println("-------------");
        while(data != null){

            if(data.toLowerCase().contains(waktu.toLowerCase())){
                token = new StringTokenizer(data,",");
                token.nextToken();
                int income = Integer.valueOf(token.nextToken());
                System.out.printf("%13s\n",income);
                totalIncome = totalIncome + income;
            }

            data = bReader.readLine();
        }
        
        System.out.println("------------- +");
        System.out.printf("%13s\n",totalIncome);

    }

    void bulanan() throws IOException{
        try{
            fileReader = new FileReader("totalPenjualan.txt");
            bReader = new BufferedReader(fileReader);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        String waktu = Utility.bulanan();
        String data = bReader.readLine();
        StringTokenizer token;
        int totalIncome = 0;
        System.out.println("-----------------------------");
        while(data != null){

            if(data.toLowerCase().contains(waktu.toLowerCase())){
                token = new StringTokenizer(data,",");
                String tgl = token.nextToken();
                int income = Integer.valueOf(token.nextToken());
                System.out.printf("%-17s",tgl);
                System.out.printf("%13s\n",income);
                totalIncome = totalIncome + income;
            }

            data = bReader.readLine();
        }
        
        System.out.println("------------------------------+");
        System.out.printf("                 %13s\n",totalIncome);

    }
    
    void tahunan() throws IOException{
        try{
            fileReader = new FileReader("totalPenjualan.txt");
            bReader = new BufferedReader(fileReader);
        }catch(Exception ex){
            System.out.println(ex);
        }
        
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
        


        String data = bReader.readLine();
        StringTokenizer token;
        int totalIncome = 0;
        System.out.println("\n-----------------------------");
        while(data != null){

            if(data.toLowerCase().contains(waktu.toLowerCase())){
                token = new StringTokenizer(data,",");
                String tgl = token.nextToken();
                int income = Integer.valueOf(token.nextToken());
                System.out.printf("%-17s",tgl);
                System.out.printf("%13s\n",income);
                totalIncome = totalIncome + income;
            }

            data = bReader.readLine();
        }
        
        System.out.println("------------------------------+");
        System.out.printf("                 %13s\n",totalIncome);
    }

}

