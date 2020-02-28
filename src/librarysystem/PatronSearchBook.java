package librarysystem;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryou Hikaru
 */
public class PatronSearchBook extends javax.swing.JDialog {
    private static CallableStatement cst;
    private static String patronId;
    private static Connection con;
    private static String title;
    private DefaultTableModel dModel;
    private Object[][] data;
    private int userLimit;

    /**
     * Creates new form PatronSearchBook
     * @param parent the parent calling this class
     * @param patronId the patron no. of the current logged on user
     * @param con the connection to database
     */
    public PatronSearchBook(java.awt.Frame parent, String patronId, Connection con) {
        super(parent);
        
        this.con = con;
        this.patronId = patronId;
        initComponents();
        autoUpdateReservationSystem();
        getUserLimit();
        
        this.getContentPane().setBackground(new java.awt.Color(242,223,167));
        this.setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        hintLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        reserveButton = new javax.swing.JButton();
        tableScrollPane = new javax.swing.JScrollPane();
        bookDetailsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search book");

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search1.png"))); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        hintLabel.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        hintLabel.setText("Please enter book title");

        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        reserveButton.setText("Reserve");
        reserveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveButtonActionPerformed(evt);
            }
        });

        bookDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN no.", "Title", "Copy no.", "Author", "Shelf ID", "Published Year", "Place of Publication", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableScrollPane.setViewportView(bookDetailsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tableScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(518, 518, 518)
                        .addComponent(reserveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(hintLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnButton)
                    .addComponent(reserveButton))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        setTableData(); 
    }//GEN-LAST:event_searchButtonActionPerformed
    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_returnButtonActionPerformed

    private void reserveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveButtonActionPerformed
        int row = bookDetailsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please select a book", "Reserve a book", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            if (userLimit >= 2) {
                JOptionPane.showMessageDialog(rootPane, "You can only have 2 kinds of transaction at a time.", "Maximum transaction reached", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(rootPane, "Either RETURN a book or check your RESERVATION.", "Maximum transaction reached", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(rootPane, "Reserve this book?", "Reserve a book", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (confirm == 0) {
                if (bookDetailsTable.getValueAt(row, 6).equals("RESERVED") || bookDetailsTable.getValueAt(row, 6).equals("WITHDRAWN")) {
                    JOptionPane.showMessageDialog(rootPane, "The book is already RESERVED or WITHDRAWN");
                    return;
                }
                reserveBook(row);
                getUserLimit();
            }
        }
    }//GEN-LAST:event_reserveButtonActionPerformed
    private void setTableData() {
        title = searchTextField.getText();
        try {
            cst = con.prepareCall("{CALL retrieveBooks(?,?)}");
            cst.setString(1, title);  // set IN parameter "p_book_title"
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            
            cst.execute();
            ResultSet rs = ((OracleCallableStatement)cst).getCursor(2);
            int i = 0;
            while (rs.next()) {             
                i++;
            }
            
            data = new Object[i][columns().length];
            dModel = new DefaultTableModel(data, columns());
            bookDetailsTable.setModel(dModel);
            
            cst.execute();
            ResultSet finalRs = ((OracleCallableStatement)cst).getCursor(2);
            i = 0;
            while (finalRs.next()) {
                dModel.setValueAt(finalRs.getString("isbn_no"), i, 0);
                dModel.setValueAt(finalRs.getString("book_title"), i, 1);
                dModel.setValueAt(finalRs.getString("copy_no"), i, 2);
                dModel.setValueAt(finalRs.getString("shelf_id"), i, 3);
                dModel.setValueAt(finalRs.getString("author_name"), i, 4);
                dModel.setValueAt(finalRs.getString("year_of_publication"), i, 5);
                dModel.setValueAt(finalRs.getString("place_of_publication"), i, 6);
                dModel.setValueAt(finalRs.getString("current_status"), i, 7);
                i++;
            } 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void reserveBook(int row) {
        int isbn = Integer.parseInt(dModel.getValueAt(row, 0).toString());
        String copyno = dModel.getValueAt(row, 2).toString();
        String bkTitle = dModel.getValueAt(row, 1).toString();

        try {
            cst = con.prepareCall("{CALL reserve(?,?,?,?)}");
            cst.setInt(1, isbn);  // set IN parameter "p_book_no"
            cst.setString(2, copyno);
            cst.setString(3, bkTitle);
            cst.setString(4, patronId);
            cst.execute();
            JOptionPane.showMessageDialog(rootPane, "Reserved successfully!", "Resevation", JOptionPane.INFORMATION_MESSAGE);
            setTableData();
        } catch (SQLException ex) { System.out.println(ex.getMessage());}
    }
    private Object[] columns() {
        Object[] ret = {"ISBN no.", "Title", "Copy no.", "Shelf ID", "Author", "Published Year", "Place of Publication", "Status"};
        return ret;
    }
    private void autoUpdateReservationSystem() {
        try {
            cst = con.prepareCall("{CALL autoupdatereservation}");
            cst.execute();
            System.out.println("RESERVE COMMIT");
        } catch (SQLException ex) {}
    }
    private void getUserLimit() {
        try {
            cst = con.prepareCall("{CALL getPatronTransactionCount(?,?)}");
            cst.setString(1, patronId);
            cst.registerOutParameter(2, Types.INTEGER);
            
            cst.execute();
            userLimit = cst.getInt(2);
            System.out.println("User limit: " + userLimit);
        }
        catch(SQLException s) {
            System.out.println(s.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookDetailsTable;
    private javax.swing.JLabel hintLabel;
    private javax.swing.JButton reserveButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
