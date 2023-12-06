/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class PagamentosUseCase {
    private EntityManager entityManager;

    public PagamentoUseCase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void refreshPagamentos() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }

    @Override
    public void deletePagamentos(int[] selected) {
    }

    @Override
    public void createNewPagamento() {
    }

    @Override
    public void saveChanges() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<prime.bean.Pagamento> merged = new ArrayList<>(list.size());
            for (prime.bean.Pagamento p : list) {
                merged.add(entityManager.merge(p));
            }
            list.clear();
            list.addAll(merged);
        }
    }
}
