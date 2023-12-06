/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class RecebimentosView extends javax.swing.JFrame {
    private RecebimentosUseCase recebimentosUseCase;

    public RecebimentosView(RecebimentosUseCase recebimentosUseCase) {
        this.recebimentosUseCase = recebimentosUseCase;
        initComponents();
        setButtonListeners();
    }

    private void initComponents() {
        // Inicialização dos componentes da interface gráfica
    }

    private void setButtonListeners() {
        refreshButton.addActionListener(evt -> refreshData());
        deleteButton.addActionListener(evt -> deleteRecebimento());
        newButton.addActionListener(evt -> addNewRecebimento());
        saveButton.addActionListener(evt -> saveChanges());
    }

    private void refreshData() {
        recebimentosUseCase.refreshData();
    }

    private void deleteRecebimento() {
        recebimentosUseCase.deleteRecebimento();
    }

    private void addNewRecebimento() {
        recebimentosUseCase.addNewRecebimento();
    }

    private void saveChanges() {
        recebimentosUseCase.saveChanges();
    }
}
