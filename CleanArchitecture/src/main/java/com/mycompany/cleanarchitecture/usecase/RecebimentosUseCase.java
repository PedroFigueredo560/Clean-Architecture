/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class RecebimentosUseCase {
    
     private EntityManager entityManager;
    private List<Recebimento> list;

    public RecebimentosUseCase(EntityManager entityManager, List<Recebimento> list) {
        this.entityManager = entityManager;
        this.list = list;
    }

    public void refreshData() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }

    public void deleteRecebimento() {
        int[] selected = masterTable.getSelectedRows();
        List<Recebimento> toRemove = new ArrayList<>(selected.length);
        for (int idx : selected) {
            Recebimento r = list.get(masterTable.convertRowIndexToModel(idx));
            toRemove.add(r);
            entityManager.remove(r);
        }
        list.removeAll(toRemove);
    }

    public void addNewRecebimento() {
        Recebimento r = new Recebimento();
        entityManager.persist(r);
        list.add(r);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void saveChanges() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<Recebimento> merged = new ArrayList<>(list.size());
            for (Recebimento r : list) {
                merged.add(entityManager.merge(r));
            }
            list.clear();
            list.addAll(merged);
        }
    }
    
}
