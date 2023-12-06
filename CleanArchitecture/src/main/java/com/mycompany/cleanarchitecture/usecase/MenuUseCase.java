/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import prime.view.compras.CotacaoCompra;
import prime.view.compras.RequisicaoCompra;
import prime.view.compras.estoque.AjustePrecos;
import prime.view.compras.estoque.EntradaProduto;
import prime.view.financeiro.Pagamentos;

/**
 *
 * @author Pedro Henrique
 */
public class MenuUseCase {
    public void abrirCotacaoCompra(String[] args) {
        CotacaoCompra.main(args);
    }

    public void abrirRequisicaoCompra(String[] args) {
        RequisicaoCompra.main(args);
    }

    public void abrirAjustePrecos(String[] args) {
        AjustePrecos.main(args);
    }

    public void abrirEntradaProduto(String[] args) {
        EntradaProduto.main(args);
    }

    public void abrirPagamentos(String[] args) {
        Pagamentos.main(args);
    }

}

