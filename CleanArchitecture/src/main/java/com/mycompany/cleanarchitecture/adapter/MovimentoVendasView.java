/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author Pedro Henrique
 */
public class MovimentoVendasView extends javax.swing.JFrame {

    private JTextField jTextField1;
    private JTextField jTextField2;
    // ... (outros componentes gráficos)

    private MovimentoVendaUseCase useCase;

    public MovimentoVendaView(MovimentoVendaUseCase useCase) {
        this.useCase = useCase;
        initComponents();
    }

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
            java.util.logging.Logger.getLogger(MovimentoVendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovimentoVendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovimentoVendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovimentoVendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MovimentoVendasView().setVisible(true);
            }
        });
    }

     private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int index = masterTable.getSelectedRow();
        VendaCab vendaCab = list.get(masterTable.convertRowIndexToModel(index));
        Collection<VendaDet> toRemove = detailTable.getSelectedRows().stream()
                .map(detailTable::convertRowIndexToModel)
                .map(vendaCab.getVendaDetCollection()::get)
                .toList();
        useCase.deleteDetail(vendaCab, toRemove);
    }

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {
        useCase.createDetail(getSelectedVendaCab(), new VendaDet());
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        useCase.refreshData();
    }

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        useCase.createVenda();
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        useCase.saveVenda();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        useCase.calculateTotals(getSelectedVendaCab(), parseDouble(jTextField11.getText()));
    }

    private void CalculaTotais(java.awt.event.KeyEvent evt) {
        useCase.calculateTotals(getSelectedVendaCab(), parseDouble(jTextField11.getText()));
    }

    // Métodos adicionais conforme necessário

    private VendaCab getSelectedVendaCab() {
        int index = masterTable.getSelectedRow();
        return list.get(masterTable.convertRowIndexToModel(index));
    }

    private Double parseDouble(String value) {
        return Double.parseDouble(value);
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
