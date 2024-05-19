/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simsditp;

import Database.SQLConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author ILHAM NA
 */
public class FormTransaksi extends javax.swing.JFrame {
    
    
    public FormTransaksi() {
        initComponents();
        tabelSiswa();

        
//       tNo_Transaki.setText("Auto fill");
       t_transaksi.setEnabled(false);
       tTanggal.setEnabled(false);
    }
    
    DefaultComboBoxModel modelc;
    DefaultTableModel model;
    DefaultTableModel modelStatus;
    Calendar kalender = new GregorianCalendar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        t_transaksi = new javax.swing.JLabel();
        tnisn = new javax.swing.JLabel();
        tnama = new javax.swing.JLabel();
        tkelas = new javax.swing.JLabel();
        id_angkatan1 = new javax.swing.JTextField();
        tAngkatan = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsiswa = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        tNama_siswa1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbstatusbayar = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tTanggal = new javax.swing.JTextField();
        cbBulan = new javax.swing.JComboBox<>();
        tJumlah_bayar = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel3.setFont(new java.awt.Font("Open Sans", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TRANSAKSI PEMBAYARAN SPP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(200, 200, 200))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(251, 251, 251));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel2.setText("No.Transaksi   ");

        jLabel5.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel5.setText("NISN ");

        jLabel6.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel6.setText("Nama Siswa");

        jLabel7.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel7.setText("Angkatan");

        jLabel8.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel8.setText("Kelas");

        jLabel10.setText(":");

        jLabel11.setText(":");

        jLabel13.setText(":");

        jLabel14.setText(":");

        jLabel15.setText(":");

        t_transaksi.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        t_transaksi.setText("Trns000");

