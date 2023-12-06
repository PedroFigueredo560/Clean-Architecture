/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class EntradaProdutoView extends javax.swing.JFrame{
    
    public class NfeView extends javax.swing.JFrame {
    private NfeUseCase nfeUseCase;

    public NfeView(NfeUseCase nfeUseCase) {
        this.nfeUseCase = nfeUseCase;
        initComponents();
    }

    private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        int masterTableSelectedRow = masterTable.getSelectedRow();
        int[] selectedRows = detailTable.getSelectedRows();
        nfeUseCase.deleteDetail(masterTableSelectedRow, selectedRows);
    }

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        int masterTableSelectedRow = masterTable.getSelectedRow();
        nfeUseCase.addNewDetail(masterTableSelectedRow);
    }

    @SuppressWarnings("unchecked")
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        nfeUseCase.refreshNfe();
    }                                             

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int[] selectedRows = masterTable.getSelectedRows();
        nfeUseCase.deleteNfe(selectedRows);
    }                                            

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        nfeUseCase.addNewNfe();
    }                                         
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        nfeUseCase.saveChanges();
    } 
}
    
}
