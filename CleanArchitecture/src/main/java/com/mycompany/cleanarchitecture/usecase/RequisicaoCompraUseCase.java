/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class RequisicaoCompraUseCase {
    
    private EntityManager entityManager;
    private List<RequisicaoCompra> list;

    public RequisicaoCompraUseCase(EntityManager entityManager, List<RequisicaoCompra> list) {
        this.entityManager = entityManager;
        this.list = list;
    }

    public void addNewDetail(int index) {
        RequisicaoCompra requisicao = list.get(index);
        Collection<ReqDet> reqDets = requisicao.getReqDetCollection();

        if (reqDets == null) {
            reqDets = new LinkedList<>();
            requisicao.setReqDetCollection((List)reqDets);
        }

        ReqDet reqDet = new ReqDet();
        entityManager.persist(reqDet);

        reqDet.setReqCab(requisicao);
        reqDets.add(reqDet);
    }

    public void deleteDetail(int index) {
        RequisicaoCompra requisicao = list.get(index);
        Collection<ReqDet> reqDets = requisicao.getReqDetCollection();
        int[] selected = detailTable.getSelectedRows();
        List<ReqDet> toRemove = new ArrayList<>(selected.length);

        for (int idx : selected) {
            int selectedIdx = detailTable.convertRowIndexToModel(idx);
            Iterator<ReqDet> iter = reqDets.iterator();
            int count = 0;
            while (count++ < selectedIdx) iter.next();
            ReqDet reqDet = iter.next();
            toRemove.add(reqDet);
            entityManager.remove(reqDet);
        }

        reqDets.removeAll(toRemove);
    }

    public void refreshData() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }

    public void saveChanges() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<RequisicaoCompra> merged = new ArrayList<>(list.size());
            for (RequisicaoCompra requisicao : list) {
                merged.add(entityManager.merge(requisicao));
            }
            list.clear();
            list.addAll(merged);
        }
    }

    public void deleteRequisicaoCompra() {
        int[] selected = masterTable.getSelectedRows();
        List<RequisicaoCompra> toRemove = new ArrayList<>(selected.length);
        for (int idx : selected) {
            RequisicaoCompra requisicao = list.get(masterTable.convertRowIndexToModel(idx));
            toRemove.add(requisicao);
            entityManager.remove(requisicao);
        }
        list.removeAll(toRemove);
    }

    public void addNewRequisicaoCompra() {
        RequisicaoCompra requisicao = new RequisicaoCompra();
        entityManager.persist(requisicao);
        list.add(requisicao);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }
    
}
