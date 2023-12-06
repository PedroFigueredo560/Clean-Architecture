/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import com.mycompany.cleanarchitecture.model.OrcamentoCab;
import com.mycompany.cleanarchitecture.model.OrcamentoDet;

import java.util.Collection;
/**
 *
 * @author Pedro Henrique
 */
public class MovimentoOrcamentoVendaUseCase {
    public void deleteDetail(OrcamentoCab orcamentoCab, Collection<OrcamentoDet> toRemove) {
        toRemove.forEach(entityManager::remove);
    }

    public void createDetail(OrcamentoCab orcamentoCab, OrcamentoDet orcamentoDet) {
        orcamentoCab.getOrcDetCollection().add(orcamentoDet);
        entityManager.persist(orcamentoDet);
    }

    public void refreshData() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        query.getResultList().forEach(entityManager::refresh);
        list.clear();
        list.addAll(query.getResultList());
    }

    public void createOrcamento() {
        OrcamentoCab orcamento = new OrcamentoCab();
        entityManager.persist(orcamento);
        list.add(orcamento);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
        jTextField2.requestFocus();
        jTextField1.setEnabled(false);
    }

    public void saveOrcamento() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            limpaCampos();
            refreshData();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<OrcamentoCab> merged = new ArrayList<>(list.size());
            list.forEach(orcamentoCab -> merged.add(entityManager.merge(orcamentoCab)));
            list.clear();
            list.addAll(merged);
        }
    }

    public void calculateTotals(OrcamentoCab orcamentoCab, Double desconto) {
        Double valorTotal = 0.0;

        for (OrcamentoDet orcamentoDet : orcamentoCab.getOrcDetCollection()) {
            if (orcamentoDet.getQtdeOrcDet() == 0) {
                orcamentoDet.setQtdeOrcDet(1);
            }
            orcamentoDet.setVlrTotalOrcDet(orcamentoDet.getVlrUnitOrcDet() * orcamentoDet.getQtdeOrcDet());
            valorTotal = valorTotal + orcamentoDet.getVlrTotalOrcDet();
        }

        if (jTextField11.getText().equals("")) {
            jTextField11.setText("0");
        }

        jTextField7.setText(String.valueOf(valorTotal));
    }
}

