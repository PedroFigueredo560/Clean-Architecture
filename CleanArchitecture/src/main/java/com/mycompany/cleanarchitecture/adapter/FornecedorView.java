/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import prime.usecase.FornecedorUseCase;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Pedro Henrique
 */

public class FornecedorView extends JPanel {
    private final FornecedorUseCase fornecedorUseCase;
    
    // Adicione os componentes da UI conforme necessário

    public FornecedorView(FornecedorUseCase fornecedorUseCase) {
        this.fornecedorUseCase = fornecedorUseCase;
        initComponents();
        if (!Beans.isDesignTime()) {
            fornecedorUseCase.iniciarTransacao();
        }
        newButton.setVisible(true);
        jButton1.setVisible(true);
        deleteButton.setVisible(true);
    }

    // Implemente os métodos de inicialização da UI e manipulação de eventos aqui

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        fornecedorUseCase.cancelarTransacao();
        fornecedorUseCase.iniciarTransacao();
        refreshTable();
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int[] selected = masterTable.getSelectedRows();
        List<Fornecedor> toRemove = new ArrayList<>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            Fornecedor f = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(f);
            fornecedorUseCase.removerFornecedor(f);
        }
        list.removeAll(toRemove);
    }

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Fornecedor f = new Fornecedor();
        fornecedorUseCase.persistirFornecedor(f);
        list.add(f);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            fornecedorUseCase.confirmarTransacao();
            fornecedorUseCase.iniciarTransacao();
            refreshButtonActionPerformed(evt);
        } catch (RollbackException rex) {
            rex.printStackTrace();
            fornecedorUseCase.iniciarTransacao();
            List<Fornecedor> merged = new ArrayList<>(list.size());
            for (Fornecedor f : list) {
                merged.add(fornecedorUseCase.mergeFornecedor(f));
            }
            list.clear();
            list.addAll(merged);
        }
    }

    private void refreshTable() {
        list.clear();
        list.addAll(fornecedorUseCase.listarFornecedores());
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                EntityManager entityManager = // Obtenha o EntityManager de alguma forma
                FornecedorUseCase fornecedorUseCase = new FornecedorUseCase(entityManager);

                JFrame frame = new JFrame();
                frame.setContentPane(new FornecedorView(fornecedorUseCase));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setTitle(args[0]);
            }
        });
    }
}