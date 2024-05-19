package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import koneksi.koneksi;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Cetak_absen extends javax.swing.JPanel {

    public Connection conn = koneksi.getKoneksi();

    public Cetak_absen() {
        initComponents();
        updateCombo();
        jtanggal.setDate(new java.util.Date());
        txtlabel2.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtcari1.setBackground(new java.awt.Color(0, 0, 0, 1));
    }

//    combBox Pilih Kelas
    private void updateCombo() {
        // Hapus semua item dalam combo box sebelum menambahkan item baru
        jkelas1.removeAllItems();

        // Tambahkan placeholder jika diperlukan
        jkelas1.addItem("-- Pilih Kelas --");

        String sql = "SELECT * from kelas";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                jkelas1.addItem(rs.getString("kelas")); // Tambahkan kembali item dari hasil query
            }
        } catch (SQLException e) {
        }

        // Setel placeholder sebagai item yang dipilih secara default
        jkelas1.setSelectedItem("-- Pilih Kelas --");

        // Tambahkan ActionListener untuk menghapus placeholder saat item lain dipilih
        jkelas1.addActionListener((ActionEvent e) -> {
            if (jkelas1.getSelectedItem() != null && jkelas1.getSelectedItem().equals("kelas")) {
                jkelas1.removeItem("-- Pilih Kelas --");
                jkelas1.revalidate();
                jkelas1.repaint();
            }
        });
    }

    void tampilkanDataAbsen() {
        // Ambil nilai kelas yang dipilih dari combo box jkelas1
        String kelas = (String) jkelas1.getSelectedItem();

        // Ambil tanggal yang dipilih dari jtanggal
        java.util.Date tanggalDate = jtanggal.getDate();

        if (kelas == null || tanggalDate == null) {
            // Jika kelas atau tanggal belum dipilih, tampilkan pesan kesalahan
//            JOptionPane.showMessageDialog(null, "Silakan pilih kelas dan tanggal terlebih dahulu.");
            return;
        }

        // Konversi tanggal yang dipilih menjadi objek java.sql.Date
        java.sql.Date tanggal = new java.sql.Date(tanggalDate.getTime());

        // Buat query SQL untuk mengambil data absen berdasarkan kelas dan tanggal yang dipilih
        String sql = "SELECT nipd, nama_siswa, kelamin, kelas, presensi, keterangan, tanggal FROM absen WHERE kelas = ? AND tanggal = ?";

        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, kelas);
            stat.setDate(2, tanggal);
            ResultSet rs = stat.executeQuery();

            // Bersihkan isi tabel absen sebelum menambahkan data baru
            DefaultTableModel model = (DefaultTableModel) tabelsiswa1.getModel();
            model.setRowCount(0);

            // Tambahkan judul kolom ke tabel absen
            model.setColumnIdentifiers(new Object[]{"NIPD", "Nama Siswa", "Jenis Kelamin", "Kelas", "Presensi", "Keterangan", "Tanggal"});

            // Tambahkan data absen ke dalam tabel absen
            while (rs.next()) {
                String nipd = rs.getString("nipd");
                String namaSiswa = rs.getString("nama_siswa");
                String jenisKelamin = rs.getString("kelamin");
                String Kelas = rs.getString("kelas");
                String presensi = rs.getString("presensi");
                String keterangan = rs.getString("keterangan");
                String tanggalStr = rs.getString("tanggal");

                // Tambahkan baris baru ke dalam tabel absen
                model.addRow(new Object[]{nipd, namaSiswa, jenisKelamin, Kelas, presensi, keterangan, tanggalStr});
            }

            // Set the cell editor for the "Presensi" column to use a JComboBox
            TableColumn presensiColumn = tabelsiswa1.getColumnModel().getColumn(4);
            JComboBox<String> comboBox = new JComboBox<>(new String[]{"Hadir", "Alpa", "Izin"});
            presensiColumn.setCellEditor(new DefaultCellEditor(comboBox));

            // Set a custom cell renderer to display the JComboBox in the table
            presensiColumn.setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    // Gunakan renderer default untuk sel yang tidak sedang diedit
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setText(value != null ? value.toString() : "");
                    return label;
                }
            });

            // Call the cetak2 method with the selected date
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cetak2(java.sql.Date tanggal) {
        String reportSource = null;
        String reportDest = null;

        try {
            Connection c = conn; // Menggunakan Connection dari java.sql
            reportSource = System.getProperty("user.dir") + "/src/Laporan/report1.jrxml";
            reportDest = System.getProperty("user.dir") + "/src/Laporan/report1.jasper";

            // Compile the JRXML file to a JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            // Create a map to hold the parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("selectedDate", tanggal);

            // Fill the report with data from the database
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);

            // Export the report to HTML file
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            // View the report
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtbutton = new javax.swing.ButtonGroup();
        mainpanel = new javax.swing.JPanel();
        cetakabsen = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtcari1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jkelas1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelsiswa1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cetak2 = new javax.swing.JButton();
        jtanggal = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtlabel2 = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        mainpanel.setLayout(new java.awt.CardLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        txtcari1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtcari1.setText("Pencarian");
        txtcari1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtcari1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcari1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcari1FocusLost(evt);
            }
        });
        txtcari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcari1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtcari1MouseExited(evt);
            }
        });
        txtcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcari1ActionPerformed(evt);
            }
        });
        txtcari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcari1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcari1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcari1KeyTyped(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N

        jkelas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkelas1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Murid Kelas");

        tabelsiswa1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelsiswa1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelsiswa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelsiswa1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelsiswa1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Kelas     :");

        cetak2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print.png"))); // NOI18N
        cetak2.setText("Cetak");
        cetak2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetak2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tanggal :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cetak2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtanggal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jkelas1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jkelas1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cetak2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcari1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Cetak Absen");

        txtlabel2.setEditable(false);
        txtlabel2.setBackground(new java.awt.Color(255, 255, 255));
        txtlabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtlabel2.setCaretColor(java.awt.Color.lightGray);
        txtlabel2.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtlabel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cetakabsenLayout = new javax.swing.GroupLayout(cetakabsen);
        cetakabsen.setLayout(cetakabsenLayout);
        cetakabsenLayout.setHorizontalGroup(
            cetakabsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakabsenLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(cetakabsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtlabel2)
                    .addGroup(cetakabsenLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 962, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cetakabsenLayout.setVerticalGroup(
            cetakabsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakabsenLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(cetakabsenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(cetakabsenLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainpanel.add(cetakabsen, "card2");

        add(mainpanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void txtcari1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcari1FocusGained
        String Pencarian = txtcari1.getText();
        if (Pencarian.equals("Pencarian")) {
            txtcari1.setText("");
        }
    }//GEN-LAST:event_txtcari1FocusGained

    private void txtcari1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcari1FocusLost
        String username = txtcari1.getText();
        if (username.equals("") || username.equals("Pencarian")) {
            txtcari1.setText("Pencarian");
        }
    }//GEN-LAST:event_txtcari1FocusLost

    private void txtcari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcari1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcari1MouseClicked

    private void txtcari1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcari1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcari1MouseExited

    private void txtcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcari1ActionPerformed

    private void txtcari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcari1KeyPressed

    private void txtcari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcari1KeyReleased
        DefaultTableModel obj = (DefaultTableModel) tabelsiswa1.getModel();
        TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
        tabelsiswa1.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(txtcari1.getText()));
        obj1.setRowFilter(RowFilter.regexFilter("(?i)" + txtcari1.getText()));       // TODO add your handling code here:
    }//GEN-LAST:event_txtcari1KeyReleased

    private void txtcari1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcari1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcari1KeyTyped

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseClicked

    private void txtlabel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabel2ActionPerformed

    private void jkelas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkelas1ActionPerformed
        tampilkanDataAbsen();        // TODO add your handling code here:
    }//GEN-LAST:event_jkelas1ActionPerformed

    private void tabelsiswa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelsiswa1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelsiswa1MouseClicked

    private void cetak2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetak2ActionPerformed
        java.util.Date tanggalDate = jtanggal.getDate();
        java.sql.Date tanggal = new java.sql.Date(tanggalDate.getTime());
        cetak2(tanggal);
    }//GEN-LAST:event_cetak2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cetak2;
    private javax.swing.JPanel cetakabsen;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> jkelas1;
    private com.toedter.calendar.JDateChooser jtanggal;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JTable tabelsiswa1;
    private javax.swing.ButtonGroup txtbutton;
    private javax.swing.JTextField txtcari1;
    private javax.swing.JTextField txtlabel2;
    // End of variables declaration//GEN-END:variables
}
