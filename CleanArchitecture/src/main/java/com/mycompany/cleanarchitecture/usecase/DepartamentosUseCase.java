/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import prime.entity.Departamento;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pedro Henrique
 */
public class DepartamentosUseCase {
    private final EntityManager entityManager;

    public DepartamentosUseCase(EntityManager entityManager) {
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

    public void persistirDepartamento(Departamento departamento) {
        entityManager.persist(departamento);
    }

    public Departamento mergeDepartamento(Departamento departamento) {
        return entityManager.merge(departamento);
    }

    public void removerDepartamento(Departamento departamento) {
        entityManager.remove(departamento);
    }

    public List<Departamento> listarDepartamentos() {
        return new ArrayList<>();
    }

}
