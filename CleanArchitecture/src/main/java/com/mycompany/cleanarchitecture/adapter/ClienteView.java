/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Pedro Henrique
*/

public class ClienteView extends JPanel {

    public ClienteView() {
        initComponents();
        TableSelectionListener listener = new TableSelectionListener();
        masterTable.getSelectionModel().addListSelectionListener(listener);
        detailTable.getSelectionModel().addListSelectionListener(listener);
        entityManager.getTransaction().begin();

        presenter = new ClientePresenter(this);
    }

    private void initComponents() {
    }

    private class TableSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == masterTable.getSelectionModel()) {
                boolean enabled = (masterTable.getSelectedRow() != -1);
                deleteButton.setEnabled(enabled);
                newDetailButton.setEnabled(enabled);
            } else {
                deleteDetailButton.setEnabled(detailTable.getSelectedRow() != -1);
            }
        }
    }

   
}
