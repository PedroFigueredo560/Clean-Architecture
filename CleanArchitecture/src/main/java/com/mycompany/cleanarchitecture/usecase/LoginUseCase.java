/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author Pedro Henrique
 */
public class LoginUseCase {

    private LoginModel loginModel;

    public LoginUseCase(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public boolean realizarLogin(String login, String senha) {
        return loginModel.verificaLogin(login, senha);
    }
}
