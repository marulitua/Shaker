Project bertujuan untuk memberi gambaran :-) 
mengenai komunikasi antar server client, 
dalam hal ini server adalah sebuah web service 
yang bertugas untuk
 - mencatat data yang dikirimkan oleh client 
 - dan menampilkan data tersebut melalui halaman web

client sendiri bertugas untuk
 - mendetekShakerProjectsi vibrasi 
 - dan mengirimkan data vibrasi tersebut

Android device dan server harus berada dalam satu network agar komunikasi dapat dilakukan (IP server harus dimasukkan terlebih dahulu).

*Directory shaker* 
untuk server menggunakan php dan mysql
untuk menjalankannya copy directory "shaker" ke directory web
lalu buat database dengan nama "shaker" kemudian import database yang ada pada "shaker.sql"
untuk melihat data tinggal buka http://localhost/shaker/

*Directory ShakerProject*
untuk android device tinggal import ke Android Studio trus compile 
                 
                 
                 *****************************
                 android:minSdkVersion="17"
                 android:targetSdkVersion="17"  
                 *****************************
