/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.DriverManager;

/**
 *
 * @author moham
 */
public class koneksi {
    private static java.sql.Connection koneksi;

    public static java.sql.Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/simsditp";
                String user = "root";
                String password = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Berhasil Konek");
            } catch (Exception e) {
                System.out.println("Gagal Konek");
            }
        }
        return koneksi;
    }

    public static void main(String args[]) {
        getKoneksi();
    }
}
