/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class CotacaoCompraView extends javax.swing.JFrame{
    
    private CotacaoUseCase cotacaoUseCase;

    public CotacaoView(CotacaoUseCase cotacaoUseCase) {
        this.cotacaoUseCase = cotacaoUseCase;
        initComponents();
    }

    private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        int index = masterTable.getSelectedRow();
        int[] selected = detailTable.getSelectedRows();
        cotacaoUseCase.deleteDetail(index, selected);
    }

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        int index = masterTable.getSelectedRow();
        cotacaoUseCase.addNewDetail(index);
    }

    @SuppressWarnings("unchecked")
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        List<Object> data = query.getResultList();
        cotacaoUseCase.refreshCotacoes(data);
    }                                             

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int[] selectedRows = masterTable.getSelectedRows();
        cotacaoUseCase.deleteCotacoes(selectedRows);
    }                                            

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        cotacaoUseCase.addNewCotacao();
    }                                         
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        cotacaoUseCase.saveChanges();
    } 
    
}
