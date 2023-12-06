/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import com.mycompany.cleanarchitecture.bean.PlanoConta;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pedro Henrique
 */
public class PlanoContaUseCase {
    private EntityManager entityManager;

    public PlanoContaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void refreshPlanosContas(List<PlanoConta> list, javax.persistence.Query query) {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }

    public void deletePlanosContas(List<PlanoConta> list, int[] selected) {
        int[] selected = masterTable.getSelectedRows();
        List<PlanoConta> toRemove = new ArrayList<>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            PlanoConta p = list.get(selected[idx]);
            toRemove.add(p);
            entityManager.remove(p);
        }
        list.removeAll(toRemove);
    }

    public void newPlanoConta(List<PlanoConta> list, javax.swing.JTable masterTable) {
        PlanoConta p = new PlanoConta();
        entityManager.persist(p);
        list.add(p);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void savePlanosContas(List<PlanoConta> list) {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            refreshPlanosContas(list, query);
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<PlanoConta> merged = new ArrayList<>(list.size());
            for (PlanoConta p : list) {
                merged.add(entityManager.merge(p));
            }
            list.clear();
            list.addAll(merged);
        }
    }
}
