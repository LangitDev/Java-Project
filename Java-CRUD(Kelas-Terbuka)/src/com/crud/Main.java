package src.com.crud;

import java.io.IOException;
import java.util.Scanner;

import src.data.Dll;
import src.data.Fungsi;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner userInput = new Scanner(System.in);

        boolean isReady = true;

        while (isReady){
            Dll.clearScreen();
            System.out.println("===========================");
            System.out.println("== Database Perpustakaan ==");
            System.out.println("===========================\n");
            System.out.println("1. Cek semua buku");
            System.out.println("2. Cari data buku");
            System.out.println("3. Tambah data buku");
            System.out.println("4. Ubah data buku");
            System.out.println("5. Hapus data buku");

            System.out.print("\nPilihan Anda: ");
            String pilihanAnda = userInput.next();
            switch (pilihanAnda) {
                case "1":
                    System.out.println("=================");
                    System.out.println("Daftar Semua Buku");
                    System.out.println("=================");
                    Fungsi.dataSemuaBuku();
                    break;
                case "2":
                System.out.println("==============");
                System.out.println("Cari Data Buku");
                System.out.println("==============");
                    Fungsi.cariData();
                    break;
                case "3":
                System.out.println("================");
                System.out.println("Tambah Data Buku");
                System.out.println("================");
                    Fungsi.tambahData();
                    Fungsi.dataSemuaBuku();
                    break;
                case "4":
                System.out.println("===============");
                System.out.println("Ubah Data Buku");
                System.out.println("===============");
                    Fungsi.updateData();
                    break;
                case "5":
                System.out.println("===============");
                System.out.println("Hapus Data Buku");
                System.out.println("===============");
                    Fungsi.hapusData();
                    break;
                default:
                    System.out.println("Data yang anda masukan bukan 1-5");
            }

            isReady = Dll.getYesroNo("Apakah Anda ingin melanjutkan?");

        }

    }

}