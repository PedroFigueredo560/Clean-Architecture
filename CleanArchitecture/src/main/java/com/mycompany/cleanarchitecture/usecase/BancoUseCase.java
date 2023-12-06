/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class BancoUseCase {
    
    private EntityManager entityManager;
    private List<Banco> list;

    public BancoUseCase(EntityManager entityManager, List<Banco> list) {
        this.entityManager = entityManager;
        this.list = list;
    }

    public void refreshData() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        list.clear();
        list.addAll(query.getResultList());
    }

    public void deleteBanco() {
        int[] selected = masterTable.getSelectedRows();
        List<Banco> toRemove = new ArrayList<>(selected.length);
        for (int idx : selected) {
            Banco b = list.get(masterTable.convertRowIndexToModel(idx));
            toRemove.add(b);
            entityManager.remove(b);
        }
        list.removeAll(toRemove);
    }

    public void addNewBanco() {
        Banco b = new Banco();
        entityManager.persist(b);
        list.add(b);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void saveChanges() {
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
    }

    public void printBancoList() {
    }
    
}