        tnisn.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        tnisn.setText("~");
        tnisn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tnisnPropertyChange(evt);
            }
        });

        tnama.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        tnama.setText("~");
        tnama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tnamaPropertyChange(evt);
            }
        });

        tkelas.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        tkelas.setText("~");
        tkelas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tkelasPropertyChange(evt);
            }
        });

        id_angkatan1.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N

        tAngkatan.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        tAngkatan.setText("~");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_transaksi))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_angkatan1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tkelas))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tnama))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tnisn)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(t_transaksi))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(tnisn))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel14)
                    .addComponent(tnama))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(tkelas))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel15)
                    .addComponent(id_angkatan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        cari.setFont(new java.awt.Font("Montserrat", 0, 11)); // NOI18N
        cari.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 18)); // NOI18N
        jLabel1.setText("Cari Siswa");

        tbsiswa.setFont(new java.awt.Font("Montserrat", 0, 11)); // NOI18N
        tbsiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbsiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbsiswa);

        jLabel4.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 18)); // NOI18N
        jLabel4.setText("Status Bayar");

        tNama_siswa1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        tNama_siswa1.setText("~");

        tbstatusbayar.setFont(new java.awt.Font("Montserrat", 0, 11)); // NOI18N
        tbstatusbayar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbstatusbayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbstatusbayarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbstatusbayar);

        jPanel4.setBackground(new java.awt.Color(251, 251, 251));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel12.setText("Bulan");

        jLabel17.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel17.setText("Tanggal");

        jLabel18.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        jLabel18.setText("Jumlah");

        tTanggal.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N

        cbBulan.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "januari", "februari", "maret", "april", "mei", "juni", "juli", "agustus", "september", "oktober", "november", "desember" }));
        cbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBulanActionPerformed(evt);
            }
        });

        tJumlah_bayar.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N

        btnSimpan.setBackground(new java.awt.Color(0, 204, 0));
        btnSimpan.setFont(new java.awt.Font("Open Sans ExtraBold", 0, 12)); // NOI18N
        btnSimpan.setText("Bayar");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(255, 0, 51));
        btnHapus.setFont(new java.awt.Font("Open Sans ExtraBold", 0, 12)); // NOI18N
        btnHapus.setText("Batalkan");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tJumlah_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jLabel17)
                    .addContainerGap(290, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tJumlah_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(185, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(cari))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tNama_siswa1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tNama_siswa1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyPressed
        tabelSiswa();
    }//GEN-LAST:event_cariKeyPressed

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
        tabelSiswa();
    }//GEN-LAST:event_cariKeyReleased

    private void tbsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsiswaMouseClicked
        ID_AUTO();
        //setting date
        int tahun = kalender.get(Calendar.YEAR);
        int bulan = kalender.get(Calendar.MONTH)+1;
        int hari = kalender.get(Calendar.DAY_OF_MONTH);
        String tanggal = tahun+"/"+bulan+"/"+hari;
        tTanggal.setText(tanggal);
        
        int i = tbsiswa.getSelectedRow();
        tnisn.setText(model.getValueAt(i, 0).toString());
        tnama.setText(model.getValueAt(i, 2).toString());
        tNama_siswa1.setText(model.getValueAt(i, 2).toString());
        tkelas.setText(model.getValueAt(i, 3).toString());
        tAngkatan.setText(model.getValueAt(i, 6).toString());

        try {
            String query = "SELECT siswa.*, spp.* FROM siswa INNER JOIN spp Using(id_spp) WHERE nisn LIKE '%?%'";
            ResultSet rs = SQLConnection.doPreparedQuery(query, tnisn.getText());
            while (rs.next()) {
                id_angkatan1.setText(rs.getString("id_spp"));
                tJumlah_bayar.setText(rs.getString("nominal"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        btnSimpan.setEnabled(true);
        btnHapus.setEnabled(false);
    }//GEN-LAST:event_tbsiswaMouseClicked

    private void tbstatusbayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbstatusbayarMouseClicked
       int trans = tbstatusbayar.getSelectedRow();
        t_transaksi.setText(modelStatus.getValueAt(trans, 0).toString());
        tTanggal.setText(modelStatus.getValueAt(trans, 1).toString());
        cbBulan.setSelectedItem(modelStatus.getValueAt(trans, 2).toString());
        tJumlah_bayar.setText(modelStatus.getValueAt(trans, 3).toString());

        try {
            String query = "SELECT pembayaran.*, petugas.*, spp.* from pembayaran INNER JOIN petugas Using(id_petugas) INNER JOIN spp Using(id_spp) WHERE id_pembayaran like '%?%'";
            ResultSet rs = SQLConnection.doPreparedQuery(query, t_transaksi.getText());
            
            while (rs.next()) {
                id_angkatan1.setText(rs.getString("id_spp"));
                tAngkatan.setText(rs.getString("tahun"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        btnSimpan.setEnabled(false);
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_tbstatusbayarMouseClicked

    private void tnisnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tnisnPropertyChange
        tabelStatBayar();
    }//GEN-LAST:event_tnisnPropertyChange

    private void tnamaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tnamaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamaPropertyChange

    private void tkelasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tkelasPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tkelasPropertyChange

    private void cbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBulanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBulanActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String no_trans = t_transaksi.getText();
        String nisn = tnisn.getText();
        String id_angkatan = id_angkatan1.getText();
        String angkatan = tAngkatan.getText();
        String tgl = tTanggal.getText();
        String bln_bayar = cbBulan.getSelectedItem().toString();
        String jml_bayar = tJumlah_bayar.getText();

        try{
            String query = "SELECT * FROM  pembayaran where nisn=? and bulan_dibayar=?";
            ResultSet rs = SQLConnection.doPreparedQuery(query, nisn, bln_bayar);

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Gagal : Transaksi ini sudah dilakukan!");
            } else {
                String insert = "INSERT INTO pembayaran VALUE (?, ?, ?, ?, ?, ?, ?)";
                SQLConnection.doPreparedUpdate(
                    insert, 
                    no_trans,
                    nisn,
                    tgl,
                    bln_bayar,
                    angkatan,
                    id_angkatan,
                    jml_bayar
                );
                JOptionPane.showMessageDialog(null, "Berhasil melakukan transaksi");
            }
        }catch (Exception ex) {
            System.out.println(""+ex);
            JOptionPane.showMessageDialog(null, "Gagal!");
        }
        
        tabelStatBayar();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        try {
            String delete = "DELETE FROM pembayaran WHERE id_pembayaran=?";
            SQLConnection.doPreparedUpdate(delete, t_transaksi.getText());
            JOptionPane.showMessageDialog(null, "Transaksi berhasil dihapus");
            tabelStatBayar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal hapus transaksi");
        }

    }//GEN-LAST:event_btnHapusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JTextField cari;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JTextField id_angkatan1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel tAngkatan;
    private javax.swing.JTextField tJumlah_bayar;
    private javax.swing.JLabel tNama_siswa1;
    private javax.swing.JTextField tTanggal;
    private javax.swing.JLabel t_transaksi;
    private javax.swing.JTable tbsiswa;
    private javax.swing.JTable tbstatusbayar;
    private javax.swing.JLabel tkelas;
    private javax.swing.JLabel tnama;
    private javax.swing.JLabel tnisn;
    // End of variables declaration//GEN-END:variables
 private void tabelSiswa() {
        String[] judul = {"NIPD", "Nama", "Kelas", "Alamat", "Telepon", "Tahun"};
        model = new DefaultTableModel(judul,0);
        tbsiswa.setModel(model);
        
        
        try {
            String sql = "SELECT siswa.*, spp.tahun from siswa INNER JOIN spp Using(id_spp) where nipd like '%"+cari.getText()+"%' or nama_siswa like '%"+cari.getText()+"%'";
            ResultSet rs = SQLConnection.doQuery(sql);
            while(rs.next()) {
               String nipd = rs.getString("nipd");
               String nama = rs.getString("nama");
               String kelas = rs.getString("nama_kelas");
               String alamat = rs.getString("alamat");
               String telp = rs.getString("no_telp");
               String tahun = rs.getString("tahun");
                
               String[] data = {nipd, nama, kelas, alamat, telp, tahun};
               model.addRow(data);
           }
        }catch(SQLException e) {
           System.out.println(e);
        }
    
    }
    private void tabelStatBayar() {
        String[] header = {"No Transaksi","Tanggal","Bulan","Jumlah Bayar","Status"};
        modelStatus = new DefaultTableModel(header,0);
        tbstatusbayar.setModel(modelStatus);
        
        try {
            String dataStat = "SELECT * FROM pembayaran WHERE nisn=?";
            
            ResultSet queryStat = SQLConnection.doPreparedQuery(dataStat, tnisn.getText());
            while(queryStat.next()) {
               String no = queryStat.getString("id_pembayaran");
               String tanggal = queryStat.getString("tgl_bayar");
               String bulan = queryStat.getString("bulan_dibayar");
               String jumlah = queryStat.getString("jumlah_bayar");
               String status = "Lunas";
                
               String[] record = {no,tanggal,bulan,jumlah,status};
               modelStatus.addRow(record);
           }
        }catch(SQLException e) {
           System.out.println(e);
        }
    }
    
    public void resetForm(){
        t_transaksi.setText(""); 
        id_angkatan1.setText(""); 
        tTanggal.setText(""); 
        tJumlah_bayar.setText("");
        cbBulan.setSelectedItem("januari");   
        tabelSiswa(); 
    }
        
    /////kode otomatis
    private void ID_AUTO(){
        try {
            String sql="select max(right(id_pembayaran,3)) as no_trans from pembayaran";
            ResultSet rs = SQLConnection.doQuery(sql);

            while(rs.next()){
                if(rs.first()==false){
                    t_transaksi.setText("TRS001");
                } else{
                    rs.last();
                    int autoid = rs.getInt(1) + 1;
                    String nomor=String.valueOf(autoid);
                    
                    for(int a=0;a<3;a++){ 
                        nomor = "0" + nomor;
                    }
                    t_transaksi.setText("TRS" + nomor);
                }
            }
        } catch (SQLException e) {
            System.out.println("error pembayaran | "+e);
        }
    }

}

