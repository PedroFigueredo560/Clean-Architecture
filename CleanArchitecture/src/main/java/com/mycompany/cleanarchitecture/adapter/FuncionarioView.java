/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author Pedro Henrique
 */
import com.mycompany.cleanarchitecture.framework.EncriptaSenha;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FuncionarioView extends JPanel {
    private final FuncionarioUseCase funcionarioUseCase;

    public FuncionarioView(FuncionarioUseCase funcionarioUseCase) {
        this.funcionarioUseCase = funcionarioUseCase;
        initComponents();
        if (!Beans.isDesignTime()) {
            funcionarioUseCase.iniciarTransacao();
        }
    }


    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        funcionarioUseCase.cancelarTransacao();
        funcionarioUseCase.iniciarTransacao();
        refreshTable();
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int[] selected = masterTable.getSelectedRows();
        List<Funcionario> toRemove = new ArrayList<>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            Funcionario f = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(f);
            funcionarioUseCase.removerFuncionario(f);
        }
        list.removeAll(toRemove);
    }

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Funcionario f = new Funcionario();
        funcionarioUseCase.persistirFuncionario(f);
        list.add(f);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            funcionarioUseCase.confirmarTransacao();
            funcionarioUseCase.iniciarTransacao();
            refreshButtonActionPerformed(evt);
        } catch (RollbackException rex) {
            rex.printStackTrace();
            funcionarioUseCase.iniciarTransacao();
            List<Funcionario> merged = new ArrayList<>(list.size());
            for (Funcionario f : list) {
                merged.add(funcionarioUseCase.mergeFuncionario(f));
            }
            list.clear();
            list.addAll(merged);
        }
    }

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {
        jPasswordField1.setText(EncriptaSenha.encripta(jPasswordField1.getText()));
    }

    private void refreshTable() {
        list.clear();
        list.addAll(funcionarioUseCase.listarFuncionarios());
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                EntityManager entityManager = FuncionarioUseCase funcionarioUseCase = new FuncionarioUseCase(entityManager);

                JFrame frame = new JFrame();
                frame.setContentPane(new FuncionarioView(funcionarioUseCase));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setTitle(args[0]);
            }
        });
    }
}