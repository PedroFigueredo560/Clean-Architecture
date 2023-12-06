/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import prime.entity.Departamento;
import prime.usecase.DepartamentoUseCase;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Pedro Henrique
 */

public class DepartamentosView extends JPanel {
    private final DepartamentosUseCase departamentoUseCase;


    public DepartamentosView(DepartamentoUseCase departamentoUseCase) {
        this.departamentoUseCase = departamentoUseCase;
        initComponents();
        if (!Beans.isDesignTime()) {
            departamentoUseCase.iniciarTransacao();
        }
    }


    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        departamentoUseCase.cancelarTransacao();
        departamentoUseCase.iniciarTransacao();
        refreshTable();
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int[] selected = masterTable.getSelectedRows();
        List<Departamento> toRemove = new ArrayList<>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            Departamento d = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(d);
            departamentoUseCase.removerDepartamento(d);
        }
        list.removeAll(toRemove);
    }

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Departamento d = new Departamento();
        departamentoUseCase.persistirDepartamento(d);
        list.add(d);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            departamentoUseCase.confirmarTransacao();
            departamentoUseCase.iniciarTransacao();
            refreshButtonActionPerformed(evt);
        } catch (RollbackException rex) {
            rex.printStackTrace();
            departamentoUseCase.iniciarTransacao();
            List<Departamento> merged = new ArrayList<>(list.size());
            for (Departamento d : list) {
                merged.add(departamentoUseCase.mergeDepartamento(d));
            }
            list.clear();
            list.addAll(merged);
        }
    }

    private void refreshTable() {
        list.clear();
        list.addAll(departamentoUseCase.listarDepartamentos());
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                EntityManager entityManager = // Obtenha o EntityManager de alguma forma
                DepartamentoUseCase departamentoUseCase = new DepartamentoUseCase(entityManager);

                JFrame frame = new JFrame();
                frame.setContentPane(new DepartamentosView(departamentoUseCase));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setTitle(args[0]);
            }
        });
    }
}