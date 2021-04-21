package com.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.StringTokenizer;


public class Dll {

    public static boolean cekDatabase(String [] keywords, boolean isDisplay) throws IOException{
        FileReader fileReader;
        BufferedReader bufferedReader;
        try{
            fileReader = new FileReader("database.txt");
            bufferedReader = new BufferedReader(fileReader);
        }catch(Exception ex){
            System.out.println(ex);
            return true;
        }


        String data = bufferedReader.readLine();
        StringTokenizer token;
        String item, ecerCost, partaiCost;
        boolean isExist = false;
        boolean notFound = true;
        int num = 0;

        if(isDisplay){

            System.out.println("-----------------------------------------------------------------------------------");
            String nomor = "Index", nama = "Nama Barang",hargaE = "Harga Ecer",hargaP = "harga Partai";
            System.out.printf("| %5s | %-30s  | %15s   | %15s   |\n", nomor, nama, hargaE, hargaP);
            System.out.println("-----------------------------------------------------------------------------------");
        }

        while(data != null){
            
            num++;
            isExist = true;
            
            for(String key : keywords){
                isExist = isExist && data.toLowerCase().contains(key.toLowerCase());
            }

            if(isExist){
                if(isDisplay){
                    
                    token = new StringTokenizer(data,",");
                    item = token.nextToken();
                    ecerCost = token.nextToken();
                    partaiCost = token.nextToken();
                    System.out.printf("| %4s  ",num);
                    System.out.printf("| %-30s  ",item);
                    System.out.printf("| %15s   ",ecerCost);
                    System.out.printf("| %15s   |\n",partaiCost);
                    notFound = false;
                }else{
                    System.out.println("Data telah tersedia di database");
                    break;
                }
            }

            data = bufferedReader.readLine();
        }
        System.out.println("-----------------------------------------------------------------------------------");

        

        if(notFound && isDisplay){
            System.out.println("Data tidak ditemukan");
        }



        fileReader.close();
        bufferedReader.close();

        
        return isExist;

    }
    
}
