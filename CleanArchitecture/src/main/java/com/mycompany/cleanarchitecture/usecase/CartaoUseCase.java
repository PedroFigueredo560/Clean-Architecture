/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class CartaoUseCase {
    
    private EntityManager entityManager;
    private List<Cartao> list;

    public CartaoUseCase(EntityManager entityManager, List<Cartao> list) {
        this.entityManager = entityManager;
        this.list = list;
    }

    public void refreshData() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        list.clear();
        list.addAll(query.getResultList());
    }

    public void deleteCartao() {
        int[] selected = masterTable.getSelectedRows();
        List<Cartao> toRemove = new ArrayList<>(selected.length);
        for (int idx : selected) {
            Cartao c = list.get(masterTable.convertRowIndexToModel(idx));
            toRemove.add(c);
            entityManager.remove(c);
        }
        list.removeAll(toRemove);
    }

    public void addNewCartao() {
        Cartao c = new Cartao();
        entityManager.persist(c);
        list.add(c);
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
            List<Cartao> merged = new ArrayList<>(list.size());
            for (Cartao c : list) {
                merged.add(entityManager.merge(c));
            }
            list.clear();
            list.addAll(merged);
        }
    }
    
}
