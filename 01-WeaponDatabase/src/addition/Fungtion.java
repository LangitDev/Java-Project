package addition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Fungtion {

    public static void weaponList() throws IOException {
        FileReader file;
        BufferedReader bufferInput;
        try{
            file = new FileReader("database.txt");
            bufferInput = new BufferedReader(file);
        }catch(Exception ex){
            System.out.println("Sorry, your data is not Found. Please Add data first!");
            addWeapon();
            return;
        }

        String data = bufferInput.readLine();
        StringTokenizer token;
        long number = 0;
        String [] field = {"No.","Name","Rank","Type","Attack","Combination"};
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("| %4s |  %-25s |  %-10s |  %-10s |  %-7s |  %s\n",field[0],field[1],field[2],field[3],field[4],field[5]);
        System.out.println("------------------------------------------------------------------------------------------------");
        while(data != null){
        token = new StringTokenizer(data, ",");
        String combination = token.nextToken();
        number++;
        System.out.printf("| %4d ", number);
        System.out.printf("|  %-25s ", token.nextToken());
        System.out.printf("|  %-10s ", token.nextToken());
        System.out.printf("|  %-10s ", token.nextToken());
        System.out.printf("|  %-7s ", token.nextToken());
        System.out.println("|  "+ combination);
        
        data = bufferInput.readLine();

        }

        file.close();
        bufferInput.close();

    }

    public static void searchWeapon() throws IOException {

        //cek data
        try{
            File file = new File("database.txt");
        }catch(Exception ex){
            System.out.println(ex);
        }

        //Masukkan keywords user
        Scanner uScanner = new Scanner(System.in);
        System.out.print("input your keywords : ");
        String key = uScanner.nextLine();
        String [] keywords = key.split("\\s");
        uScanner.close();

        //cek data ke database
        Utility.cekDatabase(keywords, true);
    }

    public static void addWeapon() throws IOException {
        
        //cek data
        FileWriter fileOut;
        BufferedWriter bufferOutput;
        try{
            fileOut = new FileWriter("database.txt", true);
            bufferOutput = new BufferedWriter(fileOut);
        }catch(Exception ex){
            System.out.println(ex);
            return;
        }
            //masukan input user
        System.out.println("input your weapon's data here:");
        Scanner uScanner = new Scanner(System.in);
        String name, rank, type, attack, combination;
        System.out.print("Weapon's name : ");
        name = uScanner.nextLine();
        System.out.print("Weapon's rank : ");
        rank = uScanner.nextLine();
        System.out.print("Weapon's type : ");
        type = uScanner.nextLine();
        System.out.print("Weapon's attack : ");
        attack = uScanner.nextLine();
        System.out.print("Weapon's combination : ");
        combination = uScanner.nextLine();

        System.out.println("\nYour new weapon's data:");
        System.out.println("--------------------------------");
        System.out.println("Weapon's name    : " + name);
        System.out.println("--------------------------------");
        System.out.println("Weapon's rank    : " + rank);
        System.out.println("Weapon's type    : " + type);
        System.out.println("Weapon's attack  : " + attack);
        System.out.println("--------------------------------");
        System.out.println("Combination code : "+combination);
        System.out.println("--------------------------------");

        boolean isAdd = Utility.getYesorNo("Are you really want to Add this data?");
        if(isAdd){
            String [] keywords = {combination, name, rank, type, attack};
            boolean isExist = Utility.cekDatabase(keywords, false);            

            if(isExist){
                System.out.println("your data as Already in database with format like this:\n");
                Utility.cekDatabase(keywords, true);
            }else{
                bufferOutput.write(combination+","+name+","+rank+","+type+","+attack);
                bufferOutput.newLine();
                System.out.println("\nThanks you, your data succesfull added");
            }
            bufferOutput.flush();
        }
        fileOut.close();
        bufferOutput.close();
    }
    
    public static void updateWeapon() throws IOException {
        //cek data
        File database = new File ("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferInput = new BufferedReader(fileInput);
        File tempDB = new File("tempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        //tampilkan data
        weaponList();

        //Masukkan input user
        Scanner uScanner = new Scanner(System.in);
        System.out.print("Input your data's number for update: ");
        int updateNum = uScanner.nextInt();
        
        int entryNumber = 0;

        String data = bufferInput.readLine();

        while(data != null){
            entryNumber++;
            if(updateNum == entryNumber){

                StringTokenizer token;
                String [] list = {"combination","name", "rank", "type", "attack"}; 
                Scanner uScanner2 = new Scanner(System.in);
                
                String [] newData = new String [5];

                token = new StringTokenizer(data, ",");
                for(int i = 0 ; i < 5 ; i++){
                    String oldData = token.nextToken();
                    boolean isOK = Utility.getYesorNo("Are you want to update weapon's "+list[i]);
                    if(isOK){
                        System.out.print("Your weapon's new " + list[i]+": ");
                        newData[i] = uScanner2.nextLine();
                    }else{
                        newData[i] = oldData;
                    }
                }
                
                token = new StringTokenizer(data, ",");
                String combination = token.nextToken();
                System.out.println("\nYour's new data are want to update: ");
                System.out.println("------------------------------");
                System.out.println("Name   : " + token.nextToken() + " --> " + newData[1] );
                System.out.println("Rank   : " + token.nextToken() + " --> " + newData[2] );
                System.out.println("Type   : " + token.nextToken() + " --> " + newData[3] );
                System.out.println("Attack : " + token.nextToken() + " --> " + newData[4] );
                System.out.println("Combination :" + combination + " --> " + newData[0] );
                
                boolean isUpdate = Utility.getYesorNo("Are you really want to update data?");
                
                if(isUpdate){
                    boolean isExist = Utility.cekDatabase(newData, false);
                    if(isExist){
                        System.out.println("\nUpdate data cenceled, your data as Already in database with format like this:");
                        Utility.cekDatabase(newData, true);
                        bufferOutput.write(data);
                    }else{
                        bufferOutput.write(newData[0] +","+ newData[1] +","+ newData[2] +","+ newData[3] +","+ newData[4]);
                        System.out.println("\nThanks you, your data succesfull updated");
                    }
                }else{
                    bufferOutput.write(data);
                }

            }else{
                bufferOutput.write(data);
                           
            }

            bufferOutput.newLine();
            data = bufferInput.readLine();
        }

        bufferOutput.flush();

        fileInput.close();
        fileOutput.close();
        bufferInput.close();
        bufferOutput.close();

        System.gc();
        database.delete();
        tempDB.renameTo(database);

    }

    public static void deleteWeapon() throws IOException {
        
        //cek data
        File database = new File ("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferInput = new BufferedReader(fileInput);
        File tempDB = new File("tempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        //tampilkan data
        weaponList();

        //Masukkan input user
        Scanner uScanner = new Scanner(System.in);
        System.out.print("Input your data's number for delete: ");
        int deleteNum = uScanner.nextInt();
        
        int entryNumber = 0;

        String data = bufferInput.readLine();

        while(data != null){
            entryNumber++;

            if(deleteNum == entryNumber){

                StringTokenizer token = new StringTokenizer(data, ",");
                String combination = token.nextToken();
                System.out.println("\nYour's data want to delete:");
                System.out.println("------------------------------");
                System.out.println("Name        : " + token.nextToken());
                System.out.println("Rank        : " + token.nextToken());
                System.out.println("Type        : " + token.nextToken());
                System.out.println("Attack      : " + token.nextToken());
                System.out.println("Combination :" + combination);

                boolean isDelete = Utility.getYesorNo("Are you want to delete?");
                if(isDelete){
                    System.out.println("\nThanks you, your data succesfull deleted");
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

        bufferOutput.flush();

        fileInput.close();
        bufferInput.close();
        fileOutput.close();
        bufferOutput.close();

        System.gc();

        database.delete();
        tempDB.renameTo(database);
    }

}
