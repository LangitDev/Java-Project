package com.warung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Penjualan {
    FileWriter fWriter = new FileWriter("history.txt",true);
    BufferedWriter bWriter = new BufferedWriter(fWriter);
    FileWriter totalWriter = new FileWriter("totalPenjualan.txt",true);
    BufferedWriter totalBuffer = new BufferedWriter(totalWriter);
    Scanner uScanner,uScanInt;
    ArrayList<String> tanggallList = new ArrayList<String>();
    ArrayList<String>bufferList = new ArrayList<String>();
    ArrayList<String>item = new ArrayList<String>();
    ArrayList<String>cost = new ArrayList<String>();
    ArrayList<Integer>value = new ArrayList<Integer>();
    ArrayList<Integer>totalCost = new ArrayList<Integer>();
    
    Penjualan() throws IOException {
        Scanner scanInt = new Scanner(System.in);
        String tanggal = Utility.waktu();
        tanggallList.add(tanggal);
        
        

        boolean isOkay = true;
        do{
            Utility.clearScreen();
            System.out.println("Selamat datang di Langit Store!");
            String nama;
            String nomor = "Code", namaBarang = "Nama Barang",hargaE = "Harga", jumlah = "Jumlah", totalnya = "Total";
            int num = 0;
            int total = 0;


            do{
                total = 0;
                num = 0;
                System.out.println("\n--------------------------------------------------------------------------------");
                System.out.printf("| %5s | %-30s  | %8s   | %6s   | %8s   |\n", nomor, namaBarang, hargaE, jumlah, totalnya);
                System.out.println("--------------------------------------------------------------------------------");
        
                for(int i = 0 ; i < item.size() ;i++){
                    num++;    
                    System.out.printf("| %4s  ", num);
                    System.out.printf("| %-30s  ",item.get(i));
                    System.out.printf("| %8s   ",cost.get(i));
                    System.out.printf("| %4s     ",value.get(i));
                    System.out.printf("| %8s   |\n", totalCost.get(i));

                    total = total + totalCost.get(i);
                }

                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("| %61s   | %8s   |\n",totalnya, total);
                System.out.println("--------------------------------------------------------------------------------");

                
                System.out.print("\nNama barang : ");
                uScanner = new Scanner(System.in);
                nama = uScanner.nextLine();
                daftar(nama);  

            }while(!nama.equalsIgnoreCase("break"));


            //Pembatalan
            
            boolean isCencel = Utility.getYesorNo("Apakah ada yang ingin Anda ubah?");
            
            while(isCencel){
                System.out.print("1 = Hapus   2 = Ganti   3 = Tambah   4 = Batal       Pilihan Anda: ");
                int pilihanAnda = scanInt.nextInt();
                if(pilihanAnda == 1){                    
                    System.out.print("Masukan kode item:");
                    int codeCencel = scanInt.nextInt();
                    item.remove(codeCencel-1); cost.remove(codeCencel-1); value.remove(codeCencel-1); totalCost.remove(codeCencel-1);
                }else if(pilihanAnda == 2){                    
                    System.out.print("Masukan kode item:");
                    int codeCencel = scanInt.nextInt();
                    item.remove(codeCencel-1); cost.remove(codeCencel-1); value.remove(codeCencel-1); totalCost.remove(codeCencel-1);
                    
                    System.out.print("nama barang : ");
                    uScanner = new Scanner(System.in);
                    nama = uScanner.nextLine();
                    daftar(nama);
                }else if(pilihanAnda == 3){
                    System.out.print("nama barang : ");
                    uScanner = new Scanner(System.in);
                    nama = uScanner.nextLine();
                    daftar(nama);
                }else{

                }

                Utility.clearScreen();
                System.out.println("\nDaftar Belanja Anda: ");
                System.out.println("\n--------------------------------------------------------------------------------");
                System.out.printf("| %5s | %-30s  | %8s   | %6s   | %8s   |\n", nomor, namaBarang, hargaE, jumlah, totalnya);
                System.out.println("--------------------------------------------------------------------------------");
                
                num = 0;
                total = 0;

                for(int i = 0 ; i < item.size() ;i++){
                    num++;
                    System.out.printf("| %4s  ", num);
                    System.out.printf("| %-30s  ",item.get(i));
                    System.out.printf("| %8s   ",cost.get(i));
                    System.out.printf("| %4s     ",value.get(i));
                    System.out.printf("| %8s   |\n", totalCost.get(i));
    
                    total = total + totalCost.get(i);
                }
    
                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("| %61s   | %8s   |\n",totalnya, total);
                System.out.println("--------------------------------------------------------------------------------");
                    
                isCencel = Utility.getYesorNo("Apakah ada yang ingin Anda ubah?");
            }

            System.out.println("--------------------------------------------------------------------------------");
            String bayarString = "Bayar", kembaliannya = "Kembalian";
            System.out.printf("| %61s   | %s  ",bayarString, " ");
            int bayar = uScanner.nextInt();
            System.out.println("--------------------------------------------------------------------------------");
            int kembalian = bayar - total;
            System.out.printf("| %61s   | %8s   \n",kembaliannya, kembalian);
            System.out.println("--------------------------------------------------------------------------------");



            System.out.println("\nTerima Kasih telah berbelanja di tempat kami.");

            
            
            total = 0;
            for(int i = 0 ; i < item.size() ; i++){
                
                bWriter.write(tanggallList.get(0)+","+item.get(i)+","+cost.get(i)+",*  "+value.get(i)+",=  "+totalCost.get(i));
                bWriter.newLine();
                total = total + totalCost.get(i);
            }
            
            bWriter.write(tanggallList.get(0)+","+"---------------------------------------"+","+"Total Belanja"+",-- "+"="+",-- "+String.valueOf(total));
            bWriter.newLine();
            bWriter.write("   "+","+"   "+","+"   "+","+"   "+","+"   "+","+tanggallList.get(0));
            bWriter.newLine();
            bWriter.flush();
            totalBuffer.write(tanggallList.get(0)+","+String.valueOf(total));
            totalBuffer.newLine();
            totalBuffer.flush();

            isOkay = Utility.getYesorNo("Apakah Anda ingin membeli lagi?");
            item.clear();
            cost.clear();
            value.clear();
            totalCost.clear();
        }while(isOkay);
        
        totalWriter.close();
        totalBuffer.close();
        fWriter.close();
        bWriter.close();
        tanggallList.clear();

        
    }




    void daftar(String nama) throws IOException{
        
        BufferedReader fileInput = new BufferedReader(new FileReader("database.txt"));
        String keywords [] = nama.split("\\s");
        String data = fileInput.readLine();

        boolean isExist;
        int num = 0;
        boolean jual = false;

        if(nama.equalsIgnoreCase("break")){
            fileInput.close();
            
            return;
        }else{
            System.out.println("\n---------------------------------------------------------------");
            String nomor = "Code", namaBarang = "Nama Barang",hargaE = "Harga Ecer";
            System.out.printf("| %5s | %-30s  | %15s   |\n", nomor, namaBarang, hargaE);
            System.out.println("---------------------------------------------------------------");
        }

        //melakukan cek data
        while (data != null){

            isExist = true;
            for(String key : keywords){
                isExist = isExist && data.toLowerCase().contains(key.toLowerCase());             
            }
            
            if(isExist){
                num++;

                StringTokenizer token = new StringTokenizer(data,",");
                String item, ecerCost;
                item = token.nextToken();
                ecerCost = token.nextToken();
                token.nextToken();
                System.out.printf("| %4s  ",num);
                System.out.printf("| %-30s  ",item);
                System.out.printf("| %15s   |\n",ecerCost);

                bufferList.add(data);
                jual = true;
                
            }


            data = fileInput.readLine();
        }

        System.out.println("---------------------------------------------------------------");

        if(jual){
            
            if(num == 1){
                StringTokenizer token = new StringTokenizer(bufferList.get(0),",");
                String barang = token.nextToken();
                String harga = token.nextToken();
                System.out.println("\nNama: "+barang+" --> harga: "+harga);
                System.out.print("Jumlah barang: ");
                int jumlah = uScanner.nextInt();
                int total = jumlah * Integer.valueOf(harga);
                item.add(barang);
                cost.add(harga);
                value.add(jumlah);
                totalCost.add(total);
                Utility.clearScreen();
                System.out.println(barang+ " " + harga +" * "+ jumlah + " = " + total + "--- berhasil dibeli------------");

            }else if(num > 1){
                uScanInt = new Scanner(System.in);
                System.out.print("\nmasukan code: ");
                int code = uScanInt.nextInt();
                if(code <= num && code > 0){
                    StringTokenizer token = new StringTokenizer(bufferList.get(code-1),",");
                    String barang = token.nextToken();
                    String harga = token.nextToken();
                    System.out.println("\nNama: "+barang+" --> harga: "+harga);
                    System.out.print("\nJumlah barang: ");
                    int jumlah = uScanner.nextInt();
                    int total = jumlah * Integer.valueOf(harga);
                    item.add(barang);
                    cost.add(harga);
                    value.add(jumlah);
                    totalCost.add(total);   
                    Utility.clearScreen();
                    System.out.println(barang+ " " + harga +" * "+ jumlah + " = " + total + "--- berhasil dibeli------------");    

                }else{
                    System.out.println("code yang Anda masukan salah, barang tidak tersedia!");
                    Utility.clearScreen();
                }
            }
        }else{
            System.out.println("barang tidak tersedia!!!");
            
        }

        
        bufferList.clear();
        fileInput.close();

    }
}
