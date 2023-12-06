/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author gisla
 */
public class PagementosView exentends javax.swing.JFrame{

    private void initComponents() {

            refreshButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                refreshPagamentos();
            });

            deleteButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                deleteSelectedPagamentos();
            });

            newButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                createNewPagamento();
            });

            saveButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                saveChanges();
            });
        }

        private void refreshPagamentos() {
            pagamentoController.refreshPagamentos();
        }

        private void deleteSelectedPagamentos() {
            int[] selected = masterTable.getSelectedRows();
            pagamentoController.deletePagamentos(selected);
        }

        private void createNewPagamento() {
            pagamentoController.createNewPagamento();
        }

        private void saveChanges() {
            pagamentoController.saveChanges();
        }

   }
