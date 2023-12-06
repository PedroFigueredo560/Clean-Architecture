/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;


import com.mycompany.cleanarchitecture.bean.TipoPgto;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;

public class TipoPgtoUseCase {
    private EntityManager entityManager;

    public TipoPgtoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void inserirTipoPgto(List<TipoPgto> list) {
        TipoPgto tp = new TipoPgto();
        entityManager.persist(tp);
        list.add(tp);
        int row = list.size() - 1;
        // masterTable.setRowSelectionInterval(row, row);
        // masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void excluirTipoPgto(List<TipoPgto> list, int[] selected) {
        List<TipoPgto> toRemove = new ArrayList<>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            TipoPgto tp = list.get(selected[idx]);
            toRemove.add(tp);
            entityManager.remove(tp);
        }
        list.removeAll(toRemove);
    }

    public void cancelarAlteracoes(List<TipoPgto> list, javax.persistence.Query query) {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }

    public void salvarAlteracoes(List<TipoPgto> list) {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();

        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<TipoPgto> merged = new ArrayList<>(list.size());
            for (TipoPgto tp : list) {
                merged.add(entityManager.merge(tp));
            }
            list.clear();
            list.addAll(merged);
        }
    }
}