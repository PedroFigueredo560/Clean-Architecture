/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class EntradaProdutoUseCase {
    
    private EntityManager entityManager;


    @Override
    public void deleteDetail(int masterTableSelectedRow, int[] selectedRows) {
        NfeCab nfe = nfeList.get(masterTableSelectedRow);
        List<NfeDet> nfeDetails = nfe.getNfeDetCollection();

        List<NfeDet> toRemove = new ArrayList<>(selectedRows.length);
        for (int selectedRow : selectedRows) {
            int rowIndex = detailTable.convertRowIndexToModel(selectedRow);
            NfeDet nfeDetail = nfeDetails.get(rowIndex);
            toRemove.add(nfeDetail);
            entityManager.remove(nfeDetail);
        }

        nfeDetails.removeAll(toRemove);
    }

    @Override
    public void addNewDetail(int masterTableSelectedRow) {
        NfeCab nfe = nfeList.get(masterTableSelectedRow);
        List<NfeDet> nfeDetails = nfe.getNfeDetCollection();

        if (nfeDetails == null) {
            nfeDetails = new ArrayList<>();
            nfe.setNfeDetCollection(nfeDetails);
        }

        NfeDet newNfeDetail = new NfeDet();
        entityManager.persist(newNfeDetail);
        newNfeDetail.setNfeCab(nfe);
        nfeDetails.add(newNfeDetail);
    }

    @Override
    public void refreshNfe() {
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        List<NfeCab> data = query.getResultList();
        nfeList.clear();
        nfeList.addAll(data);
    }

    @Override
    public void deleteNfe(int[] selectedRows) {
        List<NfeCab> toRemove = new ArrayList<>(selectedRows.length);
        for (int selectedRow : selectedRows) {
            NfeCab nfe = nfeList.get(selectedRow);
            toRemove.add(nfe);
            entityManager.remove(nfe);
        }

        nfeList.removeAll(toRemove);
    }

    @Override
    public void addNewNfe() {
        NfeCab newNfe = new NfeCab();
        entityManager.persist(newNfe);
        nfeList.add(newNfe);
    }

    @Override
    public void saveChanges() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<NfeCab> merged = new ArrayList<>(nfeList.size());
            for (NfeCab nfe : nfeList) {
                merged.add(entityManager.merge(nfe));
            }
            nfeList.clear();
            nfeList.addAll(merged);
        }
    }
    
}
