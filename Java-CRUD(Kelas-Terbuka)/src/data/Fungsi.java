package src.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Fungsi {
    
    public static void dataSemuaBuku() throws IOException {

        FileReader fileInput;
        BufferedReader bufferFile;

        try{
            fileInput = new FileReader("database.txt");
            bufferFile = new BufferedReader(fileInput);
        }catch(Exception e){
            System.out.println("Database tidak ditemukan\nSilahkan tambah data terlebih dahulu!\n");
            tambahData();
            return;
        }

        String data = bufferFile.readLine();
        StringTokenizer token = new StringTokenizer(data, ",");

        System.out.println("-----------------------------------------------------------------------------------------------");
        String [] array = {"No.","Tahun", "Nama", "Penerbit", "Judul"};
        System.out.printf("| %-3s | %-6s | %-25s | %-20s | %-20s \n", array[0],array[1],array[2],array[3],array[4] );
        System.out.println("-----------------------------------------------------------------------------------------------");

        int jumlahdata = 0;

        while (data != null){
            jumlahdata++;
            token = new StringTokenizer(data, ",");
            token.nextToken();
            System.out.printf("| %-3s ",jumlahdata);
            System.out.printf("| %-6s ",token.nextToken());
            System.out.printf("|%-25s ",token.nextToken());
            System.out.printf("|%-20s ",token.nextToken());
            System.out.printf("|%-20s \n",token.nextToken());
            data = bufferFile.readLine();
        }
        System.out.println("-----------------------------------------------------------------------------------------------");

    }

    public static void cariData() throws IOException {
        
        //cek data ada atau tidak
        try{
            File file = new File("database.txt");
        }catch(Exception e){
            System.out.println("data Anda tidak ditemukan\nSilahkan tambahkan data terlebih dahulu");
            return;
        }

        //ambil keywords
        Scanner userInput = new Scanner(System.in);
        System.out.print("\nMasukan kata kunci: ");
        String cariString = userInput.nextLine();

        String [] keywords = cariString.split("\\s");
        

        //cek data di database
        Dll.cekDataDabase(keywords, true);
    }

    public static void tambahData() throws IOException{
        
        //Mengambli input dari user
        FileWriter file = new FileWriter("database.txt",true);
        BufferedWriter bufferFile = new BufferedWriter(file);
        Scanner userInput = new Scanner (System.in);
        String penulis, judul, penerbit, tahun;

        System.out.print("\nMasukkan nama penulis: ");
        penulis = userInput.nextLine();
        System.out.print("Masukkan judul buku: ");
        judul = userInput.nextLine();
        System.out.print("Masukkan nama penerbit: ");
        penerbit = userInput.nextLine();
        System.out.print("Masukkan tahun terbit (YYYY): ");
        tahun = Dll.ambilTahun();

        //Melakukancek di database
        String cariString = tahun +" "+ penulis +" "+ penerbit +" "+ judul;
        String [] keywords = cariString.split("\\s");
//        String [] keywords = {tahun+", "+penulis+", "+penerbit+", "+judul};
        
        boolean isExist = Dll.cekDataDabase(keywords, false);
        
        //menulis data buku di database
        String penulisTanpaSpasi = penulis.replaceAll("\\s+", "");
        int noEntry = Dll.noEntryinYear(penulisTanpaSpasi, tahun) + 1 ;

        if(!isExist){
            
            
            String primaryKey = penulisTanpaSpasi+"_"+tahun+"_"+noEntry;

            System.out.println("Data yang akan Anda tambahkan adalah:");
            System.out.println("-------------------------------------");
            System.out.println("Primary key  : " + primaryKey);
            System.out.println("Tahun terbit : " + tahun);
            System.out.println("Penulis      : " + penulis);
            System.out.println("Penerbit     : " + penerbit);
            System.out.println("Judul        : " + judul);

            boolean isTambah = Dll.getYesroNo("\nApakah Anda ingin menambahkan buku ke database?");

            if(isTambah){
                //fiersabesari_2012_1,2012, fiersa besari, media kita, jejak langkah
                bufferFile.write(primaryKey+","+tahun+", "+penulis+", "+penerbit+", "+judul);
                bufferFile.newLine();
                bufferFile.flush();
            }


        }else{
            System.out.println("Buku yang ingin Anda tambahkan telah tersedia di database dengan data berikut");
            Dll.cekDataDabase(keywords, true);
        }


        
        bufferFile.close();
    }

    public static void updateData() throws IOException {
        
        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferInput = new BufferedReader(fileInput);

        File tempDB = new File("tempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        //tampilkan data
        System.out.println("Data yang tersimpan di database:");
        dataSemuaBuku();


        //Ambil Input User
        Scanner userInput = new Scanner(System.in);
        System.out.print("Masukan no data yang ingin Anda ubah: ");
        int numUpdate = userInput.nextInt();

        
        

        //tampilkan data yang akan di update
        //persiapan
        int entryCounts = 0;
        boolean notFound = true;

        //looping
        String data = bufferInput.readLine();
        while(data != null){
            boolean isUpdate = false;
            entryCounts++;

            StringTokenizer token = new StringTokenizer(data, ",");
            
            String [] fieldData = {"tahun","penulis","penerbit","judul"};
            String [] newData = new String [4];

            //tampilkan
            if(numUpdate == entryCounts){
                notFound = false;

                System.out.println("\nData yang ingin Anda ubah Adalah: ");
                System.out.println("--------------------------------------");
                System.out.println("Referensi  : " + token.nextToken());
                System.out.println("Tahun      : " + token.nextToken());
                System.out.println("Penulis    :" + token.nextToken());
                System.out.println("Penerbit   :" + token.nextToken());
                System.out.println("Judul      :" + token.nextToken());

                token = new StringTokenizer(data, ",");
                String oriData = token.nextToken();
                
                for(int i = 0; i < newData.length; i++){
                    boolean isYes = Dll.getYesroNo("Apakah anda ingin mengubah "+fieldData[i]+"?");
                    oriData = token.nextToken();
                    
                    if(isYes){

                        if(i == 0){
                            newData[i] = Dll.ambilTahun();
                        }else{
                            userInput = new Scanner(System.in);
                            System.out.print("\nMasukan "+ fieldData[i] +" baru: " );
                            newData[i] = " "+userInput.nextLine();
                        }

                    }else{
                        newData[i] = oriData;
                    }
                    
                }
                
                //Tampilkan data baru
                token = new StringTokenizer(data, ",");
                oriData = token.nextToken();
                System.out.println("data baru Anda adalah");
                System.out.println("------------------------------------------");
                System.out.println("Tahun    : "+ token.nextToken() + " --> " + newData[0]);
                System.out.println("Penulis  :"+ token.nextToken() + " --> " + newData[1]);
                System.out.println("Penerbit :"+ token.nextToken() + " --> " + newData[2]);
                System.out.println("Judul    :"+ token.nextToken() + " --> " + newData[3]);


                isUpdate = Dll.getYesroNo("Apakah Anda yakin ingin mengubah data?");

                if(isUpdate){
                    
                    boolean isExist = Dll.cekDataDabase(newData, false);

                    
                    

                    if(!isExist){
                        String tahun = newData[0];
                        String penulis = newData[1];
                        String penerbit = newData[2];
                        String judul = newData[3];

                        String penulisTanpaSpasi = penulis.replaceAll("\\s+", "");
                        int noEntry = Dll.noEntryinYear(penulisTanpaSpasi, tahun) + 1;

                        String primaryKey = penulisTanpaSpasi+"_"+tahun+"_"+noEntry;
                        bufferOutput.write(primaryKey+","+tahun+","+penulis+","+penerbit+","+judul);
                        bufferOutput.newLine();

                        System.out.println("Selamat data Anda telah berhasi diupdate");

                    }else{
                        System.out.println("Data buku sudah ada di database, proses update dibatalkan");
                        bufferOutput.write(data);
                        bufferOutput.newLine();
                    }

                }else{
                    bufferOutput.write(data);
                    bufferOutput.newLine();
                }

            }else{
                bufferOutput.write(data);
                bufferOutput.newLine();
            }

            

            
            data = bufferInput.readLine();
        }
        
        if(notFound){
            System.out.println("\nMaaf data yang Anda masukan tidak tersedia");
        }

        bufferOutput.flush();

        //close dulu semuanya
        fileInput.close();
        bufferInput.close();
        fileOutput.close();
        bufferOutput.close();

        System.gc();
        //hapus file database ori
        database.delete();
        //ganti nama database sementara
        tempDB.renameTo(database);



    }

    public static void hapusData()throws IOException{
        //ambil database ori
        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferInput = new BufferedReader(fileInput);

        //buat database sementara
        File tempDB = new File("TempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        //tampilkan data
        System.out.println("Data yang tersimpan di database:");
        dataSemuaBuku();

        //ambil input user untuk memilih data yang akan dihapus
        Scanner userInput = new Scanner(System.in);
        System.out.print("\nMasukan nomor buku yang ingin Anda hapus: ");
        int deleteNum = userInput.nextInt();


        //looping untuk membaca tiap data perbaris dan skip baca yang akan dihapus
        
        //persiapan
        int entryCounts = 0;

        //baca file
        String data = bufferInput.readLine();
        boolean notFound = false;

        //lakukan loop
        while(data != null){
            boolean isDelete = false;
            entryCounts++;

            StringTokenizer token = new StringTokenizer(data, ",");

            if(deleteNum == entryCounts){
                System.out.println("Data yang ingin Anda hapus adalah: ");
                System.out.println("-------------------------------------");
                System.out.println("Referensi   : "+token.nextToken());
                System.out.println("Tahun       : "+token.nextToken());
                System.out.println("Penulis     :"+token.nextToken());
                System.out.println("Penerbit    :"+token.nextToken());
                System.out.println("Judul       :"+token.nextToken());

                isDelete = Dll.getYesroNo("Apakah Anda yakin ingin menghapus data?");
                notFound = false;
            }else if(deleteNum > entryCounts){
                notFound = true;
            }

            if(isDelete){
                //Skip isDelete
                System.out.println("\nData Anda berhasil di hapus");
            }else{
                //kita pindahkan data yang lainnya
                bufferOutput.write(data);
                bufferOutput.newLine();
            }

            data = bufferInput.readLine();
        }

        if(notFound){
            System.out.println("\nMaaf Data yang Anda masukan tidak ditemukan");
        }

        //Menulis data file ke database sementara
        bufferOutput.flush();
        //close dulu semuanya
        fileInput.close();
        bufferInput.close();
        fileOutput.close();
        bufferOutput.close();

        System.gc();
        //hapus file database ori
        database.delete();
        //ganti nama database sementara
        tempDB.renameTo(database);

    }

}
