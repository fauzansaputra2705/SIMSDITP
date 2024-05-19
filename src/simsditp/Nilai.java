
package simsditp;

import Database.SQLConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Nilai extends javax.swing.JPanel {
    
    private DefaultTableModel tabmode;

    /**
     * Creates new form Nilai
     */
    public Nilai() {
        initComponents();
        
        updateCombo();
        comboPredikat(predikat_sikap);
        comboPredikat(predikat_keterampilan);
        comboPredikat(predikat_kompetensi);
        comboMapel();
        
        btnInputNilai.setVisible(false);
        batal.setVisible(false);
        
        tabelSiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Dapatkan baris yang diklik
                int row = tabelguru.getSelectedRow();
                // show tombol jika ada baris yang dipilih
                if (row != -1) {
                    btnInputNilai.setVisible(true);
                    batal.setVisible(true);
                } else {
                    // hide tombol jika ada baris yang dipilih
                    btnInputNilai.setVisible(false);
                    batal.setVisible(false);
                }
            }
        });

        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Sembunyikan tombol-tombol
                btnInputNilai.setVisible(false);
                batal.setVisible(false);
                tabelSiswa.clearSelection();
            }
        });
        
        
        tabelNilaiSiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Dapatkan baris yang diklik
                int row = tabelNilaiSiswa.getSelectedRow();
                // show tombol jika ada baris yang dipilih
                if (row != -1) {
                    btnEditNilaiSiswa.setVisible(true);
                    btnHapusNilaiSiswa.setVisible(true);
                    btnCancelNilaiSiswa.setVisible(true);
                    btnTambahNilaiSiswa.setVisible(false); // Sembunyikan tombol tambah
                } else {
                    btnEditNilaiSiswa.setVisible(false);
                    btnHapusNilaiSiswa.setVisible(false);
                    btnCancelNilaiSiswa.setVisible(false);
                    btnTambahNilaiSiswa.setVisible(true); // Tampilkan tombol tambah kembali
                }
            }
        });

        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Sembunyikan tombol-tombol
                btnInputNilai.setVisible(false);
                batal.setVisible(false);
                tabelSiswa.clearSelection();
            }
        });
    }
    
    private void updateCombo() {
        // Hapus semua item dalam combo box sebelum menambahkan item baru
        jkelas.removeAllItems();

        // Tambahkan placeholder jika diperlukan
        jkelas.addItem("-- Pilih Kelas --");

        try {
            String query = "SELECT DISTINCT wali_kelas FROM guru WHERE wali_kelas IS NOT NULL";
            ResultSet result = SQLConnection.doQuery(query);
            
            while (result.next()) {
                jkelas.addItem(result.getString("wali_kelas")); // Tambahkan kembali item dari hasil query
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Setel placeholder sebagai item yang dipilih secara default
        jkelas.setSelectedItem("-- Pilih Kelas --");
    }
    
    private void comboPredikat(JComboBox item) {
        item.removeAllItems();

        // Tambahkan placeholder jika diperlukan
        item.addItem("-- Pilih Predikat --");

        String[] predikat = {"A", "B", "C", "D", "E"};
            
        for (String p : predikat) {
            item.addItem(p);
        }

        // Setel placeholder sebagai item yang dipilih secara default
        jkelas.setSelectedItem("-- Pilih Predikat --");
    }
    
    private void comboMapel() {
        // Hapus semua item dalam combo box sebelum menambahkan item baru
        mapel_id.removeAllItems();

        // Tambahkan placeholder jika diperlukan
        mapel_id.addItem("-- Pilih Mapel --");

        try {
            String query = "SELECT id_mapel, nama_mapel FROM mapel";
            ResultSet result = SQLConnection.doQuery(query);
            
            while (result.next()) {
                mapel_id.addItem(result.getString("id_mapel") + "-"+ result.getString("nama_mapel")); // Tambahkan kembali item dari hasil query
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Setel placeholder sebagai item yang dipilih secara default
        mapel_id.setSelectedItem("-- Pilih Mapel --");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainpanel = new javax.swing.JPanel();
        dataSiswa = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSiswa = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jkelas = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelguru = new javax.swing.JTable();
        btnInputNilai = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PanelNilai = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        id_siswa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nama_siswa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nipd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        semester = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        tahun_ajaran = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        predikat_sikap = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        mapel_id = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        nilai_mapel = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        nilai_tugas = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        nilai_uts = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        nilai_uas = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        predikat_keterampilan = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        predikat_kompetensi = new javax.swing.JComboBox<>();
        simpan = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        catatan = new javax.swing.JTextArea();
        dataNilai = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelNilaiSiswa = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtcariNilaiSiswa = new javax.swing.JTextField();
        btnHapusNilaiSiswa = new javax.swing.JButton();
        btnEditNilaiSiswa = new javax.swing.JButton();
        btnCancelNilaiSiswa = new javax.swing.JButton();
        btnTambahNilaiSiswa = new javax.swing.JButton();
        TitleNilaiSiswa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        mainpanel.setLayout(new java.awt.CardLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        tabelSiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSiswa);

        txtcari.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtcari.setText("Pencarian");
        txtcari.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtcari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcariFocusLost(evt);
            }
        });
        txtcari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcariMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtcariMouseExited(evt);
            }
        });
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcariKeyTyped(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N

        jkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkelasActionPerformed(evt);
            }
        });

        tabelguru.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nama Guru"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelguru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelguruMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelguru);

        btnInputNilai.setText("Input Nilai");
        btnInputNilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputNilaiActionPerformed(evt);
            }
        });

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Kelas");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Murid Kelas");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Wali Kelas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jkelas, 0, 272, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnInputNilai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(batal))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInputNilai)
                    .addComponent(batal))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Data SISWA");

        javax.swing.GroupLayout dataSiswaLayout = new javax.swing.GroupLayout(dataSiswa);
        dataSiswa.setLayout(dataSiswaLayout);
        dataSiswaLayout.setHorizontalGroup(
            dataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataSiswaLayout.createSequentialGroup()
                .addGroup(dataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dataSiswaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dataSiswaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addGap(0, 845, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dataSiswaLayout.setVerticalGroup(
            dataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataSiswaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainpanel.add(dataSiswa, "card2");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Input Nilai Siswa");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        id_siswa.setEditable(false);
        id_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_siswaActionPerformed(evt);
            }
        });

        jLabel7.setText("ID Siswa");

        nama_siswa.setEditable(false);
        nama_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_siswaActionPerformed(evt);
            }
        });

        jLabel8.setText("Nama Siswa");

        nipd.setEditable(false);
        nipd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nipdActionPerformed(evt);
            }
        });

        jLabel9.setText("NIPD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(id_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nipd, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nipd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(957, 69));

        semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Pilih Semester ---", "Ganjil", "Genap" }));
        semester.setName(""); // NOI18N
        semester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semesterActionPerformed(evt);
            }
        });

        jLabel10.setText("Semester");

        jLabel11.setText("Tahun Ajaran");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(191, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(semester, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(tahun_ajaran, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tahun_ajaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setPreferredSize(new java.awt.Dimension(957, 69));

        jLabel12.setText("Sikap");

        predikat_sikap.setName(""); // NOI18N
        predikat_sikap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predikat_sikapActionPerformed(evt);
            }
        });

        jLabel14.setText("Mata Pelajaran");

        mapel_id.setName(""); // NOI18N
        mapel_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapel_idActionPerformed(evt);
            }
        });

        jLabel16.setText("Nilai Mapel");

        nilai_mapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilai_mapelActionPerformed(evt);
            }
        });

        jLabel17.setText("Nilai Tugas");

        nilai_tugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilai_tugasActionPerformed(evt);
            }
        });

        jLabel18.setText("Nilai UTS");

        nilai_uts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilai_utsActionPerformed(evt);
            }
        });

        jLabel19.setText("Nilai UAS");

        nilai_uas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilai_uasActionPerformed(evt);
            }
        });

        jLabel13.setText("Keterampilan");

        predikat_keterampilan.setName(""); // NOI18N
        predikat_keterampilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predikat_keterampilanActionPerformed(evt);
            }
        });

        jLabel15.setText("Kompetensi");

        predikat_kompetensi.setName(""); // NOI18N
        predikat_kompetensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predikat_kompetensiActionPerformed(evt);
            }
        });

        simpan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        simpan.setText("Save");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        keluar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        keluar.setText("Cancel");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("Input Nilai Siswa");

        jLabel22.setText("Catatan");

        catatan.setColumns(20);
        catatan.setRows(5);
        jScrollPane4.setViewportView(catatan);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel20)
                .addGap(0, 809, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(predikat_kompetensi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(predikat_sikap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(predikat_keterampilan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mapel_id, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel16)
                                .addComponent(jLabel13)
                                .addComponent(jLabel17)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15)
                                .addComponent(jLabel12)
                                .addComponent(nilai_mapel, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                                .addComponent(nilai_tugas))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(keluar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19)
                                .addComponent(nilai_uts, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                                .addComponent(nilai_uas))
                            .addComponent(jLabel22))
                        .addGap(52, 52, 52)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(predikat_sikap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(predikat_keterampilan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(predikat_kompetensi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapel_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nilai_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nilai_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nilai_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nilai_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(keluar)
                            .addComponent(simpan))
                        .addGap(74, 74, 74))))
        );

        javax.swing.GroupLayout PanelNilaiLayout = new javax.swing.GroupLayout(PanelNilai);
        PanelNilai.setLayout(PanelNilaiLayout);
        PanelNilaiLayout.setHorizontalGroup(
            PanelNilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(PanelNilaiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelNilaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelNilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelNilaiLayout.setVerticalGroup(
            PanelNilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNilaiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        mainpanel.add(PanelNilai, "card3");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tabelNilaiSiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelNilaiSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelNilaiSiswaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelNilaiSiswa);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N

        txtcariNilaiSiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtcariNilaiSiswa.setText("Pencarian");
        txtcariNilaiSiswa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtcariNilaiSiswa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcariNilaiSiswaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcariNilaiSiswaFocusLost(evt);
            }
        });
        txtcariNilaiSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcariNilaiSiswaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtcariNilaiSiswaMouseExited(evt);
            }
        });
        txtcariNilaiSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariNilaiSiswaActionPerformed(evt);
            }
        });
        txtcariNilaiSiswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariNilaiSiswaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariNilaiSiswaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcariNilaiSiswaKeyTyped(evt);
            }
        });

        btnHapusNilaiSiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnHapusNilaiSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hapus.png"))); // NOI18N
        btnHapusNilaiSiswa.setText("Delete");
        btnHapusNilaiSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusNilaiSiswaActionPerformed(evt);
            }
        });

        btnEditNilaiSiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnEditNilaiSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnEditNilaiSiswa.setText("Edit");
        btnEditNilaiSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNilaiSiswaActionPerformed(evt);
            }
        });

        btnCancelNilaiSiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnCancelNilaiSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        btnCancelNilaiSiswa.setText("Batal");
        btnCancelNilaiSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelNilaiSiswaActionPerformed(evt);
            }
        });

        btnTambahNilaiSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        btnTambahNilaiSiswa.setText("Tambah");
        btnTambahNilaiSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahNilaiSiswaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTambahNilaiSiswa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditNilaiSiswa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusNilaiSiswa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelNilaiSiswa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcariNilaiSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHapusNilaiSiswa)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditNilaiSiswa)
                        .addComponent(btnCancelNilaiSiswa)
                        .addComponent(btnTambahNilaiSiswa))
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcariNilaiSiswa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                .addContainerGap())
        );

        TitleNilaiSiswa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TitleNilaiSiswa.setText("Data Nilai Siswa");

        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataNilaiLayout = new javax.swing.GroupLayout(dataNilai);
        dataNilai.setLayout(dataNilaiLayout);
        dataNilaiLayout.setHorizontalGroup(
            dataNilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataNilaiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(dataNilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataNilaiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dataNilaiLayout.createSequentialGroup()
                        .addGroup(dataNilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataNilaiLayout.createSequentialGroup()
                                .addComponent(TitleNilaiSiswa)
                                .addGap(0, 786, Short.MAX_VALUE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        dataNilaiLayout.setVerticalGroup(
            dataNilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataNilaiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(TitleNilaiSiswa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainpanel.add(dataNilai, "card2");

        add(mainpanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void tabelSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSiswaMouseClicked
        if (evt.getClickCount() == 1) { // Pastikan hanya single click
            int selectedRowIndex = tabelSiswa.getSelectedRow();
            if (selectedRowIndex != -1) { // Pastikan indeks baris valid
                DefaultTableModel model = (DefaultTableModel) tabelSiswa.getModel();
                String id = model.getValueAt(selectedRowIndex, 0).toString();
                String nipdSiswa = model.getValueAt(selectedRowIndex, 1).toString();
                String nama = model.getValueAt(selectedRowIndex, 2).toString();

                id_siswa.setText(id);
                nipd.setText(nipdSiswa);
                nama_siswa.setText(nama);
                datatableNilaiSiswa();
            }
        }
    }//GEN-LAST:event_tabelSiswaMouseClicked

    private void txtcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusGained
        String Pencarian = txtcari.getText();
        if (Pencarian.equals("Pencarian")) {
            txtcari.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariFocusGained

    private void txtcariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusLost
        String username = txtcari.getText();
        if (username.equals("") || username.equals("Pencarian")) {
            txtcari.setText("Pencarian");
        }
    }//GEN-LAST:event_txtcariFocusLost

    private void txtcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariMouseClicked

    }//GEN-LAST:event_txtcariMouseClicked

    private void txtcariMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariMouseExited

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyPressed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        DefaultTableModel obj = (DefaultTableModel) tabelSiswa.getModel();
        TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
        tabelSiswa.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(txtcari.getText()));
        obj1.setRowFilter(RowFilter.regexFilter("(?i)" + txtcari.getText()));
    }//GEN-LAST:event_txtcariKeyReleased

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyTyped

    private void jkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkelasActionPerformed
        jkelas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTabelGuru();
                tampilkanDataSiswa();
            }
        });
    }//GEN-LAST:event_jkelasActionPerformed

    void updateTabelGuru() {
        // Bersihkan isi tabel guru sebelum menambahkan data baru
        DefaultTableModel model = (DefaultTableModel) tabelguru.getModel();
        model.setRowCount(0);

        // Ambil nilai yang dipilih dari combo box jkelas
        String kelas = (String) jkelas.getSelectedItem();

        Object[] Baris = {"Wali Kelas"};
        tabmode = new DefaultTableModel(null, Baris);
        // Buat query SQL untuk mengambil data guru berdasarkan kelas yang dipilih
        try {
            String query = "SELECT nama_guru FROM guru WHERE wali_kelas = ?";
            ResultSet result = SQLConnection.doPreparedQuery(query, kelas);

            // Tambahkan data guru ke dalam tabel guru
            while (result.next()) {
                String namaGuru = result.getString("nama_guru");
                model.addRow(new Object[]{namaGuru});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void tampilkanDataSiswa() {
        // Ambil nilai kelas yang dipilih dari combo box jkelas
        String kelas = (String) jkelas.getSelectedItem();

        // Buat query SQL untuk mengambil data siswa berdasarkan kelas yang dipilih
        try {
            String query = "SELECT id_siswa, nipd, nama_siswa, kelamin, tempat_lahir, tanggal_lahir "
                    + "FROM siswa "
                    + "WHERE kelas = ?";
            ResultSet result = SQLConnection.doPreparedQuery(query, kelas);

            // Bersihkan isi tabel siswa sebelum menambahkan data baru
            DefaultTableModel model = (DefaultTableModel) tabelSiswa.getModel();
            model.setRowCount(0);

            // Tambahkan judul kolom ke tabel siswa
            model.setColumnIdentifiers(new Object[]{"ID Siswa", "NIPD", "Nama Siswa", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir"});

            // Tambahkan data siswa ke dalam tabel siswa
            while (result.next()) {
                String id = result.getString("id_siswa");
                String nipdSiswa = result.getString("nipd");
                String namaSiswa = result.getString("nama_siswa");
                String jenisKelamin = result.getString("kelamin");
                String tempatLahir = result.getString("tempat_lahir");
                String tanggalLahir = result.getString("tanggal_lahir");

                // Tambahkan baris baru ke dalam tabel siswa
                model.addRow(new Object[]{id, nipdSiswa, namaSiswa, jenisKelamin, tempatLahir, tanggalLahir});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void tabelguruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelguruMouseClicked
        
    }//GEN-LAST:event_tabelguruMouseClicked

    private void btnInputNilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputNilaiActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(dataNilai);
        mainpanel.repaint();
        mainpanel.revalidate();
        tabelSiswa.clearSelection();
        
        TitleNilaiSiswa.setText("Data Nilai Siswa "+ nama_siswa.getText() + "(" + id_siswa.getText() + ")");
    }//GEN-LAST:event_btnInputNilaiActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed

        jkelas.revalidate();
        jkelas.repaint();
        
        id_siswa.setText("");
        nipd.setText("");
        nama_siswa.setText("");
    }//GEN-LAST:event_batalActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseClicked

    private void semesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semesterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semesterActionPerformed

    private void nipdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nipdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nipdActionPerformed

    private void nama_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_siswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_siswaActionPerformed

    private void id_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_siswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_siswaActionPerformed

    protected void datatableNilaiSiswa() {
        Object[] Baris = {"ID", "Kode Mapel", "Nama Mata Pelajaran", "Semester", "Tahun Ajaran"};
        tabmode = new DefaultTableModel(null, Baris);

        try {
            String query = "SELECT nilai.*, mapel.id_mapel, mapel.nama_mapel FROM nilai "
                    + "JOIN mapel ON mapel.id_mapel = nilai.mapel_id "
                    + "JOIN siswa ON siswa.id_siswa = nilai.siswa_id "
                    + "WHERE siswa.id_siswa = ?";
            ResultSet result = SQLConnection.doPreparedQuery(
                    query,
                    id_siswa.getText()
            );
            
            while (result.next()) {
                tabmode.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("id_mapel"),
                    result.getString("nama_mapel"),
                    result.getString("semester"),
                    result.getString("tahun_ajaran")
                });
            }
            tabelNilaiSiswa.setModel(tabmode);
            btnEditNilaiSiswa.setVisible(false);
            btnHapusNilaiSiswa.setVisible(false);
            btnCancelNilaiSiswa.setVisible(false);
            DefaultTableModel obj = (DefaultTableModel) tabelNilaiSiswa.getModel();
            TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
            tabelNilaiSiswa.setRowSorter(obj1);
            obj.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.DELETE) {
                        // Data telah dihapus, perbarui filter
                        updateFilter();
                    }
                }

                private void updateFilter() {
                    String searchText = txtcariNilaiSiswa.getText();
                    // Periksa apakah ada teks pencarian yang diterapkan
                    if (searchText.isEmpty()) {
                        // Jika tidak ada teks pencarian, hapus filter dan tampilkan semua data
                        obj1.setRowFilter(null);
                    } else {
                        // Jika ada teks pencarian, terapkan filter
                        RowFilter<DefaultTableModel, Object> rf = null;
                        try {
                            rf = RowFilter.regexFilter("(?i)" + searchText);
                        } catch (java.util.regex.PatternSyntaxException e) {
                            return;
                        }
                        obj1.setRowFilter(rf);
                    }
                }
            });
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }
    }
    
    private void tabelNilaiSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelNilaiSiswaMouseClicked
        if (evt.getClickCount() == 1) { // Pastikan hanya single click
            int selectedRowIndex = tabelNilaiSiswa.getSelectedRow();
            int selectedModelRowIndex = tabelNilaiSiswa.convertRowIndexToModel(selectedRowIndex);
            if (selectedModelRowIndex != -1) { // Pastikan indeks baris valid
                DefaultTableModel model = (DefaultTableModel) tabelNilaiSiswa.getModel();
                int a = Integer.parseInt(model.getValueAt(selectedModelRowIndex, 0).toString());
                String b = model.getValueAt(selectedModelRowIndex, 1).toString();
                String c = model.getValueAt(selectedModelRowIndex, 2).toString();
                
                try {
                    String query = "SELECT nilai.*, mapel.id_mapel, mapel.nama_mapel FROM nilai "
                            + "JOIN mapel ON mapel.id_mapel = nilai.mapel_id "
                            + "WHERE nilai.id = ?";
                    ResultSet result = SQLConnection.doPreparedQuery(
                            query,
                            a
                    );
    
                    while (result.next()) {
                        String mapel = result.getString("id_mapel") + "-" + result.getString("nama_mapel");
                        idNilai = result.getInt("id");
                        mapel_id.setSelectedItem(mapel);
                        id_siswa.setText(result.getString("siswa_id"));
                        semester.setSelectedItem(result.getString("semester"));
                        tahun_ajaran.setText(result.getString("tahun_ajaran"));
                        nilai_mapel.setText(result.getString("nilai_mapel"));
                        nilai_tugas.setText(result.getString("nilai_tugas"));
                        nilai_uts.setText(result.getString("nilai_uts"));
                        nilai_uas.setText(result.getString("nilai_uas"));
                        predikat_sikap.setSelectedItem(result.getString("predikat_sikap"));
                        predikat_keterampilan.setSelectedItem(result.getString("predikat_keterampilan"));
                        predikat_kompetensi.setSelectedItem(result.getString("predikat_kompetensi"));
                        catatan.setText(result.getString("catatan"));
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "data gagal diambil: " + e);
                }
            }
        }
    }//GEN-LAST:event_tabelNilaiSiswaMouseClicked

    private void txtcariNilaiSiswaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaFocusGained
        String Pencarian = txtcariNilaiSiswa.getText();
        if (Pencarian.equals("Pencarian")) {
            txtcariNilaiSiswa.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariNilaiSiswaFocusGained

    private void txtcariNilaiSiswaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaFocusLost
        String username = txtcariNilaiSiswa.getText();
        if (username.equals("") || username.equals("Pencarian")) {
            txtcariNilaiSiswa.setText("Pencarian");
        }
    }//GEN-LAST:event_txtcariNilaiSiswaFocusLost

    private void txtcariNilaiSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaMouseClicked

    }//GEN-LAST:event_txtcariNilaiSiswaMouseClicked

    private void txtcariNilaiSiswaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariNilaiSiswaMouseExited

    private void txtcariNilaiSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariNilaiSiswaActionPerformed

    private void txtcariNilaiSiswaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariNilaiSiswaKeyPressed

    private void txtcariNilaiSiswaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaKeyReleased
        DefaultTableModel obj = (DefaultTableModel) tabelNilaiSiswa.getModel();
        TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
        tabelNilaiSiswa.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(txtcariNilaiSiswa.getText()));
        obj1.setRowFilter(RowFilter.regexFilter("(?i)" + txtcariNilaiSiswa.getText()));
    }//GEN-LAST:event_txtcariNilaiSiswaKeyReleased

    private void txtcariNilaiSiswaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariNilaiSiswaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariNilaiSiswaKeyTyped

    private void btnHapusNilaiSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusNilaiSiswaActionPerformed
        int barisTerpilih = tabelNilaiSiswa.getSelectedRow();

        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            int konfirmasi = JOptionPane.showConfirmDialog(null, "<html>Apakah Anda Akan Menghapus Data Nilai <b>"+ id_siswa.getText() +"</b> <b>" + nama_siswa.getText() + "</b> </html>", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                try {
                    String query = "DELETE FROM nilai WHERE id = ?";
                    Boolean delete = SQLConnection.doPreparedUpdate(query, idNilai);

                    if (Boolean.TRUE.equals(delete)) {
                        JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                        kosong();
                        btnTambahNilaiSiswa.setVisible(true);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "data gagal dihapus: " + e);
                }
            }
            datatableNilaiSiswa();
        }        

