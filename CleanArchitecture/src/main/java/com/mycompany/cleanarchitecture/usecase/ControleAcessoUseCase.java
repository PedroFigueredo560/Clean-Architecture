/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import prime.entity.Funcionario;
import prime.entity.NivelAcesso;

import javax.persistence.EntityManager;
import java.util.List;
/**
 *
 * @author Pedro Henrique
 */

public class ControleAcessoUseCase {
    private final EntityManager entityManager;

    public ControleAcessoUseCase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Funcionario> listarFuncionarios() {
        // Implemente a lógica para listar todos os funcionários
        return null;
    }

    public void concederAcesso(Funcionario funcionario, String modulo) {
        NivelAcesso nivelAcesso = new NivelAcesso();
        entityManager.persist(nivelAcesso);
        nivelAcesso.setCodFunc(funcionario.getCodFuncionario());
        nivelAcesso.setNomeModulo(modulo);
    }

    public void retirarAcesso(NivelAcesso nivelAcesso) {
        entityManager.remove(nivelAcesso);
    }

    public void salvarAlteracoes() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
