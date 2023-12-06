/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class BancoView extends javax.swing.JFrame {
    private BancoUseCase bancoUseCase;

    public BancoView(BancoUseCase bancoUseCase) {
        this.bancoUseCase = bancoUseCase;
        initComponents();
        setButtonListeners();
    }

    private void initComponents() {
        // Inicialização dos componentes da interface gráfica
        // ...
    }

    private void setButtonListeners() {
        refreshButton.addActionListener(evt -> refreshData());
        deleteButton.addActionListener(evt -> deleteBanco());
        newButton.addActionListener(evt -> addNewBanco());
        saveButton.addActionListener(evt -> saveChanges());
        jButton1.addActionListener(evt -> printBancoList());
    }

    private void refreshData() {
        bancoUseCase.refreshData();
    }

    private void deleteBanco() {
        bancoUseCase.deleteBanco();
    }

    private void addNewBanco() {
        bancoUseCase.addNewBanco();
    }

    private void saveChanges() {
        bancoUseCase.saveChanges();
    }

    private void printBancoList() {
        bancoUseCase.printBancoList();
    }
}
