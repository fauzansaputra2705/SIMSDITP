package simsditp;

import Database.SQLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class Siswa extends javax.swing.JPanel {

    private DefaultTableModel tabmode;

    public Siswa() {
        initComponents();
        autonumber();
        updateCombo();
        updateCombo1();
        txtlabel.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtlabel1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtlabel2.setBackground(new java.awt.Color(0, 0, 0, 1));
        jlabel.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtcari.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtid.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtnipd.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtnama.setBackground(new java.awt.Color(0, 0, 0, 1));
        txttempatlahir.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtid1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtnipd1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtnama1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txttempatlahir1.setBackground(new java.awt.Color(0, 0, 0, 1));
        tabelsiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Dapatkan baris yang diklik
                int row = tabelsiswa.getSelectedRow();
                // Tampilkan tombol edit, hapus, dan batal jika ada baris yang dipilih
                if (row != -1) {
                    edit.setVisible(true);
                    hapus.setVisible(true);
                    batal.setVisible(true);
                    tambah.setVisible(false); // Sembunyikan tombol tambah
                } else {
                    edit.setVisible(false);
                    hapus.setVisible(false);
                    batal.setVisible(false);
                    tambah.setVisible(true); // Tampilkan tombol tambah kembali
                }
            }
        });

        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Sembunyikan tombol-tombol
                edit.setVisible(false);
                hapus.setVisible(false);
                batal.setVisible(false);
                tabelsiswa.clearSelection();
                tambah.setVisible(true);
                kosong();
                autonumber();
            }
        });
        keluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Sembunyikan tombol-tombol
                edit.setVisible(false);
                hapus.setVisible(false);
                batal.setVisible(false);
                tabelsiswa.clearSelection();
                tambah.setVisible(true);
                kosong();
                autonumber();
            }
        });

