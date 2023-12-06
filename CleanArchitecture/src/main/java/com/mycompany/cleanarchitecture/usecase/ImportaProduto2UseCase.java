/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author Pedro Henrique
 */

import prime.bean.Produto;
import prime.model.ProdutoModel;

import java.util.List;

public class ImportarProduto2UseCase {

    private final ProdutoModel produtoModel;

    public ProdutoUseCase(ProdutoModel produtoModel) {
        this.produtoModel = produtoModel;
    }

    public List<Produto> listarProdutos() {
        return produtoModel.obterProdutos();
    }
}