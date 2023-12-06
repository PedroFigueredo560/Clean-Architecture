/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Pedro Henrique
 */

public class ClienteUseCase {

    public void deleteDetail(Cliente cliente, int selectedIndex) {
        Collection<ClienteEndereco> clienteEnderecos = cliente.getClienteEnderecoCollection();
        List<ClienteEndereco> toRemove = new ArrayList<>(1);

        if (selectedIndex >= 0 && selectedIndex < clienteEnderecos.size()) {
            Iterator<ClienteEndereco> iter = clienteEnderecos.iterator();
            for (int count = 0; count < selectedIndex; count++) {
                iter.next();
            }
            ClienteEndereco clienteEndereco = iter.next();
            toRemove.add(clienteEndereco);
        }

        clienteEnderecos.removeAll(toRemove);
    }

    public void addNewDetail(Cliente cliente, ClienteEndereco clienteEndereco) {
        Collection<ClienteEndereco> clienteEnderecos = cliente.getClienteEnderecoCollection();

        if (clienteEnderecos == null) {
            clienteEnderecos = new LinkedList<>();
            cliente.setClienteEnderecoCollection(clienteEnderecos);
        }


        clienteEnderecos.add(clienteEndereco);
    }

    public void refreshData() {
    }
}