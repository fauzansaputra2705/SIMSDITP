package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {
// Add JLabel to display user level

    private String userLevel;

    public void checkLoginStatus(Login loginWindow) {
        if (!loginWindow.isLoggedIn()) {
            // Jika pengguna belum Login, tampilkan jendela Login
            loginWindow.revalidate();
            loginWindow.setVisible(true);
            this.dispose(); // Tutup jendela dashboard
        }
    }

    public Dashboard() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pn_menu = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        navbar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lebeluser = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        panelutama = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        sidebar.setBackground(new java.awt.Color(255, 255, 255));
        sidebar.setPreferredSize(new java.awt.Dimension(230, 415));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        pn_menu.setBackground(new java.awt.Color(60, 91, 111));
        pn_menu.setLayout(new javax.swing.BoxLayout(pn_menu, javax.swing.BoxLayout.Y_AXIS));
        pn_menu.add(filler1);

        jScrollPane1.setViewportView(pn_menu);

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        getContentPane().add(sidebar, java.awt.BorderLayout.LINE_START);

        navbar.setBackground(new java.awt.Color(153, 153, 255));
        navbar.setPreferredSize(new java.awt.Dimension(713, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/60x60.png"))); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sekolah Dasar Islam Teladan Pulogadung - 2023/2024");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Sekolah Dasar");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Aplikasi Manajemen");

        lebeluser.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lebeluser.setForeground(new java.awt.Color(255, 255, 255));
        lebeluser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lebeluser.setText("Admin");
        lebeluser.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        jLabel9.setToolTipText("");

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lebeluser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addGap(21, 21, 21))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(navbarLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(navbarLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lebeluser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel9)
                            .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(navbarLayout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jLabel7))
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        getContentPane().add(navbar, java.awt.BorderLayout.PAGE_START);

        content.setBackground(new java.awt.Color(255, 255, 255));

        panelutama.setBackground(new java.awt.Color(255, 255, 255));
        panelutama.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelutama, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelutama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(content, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(785, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        panelutama.add(new Home());
        panelutama.repaint();
        panelutama.revalidate();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Login loginWindow = new Login();
        Dashboard dashboardWindow = new Dashboard();

        // Tampilkan jendela Login saat aplikasi dimulai
        loginWindow.setVisible(true);

        // Ketika pengguna berhasil Login, tutup jendela Login dan tampilkan dashboard
        if (loginWindow.isLoggedIn()) {
            loginWindow.dispose(); // Tutup jendela Login
            dashboardWindow.setVisible(true); // Tampilkan jendela dashboard
        }
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lebeluser;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel panelutama;
    private javax.swing.JPanel pn_menu;
    private javax.swing.JPanel sidebar;
    // End of variables declaration//GEN-END:variables

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public void execute() {
        ImageIcon iconMaster = new ImageIcon(getClass().getResource("/img/master1.png"));
        ImageIcon iconHome = new ImageIcon(getClass().getResource("/img/Home1.png"));
        ImageIcon iconTransaksi = new ImageIcon(getClass().getResource("/img/proses.png"));
        ImageIcon iconReport = new ImageIcon(getClass().getResource("/img/report1.png"));
        ImageIcon iconlogout = new ImageIcon(getClass().getResource("/img/keluar.png"));
        ImageIcon iconsiswa = new ImageIcon(getClass().getResource("/img/siswa1.png"));
        ImageIcon iconguru = new ImageIcon(getClass().getResource("/img/pegawai.png"));
        ImageIcon iconmapel = new ImageIcon(getClass().getResource("/img/buku.png"));
        ImageIcon iconSpp = new ImageIcon(getClass().getResource("/img/spp.png"));
        ImageIcon iconAbsen = new ImageIcon(getClass().getResource("/img/absen1.png"));
        ImageIcon iconNilai = new ImageIcon(getClass().getResource("/img/nilai1.png"));
        ImageIcon iconkelas = new ImageIcon(getClass().getResource("/img/kelas.png"));

        Menu_Sidebar masBarang1 = new Menu_Sidebar(null, true, iconsiswa, "Siswa", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                CRUD_Siswa siswa = new CRUD_Siswa();
                panelutama.add(siswa);
                siswa.setVisible(true); // Menampilkan Siswa
                panelutama.repaint();
                panelutama.revalidate();

                siswa.kosong();
                siswa.datatable();
            }
        });

        Menu_Sidebar masBarang2 = new Menu_Sidebar(null, true, iconguru, "Pegawai", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                CRUD_Pegawai pegawai = new CRUD_Pegawai();
                panelutama.add(pegawai);
                pegawai.setVisible(true); // Menampilkan CRUD_Pegawai
                panelutama.repaint();
                panelutama.revalidate();

                pegawai.kosong();
                pegawai.datatable();
            }
        });

        Menu_Sidebar masBarang3 = new Menu_Sidebar(null, true, iconkelas, "Kelas", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                CRUD_Kelas kelas = new CRUD_Kelas();
                panelutama.add(kelas);
                kelas.setVisible(true); // Menampilkan Kelas
                panelutama.repaint();
                panelutama.revalidate();

                kelas.tampilkanDataSiswa();
                kelas.updateTabelGuru();
            }
        });

        Menu_Sidebar masBarang4 = new Menu_Sidebar(null, true, iconmapel, "Mata Pelajaran", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                CRUD_Mapel mapel = new CRUD_Mapel();
                panelutama.add(mapel);
                mapel.setVisible(true); // Menampilkan CRUD_Mapel
                panelutama.repaint();
                panelutama.revalidate();

                mapel.kosong();
                mapel.datatable();
            }
        });

        Menu_Sidebar transaksi1 = new Menu_Sidebar(null, true, iconSpp, "SPP", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                spp spp = new spp();
                panelutama.add(spp);
                spp.setVisible(true); // Menampilkan SPP
                panelutama.repaint();
                panelutama.revalidate();

            }
        });

        Menu_Sidebar transaksi2 = new Menu_Sidebar(null, true, iconAbsen, "Absesi", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                Absen absen = new Absen();
                panelutama.add(absen);
                absen.setVisible(true); // Menampilkan Absen
                panelutama.repaint();
                panelutama.revalidate();

            }
        });

        Menu_Sidebar transaksi3 = new Menu_Sidebar(null, true, iconNilai, "Nilai Ujian", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                Nilai_Ujian nilaiUjian = new Nilai_Ujian();
                panelutama.add(nilaiUjian);
                nilaiUjian.setVisible(true); // Menampilkan Niai Ujian
                panelutama.repaint();
                panelutama.revalidate();

            }
        });

        Menu_Sidebar menuHome = new Menu_Sidebar(iconHome, false, null, "Home", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                Home content_bg1 = new Home();
                panelutama.add(content_bg1);
                content_bg1.setVisible(true); // Menampilkan Kelas
                panelutama.repaint();
                panelutama.revalidate();

                content_bg1.datatable();
            }
        });

        Menu_Sidebar report1 = new Menu_Sidebar(null, true, iconAbsen, "Cetak Absen", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelutama.removeAll();
                Cetak_absen cetakabsen = new Cetak_absen();
                panelutama.add(cetakabsen);
                cetakabsen.setVisible(true); // Menampilkan Siswa
                panelutama.repaint();
                panelutama.revalidate();
            }
        });

        Menu_Sidebar report2 = new Menu_Sidebar(null, true, iconSpp, "Cetak SPP", null);
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panelutama.removeAll();
//                cetakspp cetakspp = new cetakspp();
//                panelutama.add(cetakspp);
//                cetakspp.setVisible(true); // Menampilkan Siswa
//                panelutama.repaint();
//                panelutama.revalidate();
//            }
//        });

        Menu_Sidebar report3 = new Menu_Sidebar(null, true, iconNilai, "Cetak Nilai", null);
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panelutama.removeAll();
//                cetaknilai cetaknilai = new cetaknilai();
//                panelutama.add(cetaknilai);
//                cetaknilai.setVisible(true); // Menampilkan Siswa
//                panelutama.repaint();
//                panelutama.revalidate();
//            }
//        });

        Menu_Sidebar menuLogout = new Menu_Sidebar(iconlogout, false, null, "Log Out", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmLogout()) {
                    dispose();
                    Login login = new Login();
                    login.setVisible(true); // Menampilkan Halaman Login kembali Ketika Log Out
                    login.revalidate();
                } else {

                }

            }
        });

        Menu_Sidebar menuMaster = new Menu_Sidebar(iconMaster, false, null, "Master", null, masBarang1, masBarang2, masBarang3, masBarang4);
        Menu_Sidebar menuReport = new Menu_Sidebar(iconReport, false, null, "Report", null, report1, report2, report3);

        if ("admin".equalsIgnoreCase(userLevel)) {
            Menu_Sidebar menuTransaksi = new Menu_Sidebar(iconTransaksi, false, null, "Transaksi", null, transaksi1, transaksi2, transaksi3);
            addMenu(menuHome, menuMaster, menuTransaksi, menuReport, menuLogout);
            lebeluser.setText("Admin"); // Set label text for admin
        } else if ("guru".equalsIgnoreCase(userLevel)) {
            Menu_Sidebar menuTransaksi = new Menu_Sidebar(iconTransaksi, false, null, "Transaksi", null, transaksi2, transaksi3);
            addMenu(menuHome, menuTransaksi, menuLogout);
            lebeluser.setText("Guru"); // Set label text for guru
        }

    }

    void addMenu(Menu_Sidebar... menu) {
        for (int i = 0; i < menu.length; i++) {
            pn_menu.add(menu[i]);
            ArrayList<Menu_Sidebar> subMenu = menu[i].getSubMenu();
            for (Menu_Sidebar m : subMenu) {
                addMenu(m);
            }
        }
        pn_menu.revalidate();
    }

    public boolean confirmLogout() {
        int result = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

}
