/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author Pedro Henrique
 */
public class Produtoview extends javax.swing.JFrame {

    /**
     * Creates new form Produtoview
     */
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
import prime.entity.Produto;
import prime.usecase.ProdutoService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProdutoView extends javax.swing.JPanel {
    private EntityManager entityManager;
    private List<Produto> list;
    private Query query;
    private ProdutoService produtoService;

    public ProdutoView() {
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }

        produtoService = new ProdutoService(entityManager);
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        produtoService.refreshProdutos(list, query);
        newButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        produtoService.deleteProdutos(list, masterTable.getSelectedRows());
    }

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        produtoService.newProduto(list, masterTable);
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        produtoService.saveProdutos(list);
        newButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        produtoService.exit(list, query);
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                ProdutoView pv = new ProdutoView();
                frame.setContentPane(pv);
                frame.pack();
                frame.setVisible(true);
                frame.setTitle(args[0]);
            }
        });
    }

}