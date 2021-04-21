package com.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.warung.Utility;

public class ListItem {

    ListItem() throws IOException {
        
        FileReader fileReader;
        BufferedReader bufferedReader;
        boolean back = false;
        do{
            Utility.clearScreen();        
            try{
                fileReader = new FileReader("database.txt");
                bufferedReader = new BufferedReader(fileReader);
            }catch(Exception ex){
                System.out.println(ex);
                return;
            }


            String data = bufferedReader.readLine();
            StringTokenizer token;
            String item, ecerCost, partaiCost;

            System.out.println("---------------------------------------------------------------------------");
            String nama = "Nama Barang",hargaE = "Harga Ecer",hargaP = "harga Partai";
            System.out.printf("| %-30s  | %15s   | %15s   |\n", nama, hargaE, hargaP);
            System.out.println("---------------------------------------------------------------------------");

            while(data != null){
                
                token = new StringTokenizer(data,",");
                item = token.nextToken();
                ecerCost = token.nextToken();
                partaiCost = token.nextToken();

                System.out.printf("| %-30s  ",item);
                System.out.printf("| %15s   ",ecerCost);
                System.out.printf("| %15s   |\n",partaiCost);

                data = bufferedReader.readLine();
            }

            System.out.println("---------------------------------------------------------------------------");
            back = Utility.getYesorNo("Apakah Anda ingin kembali ke Menu Database?");
        }while(!back);
        fileReader.close();
        bufferedReader.close();

    }

    
}
