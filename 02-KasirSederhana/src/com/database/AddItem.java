package com.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.warung.Utility;

public class AddItem {

    AddItem() throws IOException{
        FileWriter fileWriter = new FileWriter("database.txt",true);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        boolean back = false;
        do{        
            
            Utility.clearScreen();
            
            Scanner uScanner = new Scanner(System.in);
            String item, ecerCost, partaiCost;

            System.out.print("Nama Barang: ");
            item = uScanner.nextLine();
            System.out.print("Harga Ecer: ");
            ecerCost = uScanner.next();
            System.out.print("Harga Partai: ");
            partaiCost = uScanner.next();
            
            String key = item+" "+ecerCost+" "+partaiCost;

            String [] keyword = key.split("\\s");
                        
            boolean isExist = Dll.cekDatabase(keyword, false);
            
            if(isExist){
                System.out.println("Dengan format sebagai berikut");
                Dll.cekDatabase(keyword, true);
            }else{
                System.out.println("Data yang ingin Tambahkan adalah");

                System.out.println("---------------------------------------------------------------------------");
                String nama = "Nama Barang",hargaE = "Harga Ecer",hargaP = "harga Partai";
                System.out.printf("| %-30s  | %15s   | %15s   |\n", nama, hargaE, hargaP);
                System.out.println("---------------------------------------------------------------------------");
                System.out.printf("| %-30s  ",item);
                System.out.printf("| %15s   ",ecerCost);
                System.out.printf("| %15s   |\n",partaiCost);
                System.out.println("---------------------------------------------------------------------------");

                boolean isAdd = Utility.getYesorNo("Apakah Anda yakin ingin menambahkan data?");
                if(isAdd){
                    bWriter.write(item+","+ecerCost+","+partaiCost);
                    bWriter.newLine();
                    System.out.println("\nData Anda berhasil ditambahkan");
                }else{
                    System.out.println("\nPenambahan data dibatalkan");
                }

            }

            bWriter.flush();

            back = Utility.getYesorNo("Apakah Anda ingin kembali ke Menu Database?");
        }while(!back);

        bWriter.close();
        fileWriter.close();



    }
    
}
