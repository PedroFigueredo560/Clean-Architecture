/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import prime.entity.Funcionario;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
/**
 *
 * @author Pedro Henrique
 */

public class FuncionarioUseCase {
    private final EntityManager entityManager;

    public FuncionarioUseCase(EntityManager entityManager) {
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

    public void persistirFuncionario(Funcionario funcionario) {
        entityManager.persist(funcionario);
    }

    public Funcionario mergeFuncionario(Funcionario funcionario) {
        return entityManager.merge(funcionario);
    }

    public void removerFuncionario(Funcionario funcionario) {
        entityManager.remove(funcionario);
    }

}