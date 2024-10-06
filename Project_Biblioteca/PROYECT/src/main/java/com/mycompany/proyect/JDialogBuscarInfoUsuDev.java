/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.proyect;

import static com.mycompany.proyect.jFrameIndex.m;
import static com.mycompany.proyect.jFrameIndex.md;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.lang.Object;
/**
 *
 * @author jose
 */
public class JDialogBuscarInfoUsuDev extends javax.swing.JDialog {

    private jFrameIndex frame;
    

    public JDialogBuscarInfoUsuDev(java.awt.Frame parent, boolean modal,jFrameIndex frame) {
        super(parent, modal);
        this.frame = frame;
        initComponents();
        frame.preparaTablaAyudaUsuario(jTable1);
    }
    
    private void editaLibroBBDD(){
        int fila = jTable1.getSelectedRow();
        if (fila >= 0){
            frame.jTextFielPrestarUsu.setText(frame.mbu.getValueAt(fila, 1)+"");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDiaAgreLib = new javax.swing.JLabel();
        jButtonJDiaEdiUsuEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelDiaAgreLib.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabelDiaAgreLib.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDiaAgreLib.setText("Ayuda Usuarios");

        jButtonJDiaEdiUsuEnviar.setText("Salir con los datos");
        jButtonJDiaEdiUsuEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonJDiaEdiUsuEnviarMouseClicked(evt);
            }
        });
        jButtonJDiaEdiUsuEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJDiaEdiUsuEnviarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabelDiaAgreLib, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jButtonJDiaEdiUsuEnviar)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabelDiaAgreLib)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonJDiaEdiUsuEnviar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonJDiaEdiUsuEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJDiaEdiUsuEnviarActionPerformed
        editaLibroBBDD();
        dispose();
    }//GEN-LAST:event_jButtonJDiaEdiUsuEnviarActionPerformed

    private void jButtonJDiaEdiUsuEnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonJDiaEdiUsuEnviarMouseClicked
        
    }//GEN-LAST:event_jButtonJDiaEdiUsuEnviarMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonJDiaEdiUsuEnviar;
    private javax.swing.JLabel jLabelDiaAgreLib;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
