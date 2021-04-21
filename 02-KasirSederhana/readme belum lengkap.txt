Assalamu alaikum wr. wb.
Ini adalah proyek ketiga yang telah berhasil aku selesaian. ini adalah sebuah otak aplikasi
dengan tipe kasir sederhana. kali ini aku membuat Readme ini sebagai tambahan tugas agar
diriku dapat lebih memahami tentang programmer.

Disini readme ini bertugas untuk memberitahu langkah-langkah yang aku gunakan dalam 
pembuatan aplikasi kasir sederhana tersebut. baiklah kita mulai saja.

Pertama-tama aku membuat file Warung.java sebagai aplikasi utama dengan method main ....
disini aku membuat sebuah main menu dengan 5 pilihan yaitu, 1.Penjualan, 2. History,
3. Pendapatan, 4. Edit Barang, 5. Exit. untuk menampilkan di layar console aku
menggunakan println, kemudian untuk membuat pilihannya aku menggunakan switch case.
sedangkan untuk pilih penggunanya aku menggunakan Scanner. kemudian, Main menu 
tersebut aku bungkus dengan looping do while, menggunakan boolean isRoll = true sebagai
pilihan utama. sehingga, ketika telah selesai dengan pilihan  1-4 maka ia akan otomatis
melakukan looping, pada setiap awal looping, aku menambahkan sebuah method clearScreen yang
berisikan code untuk membersihkan layar. Juga, aku menggukan string ketika mengambil
pilihan pengguna agar tidak error ketika seseorang memasukan pilihan yang bukan angka.
sedangkan dengan looping tadi, ketika seseorang mamasukan pilihan selain dari 1-5, maka
looping akan otomatis berlaku, sehingga seolah input yang dimasukannya tidak berarti.
Lalu, pada bagian 5. Exit aku membuat code isRoll = false sehingga ketika user memasukan
pilihan tersebut maka looping akan otomatis terhenti, sebab looping akan mengikuti isRoll
jika true, maka ia akan terus loop, jika false maka ia tidak akan melakukan loop. codingan
setelah box do while hanyalah closeing untuk Scanner. sehingga aplikasipun otomatis selelai
karena tidak ada lagi perintah.

file Penjualan.java, untuk mempermudah dan agar tidak terlalu pusing aku memilih 
membuat file .java lainnnya sebagai wadah untuk setiap pilihan yang tersedia pada Main menu
kecuali exit. dan Sekarang kita berada piliahan pertama, Penjualan.
aku membuat Penjualan.java sebagai sebuah object, sehingga ia harus memiliki nama class yang
sama dengan .java, aku juga membuat constructor sebagai basis utama pada bagian ini
sehingga akan otomatis terpanggil ketika object ini di buat di main aplikasi.
perlu diketahui keseluruhan program dibuat di package com.warung.
pada constructor tersebut aku juga menambahkan throws IOException, selain constructor
aku menanbah beberapa attribute untuk object Penjualan ini. yaitu dua FileWriter dng Bufferednya
masing-masing, deklarasi Scanner, dan beberapa ArrayList yang akan berguna sebagai penyimpan
data sementara.

