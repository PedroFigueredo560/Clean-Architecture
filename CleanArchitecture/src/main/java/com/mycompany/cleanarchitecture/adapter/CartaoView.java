/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class CartaoView extends javax.swing.JFrame {
    private CartaoUseCase cartaoUseCase;
    private JTable masterTable;
    private JButton refreshButton;
    private JButton deleteButton;
    private JButton newButton;
    private JButton saveButton;

    public CartaoView(CartaoUseCase cartaoUseCase) {
        this.cartaoUseCase = cartaoUseCase;
        initComponents();
        setButtonListeners();
    }

    private void initComponents() {
        // Inicialização dos componentes da interface gráfica
        // ...
        // Exemplo: masterTable, refreshButton, deleteButton, newButton, saveButton
        // Configuração dos componentes na janela
        // ...
    }

    private void setButtonListeners() {
        refreshButton.addActionListener(evt -> refreshData());
        deleteButton.addActionListener(evt -> deleteCartao());
        newButton.addActionListener(evt -> addNewCartao());
        saveButton.addActionListener(evt -> saveChanges());

        masterTable.getSelectionModel().addListSelectionListener(new TableSelectionListener());
    }

    private void refreshData() {
        cartaoUseCase.refreshData();
    }

    private void deleteCartao() {
        cartaoUseCase.deleteCartao();
    }

    private void addNewCartao() {
        cartaoUseCase.addNewCartao();
    }

    private void saveChanges() {
        cartaoUseCase.saveChanges();
    }

    private class TableSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == masterTable.getSelectionModel()) {
                boolean enabled = (masterTable.getSelectedRow() != -1);
                deleteButton.setEnabled(enabled);
            }
        }
    }
}
