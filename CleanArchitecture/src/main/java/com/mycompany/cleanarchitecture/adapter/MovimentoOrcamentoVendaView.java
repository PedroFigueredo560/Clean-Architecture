/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;


import com.mycompany.cleanarchitecture.model.OrcamentoCab;
import com.mycompany.cleanarchitecture.model.OrcamentoDet;

import javax.swing.*;
import java.util.Collection;
/**
 *
 * @author Pedro Henrique
 */
public class MovimentoOrcamentoVendaView extends javax.swing.JFrame {

    /**
     * Creates new form MovimentoOrcamentoVendaView
     */
    public MovimentoOrcamentoVendaView() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MovimentoOrcamentoVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovimentoOrcamentoVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovimentoOrcamentoVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovimentoOrcamentoVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MovimentoOrcamentoVendaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public class MovimentoOrcamentoView extends JPanel {

        private JTextField jTextField2;
        private JTextField jTextField3;
        private JTextField jTextField4;
        private JTextField jTextField5;
        private JTextField jTextField6;
        private JTextField jTextField7;
        private JTextField jTextField8;

        private MovimentoOrcamentoUseCase useCase;

        public MovimentoOrcamentoView(MovimentoOrcamentoUseCase useCase) {
            this.useCase = useCase;
            initComponents();
        }

        private void deleteDetailButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            int index = masterTable.getSelectedRow();
            OrcamentoCab orcamentoCab = list.get(masterTable.convertRowIndexToModel(index));
            Collection<OrcamentoDet> toRemove = detailTable.getSelectedRows().stream()
                    .map(detailTable::convertRowIndexToModel)
                    .map(orcamentoCab.getOrcDetCollection()::get)
                    .toList();
            useCase.deleteDetail(orcamentoCab, toRemove);
        }

        private void newDetailButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            useCase.createDetail(getSelectedOrcamentoCab(), new OrcamentoDet());
        }

        private void refreshButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            useCase.refreshData();
        }

        private void newButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            useCase.createOrcamento();
        }

        private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            useCase.saveOrcamento();
        }

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
            useCase.calculateTotals(getSelectedOrcamentoCab(), parseDouble(jTextField11.getText()));
        }

        // Métodos adicionais conforme necessário

        private OrcamentoCab getSelectedOrcamentoCab() {
            int index = masterTable.getSelectedRow();
            return list.get(masterTable.convertRowIndexToModel(index));
        }

        private Double parseDouble(String value) {
            return Double.parseDouble(value);
        }
    }
}