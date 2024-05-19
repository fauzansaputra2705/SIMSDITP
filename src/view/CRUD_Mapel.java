package view;

import koneksi.koneksi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CRUD_Mapel extends javax.swing.JPanel {

    private Connection conn = new koneksi().getKoneksi();
    private DefaultTableModel tabmode;

    public CRUD_Mapel() {
        initComponents();
        autonumber();
        txtlabel.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtlabel1.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtlabel2.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtcari.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtkode.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtkode1.setBackground(new java.awt.Color(0, 0, 0, 1));
        edit.setVisible(false);
        hapus.setVisible(false);
        cancel.setVisible(false);
        tabelmapel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Dapatkan baris yang diklik
                int row = tabelmapel.getSelectedRow();
                // Tampilkan tombol edit, hapus, dan batal jika ada baris yang dipilih
                if (row != -1) {
                    edit.setVisible(true);
                    hapus.setVisible(true);
                    cancel.setVisible(true);
                    tambah.setVisible(false); // Sembunyikan tombol tambah
                } else {
                    edit.setVisible(false);
                    hapus.setVisible(false);
                    cancel.setVisible(false);
                    tambah.setVisible(true); // Tampilkan tombol tambah kembali
                }
            }
        });

        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Sembunyikan tombol-tombol
                edit.setVisible(false);
                hapus.setVisible(false);
                cancel.setVisible(false);
                tabelmapel.clearSelection();
                tambah.setVisible(true);
                kosong();
                autonumber();
            }
        });
        batal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Sembunyikan tombol-tombol
                edit.setVisible(false);
                hapus.setVisible(false);
                cancel.setVisible(false);
                tabelmapel.clearSelection();
                tambah.setVisible(true);
                kosong();
                autonumber();
            }
        });
    }

    protected void aktif() {
        cbkategori.setSelectedItem(null);
    }

    protected void kosong() {
        cbkategori.setSelectedItem(0);
        cbmapel.setSelectedItem(0);
    }

    private void autonumber() {
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT MAX(id_mapel) AS id FROM mapel";
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                Object[] obj = new Object[1];
                obj[0] = res.getString("id");
                if (obj[0] == null) {
                    obj[0] = "MP000";
                }
                String str = (String) obj[0];
                String kd = str.substring(2, 5);
                int int_code = Integer.parseInt(kd);
                int_code++;
                String a = String.format("%03d", int_code);
                String b = "MP" + a;
                txtkode.setText(b);
                txtkode.setEditable(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void datatable() {
        Object[] Baris = {"Kode Mapel", "Nama Mata Pelajaran", "Kategori"};
        tabmode = new DefaultTableModel(null, Baris);

        try {
            String sql = "SELECT * FROM mapel where id_mapel like '%" + "%' or nama_mapel like '%" + "%' order by id_mapel asc";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                tabmode.addRow(new Object[]{
                    hasil.getString(1),
                    hasil.getString(2),
                    hasil.getString(3),});
            }
            tabelmapel.setModel(tabmode);
            edit.setVisible(false);
            hapus.setVisible(false);
            cancel.setVisible(false);
            DefaultTableModel obj = (DefaultTableModel) tabelmapel.getModel();
            TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
            tabelmapel.setRowSorter(obj1);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        dataMapel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelmapel = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        hapus = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtlabel = new javax.swing.JTextField();
        tambahMapel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtkode = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        simpan = new javax.swing.JButton();
        cbmapel = new javax.swing.JComboBox<>();
        cbkategori = new javax.swing.JComboBox<>();
        batal = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtlabel1 = new javax.swing.JTextField();
        editMapel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtkode1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        simpan1 = new javax.swing.JButton();
        cbmapel1 = new javax.swing.JComboBox<>();
        cbkategori1 = new javax.swing.JComboBox<>();
        batal1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtlabel2 = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("DATA TAMBAH SISWA");

        setLayout(new java.awt.CardLayout());

        mainpanel.setLayout(new java.awt.CardLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tabelmapel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "KODE MAPEL", "MATA PELAJARAN", "KATEGORI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelmapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelmapelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelmapel);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cari.png"))); // NOI18N

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

        hapus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hapus.png"))); // NOI18N
        hapus.setText("Delete");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        edit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        cancel.setText("Batal");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hapus)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edit)
                        .addComponent(cancel)
                        .addComponent(tambah))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Data Mata Pelajaran");

        txtlabel.setEditable(false);
        txtlabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtlabel.setCaretColor(java.awt.Color.lightGray);
        txtlabel.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtlabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataMapelLayout = new javax.swing.GroupLayout(dataMapel);
        dataMapel.setLayout(dataMapelLayout);
        dataMapelLayout.setHorizontalGroup(
            dataMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataMapelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(dataMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtlabel)
                    .addGroup(dataMapelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 748, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataMapelLayout.setVerticalGroup(
            dataMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataMapelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(dataMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(dataMapelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainpanel.add(dataMapel, "card2");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtkode.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtkode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkodeActionPerformed(evt);
            }
        });
        txtkode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtkodeKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Nama Mapel");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Kategori");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Kode Mapel");

        simpan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        simpan.setText("Save");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        cbmapel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIDIKAN AGAMA ISLAM", "BAHASA INDONESIA", "PPKN", "MATEMATIKA", "IPA", "IPS", "PJOK", "SBdP", "PLBJ", "FIQIH", "BAHASA INGGRIS" }));
        cbmapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmapelActionPerformed(evt);
            }
        });
        cbmapel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbmapelKeyPressed(evt);
            }
        });

        cbkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "WAJIB", "MUATAN LOKAL" }));

        batal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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
                    .addComponent(txtkode)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(batal)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbmapel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbkategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(10, 10, 10)
                .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(cbmapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(10, 10, 10)
                .addComponent(cbkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(simpan)
                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Tambah Data Mata Pelajaran");

        txtlabel1.setEditable(false);
        txtlabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtlabel1.setCaretColor(java.awt.Color.lightGray);
        txtlabel1.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtlabel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tambahMapelLayout = new javax.swing.GroupLayout(tambahMapel);
        tambahMapel.setLayout(tambahMapelLayout);
        tambahMapelLayout.setHorizontalGroup(
            tambahMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahMapelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(tambahMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtlabel1)
                    .addGroup(tambahMapelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 650, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tambahMapelLayout.setVerticalGroup(
            tambahMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahMapelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tambahMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(tambahMapelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainpanel.add(tambahMapel, "card2");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtkode1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtkode1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtkode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkode1ActionPerformed(evt);
            }
        });
        txtkode1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtkode1KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Nama Mapel");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Kategori");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Kode Mapel");

        simpan1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        simpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        simpan1.setText("Save");
        simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan1ActionPerformed(evt);
            }
        });

        cbmapel1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIDIKAN AGAMA ISLAM", "BAHASA INDONESIA", "PPKN", "MATEMATIKA", "IPA", "IPS", "PJOK", "SBdP", "PLBJ", "FIQIH", "BAHASA INGGRIS" }));
        cbmapel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbmapel1KeyPressed(evt);
            }
        });

        cbkategori1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "WAJIB", "MUATAN LOKAL" }));

        batal1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        batal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/batal.png"))); // NOI18N
        batal1.setText("Batal");
        batal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtkode1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(simpan1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(batal1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbmapel1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbkategori1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(10, 10, 10)
                .addComponent(txtkode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(10, 10, 10)
                .addComponent(cbmapel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(10, 10, 10)
                .addComponent(cbkategori1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(simpan1)
                    .addComponent(batal1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Edit Data Mata Pelajaran");

        txtlabel2.setEditable(false);
        txtlabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtlabel2.setCaretColor(java.awt.Color.lightGray);
        txtlabel2.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtlabel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlabel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editMapelLayout = new javax.swing.GroupLayout(editMapel);
        editMapel.setLayout(editMapelLayout);
        editMapelLayout.setHorizontalGroup(
            editMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editMapelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(editMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtlabel2)
                    .addGroup(editMapelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 697, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editMapelLayout.setVerticalGroup(
            editMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editMapelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(editMapelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(editMapelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainpanel.add(editMapel, "card2");

        add(mainpanel, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void tabelmapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelmapelMouseClicked
        if (evt.getClickCount() == 1) { // Pastikan hanya single click
            int selectedRowIndex = tabelmapel.getSelectedRow();
            int selectedModelRowIndex = tabelmapel.convertRowIndexToModel(selectedRowIndex);
            if (selectedModelRowIndex != -1) { // Pastikan indeks baris valid
                DefaultTableModel model = (DefaultTableModel) tabelmapel.getModel();
                String a = model.getValueAt(selectedModelRowIndex, 0).toString();
                String b = model.getValueAt(selectedModelRowIndex, 1).toString();
                String c = model.getValueAt(selectedModelRowIndex, 2).toString();

                txtkode1.setText(a);
                cbmapel1.setSelectedItem(b);
                cbkategori1.setSelectedItem(c);
            }
        }

        // Setelah mengisi komponen lain, atur tabel menjadi tidak dapat diedit
        setTableNonEditable();
    }//GEN-LAST:event_tabelmapelMouseClicked

    private void setTableNonEditable() {
        for (int i = 0; i < tabelmapel.getColumnCount(); i++) {
            Class<?> col_class = tabelmapel.getColumnClass(i);
            tabelmapel.setDefaultEditor(col_class, null); // Menetapkan editor default ke null untuk semua kolom
        }
    }
    private void txtlabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabelActionPerformed

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
        DefaultTableModel obj = (DefaultTableModel) tabelmapel.getModel();
        TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
        tabelmapel.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(txtcari.getText()));
        obj1.setRowFilter(RowFilter.regexFilter("(?i)" + txtcari.getText()));
    }//GEN-LAST:event_txtcariKeyReleased

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyTyped

    private void txtkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodeActionPerformed

    private void txtkodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodeKeyPressed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        String sql = "insert into mapel values (?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtkode.getText());
            stat.setString(2, cbmapel.getSelectedItem().toString());
            stat.setString(3, cbkategori.getSelectedItem().toString());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
            autonumber();
            txtkode.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan" + e);
        }
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_simpanActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int barisTerpilih = tabelmapel.getSelectedRow();

        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            int konfirmasi = JOptionPane.showConfirmDialog(null, "<html>Apakah Anda Akan Menghapus Data Mapel <b>" + cbmapel.getSelectedItem() + "</b></html>", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM mapel WHERE id_mapel ='" + txtkode.getText() + "'";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                    kosong();
                    datatable();
                    txtkode.requestFocus();
                    tambah.setVisible(true);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "data gagal dihapus" + e);
                }
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void cbmapelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbmapelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmapelKeyPressed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(editMapel);
        mainpanel.repaint();
        mainpanel.revalidate();        // TODO add your handling code here:
    }//GEN-LAST:event_editActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(dataMapel);
        mainpanel.repaint();
        mainpanel.revalidate();        // TODO add your handling code here:
    }//GEN-LAST:event_batalActionPerformed

    private void txtlabel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabel1ActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(tambahMapel);
        mainpanel.repaint();
        mainpanel.revalidate();
        tabelmapel.clearSelection();// TODO add your handling code here:
    }//GEN-LAST:event_tambahActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        datatable();
    }//GEN-LAST:event_cancelActionPerformed

    private void txtkode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkode1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkode1ActionPerformed

    private void txtkode1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkode1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkode1KeyPressed

    private void simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan1ActionPerformed
        try {
            String sql = "UPDATE mapel SET id_mapel=?,nama_mapel=?,kategori=? where id_mapel='" + txtkode.getText() + "'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtkode.getText());
            stat.setString(2, cbmapel.getSelectedItem().toString());
            stat.setString(3, cbkategori.getSelectedItem().toString());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil diubah");
            kosong();
            autonumber();
            txtkode.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal diubah" + e);
        }
        datatable();        // TODO add your handling code here:
    }//GEN-LAST:event_simpan1ActionPerformed

    private void cbmapel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbmapel1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmapel1KeyPressed

    private void batal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batal1ActionPerformed
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();

        mainpanel.add(dataMapel);
        mainpanel.repaint();
        mainpanel.revalidate();        // TODO add your handling code here:
    }//GEN-LAST:event_batal1ActionPerformed

    private void txtlabel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabel2ActionPerformed

    private void cbmapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmapelActionPerformed

    }//GEN-LAST:event_cbmapelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JButton batal1;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox<String> cbkategori;
    private javax.swing.JComboBox<String> cbkategori1;
    private javax.swing.JComboBox<String> cbmapel;
    private javax.swing.JComboBox<String> cbmapel1;
    private javax.swing.JPanel dataMapel;
    private javax.swing.JButton edit;
    private javax.swing.JPanel editMapel;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JButton simpan;
    private javax.swing.JButton simpan1;
    private javax.swing.JTable tabelmapel;
    private javax.swing.JButton tambah;
    private javax.swing.JPanel tambahMapel;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtkode1;
    private javax.swing.JTextField txtlabel;
    private javax.swing.JTextField txtlabel1;
    private javax.swing.JTextField txtlabel2;
    // End of variables declaration//GEN-END:variables
}
