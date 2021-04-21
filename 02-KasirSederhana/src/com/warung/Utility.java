package com.warung;

import java.io.IOException;
import java.time.Year;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Utility {

    public static boolean getYesorNo(String message){
        System.out.print("\n"+message+" (y/n) ");
        Scanner scan = new Scanner(System.in);
        String user = scan.next();
        if(user.equalsIgnoreCase("y")){
            return true;
        }


        return false;
    }

    public static void clearScreen()throws IOException{
        try{
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }

        }catch(Exception ex){
            System.err.println(ex);
        }
    }

    public static String waktu() throws IOException{          
        Scanner uScanner = new Scanner(System.in);
        boolean ulang = false;
        String tgl, bulan = "a", tahun = "a";
        int tanggal = 0;
        do{
        clearScreen();
        System.out.print("Masukan (tanggal bulan tahun) : ");
        tgl = uScanner.nextLine();
        StringTokenizer token = new StringTokenizer(tgl," ");
        try{
            ulang = false;
            boolean testTanggal = false;
            boolean testBulan = false;
            tanggal = Integer.valueOf(token.nextToken());
            bulan = token.nextToken();
            tahun = token.nextToken();


            if(tanggal > 0 && tanggal < 32){ testTanggal = true; }
            
            if(bulan.toLowerCase().contains("januari")||bulan.toLowerCase().contains("februari")||bulan.toLowerCase().contains("maret")||bulan.toLowerCase().contains("april")||
            bulan.toLowerCase().contains("mei")||bulan.toLowerCase().contains("juni")||bulan.toLowerCase().contains("juli")||bulan.toLowerCase().contains("agustus")||bulan.toLowerCase().contains("september")||
            bulan.toLowerCase().contains("oktober")||bulan.toLowerCase().contains("november")||bulan.toLowerCase().contains("desember")){ testBulan = true;}
            
            Year.parse(tahun);
            
            if(!testTanggal||!testBulan){
                System.out.println("Maaf format yang anda Masukan salah.");
                ulang =  true;
            }
        
        }catch(Exception exception){
            System.out.println("Maaf format yang anda Masukan salah.");
            ulang = true;
        }
        
        }while(ulang);
        return tanggal+" "+bulan+" "+tahun;
    }

    public static String bulanan() throws IOException{
        Scanner uScanner = new Scanner(System.in);
        boolean ulang = false;
        String tgl, bulan = "a", tahun = "a";
        do{
        clearScreen();
        System.out.print("Masukan (bulan tahun) : ");
        tgl = uScanner.nextLine();
        StringTokenizer token = new StringTokenizer(tgl," ");
        try{
            ulang = false;
            boolean testBulan = false;
            bulan = token.nextToken();
            tahun = token.nextToken();
            
            if(bulan.toLowerCase().contains("januari")||bulan.toLowerCase().contains("februari")||bulan.toLowerCase().contains("maret")||bulan.toLowerCase().contains("april")||
            bulan.toLowerCase().contains("mei")||bulan.toLowerCase().contains("juni")||bulan.toLowerCase().contains("juli")||bulan.toLowerCase().contains("agustus")||bulan.toLowerCase().contains("september")||
            bulan.toLowerCase().contains("oktober")||bulan.toLowerCase().contains("november")||bulan.toLowerCase().contains("desember")){ testBulan = true;}
            
            Year.parse(tahun);
            
            if(!testBulan){
                System.out.println("Maaf format yang anda Masukan salah.");
                ulang =  true;
            }
        
        }catch(Exception exception){
            System.out.println("Maaf format yang anda Masukan salah.");
            ulang = true;
        }
        
        }while(ulang);
        return bulan+" "+tahun;
    }
}
