/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;
import prime.bean.Produto;
import prime.model.ProdutoModel;
import java.util.List;
/**
 *
 * @author Pedro Henrique
 */


public class ImportaProdutoUseCase {

    private final ProdutoModel produtoModel;

    public ImportaProdutoUseCase(ProdutoModel produtoModel) {
        this.produtoModel = produtoModel;
    }

    public List<Produto> listarProdutos() {
        return produtoModel.obterProdutos();
    }
}