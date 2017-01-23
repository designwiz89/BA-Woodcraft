/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ba.woodcraft;

import dialogs.DlgActivities;
import dialogs.DlgEmployee;
import dialogs.DlgLogin;
import dialogs.DlgRecords;
import dialogs.DlgStages;
import dialogs.DlgUsers;
import dialogs.DlgVarieties;
import dialogs.DlgRecords;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import utilities.DBConnection;

/**
 *
 * @author Lemuel Apa
 */
public class BA_Main extends javax.swing.JFrame {

    static String dbtable = "";
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    final public static String QUERIES[] = {
        "SELECT user_id as 'User ID', password as 'Password', date_added as 'Date Added' FROM user",
        "SELECT employee_id as 'Employee ID', name as 'Name', position as 'Position', date_added as 'Date Added' FROM employee",
        "SELECT act_id as 'Activity ID', description as 'Description' FROM activities",
        "SELECT var_id as 'Variety ID', description as 'Description' FROM varieties",
        "SELECT stage_id as 'Stage ID', description as 'Description' FROM stages",
        "SELECT ldar_id,activities, date, employee_id,period,week, op_day,total_reg_hrs, total_ot_hrs FROM labdar"
    };
    final String SEARCH_OPT[][] = {
        {"user_id", "password", "date_added"},
        {"employee_id", "name", "position"},
        {"act_id", "description"},
        {"var_id", "description"},
        {"stage_id", "description"},
        { "ldar_id","activities", "date", "employee_id", "period", "week", "op_day", "total_reg_hrs", "total_ot_hrs"}
    };

    DlgUsers frmUser;
    DlgEmployee frmEmployee;
    DlgActivities frmActivities;
    DlgVarieties frmVarieties;
    DlgStages frmStages;
    DlgRecords frmRecords;

    /**
     * Creates new form BA_Main
     */
    public BA_Main() {
        initComponents();
//        DiaDlgLogin= new DialogDlgLogintrue);
//        u.setLocationRelativeTo(this);
        
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.showRecords(QUERIES[5], "labdar");
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        this.setTitle("BA Tissue & Embryo Culture Laboratory | " + dateobj.toString());
//        u.setVisible(true);

    }

