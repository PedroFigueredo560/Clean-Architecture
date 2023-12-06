/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import com.mycompany.cleanarchitecture.model.VendaCab;
import com.mycompany.cleanarchitecture.model.VendaDet;

import java.util.Collection;
/**
 *
 * @author Pedro Henrique
 */
public class MovimentoVendasUseCase {
    public void deleteDetail(VendaCab vendaCab, Collection<VendaDet> toRemove) {
        toRemove.forEach(entityManager::remove);
    }

    public void createDetail(VendaCab vendaCab, VendaDet vendaDet) {
        vendaCab.getVendaDetCollection().add(vendaDet);
        entityManager.persist(vendaDet);
    }

    public void refreshData() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        query.getResultList().forEach(entityManager::refresh);
        list.clear();
        list.addAll(query.getResultList());
    }

    public void createVenda() {
        VendaCab V = new VendaCab();
        entityManager.persist(V);
        list.add(V);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
        jTextField2.requestFocus();
        jTextField1.setEnabled(false);
    }

    public void saveVenda() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            limpaCampos();
            refreshData();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<VendaCab> merged = new ArrayList<>(list.size());
            list.forEach(vendaCab -> merged.add(entityManager.merge(vendaCab)));
            list.clear();
            list.addAll(merged);
        }
    }

    public void calculateTotals(VendaCab vendaCab, Double desconto) {
        Double valorTotal = 0.0;
        Double totalGeral = 0.0;

        for (VendaDet vendaDet : vendaCab.getVendaDetCollection()) {
            if (vendaDet.getQtdeVendaDet() == 0) {
                vendaDet.setQtdeVendaDet(1);
            }
            vendaDet.setVlrTotalVendaDet(vendaDet.getVlrUnitVendaDet() * vendaDet.getQtdeVendaDet());
            valorTotal = valorTotal + vendaDet.getVlrTotalVendaDet();
        }

        if (jTextField11.getText().equals("")) {
            jTextField11.setText("0");
        }

        totalGeral = valorTotal - Double.parseDouble(jTextField11.getText());

        jTextField9.setText(String.valueOf(valorTotal));
        jTextField12.setText(String.valueOf(totalGeral));
    }
}