// Sembunyikan tombol edit saat frame pertama kali dibuat
        edit.setVisible(false);

    }

    protected void kosong() {
        txtnipd.setText("");
        txtnama.setText("");
        cbkelamin.setSelectedItem(0);
        cbkelas.setSelectedItem(0);
        txttempatlahir.setText("");
        jtanggal.setDate(null);
        txtbutton.clearSelection();
    }

    protected void kosong1() {
        txtnipd1.setText("");
        txtnama1.setText("");
        cbkelamin1.setSelectedItem(0);
        cbkelas1.setSelectedItem(0);
        txttempatlahir1.setText("");
        jtanggal1.setDate(null);
        txtbutton.clearSelection();
    }

    private void autonumber() {
        try {
            String query = "SELECT MAX(id_siswa) AS id FROM siswa";
            ResultSet result = SQLConnection.doQuery(query);
            
            while (result.next()) {
                Object[] obj = new Object[1];
                obj[0] = result.getString("id");
                if (obj[0] == null) {
                    obj[0] = "S000";
                }
                String str = (String) obj[0];
                String kd = str.substring(1, 4);
                int int_code = Integer.parseInt(kd);
                int_code++;
                String a = String.format("%03d", int_code);
                String b = "S" + a;
                txtid.setText(b);
                txtid.setEditable(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void datatable() {
        Object[] Baris = {"ID Siswa", "NIPD", "Nama Siswa", "Kelas", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir"};
        tabmode = new DefaultTableModel(null, Baris);

        try {
            String query = "SELECT * FROM siswa ORDER BY id_siswa ASC";
            ResultSet result = SQLConnection.doQuery(query);
            
            while (result.next()) {
                tabmode.addRow(new Object[]{
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),});
            }
            tabelsiswa.setModel(tabmode);
            edit.setVisible(false);
            hapus.setVisible(false);
            batal.setVisible(false);
            DefaultTableModel obj = (DefaultTableModel) tabelsiswa.getModel();
            TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
            tabelsiswa.setRowSorter(obj1);
            obj.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.DELETE) {
                        // Data telah dihapus, perbarui filter
                        updateFilter();
                    }
                }

                private void updateFilter() {
                    String searchText = txtcari.getText();
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }
    }

    private void updateCombo() {
        try {
            String query = "SELECT * FROM kelas";
            ResultSet result = SQLConnection.doQuery(query);
            while (result.next()) {
                cbkelas.addItem(result.getString("kelas"));
            }
        } catch (SQLException e) {
        }
    }
    
    private void updateCombo1() {
        try {
            String query = "SELECT * FROM kelas";
            ResultSet result = SQLConnection.doQuery(query);
            while (result.next()) {
                cbkelas1.addItem(result.getString("kelas"));
            }
        } catch (SQLException e) {
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
        dataSiswa = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelsiswa = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtlabel = new javax.swing.JTextField();
        tambahSiswa = new javax.swing.JPanel();
        jlabel = new javax.swing.JLabel();
        txtlabel1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbkelas = new javax.swing.JComboBox<>();
        txttempatlahir = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        cbkelamin = new javax.swing.JComboBox<>();
        txtnipd = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        keluar = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        editSiswa = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtlabel2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txttempatlahir1 = new javax.swing.JTextField();
        cbkelamin1 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        txtnipd1 = new javax.swing.JTextField();
        txtid1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtnama1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cbkelas1 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        keluar1 = new javax.swing.JButton();
        simpan1 = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        mainpanel.setLayout(new java.awt.CardLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        tabelsiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelsiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelsiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelsiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelsiswa);

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

        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hapus.png"))); // NOI18N
        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(batal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcari, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tambah)
                        .addComponent(edit)
                        .addComponent(hapus)
                        .addComponent(batal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Data Siswa");

        txtlabel.setEditable(false);
        txtlabel.setBackground(new java.awt.Color(255, 255, 255));
        txtlabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtlabel.setCaretColor(java.awt.Color.lightGray);
        txtlabel.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtlabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataSiswaLayout = new javax.swing.GroupLayout(dataSiswa);
        dataSiswa.setLayout(dataSiswaLayout);
        dataSiswaLayout.setHorizontalGroup(
            dataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataSiswaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(dataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtlabel)
                    .addGroup(dataSiswaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 980, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataSiswaLayout.setVerticalGroup(
            dataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataSiswaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(dataSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(dataSiswaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainpanel.add(dataSiswa, "card2");

        jlabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlabel.setText("Data Tambah Siswa");

        txtlabel1.setEditable(false);
        txtlabel1.setBackground(new java.awt.Color(255, 255, 255));
        txtlabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtlabel1.setCaretColor(java.awt.Color.lightGray);
        txtlabel1.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtlabel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabel1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel22.setText("Jenis Kelamin");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel23.setText("Nama Siswa");

        cbkelas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 A", "1 B", "1 C", "2 A", "2 B", "2 C", "3 A", "3 B", "3 C", "4 A", "4 B", "4 C", "5 A", "5 B", "5 C", "6 A", "6 B", "6 C" }));
        cbkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkelasActionPerformed(evt);
            }
        });
        cbkelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbkelasKeyPressed(evt);
            }
        });

        txttempatlahir.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txttempatlahir.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txttempatlahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttempatlahirActionPerformed(evt);
            }
        });
        txttempatlahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttempatlahirKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("ID Siswa");

        txtid.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidKeyPressed(evt);
            }
        });

        cbkelamin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbkelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - Laki", "Perempuan" }));
        cbkelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkelaminActionPerformed(evt);
            }
        });
        cbkelamin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbkelaminKeyPressed(evt);
            }
        });

        txtnipd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtnipd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtnipd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnipdActionPerformed(evt);
            }
        });
        txtnipd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnipdKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("NIPD");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel24.setText("Kelas");

        txtnama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtnama.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });
        txtnama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnamaKeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel21.setText("Tanggal Lahir");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel20.setText("Tempat Lahir");

        keluar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        keluar.setText("Cancel");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keluar)
                .addContainerGap(921, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txttempatlahir, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbkelamin, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbkelas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtnama, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtnipd, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(468, 468, 468)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keluar)
                    .addComponent(simpan))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addGap(0, 0, 0)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel19)
                    .addGap(0, 0, 0)
                    .addComponent(txtnipd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel23)
                    .addGap(0, 0, 0)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel24)
                    .addGap(0, 0, 0)
                    .addComponent(cbkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel22)
                    .addGap(0, 0, 0)
                    .addComponent(cbkelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel20)
                    .addGap(0, 0, 0)
                    .addComponent(txttempatlahir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel21)
                    .addContainerGap(91, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout tambahSiswaLayout = new javax.swing.GroupLayout(tambahSiswa);
        tambahSiswa.setLayout(tambahSiswaLayout);
        tambahSiswaLayout.setHorizontalGroup(
            tambahSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahSiswaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(tambahSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtlabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tambahSiswaLayout.createSequentialGroup()
                        .addComponent(jlabel)
                        .addGap(0, 882, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tambahSiswaLayout.setVerticalGroup(
            tambahSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahSiswaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tambahSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabel)
                    .addGroup(tambahSiswaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainpanel.add(tambahSiswa, "card2");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Edit Data Siswa");

        txtlabel2.setEditable(false);
        txtlabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtlabel2.setCaretColor(java.awt.Color.lightGray);
        txtlabel2.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtlabel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabel2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Nama Siswa");

        txttempatlahir1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txttempatlahir1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txttempatlahir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttempatlahir1ActionPerformed(evt);
            }
        });
        txttempatlahir1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttempatlahir1KeyPressed(evt);
            }
        });

        cbkelamin1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbkelamin1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - Laki", "Perempuan" }));
        cbkelamin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkelamin1ActionPerformed(evt);
            }
        });
        cbkelamin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbkelamin1KeyPressed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Tanggal Lahir");

        txtnipd1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtnipd1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtnipd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnipd1ActionPerformed(evt);
            }
        });
        txtnipd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnipd1KeyPressed(evt);
            }
        });

        txtid1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtid1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid1ActionPerformed(evt);
            }
        });
        txtid1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtid1KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("ID Siswa");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Tempat Lahir");

        txtnama1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtnama1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtnama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnama1ActionPerformed(evt);
            }
        });
        txtnama1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnama1KeyPressed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("NIPD");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Kelas");

        cbkelas1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbkelas1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 A", "1 B", "1 C", "2 A", "2 B", "2 C", "3 A", "3 B", "3 C", "4 A", "4 B", "4 C", "5 A", "5 B", "5 C", "6 A", "6 B", "6 C" }));
        cbkelas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkelas1ActionPerformed(evt);
            }
        });
        cbkelas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbkelas1KeyPressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Jenis Kelamin");

        keluar1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        keluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        keluar1.setText("Cancel");
        keluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluar1ActionPerformed(evt);
            }
        });

        simpan1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        simpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        simpan1.setText("Save");
        simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simpan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(keluar1)
                .addContainerGap(915, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(1008, 1008, 1008))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txttempatlahir1)
                                .addComponent(cbkelas1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtnama1)
                                .addComponent(txtnipd1)
                                .addComponent(txtid1)
                                .addComponent(cbkelamin1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap()))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(480, 480, 480)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keluar1)
                    .addComponent(simpan1))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6)
                    .addGap(0, 0, 0)
                    .addComponent(txtid1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel25)
                    .addGap(0, 0, 0)
                    .addComponent(txtnipd1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel29)
                    .addGap(0, 0, 0)
                    .addComponent(txtnama1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel30)
                    .addGap(0, 0, 0)
                    .addComponent(cbkelas1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel28)
                    .addGap(0, 0, 0)
                    .addComponent(cbkelamin1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel26)
                    .addGap(0, 0, 0)
                    .addComponent(txttempatlahir1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel27)
                    .addContainerGap(100, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout editSiswaLayout = new javax.swing.GroupLayout(editSiswa);
        editSiswa.setLayout(editSiswaLayout);
        editSiswaLayout.setHorizontalGroup(
            editSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSiswaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(editSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editSiswaLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editSiswaLayout.createSequentialGroup()
                        .addGroup(editSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtlabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        editSiswaLayout.setVerticalGroup(
            editSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSiswaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(editSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(editSiswaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainpanel.add(editSiswa, "card2");

        add(mainpanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void tabelsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelsiswaMouseClicked
        int selectedRowIndex = tabelsiswa.getSelectedRow();
        int selectedModelRowIndex = tabelsiswa.convertRowIndexToModel(selectedRowIndex);
        if (selectedModelRowIndex != -1) { // Pastikan indeks baris valid
            DefaultTableModel model = (DefaultTableModel) tabelsiswa.getModel();
            String a = model.getValueAt(selectedModelRowIndex, 0).toString();
            String b = model.getValueAt(selectedModelRowIndex, 1).toString();
            String c = model.getValueAt(selectedModelRowIndex, 2).toString();
            String d = model.getValueAt(selectedModelRowIndex, 3).toString();
            String e = model.getValueAt(selectedModelRowIndex, 4).toString();
            String f = model.getValueAt(selectedModelRowIndex, 5).toString();
            String g = model.getValueAt(selectedModelRowIndex, 6).toString();

            txtid1.setText(a);
            txtnipd1.setText(b);
            txtnama1.setText(c);
            cbkelas1.setSelectedItem(d);
            cbkelamin1.setSelectedItem(e);
            txttempatlahir1.setText(f);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date tanggal = sdf.parse(g);
                jtanggal1.setDate(tanggal);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        setTableNonEditable();
    }//GEN-LAST:event_tabelsiswaMouseClicked
    private void setTableNonEditable() {
        for (int i = 0; i < tabelsiswa.getColumnCount(); i++) {
            Class<?> col_class = tabelsiswa.getColumnClass(i);
            tabelsiswa.setDefaultEditor(col_class, null); // Menetapkan editor default ke null untuk semua kolom
        }
    }
    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyPressed

    private void txtlabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabelActionPerformed

    private void txtcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariMouseClicked

    }//GEN-LAST:event_txtcariMouseClicked

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyTyped

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        DefaultTableModel obj = (DefaultTableModel) tabelsiswa.getModel();
        TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
        tabelsiswa.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(txtcari.getText()));
        obj1.setRowFilter(RowFilter.regexFilter("(?i)" + txtcari.getText()));
    }//GEN-LAST:event_txtcariKeyReleased

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

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseClicked

    private void txtcariMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcariMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariMouseExited

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(tambahSiswa);
        mainpanel.repaint();
        mainpanel.revalidate();
        tabelsiswa.clearSelection();
    }//GEN-LAST:event_tambahActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(editSiswa);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_editActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_batalActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int barisTerpilih = tabelsiswa.getSelectedRow();

        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            int konfirmasi = JOptionPane.showConfirmDialog(null, "<html>Apakah Anda Akan Menghapus Data Siswa <b>" + txtid1.getText() + "</b> <b>" + txtnama1.getText() + "</b></html>", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String siswa = tabelsiswa.getValueAt(barisTerpilih, 0).toString();
                try {
                    String query = "DELETE FROM siswa WHERE id_siswa = ?";
                    Boolean delete = SQLConnection.doPreparedUpdate(query, txtid1.getText());
                    
                    if (Boolean.TRUE.equals(delete)) {
                        JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                        kosong();
                        datatable();
                        tambah.setVisible(true);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "data gagal dihapus" + e);
                }
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void txtnipd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnipd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnipd1ActionPerformed

    private void txtnipd1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnipd1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnipd1KeyPressed

    private void txttempatlahir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttempatlahir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttempatlahir1ActionPerformed

    private void txttempatlahir1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttempatlahir1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttempatlahir1KeyPressed

    private void cbkelas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkelas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkelas1ActionPerformed

    private void cbkelas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbkelas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkelas1KeyPressed

    private void simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan1ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(jtanggal1.getDate());
        try {
            String query = "UPDATE siswa SET id_siswa=?,nipd=?,nama_siswa=?,kelas=?,kelamin=?,tempat_lahir=?,tanggal_lahir=? where id_siswa=?";
            Boolean update = SQLConnection.doPreparedUpdate(
                    query, 
                    txtid1.getText(),
                    txtnipd1.getText(),
                    txtnama1.getText(),
                    cbkelas1.getSelectedItem().toString(),
                    cbkelamin1.getSelectedItem().toString(),
                    txttempatlahir1.getText(),
                    date,
                    txtid1.getText()
            );
            
            if (Boolean.TRUE.equals(update)) {
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang diubah");
            }
            kosong1();
            autonumber();
            datatable();
            keluar1ActionPerformed(null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah: " + e.getMessage());
        }
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_simpan1ActionPerformed

    private void keluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluar1ActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(dataSiswa);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_keluar1ActionPerformed

    private void cbkelamin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkelamin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkelamin1ActionPerformed

    private void cbkelamin1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbkelamin1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkelamin1KeyPressed

    private void txtnama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnama1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama1ActionPerformed

    private void txtnama1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnama1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama1KeyPressed

    private void jtanggal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtanggal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtanggal1KeyPressed

    private void txtid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid1ActionPerformed

    private void txtid1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtid1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid1KeyPressed

    private void txtlabel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabel2ActionPerformed

    private void jtanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtanggalKeyPressed

    private void txtnamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbkelas.requestFocus();
            cbkelas.showPopup();
        }
    }//GEN-LAST:event_txtnamaKeyPressed

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void cbkelaminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbkelaminKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txttempatlahir.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cbkelaminKeyPressed

    private void cbkelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkelaminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkelaminActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(dataSiswa);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_keluarActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(jtanggal.getDate());
        try {
            String query = "INSERT INTO siswa (id_siswa, nipd, nama_siswa, kelas, kelamin, tempat_lahir, tanggal_lahir) VALUES (?,?,?,?,?,?,?)";
            Boolean insert = SQLConnection.doPreparedUpdate(
                    query, 
                    txtid.getText(),
                    txtnipd.getText(),
                    txtnama.getText(),
                    cbkelas.getSelectedItem().toString(),
                    cbkelamin.getSelectedItem().toString(),
                    txttempatlahir.getText(),
                    date
            );
            if (Boolean.TRUE.equals(insert)) {
                JOptionPane.showMessageDialog(null, "data berhasil disimpan");
                autonumber();
                kosong();
                keluarActionPerformed(null);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan" + e);
        }
        datatable();   
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanActionPerformed

    private void cbkelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbkelasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbkelamin.requestFocus();
            cbkelamin.showPopup();
        }// TODO add your handling code here:
    }//GEN-LAST:event_cbkelasKeyPressed

    private void cbkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkelasActionPerformed

    private void txttempatlahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttempatlahirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttempatlahirKeyPressed

    private void txttempatlahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttempatlahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttempatlahirActionPerformed

    private void txtnipdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnipdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtnama.requestFocus();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtnipdKeyPressed

    private void txtnipdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnipdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnipdActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtnipd.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtidKeyPressed

    private void txtlabel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JComboBox<String> cbkelamin;
    private javax.swing.JComboBox<String> cbkelamin1;
    private javax.swing.JComboBox<String> cbkelas;
    private javax.swing.JComboBox<String> cbkelas1;
    private javax.swing.JPanel dataSiswa;
    private javax.swing.JButton edit;
    private javax.swing.JPanel editSiswa;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel;
    private javax.swing.JButton keluar;
    private javax.swing.JButton keluar1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JButton simpan;
    private javax.swing.JButton simpan1;
    private javax.swing.JTable tabelsiswa;
    private javax.swing.JButton tambah;
    private javax.swing.JPanel tambahSiswa;
    private javax.swing.ButtonGroup txtbutton;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtid1;
    private javax.swing.JTextField txtlabel;
    private javax.swing.JTextField txtlabel1;
    private javax.swing.JTextField txtlabel2;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnama1;
    private javax.swing.JTextField txtnipd;
    private javax.swing.JTextField txtnipd1;
    private javax.swing.JTextField txttempatlahir;
    private javax.swing.JTextField txttempatlahir1;
    // End of variables declaration//GEN-END:variables
}
