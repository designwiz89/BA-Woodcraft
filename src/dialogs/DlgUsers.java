/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dialogs;

import java.sql.Connection;
import java.sql.Statement;
import utilities.DBConnection;
import ba.woodcraft.BA_Main;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import utilities.Utils;

/**
 *
 * @author zamora
 */
public class DlgUsers extends javax.swing.JDialog {
    
    public String uid;

    /**
     * Creates new form UsersDialog
     */
    public DlgUsers(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        pnlNewUser = new javax.swing.JPanel();
        txtUID = new javax.swing.JTextField();
        txtUPass = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New User");
        setResizable(false);

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete.png"))); // NOI18N
        btnCancel.setMnemonic('c');
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/save-png-image-4017.png"))); // NOI18N
        btnSave.setMnemonic('s');
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        pnlNewUser.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        pnlNewUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtUID.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        txtUID.setText("User ID");
        txtUID.setToolTipText("Enter User ID");

        txtUPass.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        txtUPass.setText("Password");
        txtUPass.setToolTipText("Enter Password");

        javax.swing.GroupLayout pnlNewUserLayout = new javax.swing.GroupLayout(pnlNewUser);
        pnlNewUser.setLayout(pnlNewUserLayout);
        pnlNewUserLayout.setHorizontalGroup(
            pnlNewUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUID)
                    .addComponent(txtUPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlNewUserLayout.setVerticalGroup(
            pnlNewUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewUserLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtUID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtUPass, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addComponent(pnlNewUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlNewUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(this.getTitle().equals("Update User")){
            try {
                Connection conn = DBConnection.connectDb();
                String sql =    "UPDATE user SET user_id='" + txtUID.getText() + "', password='" + txtUPass.getText() + "' " +
                                "WHERE user_id='" + uid + "'";
                System.out.println(sql);
                Statement pst = conn.createStatement();
                pst.execute(sql);
                Utils.showMessage(this, "update");
                BA_Main.showRecords(BA_Main.tblList,BA_Main.QUERIES[0],"user");

                this.setVisible(false);

            }catch (Exception e){
                Utils.showMessage(this,"error");
            }
        }else{
            try {
                Connection conn = DBConnection.connectDb();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dateobj = new Date();
                //System.out.println(df.format(dateobj));
                String sql = "INSERT INTO user values('" + txtUID.getText() + "', '" + txtUPass.getText() + "','" + df.format(dateobj) + "')";
                System.out.println(sql);
                Statement pst = conn.createStatement();
                pst.execute(sql);
                Utils.showMessage(this, "insert");
                BA_Main.showRecords(BA_Main.tblList,BA_Main.QUERIES[0],"user");

                this.setVisible(false);

            }catch (Exception e){
                Utils.showMessage(this,"error");
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

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
            //
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(DialogUseDlgUserstName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(DialogUsers.DlgUsersme()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(DialogUsers.claDlgUsers)).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgUsers dialog = new DlgUsers(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    public static javax.swing.JPanel pnlNewUser;
    public javax.swing.JTextField txtUID;
    public javax.swing.JTextField txtUPass;
    // End of variables declaration//GEN-END:variables
}
