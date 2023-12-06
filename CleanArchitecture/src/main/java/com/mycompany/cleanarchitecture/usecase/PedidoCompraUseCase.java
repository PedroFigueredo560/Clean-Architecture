/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class PedidoCompraUseCase {
    
    private EntityManager entityManager;
    private List<PedidoCab> list;

    public PedidoCompraUseCase(EntityManager entityManager, List<PedidoCab> list) {
        this.entityManager = entityManager;
        this.list = list;
    }

    public void deleteDetail(int index, int[] selected) {
        PedidoCab pedidoCab = list.get(index);
        Collection<PedidoDet> pedidoDets = pedidoCab.getPedidoDetCollection();
        List<PedidoDet> toRemove = new ArrayList<>(selected.length);

        for (int idx : selected) {
            int selectedIdx = detailTable.convertRowIndexToModel(idx);
            Iterator<PedidoDet> iter = pedidoDets.iterator();
            int count = 0;
            while (count++ < selectedIdx) iter.next();
            PedidoDet pedidoDet = iter.next();
            toRemove.add(pedidoDet);
            entityManager.remove(pedidoDet);
        }

        pedidoDets.removeAll(toRemove);
    }

    public void addNewDetail(int index) {
        PedidoCab pedidoCab = list.get(index);
        Collection<PedidoDet> pedidoDets = pedidoCab.getPedidoDetCollection();

        if (pedidoDets == null) {
            pedidoDets = new LinkedList<>();
            pedidoCab.setPedidoDetCollection((List)pedidoDets);
        }

        PedidoDet pedidoDet = new PedidoDet();
        entityManager.persist(pedidoDet);

        pedidoDet.setPedidoCab(pedidoCab);
        pedidoDets.add(pedidoDet);
    }

    public void refreshPedidos(List<Object> data) {
    }

    public void deletePedidos(int[] selectedRows) {
        for (int idx : selectedRows) {
            PedidoCab pedidoCab = list.get(idx);
            entityManager.remove(pedidoCab);
        }
        list.removeAll(list.subList(selectedRows[0], selectedRows[selectedRows.length - 1]));
    }

    public void addNewPedido() {
        PedidoCab pedidoCab = new PedidoCab();
        entityManager.persist(pedidoCab);
        list.add(pedidoCab);
    }

    public void saveChanges() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<PedidoCab> merged = new ArrayList<>(list.size());
            for (PedidoCab pedidoCab : list) {
                merged.add(entityManager.merge(pedidoCab));
            }
            list.clear();
            list.addAll(merged);
        }
    }
    
}
