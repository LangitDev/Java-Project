package com.database;

import java.io.IOException;
import java.util.Scanner;

import com.warung.Utility;

public class SearchItem {
    SearchItem() throws IOException{
        boolean back = false;
        do{        
            
            Utility.clearScreen();
            
            System.out.print("Masukan kata kunci: ");
            Scanner uScanner = new Scanner(System.in);
            String key = uScanner.nextLine();

            String [] keyword = key.split("\\s");
            
            Dll.cekDatabase(keyword, true);

            back = Utility.getYesorNo("Apakah Anda ingin kembali ke Menu Database?");
        }while(!back);

    }
}
