
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class EditBook extends javax.swing.JDialog {
    private static CallableStatement cst;
    private static String patronId;
    private static Connection con;
    private static String title;
    private DefaultTableModel dModel;
    private Object[][] data;

    /**
     * Creates new form PatronSearchBook
     * @param parent
     */
    public EditBook(java.awt.Frame parent, String patronId, Connection con) {
        super(parent);
        
        this.con = con;
        this.patronId = patronId;
        initComponents();
        
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
        addBookButton = new javax.swing.JButton();
        tableScrollPane = new javax.swing.JScrollPane();
        bookDetailsTable = new javax.swing.JTable();
        editBookButton = new javax.swing.JButton();

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

        addBookButton.setText("Add book");
        addBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookButtonActionPerformed(evt);
            }
        });

        bookDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN no.", "Title", "Author", "Published Year", "Place of Publication", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableScrollPane.setViewportView(bookDetailsTable);

        editBookButton.setText("Edit book");
        editBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBookButtonActionPerformed(evt);
            }
        });

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
                        .addGap(393, 393, 393)
                        .addComponent(editBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(addBookButton)
                    .addComponent(editBookButton))
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

    private void addBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookButtonActionPerformed
        
    }//GEN-LAST:event_addBookButtonActionPerformed

    private void editBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBookButtonActionPerformed
        int row = bookDetailsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please select a book", "Update a book", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            new EditBookDialog(this, con, Integer.parseInt(bookDetailsTable.getValueAt(row, 0).toString())).setVisible(true);
        }
    }//GEN-LAST:event_editBookButtonActionPerformed
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
                dModel.setValueAt(finalRs.getString("author_name"), i, 2);
                dModel.setValueAt(finalRs.getString("year_of_publication"), i, 3);
                dModel.setValueAt(finalRs.getString("place_of_publication"), i, 4);
                dModel.setValueAt(finalRs.getString("current_status"), i, 5);
                i++;
            } 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private Object[] columns() {
        Object[] ret = {"ISBN no.", "Title", "Author", "Published Year", "Place of Publication", "Status"};
        return ret;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookButton;
    private javax.swing.JTable bookDetailsTable;
    private javax.swing.JButton editBookButton;
    private javax.swing.JLabel hintLabel;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}