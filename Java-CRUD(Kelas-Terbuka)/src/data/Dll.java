package src.data;

import java.time.Year;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Dll {
    
    protected static int noEntryinYear(String penulis, String tahun) throws IOException{
        FileReader fileInput = new FileReader("database.txt");
        BufferedReader bufferFile = new BufferedReader(fileInput);

        int entry = 0;
        String data = bufferFile.readLine();
        Scanner dataScanner;
        String primaryKey;

        while(data != null){
            dataScanner = new Scanner(data);
            dataScanner.useDelimiter(",");
            primaryKey = dataScanner.next();
            dataScanner = new Scanner(primaryKey);
            dataScanner.useDelimiter("_");

            if(penulis.equalsIgnoreCase(dataScanner.next()) && tahun.equalsIgnoreCase(dataScanner.next()) ){
                entry = dataScanner.nextInt();
            }
            data = bufferFile.readLine();

        }

        return entry;
    }

    protected static boolean cekDataDabase(String [] keywords, boolean isDisplay) throws IOException{
        FileReader fileInput = new FileReader("database.txt");
        BufferedReader fileBuffer = new BufferedReader(fileInput);

        String data = fileBuffer.readLine();
        int jumlahData = 0;
        boolean isExist, notFound;

            if(isDisplay){
            System.out.println("-----------------------------------------------------------------------------------------------");
            String [] array = {"No.","Tahun", "Nama", "Penerbit", "Judul"};
            System.out.printf("| %-3s | %-6s | %-25s | %-20s | %-20s \n", array[0],array[1],array[2],array[3],array[4] );
            System.out.println("-----------------------------------------------------------------------------------------------");
            }
        
        isExist = false;        
        notFound = true;

        while(data != null){
            
            //cek keywords di dalam baris
            isExist = true;
            jumlahData++;

            //langkah 1 cek apakah data ada dalam baris dengan true atau false
            //harus di kalikan denga  && terlebih dahulu, agar memperoleh hasil akhir yang benar
            //jika tidak, hasil akhir akan mengikuti hasil dari hasil kata terakhir.
            for(String isiArraykeyword : keywords){
                isExist = isExist && data.toLowerCase().contains(isiArraykeyword.toLowerCase());
            }


            if(isExist){
                if(isDisplay){
                    StringTokenizer token = new StringTokenizer(data, ",");
                    token.nextToken();
                    System.out.printf("| %-3s ",jumlahData);
                    System.out.printf("| %-6s ",token.nextToken());
                    System.out.printf("|%-25s ",token.nextToken());
                    System.out.printf("|%-20s ",token.nextToken());
                    System.out.printf("|%-20s \n",token.nextToken());
                    notFound = false;
                }else{
                    break;
                }
            }
            data = fileBuffer.readLine();
        }
        
        if(isDisplay){
            String kata = "Maaf kata kunci yang Anda masukan tidak ditemukan";
            if(notFound == true){
                System.err.printf("|         %-74s     \n", kata);
            }
        
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
        return isExist;
    }
    
    public static void clearScreen() throws IOException{
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        }catch (Exception ex){
            System.err.println("tidak bisa Clear Screen");
        }
    }

    static String ambilTahun()throws IOException{
        Scanner userInput = new Scanner(System.in); 
        String tahun = userInput.nextLine();       
        boolean tahuninValid = true;

        while(tahuninValid){
            try{
                Year.parse(tahun);
                tahuninValid = false;
            }catch(Exception ex){
                System.out.print("Format tahun yang Anda masukan salah!\nMasukan ulang tahun terbit (YYYY): ");
                tahun = userInput.nextLine();
            }
        }

        return tahun;
    }

    public static boolean getYesroNo(String message){
        Scanner userInput = new Scanner(System.in);
        System.out.print("\n"+message + "(y/n) ");
        String jawabanUser = userInput.next();

        while(!jawabanUser.equalsIgnoreCase("y")&&!jawabanUser.equalsIgnoreCase("n")){
            System.out.print("Jawaban Anda bukan y atau n. \nSilahkan masukan (y/n) ");
            jawabanUser = userInput.next();
        }

        return jawabanUser.equalsIgnoreCase("y");
    }
}
