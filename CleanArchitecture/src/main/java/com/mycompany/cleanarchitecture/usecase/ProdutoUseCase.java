/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author Pedro Henrique
 */
import com.mycompany.cleanarchitecture.bean.Produto;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoUseCase {
    private EntityManager entityManager;

    public ProdutoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void refreshProdutos(List<Produto> list, javax.persistence.Query query) {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }

    public void deleteProdutos(List<Produto> list, int[] selected) {
        int[] selected = masterTable.getSelectedRows();
        List<Produto> toRemove = new ArrayList<>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            Produto p = list.get(selected[idx]);
            toRemove.add(p);
            entityManager.remove(p);
        }
        list.removeAll(toRemove);
    }

    public void newProduto(List<Produto> list, javax.swing.JTable masterTable) {
        Produto p = new Produto();
        entityManager.persist(p);
        list.add(p);
        int row = masterTable.getRowCount() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }

    public void saveProdutos(List<Produto> list) {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            refreshProdutos(list, query);
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<Produto> merged = new ArrayList<>(list.size());
            for (Produto p : list) {
                merged.add(entityManager.merge(p));
            }
            list.clear();
            list.addAll(merged);
        }
    }

    public void exit(List<Produto> list, javax.persistence.Query query) {
        if (JOptionPane.showConfirmDialog(null, "Salvar Alterações ao Sair?") == JOptionPane.YES_OPTION) {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } else {
            entityManager.getTransaction().rollback();
            entityManager.getTransaction().begin();
            java.util.Collection data = query.getResultList();
            for (Object entity : data) {
                entityManager.refresh(entity);
            }
            list.clear();
            list.addAll(data);
        }
    }
}