    public void showRecords(String sql, String tblName) {
        try {
            conn = DBConnection.connectDb();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblList.setModel(DbUtils.resultSetToTableModel(rs));
            dbtable = tblName;

            cboSearchOptions.removeAllItems();

            switch (tblName) {
                case "user":
                    for (String item : SEARCH_OPT[0]) {
                        cboSearchOptions.addItem(item);
                    }
                    break;
                case "employee":
                    for (String item : SEARCH_OPT[1]) {
                        cboSearchOptions.addItem(item);
                    }
                    break;
                case "activities":
                    for (String item : SEARCH_OPT[2]) {
                        cboSearchOptions.addItem(item);
                    }
                    break;
                case "varieties":
                    for (String item : SEARCH_OPT[3]) {
                        cboSearchOptions.addItem(item);
                    }
                    break;
                case "stages":
                    for (String item : SEARCH_OPT[4]) {
                        cboSearchOptions.addItem(item);
                    }
                    break;
                case "labdar":
                    for (String item : SEARCH_OPT[5]) {
                        cboSearchOptions.addItem(item);
                    }
                    break;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public static void showRecords(JTable tbl, String sql, String tblName) {
        try {
            Connection c = DBConnection.connectDb();
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet r = p.executeQuery();
            tbl.setModel(DbUtils.resultSetToTableModel(r));
            dbtable = tblName;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void search(boolean searchDefault) {
        try {
            Connection c = DBConnection.connectDb();
            Statement s = c.createStatement();
            String qry = "";
            if (searchDefault) {
                qry = "SELECT * FROM " + dbtable;
            } else {
                qry = "SELECT * FROM " + dbtable + " WHERE " + cboSearchOptions.getSelectedItem() + "='" + txtSearch.getText() + "'";
            }
            ResultSet r = s.executeQuery(qry);
            tblList.setModel(DbUtils.resultSetToTableModel(r));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void deleteRecords(String colID, String colVal, String tblName, String view) {
        try {
            Connection c = DBConnection.connectDb();
            Statement s = c.createStatement();
            String sql = "DELETE FROM " + tblName + " WHERE " + colID + "='" + colVal + "'";
            System.out.print(sql);
            s.execute(sql);

            dbtable = tblName;
            JOptionPane.showMessageDialog(this, "Record has been deleted successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
            showRecords(view, tblName);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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

        jToolBar1 = new javax.swing.JToolBar();
        btnDAR = new javax.swing.JButton();
        btnActivities = new javax.swing.JButton();
        btnStages = new javax.swing.JButton();
        btnVarieties = new javax.swing.JButton();
        btnEmployee = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        jToolBar3 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        txtSearch = new javax.swing.JTextField();
        cboSearchOptions = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BA Tissue & Embryo Culture Laboratory");

        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/laboratory-dar.png"))); // NOI18N
        btnDAR.setText("Lab DAR");
        btnDAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDAR.setFocusable(false);
        btnDAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDARActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDAR);

        btnActivities.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/hand.png"))); // NOI18N
        btnActivities.setText("Activities");
        btnActivities.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActivities.setFocusable(false);
        btnActivities.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActivities.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActivities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivitiesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnActivities);

        btnStages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ladder.png"))); // NOI18N
        btnStages.setText("Stages");
        btnStages.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStages.setFocusable(false);
        btnStages.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStages.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStagesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnStages);

        btnVarieties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/varieties.png"))); // NOI18N
        btnVarieties.setText("Varieties");
        btnVarieties.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVarieties.setFocusable(false);
        btnVarieties.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVarieties.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVarieties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVarietiesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnVarieties);

        btnEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/people32.png"))); // NOI18N
        btnEmployee.setMnemonic('m');
        btnEmployee.setText("Employee");
        btnEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmployee.setFocusable(false);
        btnEmployee.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmployee.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEmployee);

        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user-icon.png"))); // NOI18N
        btnUser.setText("User");
        btnUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUser.setFocusable(false);
        btnUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });
        jToolBar1.add(btnUser);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMaximumSize(new java.awt.Dimension(244, 428));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/BA logo 220.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html><center>BA TISSUE & EMBRYO<br>CULTURE LABORATORY<br><br><h2>INVENTORY<br>and<br>PAYROLL SYSTEM</h2></center></html>");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<html>Developed by : LEMUEL L. APA<br>Email: lemuel_apa@yahoo.com</html>");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(275, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblList.setAutoCreateRowSorter(true);
        tblList.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        tblList.setGridColor(new java.awt.Color(204, 204, 204));
        tblList.setRowHeight(18);
        tblList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblList);

        jToolBar2.setRollover(true);

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/add32.png"))); // NOI18N
        btnAdd.setMnemonic('a');
        btnAdd.setText("Add");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jToolBar3.add(btnAdd);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/live-edit.png"))); // NOI18N
        btnEdit.setMnemonic('e');
        btnEdit.setText("Edit");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar3.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete.png"))); // NOI18N
        btnDelete.setMnemonic('d');
        btnDelete.setText("Delete");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar3.add(btnDelete);
        jToolBar3.add(jSeparator2);

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jToolBar3.add(txtSearch);

        jToolBar3.add(cboSearchOptions);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/search-button-png-image-42853.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSearch.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jToolBar3.add(btnSearch);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user-icon.png"))); // NOI18N
        jMenuItem1.setText("New User");
        fileMenu.add(jMenuItem1);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/people32.png"))); // NOI18N
        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("New Employee");
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/button-delete-icon.png"))); // NOI18N
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Do you really want to close this application?", "System Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        showRecords(QUERIES[0], "user");
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        switch (dbtable) {
            case "user":
                frmUser = new DlgUsers(this, true);
                frmUser.setLocationRelativeTo(this);
                frmUser.setVisible(true);
                break;
            case "employee":
                frmEmployee = new DlgEmployee(this, true);
                frmEmployee.setLocationRelativeTo(this);
                frmEmployee.setVisible(true);
                break;
            case "activities":
                frmActivities = new DlgActivities(this, true);
                frmActivities.setLocationRelativeTo(this);
                frmActivities.setVisible(true);
                break;
            case "varieties":
                frmVarieties = new DlgVarieties(this, true);
                frmVarieties.setLocationRelativeTo(this);
                frmVarieties.setVisible(true);
                break;
            case "stages":
                frmStages = new DlgStages(this, true);
                frmStages.setLocationRelativeTo(this);
                frmStages.setVisible(true);
                break;
            case "labdar":
                frmRecords = new DlgRecords(this, true);
                frmRecords.setLocationRelativeTo(this);
                frmRecords.setVisible(true);
                break;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int opt;

        switch (dbtable) {
            case "user":
                opt = JOptionPane.showConfirmDialog(this, "Do you really want to delete this record?", "System Message", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    deleteRecords("user_id", tblList.getValueAt(tblList.getSelectedRow(), 0).toString(), "user", QUERIES[0]);
                }
                break;
            case "employee":
                opt = JOptionPane.showConfirmDialog(this, "Do you really want to delete this record?", "System Message", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    deleteRecords("employee_id", tblList.getValueAt(tblList.getSelectedRow(), 0).toString(), "employee", QUERIES[1]);
                }
                break;
            case "activities":
                opt = JOptionPane.showConfirmDialog(this, "Do you really want to delete this record?", "System Message", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    deleteRecords("employee_id", tblList.getValueAt(tblList.getSelectedRow(), 0).toString(), "employee", QUERIES[1]);
                }
                break;
             case "dar":
                opt = JOptionPane.showConfirmDialog(this, "Do you really want to delete this record?", "System Message", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    deleteRecords("ldar_id", tblList.getValueAt(tblList.getSelectedRow(), 0).toString(), "labdar", QUERIES[5]);
                }
                break;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        switch (dbtable) {
            case "user":
                frmUser = new DlgUsers(this, true);
                frmUser.uid = tblList.getValueAt(tblList.getSelectedRow(), 0).toString();
                frmUser.txtUID.setText(tblList.getValueAt(tblList.getSelectedRow(), 0).toString());
                frmUser.txtUPass.setText(tblList.getValueAt(tblList.getSelectedRow(), 1).toString());
                frmUser.setTitle("Update User");
                frmUser.setLocationRelativeTo(this);
                frmUser.setVisible(true);
                break;
            case "employee":
                frmEmployee = new DlgEmployee(this, true);
                frmEmployee.eid = tblList.getValueAt(tblList.getSelectedRow(), 0).toString();
                frmEmployee.txtName.setText(tblList.getValueAt(tblList.getSelectedRow(), 1).toString());
                frmEmployee.cboPosition.setSelectedItem(tblList.getValueAt(tblList.getSelectedRow(), 2).toString());
                frmEmployee.setTitle("Update Employee");
                frmEmployee.setLocationRelativeTo(this);
                frmEmployee.setVisible(true);
                break;
            case "activities":
                frmActivities = new DlgActivities(this, true);
                frmActivities.aid = tblList.getValueAt(tblList.getSelectedRow(), 0).toString();
                frmActivities.txtDescription.setText(tblList.getValueAt(tblList.getSelectedRow(), 1).toString());
                frmActivities.setTitle("Update Activity");
                frmActivities.setLocationRelativeTo(this);
                frmActivities.setVisible(true);
                break;
            case "varieties":
                frmVarieties = new DlgVarieties(this, true);
                frmVarieties.vid = tblList.getValueAt(tblList.getSelectedRow(), 0).toString();
                frmVarieties.txtDescription.setText(tblList.getValueAt(tblList.getSelectedRow(), 1).toString());
                frmVarieties.setTitle("Update Variety");
                frmVarieties.setLocationRelativeTo(this);
                frmVarieties.setVisible(true);
                break;
            case "stages":
                frmStages = new DlgStages(this, true);
                frmStages.sid = tblList.getValueAt(tblList.getSelectedRow(), 0).toString();
                frmStages.txtDescription.setText(tblList.getValueAt(tblList.getSelectedRow(), 1).toString());
                frmStages.setTitle("Update Stage");
                frmStages.setLocationRelativeTo(this);
                frmStages.setVisible(true);
                break;
                 case "dar":
                frmRecords = new DlgRecords(this, true);
                frmRecords.rid = tblList.getValueAt(tblList.getSelectedRow(), 0).toString();
                frmRecords.dateoop.setDate((Date) tblList.getValueAt(tblList.getSelectedRow(), 1));
                frmRecords.periodjTextField.setText(tblList.getValueAt(tblList.getSelectedRow(), 3).toString());
                 frmRecords.weekjTextField.setText(tblList.getValueAt(tblList.getSelectedRow(), 4).toString());
//                 frmRecords.dayoopjComboBox.setSelectedItem(tblList.getValueAt(tblList.getSelectedRow(), 5));
                 frmRecords.tghjTextField.setText(tblList.getValueAt(tblList.getSelectedRow(), 6).toString());
                 frmRecords.tothjTextField.setText(tblList.getValueAt(tblList.getSelectedRow(), 7).toString());
                
               
                 frmRecords.ac1.setSelected(true);
                 
                frmRecords.setTitle("Update Records");
                frmRecords.setLocationRelativeTo(this);
                frmRecords.setVisible(true);
                break;
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (evt.getKeyCode() == 10) {
            search(false);
        } else if (txtSearch.getText().equals("")) {
            search(true);
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeActionPerformed
        showRecords(QUERIES[1], "employee");
    }//GEN-LAST:event_btnEmployeeActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        search(false);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnVarietiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVarietiesActionPerformed
        showRecords(QUERIES[3], "varieties");
    }//GEN-LAST:event_btnVarietiesActionPerformed

    private void btnActivitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivitiesActionPerformed
        showRecords(QUERIES[2], "activities");
    }//GEN-LAST:event_btnActivitiesActionPerformed

    private void btnStagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStagesActionPerformed
        showRecords(QUERIES[4], "stages");
    }//GEN-LAST:event_btnStagesActionPerformed

    private void btnDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDARActionPerformed
        // TODO add your handling code here:
        showRecords(QUERIES[5], "labdar");
    }//GEN-LAST:event_btnDARActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BA_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BA_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BA_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BA_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BA_Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivities;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDAR;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEmployee;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStages;
    private javax.swing.JButton btnUser;
    private javax.swing.JButton btnVarieties;
    private javax.swing.JComboBox cboSearchOptions;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem saveAsMenuItem;
    public static javax.swing.JTable tblList;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