// TODO add your handling code here:
    }//GEN-LAST:event_btnHapusNilaiSiswaActionPerformed

    private void btnEditNilaiSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNilaiSiswaActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(PanelNilai);
        mainpanel.repaint();
        mainpanel.revalidate();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditNilaiSiswaActionPerformed

    private void btnCancelNilaiSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelNilaiSiswaActionPerformed
        datatableNilaiSiswa();
    }//GEN-LAST:event_btnCancelNilaiSiswaActionPerformed

    private void btnTambahNilaiSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahNilaiSiswaActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(PanelNilai);
        mainpanel.repaint();
        mainpanel.revalidate();
        tabelNilaiSiswa.clearSelection();// TODO add your handling code here:
        idNilai = 0;
    }//GEN-LAST:event_btnTambahNilaiSiswaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(dataSiswa);
        mainpanel.repaint();
        mainpanel.revalidate();
        
        btnInputNilai.setVisible(false);
        batal.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(dataNilai);
        mainpanel.repaint();
        mainpanel.revalidate();
        updateCombo();

    }//GEN-LAST:event_keluarActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        JTextField[] requiredTextFields = new JTextField[] {
            tahun_ajaran,
            nilai_mapel,
            nilai_tugas,
            nilai_uts,
            nilai_uas
        };
        JComboBox[] requiredComboBox = new JComboBox[] {
            semester,
            predikat_sikap,
            predikat_keterampilan,
            predikat_kompetensi
        };

        for (JTextField tf : requiredTextFields) {
            if (tf.getText().equals("")) {
                JOptionPane.showMessageDialog(null,  " wajib diisi.");
                return;
            }
        }
        for (JComboBox cb : requiredComboBox) {
            if (cb.getSelectedItem().toString().equals("-- Pilih Mapel --")) {
                JOptionPane.showMessageDialog(null, " wajib diisi.");
                return;
            }
            if (cb.getSelectedItem().toString().equals("-- Pilih Semester --")) {
                JOptionPane.showMessageDialog(null, " wajib diisi.");
                return;
            }
            if (cb.getSelectedItem().toString().equals("-- Pilih Predikat --")) {
                JOptionPane.showMessageDialog(null,  cb.toString()+" wajib diisi.");
                return;
            }
        }
        try {
            Boolean insert = false;
            String idMapel = mapel_id.getSelectedItem().toString().split("-")[0];
            if (idNilai > 0) {
                String query = "UPDATE nilai SET mapel_id=?,siswa_id=?,semester=?,tahun_ajaran=?,nilai_mapel=?,nilai_tugas=?,nilai_uts=?,nilai_uas=?,predikat_sikap=?,predikat_keterampilan=?,predikat_kompetensi=?,catatan=? WHERE id =?";
                insert = SQLConnection.doPreparedUpdate(
                    query,
                    idMapel,
                    id_siswa.getText(),
                    semester.getSelectedItem().toString(),
                    tahun_ajaran.getText(),
                    nilai_mapel.getText(),
                    nilai_tugas.getText(),
                    nilai_uts.getText(),
                    nilai_uas.getText(),
                    predikat_sikap.getSelectedItem().toString(),
                    predikat_keterampilan.getSelectedItem().toString(),
                    predikat_kompetensi.getSelectedItem().toString(),
                    catatan.getText(),
                    idNilai
                );
            }else {
                String query = "INSERT INTO nilai (mapel_id,siswa_id,semester,tahun_ajaran,nilai_mapel,nilai_tugas,nilai_uts,nilai_uas,predikat_sikap,predikat_keterampilan,predikat_kompetensi,catatan) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                insert = SQLConnection.doPreparedUpdate(
                    query,
                    idMapel,
                    id_siswa.getText(),
                    semester.getSelectedItem().toString(),
                    tahun_ajaran.getText(),
                    nilai_mapel.getText(),
                    nilai_tugas.getText(),
                    nilai_uts.getText(),
                    nilai_uas.getText(),
                    predikat_sikap.getSelectedItem().toString(),
                    predikat_keterampilan.getSelectedItem().toString(),
                    predikat_kompetensi.getSelectedItem().toString(),
                    catatan.getText()
                );
            }

            if (Boolean.TRUE.equals(insert)) {
                JOptionPane.showMessageDialog(null, "data berhasil disimpan");
                keluarActionPerformed(null);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan" + e);
        }
        datatableNilaiSiswa();
    }//GEN-LAST:event_simpanActionPerformed

    private void predikat_kompetensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predikat_kompetensiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_predikat_kompetensiActionPerformed

    private void predikat_keterampilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predikat_keterampilanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_predikat_keterampilanActionPerformed

    private void nilai_uasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilai_uasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nilai_uasActionPerformed

    private void nilai_utsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilai_utsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nilai_utsActionPerformed

    private void nilai_tugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilai_tugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nilai_tugasActionPerformed

    private void nilai_mapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilai_mapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nilai_mapelActionPerformed

    private void mapel_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapel_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mapel_idActionPerformed

    private void predikat_sikapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predikat_sikapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_predikat_sikapActionPerformed

    private void kosong()
    {
        mapel_id.setSelectedItem("-- Pilih Mapel --");
        semester.setSelectedItem("-- Pilih Semester --");
        tahun_ajaran.setText("");
        nilai_mapel.setText("");
        nilai_tugas.setText("");
        nilai_uts.setText("");
        nilai_uas.setText("");
        predikat_sikap.setSelectedItem("-- Pilih Predikat --");
        predikat_keterampilan.setSelectedItem("-- Pilih Predikat --");
        predikat_kompetensi.setSelectedItem("-- Pilih Predikat --");
        catatan.setText("");
    }


    private int idNilai = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelNilai;
    private javax.swing.JLabel TitleNilaiSiswa;
    private javax.swing.JButton batal;
    private javax.swing.JButton btnCancelNilaiSiswa;
    private javax.swing.JButton btnEditNilaiSiswa;
    private javax.swing.JButton btnHapusNilaiSiswa;
    private javax.swing.JButton btnInputNilai;
    private javax.swing.JButton btnTambahNilaiSiswa;
    private javax.swing.JTextArea catatan;
    private javax.swing.JPanel dataNilai;
    private javax.swing.JPanel dataSiswa;
    private javax.swing.JTextField id_siswa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> jkelas;
    private javax.swing.JButton keluar;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JComboBox<String> mapel_id;
    private javax.swing.JTextField nama_siswa;
    private javax.swing.JTextField nilai_mapel;
    private javax.swing.JTextField nilai_tugas;
    private javax.swing.JTextField nilai_uas;
    private javax.swing.JTextField nilai_uts;
    private javax.swing.JTextField nipd;
    private javax.swing.JComboBox<String> predikat_keterampilan;
    private javax.swing.JComboBox<String> predikat_kompetensi;
    private javax.swing.JComboBox<String> predikat_sikap;
    private javax.swing.JComboBox<String> semester;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabelNilaiSiswa;
    private javax.swing.JTable tabelSiswa;
    private javax.swing.JTable tabelguru;
    private javax.swing.JTextField tahun_ajaran;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcariNilaiSiswa;
    // End of variables declaration//GEN-END:variables
}
