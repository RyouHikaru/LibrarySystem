package librarysystem;


import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shanice Vergonia
 */
public class PatronLogin extends javax.swing.JFrame {
    private static Connection con;
    private static ArrayList<String> idList;
    private static ArrayList<String> pwList;
    private String patronId;

    /**
     * Creates new form PatronLogin
     * @throws java.sql.SQLException
     */
    public PatronLogin() throws SQLException {
        initComponents();
        
        idList = new ArrayList();
        pwList = new ArrayList();
        connectToDatabase();
        
        this.setPreferredSize(new Dimension(350, 540));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        username1_tf = new javax.swing.JTextField();
        login1_b = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        passwrod1_tf = new javax.swing.JPasswordField();
        patronLogin_Label = new javax.swing.JLabel();
        change1_b = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(null);
        setPreferredSize(new java.awt.Dimension(350, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(null);

        jLayeredPane1.setBackground(new java.awt.Color(242, 223, 167));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLayeredPane1.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLayeredPane1.setOpaque(true);

        username1_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username1_tfActionPerformed(evt);
            }
        });

        login1_b.setBackground(new java.awt.Color(255, 255, 255));
        login1_b.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        login1_b.setText("LOGIN");
        login1_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login1_bActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/password.png"))); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(login1_b, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(8, 8, 8)
                        .addComponent(username1_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwrod1_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(462, 462, 462))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(username1_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(passwrod1_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(login1_b)
                .addContainerGap())
        );
        jLayeredPane1.setLayer(username1_tf, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(login1_b, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(passwrod1_tf, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(70, 130, 200, 240);

        patronLogin_Label.setBackground(new java.awt.Color(0, 0, 0));
        patronLogin_Label.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        patronLogin_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patron_login.png"))); // NOI18N
        patronLogin_Label.setToolTipText("");
        patronLogin_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(patronLogin_Label);
        patronLogin_Label.setBounds(50, 50, 270, 50);

        change1_b.setBackground(new java.awt.Color(255, 255, 255));
        change1_b.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        change1_b.setText("Change to Librarian login");
        change1_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change1_bActionPerformed(evt);
            }
        });
        getContentPane().add(change1_b);
        change1_b.setBounds(80, 400, 170, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/library-services4.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 450, 820);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void login1_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login1_bActionPerformed
        int key;
        String id = username1_tf.getText();
        String pw = passwrod1_tf.getText();

        if (idList.contains(id)) {
            key = idList.indexOf(id);
            if (pwList.get(key).equals(pw)) {
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT patron_no FROM patron WHERE loginid = " + id);

                    while (rs.next()) {
                        patronId = rs.getString("patron_no");
                    }
                }
                catch(SQLException e) {}
                
                JOptionPane.showMessageDialog(rootPane, "Login Success", "Login", JOptionPane.INFORMATION_MESSAGE);
                new PatronMainScreen(patronId, con).setVisible(true);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "Invalid password", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
                passwrod1_tf.setText("");
            }
        } 
        else {
            JOptionPane.showMessageDialog(rootPane, "No such user", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
            username1_tf.setText("");
            passwrod1_tf.setText("");
        }
    }//GEN-LAST:event_login1_bActionPerformed

    private void username1_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username1_tfActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_username1_tfActionPerformed

    private void change1_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_change1_bActionPerformed
        new LibrarianLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_change1_bActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(PatronLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatronLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatronLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatronLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            new PatronLogin().setVisible(true);
        }
        catch(SQLException e) {}
    }
    public static void connectToDatabase() throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "admin";
        String password = "123";
        con = DriverManager.getConnection(host, username, password);
        con.setAutoCommit(true);
        System.out.println("Connected to database.");
        findInUsers();     
    }
    public static void findInUsers() {
        try {
            String query = "SELECT * FROM users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                idList.add(rs.getString("loginid"));
                pwList.add(rs.getString("password"));
            }
            System.out.println(idList);
            System.out.println(pwList);
        }
        catch(SQLException e) {}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton change1_b;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JButton login1_b;
    private javax.swing.JPasswordField passwrod1_tf;
    private javax.swing.JLabel patronLogin_Label;
    private javax.swing.JTextField username1_tf;
    // End of variables declaration//GEN-END:variables
}