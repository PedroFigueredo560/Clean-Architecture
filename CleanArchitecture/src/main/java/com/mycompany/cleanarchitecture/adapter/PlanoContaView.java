/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import prime.entity.PlanoConta;
import prime.usecase.PlanoContaService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author Pedro Henrique
 */

public class PlanoContaView extends javax.swing.JPanel {
    private EntityManager entityManager;
    private List<PlanoConta> list;
    private Query query;
    private PlanoContaService planoContaService;

    public PlanoContaView() {
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }

        planoContaService = new PlanoContaService(entityManager);
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        planoContaService.refreshPlanosContas(list, query);
        newButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        planoContaService.deletePlanosContas(list, masterTable.getSelectedRows());
    }

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        planoContaService.newPlanoConta(list, masterTable);
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        planoContaService.savePlanosContas(list);
    }

}
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
