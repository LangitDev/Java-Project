package com.warung;

import java.io.IOException;
import java.util.Scanner;
import com.database.*;

public class Warung{
    public static void main(String[] args) throws IOException {
        Scanner uScanner = new Scanner(System.in);
        boolean isRoll = true;
        do{
            Utility.clearScreen();
            System.out.println("========================");
            System.out.println("===     WarungKu     ===");
            System.out.println("========================");
            System.out.println("[1] Penjualan");
            System.out.println("[2] History Penjualan");
            System.out.println("[3] Pendapatan");
            System.out.println("[4] Edit Barang");
            System.out.println("[5] Exit");

            System.out.print("\nYour Input: ");
            
            String inputUser = uScanner.next();

            switch(inputUser){
                case "1":
                new Penjualan();
                break;
                case "2":
                new History();
                break;
                case "3":
                new Pendapatan();
                break;
                case "4":
                new DatabaseItem();
                break;
                case "5":
                    isRoll = false;
                break;
                default:
            }
            
            

        }while(isRoll);
        uScanner.close();
    }

}