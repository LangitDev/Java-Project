package com.latihan;

import java.io.IOException;
import java.util.Scanner;
import addition.Utility;
import addition.Fungtion;

public class Main {
    public static void main(String[] args) throws IOException {

        boolean again = false;
        do{
            Utility.clearScreen();

            System.out.println("=====================");    
            System.out.println("== Weapon Database ==");
            System.out.println("=====================");

            System.out.println("[1] Weapon's List");
            System.out.println("[2] Search Weapon");
            System.out.println("[3] Add New Weapon");
            System.out.println("[4] Update Weapon");
            System.out.println("[5] Delete weapon");
            System.out.println("[6] Exit\n");

            System.out.print("Your Selected: ");
            
            Scanner uScanner = new Scanner(System.in);
            String userSelect = uScanner.next();
            uScanner.close();
                
            switch(userSelect){
                case "1":
                    System.out.println("========================");
                    System.out.println("     WEAPON's LIST");
                    System.out.println("========================");
                    
                    Fungtion.weaponList();
                    again = Utility.getYesorNo("Are you want to back Main menu?");
                    break;
                    
                case "2":
                    System.out.println("========================");
                    System.out.println("     SEARCH WEAPON");
                    System.out.println("========================");

                    Fungtion.searchWeapon();
                    again = Utility.getYesorNo("Are you want to back Main menu?");
                    break;

                case "3":
                    Fungtion.addWeapon();
                    again = Utility.getYesorNo("Are you want to back Main menu?");
                    break;
                case "4":
                    Fungtion.updateWeapon();                     
                    again = Utility.getYesorNo("Are you want to back Main menu?");
                    break;
                case "5":
                    Fungtion.deleteWeapon();
                    again = Utility.getYesorNo("Are you want to back Main menu?");
                    break;
                case "6":
                    boolean exit = Utility.getYesorNo("Are you want to Exit?");
                    if(exit){
                        again = false;
                        break;
                    }else{
                        again = true;
                    }
                default:
                    again = true;
            }
        }while(again);
    }
    
}
