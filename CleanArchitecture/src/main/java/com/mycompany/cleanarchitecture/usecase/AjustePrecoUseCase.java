/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import com.mycompany.cleanarchitecture.domain.Produto;
import java.util.List;

/**
 *
 * @author gisla
 */
public class AjustePrecoUseCase {
    
      public void ajustarPrecos(List<Produto> produtos, double percentual, boolean aumenta) {
        for (Produto produto : produtos) {
            if (aumenta) {
                Double valor = (produto.getVlrVendaProduto() * (percentual / 100)) + produto.getVlrVendaProduto();
                produto.setVlrVendaProduto(valor);
            } else {
                Double valor = produto.getVlrVendaProduto() - (produto.getVlrVendaProduto() * (percentual / 100));
                produto.setVlrVendaProduto(valor);
            }
        }
    }
    
}
