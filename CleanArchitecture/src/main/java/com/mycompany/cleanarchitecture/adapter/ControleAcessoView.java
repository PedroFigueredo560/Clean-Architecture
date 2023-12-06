/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import prime.entity.Funcionario;
import prime.usecase.ControleAcessoUseCase;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Pedro Henrique
 */

public class ControleAcessoView extends JFrame {
    private final ControleAcessoUseCase controleAcessoUseCase;


    public ControleAcessoView(ControleAcessoUseCase controleAcessoUseCase) {
        this.controleAcessoUseCase = controleAcessoUseCase;
        initComponents();
        if (!Beans.isDesignTime()) {
            controleAcessoUseCase.iniciarTransacao();
        }
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Funcionario funcionario = funcionarioList.get(jTable1.getSelectedRow());
        String modulo = jTree1.getLastSelectedPathComponent().toString();
        controleAcessoUseCase.concederAcesso(funcionario, modulo);
        atualizarTabelaNivelAcesso();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int index = jTable2.getSelectedRow();
        NivelAcesso nivelAcesso = nivelAcessoList.get(index);
        controleAcessoUseCase.retirarAcesso(nivelAcesso);
        atualizarTabelaNivelAcesso();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        controleAcessoUseCase.salvarAlteracoes();
    }

    private void atualizarTabelaNivelAcesso() {
        nivelAcessoList.clear();
        nivelAcessoList.addAll(controleAcessoUseCase.listarNiveisAcesso());
    }
}