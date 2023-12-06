/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import prime.entity.Fornecedor;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Henrique
 */

public class FornecedorUseCase {
    private final EntityManager entityManager;

    public FornecedorUseCase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void iniciarTransacao() {
        entityManager.getTransaction().begin();
    }

    public void cancelarTransacao() {
        entityManager.getTransaction().rollback();
    }

    public void confirmarTransacao() throws RollbackException {
        entityManager.getTransaction().commit();
    }

    public void persistirFornecedor(Fornecedor fornecedor) {
        entityManager.persist(fornecedor);
    }

    public Fornecedor mergeFornecedor(Fornecedor fornecedor) {
        return entityManager.merge(fornecedor);
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        entityManager.remove(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return new ArrayList<>();
    }

}