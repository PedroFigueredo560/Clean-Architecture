/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import java.util.List;
import com.mycompany.cleanarchiteture.domain.Unidade;
/**
 *
 * @author Pedro Henrique
 */
public class UnidadeUseCase {
     private List<Unidade> unidades;

    public UnidadeUseCase() {
        unidades = new ArrayList<>();
        // Adiciona algumas unidades de exemplo
        unidades.add(new Unidade("Unidade 1"));
        unidades.add(new Unidade("Unidade 2"));
    }

    public List<Unidade> getAllUnidades() {
        return new ArrayList<>(unidades);
    }

    public void saveUnidade(Unidade unidade) {
        unidades.add(unidade);
    }

    public void deleteUnidade(Unidade unidade) {
        unidades.remove(unidade);
    }
}
