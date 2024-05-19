package view;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;

public class Menu_Sidebar extends javax.swing.JPanel {

    Color defaultcolor, clickedcolor, white;
    private boolean showing = false;
    private Color defaultColor = new Color(60, 91, 111);
    private Color hoverColor = new Color(190, 190, 190);

    public ArrayList<Menu_Sidebar> getSubMenu() {
        return subMenu;
    }

    private final ArrayList<Menu_Sidebar> subMenu = new ArrayList<>();

    private ActionListener act;

    public Menu_Sidebar(Icon icon, boolean sbm, Icon iconsub, String namamenu, ActionListener act, Menu_Sidebar... SubMenu) {
        initComponents();
        clickedcolor = new Color(230, 230, 230);
        white = new Color(255, 255, 255);

        lb_icon.setIcon(icon);
        lb_namamenu.setText(namamenu);
        lb_iconsub.setIcon(iconsub);
        lb_iconsub.setVisible(sbm);

        if (act != null) {
            this.act = act;
        }

        this.setSize(new Dimension(Integer.MAX_VALUE, 50));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 50));

        for (int i = 0; i < SubMenu.length; i++) {
            this.subMenu.add(SubMenu[i]);
            SubMenu[i].setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_namamenu = new javax.swing.JLabel();
        lb_iconsub = new javax.swing.JLabel();
        lb_icon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(60, 91, 111));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        lb_namamenu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lb_namamenu.setForeground(new java.awt.Color(255, 255, 255));
        lb_namamenu.setText("namaMenu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lb_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_iconsub, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_namamenu, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_namamenu, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(lb_iconsub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (showing) {
            hideMenu();
        } else {
            showMenu();
            setBackground(Color.gray);
            animateColorChange(clickedcolor);
        }
        if (act != null) {
            act.actionPerformed(null);
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setBackground(hoverColor);
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setBackground(defaultColor);
    }//GEN-LAST:event_formMouseExited

    private void animateColorChange(Color targetColor) {
        // Warna asal
        Color startColor = getBackground();
        // Jumlah langkah dalam animasi
        int steps = 10;
        // Waktu total animasi (dalam milidetik)
        int duration = 260;
        // Waktu tunggu antara setiap langkah (dalam milidetik)
        int delay = duration / steps;

        // Menghitung perubahan warna untuk setiap langkah
        int dr = (targetColor.getRed() - startColor.getRed()) / steps;
        int dg = (targetColor.getGreen() - startColor.getGreen()) / steps;
        int db = (targetColor.getBlue() - startColor.getBlue()) / steps;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int step = 0;

            @Override
            public void run() {
                if (step >= steps) {
                    // Menghentikan timer setelah animasi selesai
                    timer.cancel();
                    // Mengembalikan warna latar belakang ke warna asal setelah animasi selesai
                    setBackground(defaultcolor);
                } else {
                    // Mengubah warna latar belakang menu secara bertahap
                    setBackground(new Color(
                            startColor.getRed() + dr * step,
                            startColor.getGreen() + dg * step,
                            startColor.getBlue() + db * step
                    ));
                    step++;
                }
            }
        }, delay, delay);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_icon;
    private javax.swing.JLabel lb_iconsub;
    private javax.swing.JLabel lb_namamenu;
    // End of variables declaration//GEN-END:variables

    private void hideMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < subMenu.size(); i++) {
                    sleep();
                    subMenu.get(i).setVisible(false);
                    subMenu.get(i).hideMenu();
                }
                getParent().repaint();
                getParent().revalidate();
                showing = false;
            }
        }).start();
    }

    private void showMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < subMenu.size(); i++) {
                    sleep();
                    subMenu.get(i).setVisible(true);
                }
                showing = true;
                getParent().repaint();
                getParent().revalidate();
            }
        }).start();
    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (Exception e) {
        }
    }
}
