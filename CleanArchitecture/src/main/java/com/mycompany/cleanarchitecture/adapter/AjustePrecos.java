/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import com.mycompany.cleanarchitecture.usecase.ComprasUseCase;
import javax.swing.JPanel;

/**
 *
 * @author gisla
 */
public class AjustePrecos extends javax.swing.JFrame {
    private ComprasUseCase ajustePrecosUseCase;

    public AjustePrecos(ComprasUseCase ajustePrecosUseCase) {
        this.ajustePrecosUseCase = ajustePrecosUseCase;
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        List<Produto> selectedProdutos = getSelectedProdutos();
        if (selectedProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos um produto.");
            return;
        }

        double percentual = Double.parseDouble(jTextField2.getText());
        boolean aumenta = jRadioButton1.isSelected();

        ajustePrecosUseCase.ajustarPrecos(selectedProdutos, percentual, aumenta);
    }
    
    private List<Produto> getSelectedProdutos() {
        int[] selectedRows = masterTable.getSelectedRows();
        List<Produto> selectedProdutos = new ArrayList<>();
        for (int idx : selectedRows) {
            selectedProdutos.add(list.get(masterTable.convertRowIndexToModel(idx)));
        }
        return selectedProdutos;
    }
}
