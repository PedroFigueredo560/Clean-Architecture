/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class RequisicaoCompraView extends javax.swing.JFrame{
    
    private RequisicaoCompraUseCase requisicaoCompraUseCase;

    public RequisicaoCompraView(RequisicaoCompraUseCase requisicaoCompraUseCase) {
        this.requisicaoCompraUseCase = requisicaoCompraUseCase;
        initComponents();
        setButtonListeners();
    }

    private void initComponents() {
        // Inicialização dos componentes da interface gráfica
    }

    private void setButtonListeners() {
        newDetailButton.addActionListener(evt -> addNewDetail());
        deleteDetailButton.addActionListener(evt -> deleteDetail());
        refreshButton.addActionListener(evt -> refreshData());
        saveButton.addActionListener(evt -> saveChanges());
        deleteButton.addActionListener(evt -> deleteRequisicaoCompra());
        newButton.addActionListener(evt -> addNewRequisicaoCompra());
    }

    private void addNewDetail() {
        int index = masterTable.getSelectedRow();
        requisicaoCompraUseCase.addNewDetail(index);
    }

    private void deleteDetail() {
        int index = masterTable.getSelectedRow();
        requisicaoCompraUseCase.deleteDetail(index);
    }

    private void refreshData() {
        requisicaoCompraUseCase.refreshData();
    }

    private void saveChanges() {
        requisicaoCompraUseCase.saveChanges();
    }

    private void deleteRequisicaoCompra() {
        requisicaoCompraUseCase.deleteRequisicaoCompra();
    }

    private void addNewRequisicaoCompra() {
        requisicaoCompraUseCase.addNewRequisicaoCompra();
    }

    private void limpaCampos() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField4.setText("");
        jTextField3.setText("");
    }
    
}
