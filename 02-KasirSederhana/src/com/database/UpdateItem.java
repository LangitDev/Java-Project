package com.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.warung.Utility;

public class UpdateItem {
    File database;
    FileReader fileReader;
    BufferedReader bReader;
    File tempDB;
    FileWriter fileWriter;
    BufferedWriter bWriter;

    UpdateItem() throws IOException{
        database = new File("database.txt");
        fileReader = new FileReader(database);
        bReader = new BufferedReader(fileReader);
        tempDB = new File("tempDB.txt");
        fileWriter = new FileWriter(tempDB);
        bWriter = new BufferedWriter(fileWriter);
        boolean back = false;
        do{        
            
            Utility.clearScreen();
            
            System.out.print("Masukan kata kunci Data yang ingin Anda ganti (Klik Enter untuk semua data): ");
            Scanner uScanner = new Scanner(System.in);
            String key = uScanner.nextLine();

            String [] keyword = key.split("\\s");
            
            Dll.cekDatabase(keyword, true);

            //masukan no item yg ingin di hapus
            uScanner = new Scanner(System.in);
            
            System.out.print("Masukan no. Index dari data yang ingin Anda ganti: ");
            int num = uScanner.nextInt();
            int entry = 0;

            String data = bReader.readLine();
            StringTokenizer token;
            String item, ecerCost, partaiCost;
    

            while(data != null){
                entry++;

                if(num == entry){
                    token = new StringTokenizer(data,",");
                    item = token.nextToken();
                    ecerCost = token.nextToken();
                    partaiCost = token.nextToken();
                    System.out.println("\nData yang ingin Anda ubah:");

                    System.out.println("-----------------------------------------------------------------------------------");
                    String nomor = "Index", nama = "Nama Barang",hargaE = "Harga Ecer",hargaP = "harga Partai";
                    System.out.printf("| %5s | %-30s  | %15s   | %15s   |\n", nomor, nama, hargaE, hargaP);
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.printf("| %4s  ",entry);
                    System.out.printf("| %-30s  ",item);
                    System.out.printf("| %15s   ",ecerCost);
                    System.out.printf("| %15s   |\n",partaiCost);
                    System.out.println("-----------------------------------------------------------------------------------");
                    
                    System.out.println("\nData Baru Anda : "+item);
                    String hargaEcer, hargaPartai;
                    uScanner = new Scanner(System.in);
                    System.out.print("Harga Ecer: ");
                    hargaEcer = uScanner.nextLine();
                    System.out.print("Harga Partai: ");
                    hargaPartai = uScanner.nextLine();
                    System.out.println(" ");
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Nama Barang  : " + item);
                    System.out.println("Harga Ecer   : " + ecerCost+ " --> " + hargaEcer);
                    System.out.println("Harga Partai : " + partaiCost + " --> " + hargaPartai);
                    System.out.println("-----------------------------------------------------------------------------------");

                    boolean isUpdate = Utility.getYesorNo("\nApakah Anda yakin ingin mengubah data?");
                    
                    if(isUpdate){
                        bWriter.write(item+","+hargaEcer+","+hargaPartai);
                        System.out.println("\nSelamat data Anda berhasil diubah");

                    }else{
                        bWriter.write(data);
                    }

                }else{
                    bWriter.write(data);
                }

                bWriter.newLine();
                data = bReader.readLine();
            }
            
            bReader.close();
            bWriter.close();
            fileReader.close();
            fileWriter.close();
            
            System.gc();

            database.delete();
            tempDB.renameTo(database);








            back = Utility.getYesorNo("Apakah Anda ingin kembali ke Menu Database?");
        }while(!back);

    }
}
