package com.database;

import java.io.IOException;
import java.util.Scanner;

import com.warung.Utility;

import jdk.jshell.execution.Util;

public class DatabaseItem {
    public DatabaseItem() throws IOException{

        boolean back = false;
        do{
            Utility.clearScreen();
            System.out.println("=====================");
            System.out.println("[1] List Item");
            System.out.println("[2] Search Item");
            System.out.println("[3] Add New Item");
            System.out.println("[4] Update Item");
            System.out.println("[5] Delete Item");
            System.out.println("[6] Main Menu");

            Scanner uScanner = new Scanner(System.in);
            System.out.print("\nPilihan Anda: ");
            String user = uScanner.next();

            switch(user){
                case "1":
                new ListItem();
                break;
                case "2":
                new SearchItem();
                break;
                case "3":
                new AddItem();
                break;
                case "4":
                new UpdateItem();
                break;
                case "5":
                new DeleteItem();
                break;
                case "6":
                back = true;
                break;
                default:
            }

        }while(!back);


    }
    
}
