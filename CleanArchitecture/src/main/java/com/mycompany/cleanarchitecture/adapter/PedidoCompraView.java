/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class PedidoCompraView extends javax.swing.JFrame{
    
      private PedidoCompraUseCase pedidoUseCase;

    public PedidoCompraView(PedidoUseCase pedidoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
        initComponents();
        setButtonListeners();
    }

    private void initComponents() {
        // Inicialização dos componentes da interface gráfica
        // ...
    }

    private void setButtonListeners() {
        deleteDetailButton.addActionListener(evt -> deleteDetail());
        newDetailButton.addActionListener(evt -> addNewDetail());
        refreshButton.addActionListener(evt -> refreshData());
        deleteButton.addActionListener(evt -> deletePedidos());
        newButton.addActionListener(evt -> addNewPedido());
        saveButton.addActionListener(evt -> saveChanges());
    }

    private void deleteDetail() {
        int index = masterTable.getSelectedRow();
        int[] selected = detailTable.getSelectedRows();
        pedidoUseCase.deleteDetail(index, selected);
    }

    private void addNewDetail() {
        int index = masterTable.getSelectedRow();
        pedidoUseCase.addNewDetail(index);
    }

    private void refreshData() {
        // Lógica para atualizar os dados
        // Por exemplo:
        List<Object> data = query.getResultList();
        pedidoUseCase.refreshPedidos(data);
    }

    private void deletePedidos() {
        int[] selectedRows = masterTable.getSelectedRows();
        pedidoUseCase.deletePedidos(selectedRows);
    }

    private void addNewPedido() {
        pedidoUseCase.addNewPedido();
    }

    private void saveChanges() {
        pedidoUseCase.saveChanges();
    }
    
}
