/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import prime.model.Unidade;
import prime.usecase.UnidadeUseCase;

public class UnidadeView extends JPanel {
    private UnidadeUseCase unidadeUseCase;
    private List<Unidade> unidades;
    private DefaultTableModel tableModel;

    private JButton saveButton;
    private JButton refreshButton;
    private JButton newButton;
    private JButton deleteButton;
    private JTextField descricaoUnidadeField;
    private JTable masterTable;
    private JScrollPane masterScrollPane;
    private JLabel descricaoUnidadeLabel;

    public UnidadeView() {
        initComponents();
        unidadeUseCase = new UnidadeUseCase();
        unidades = unidadeUseCase.getAllUnidades();
        bindData();
    }

    private void initComponents() {
        // Configuração da UI (Layout, Eventos, etc.)
        // ...

        masterScrollPane = new JScrollPane();
        masterTable = new JTable();
        descricaoUnidadeLabel = new JLabel();
        descricaoUnidadeField = new JTextField();
        saveButton = new JButton();
        refreshButton = new JButton();
        newButton = new JButton();
        deleteButton = new JButton();

        // Criação e configuração do modelo da tabela
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Descrição Unidade" });
        masterTable.setModel(tableModel);

        // Adição de outros componentes à UI
        // ...
    }

    private void bindData() {
        // Lógica para vincular os dados à tabela
        for (Unidade unidade : unidades) {
            tableModel.addRow(new Object[] { unidade.getDescricaoUnidade() });
        }
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String descricao = descricaoUnidadeField.getText();
        if (!descricao.isEmpty()) {
            Unidade newUnidade = new Unidade(descricao);
            unidadeUseCase.saveUnidade(newUnidade);
            unidades.add(newUnidade);
            tableModel.addRow(new Object[] { newUnidade.getDescricaoUnidade() });
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = masterTable.getSelectedRow();
        if (selectedRow != -1) {
            Unidade selectedUnidade = unidades.get(selectedRow);
            unidadeUseCase.deleteUnidade(selectedUnidade);
            unidades.remove(selectedUnidade);
            tableModel.removeRow(selectedRow);
        }
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new UnidadeView());
            frame.pack();
            frame.setVisible(true);
            frame.setTitle(args[0]);
        });
    }
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
