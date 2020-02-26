
import java.sql.Connection;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shanice Vergonia
 */
public class LibMainScreen extends javax.swing.JFrame {
    private String patronId;
    private static Connection con;
    /**
     * Creates new form PatronManScreen
     */
    public LibMainScreen(String patronId, Connection con) {
        initComponents();
        
        this.patronId = patronId;
        this.con = con;
        
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcome1_label = new javax.swing.JLabel();
        manageTransacation_b = new javax.swing.JButton();
        manageUsers_b = new javax.swing.JButton();
        manageBooks_b = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        options1_jmenu = new javax.swing.JMenu();
        menu_logout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(null);
        setPreferredSize(new java.awt.Dimension(350, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(341, 515));
        getContentPane().setLayout(null);

        welcome1_label.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        welcome1_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/welcome_librarian.png"))); // NOI18N
        welcome1_label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(welcome1_label);
        welcome1_label.setBounds(40, 90, 250, 70);

        manageTransacation_b.setBackground(new java.awt.Color(255, 255, 255));
        manageTransacation_b.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        manageTransacation_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test.png"))); // NOI18N
        manageTransacation_b.setText("Manage Transactions");
        manageTransacation_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTransacation_bActionPerformed(evt);
            }
        });
        getContentPane().add(manageTransacation_b);
        manageTransacation_b.setBounds(80, 180, 176, 40);

        manageUsers_b.setBackground(new java.awt.Color(255, 255, 255));
        manageUsers_b.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        manageUsers_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gear.png"))); // NOI18N
        manageUsers_b.setText("Manage Users");
        manageUsers_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUsers_bActionPerformed(evt);
            }
        });
        getContentPane().add(manageUsers_b);
        manageUsers_b.setBounds(80, 240, 170, 40);

        manageBooks_b.setBackground(new java.awt.Color(255, 255, 255));
        manageBooks_b.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        manageBooks_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/books.png"))); // NOI18N
        manageBooks_b.setText("Manage Books");
        manageBooks_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageBooks_bActionPerformed(evt);
            }
        });
        getContentPane().add(manageBooks_b);
        manageBooks_b.setBounds(80, 300, 170, 38);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library-services4.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 350, 540);

        options1_jmenu.setText("Options");
        options1_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                options1_jmenuActionPerformed(evt);
            }
        });

        menu_logout.setText("Logout");
        menu_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_logoutActionPerformed(evt);
            }
        });
        options1_jmenu.add(menu_logout);

        jMenuBar1.add(options1_jmenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_logoutActionPerformed
        new LibrarianLogin().setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_menu_logoutActionPerformed

    private void options1_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_options1_jmenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_options1_jmenuActionPerformed

    private void manageTransacation_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTransacation_bActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_manageTransacation_bActionPerformed

    private void manageUsers_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUsers_bActionPerformed
        new LibrarianManageUser(this, con).setVisible(true);
    }//GEN-LAST:event_manageUsers_bActionPerformed

    private void manageBooks_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageBooks_bActionPerformed
        new EditBook(this, "", con).setVisible(true);
    }//GEN-LAST:event_manageBooks_bActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton manageBooks_b;
    private javax.swing.JButton manageTransacation_b;
    private javax.swing.JButton manageUsers_b;
    private javax.swing.JMenuItem menu_logout;
    private javax.swing.JMenu options1_jmenu;
    private javax.swing.JLabel welcome1_label;
    // End of variables declaration//GEN-END:variables
}
