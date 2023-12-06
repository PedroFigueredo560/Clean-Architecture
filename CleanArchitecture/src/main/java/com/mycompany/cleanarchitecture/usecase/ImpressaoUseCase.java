/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import prime.model.ImpressaoModel;
/**
 *
 * @author Pedro Henrique
 */
public class ImpressaoUseCase {
    public static String obterConteudoArquivo(String arquivo) {
        try {
            return ImpressaoModel.carregaArquivo(arquivo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}