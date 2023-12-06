/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author gisla
 */
public class ConfirmaCotacaoCompraView extends javax.swing.JFrame{
     private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        int index = masterTable.getSelectedRow();
        prime.bean.CotCab C = list.get(masterTable.convertRowIndexToModel(index));
        Collection<prime.bean.CotDet> cs = C.getCotDetCollection();
        int[] selected = detailTable.getSelectedRows();
        List<prime.bean.CotDet> toRemove = new ArrayList<prime.bean.CotDet>(selected.length);
        for (int idx=0; idx<selected.length; idx++) {
            selected[idx] = detailTable.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<prime.bean.CotDet> iter = cs.iterator();
            while (count++ < selected[idx]) iter.next();
            prime.bean.CotDet c = iter.next();
            toRemove.add(c);
            entityManager.remove(c);
        }
        cs.removeAll(toRemove);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
    }                                                  

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        int index = masterTable.getSelectedRow();
        prime.bean.CotCab C = list.get(masterTable.convertRowIndexToModel(index));
        Collection<prime.bean.CotDet> cs = C.getCotDetCollection();
        if (cs == null) {
            cs = new LinkedList<prime.bean.CotDet>();
            C.setCotDetCollection((List)cs);
        }
        prime.bean.CotDet c = new prime.bean.CotDet();
        entityManager.persist(c);
        c.setCotCab(C);
        cs.add(c);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
        int row = cs.size()-1;
        detailTable.setRowSelectionInterval(row, row);
        detailTable.scrollRectToVisible(detailTable.getCellRect(row, 0, true));
    }                                               
    
    @SuppressWarnings("unchecked")
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }                                             

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int[] selected = masterTable.getSelectedRows();
        List<prime.bean.CotCab> toRemove = new ArrayList<prime.bean.CotCab>(selected.length);
        for (int idx=0; idx<selected.length; idx++) {
            prime.bean.CotCab C = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(C);
            entityManager.remove(C);
        }
        list.removeAll(toRemove);
    }                                            

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        prime.bean.CotCab C = new prime.bean.CotCab();
        entityManager.persist(C);
        list.add(C);
        int row = list.size()-1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }                                         
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<prime.bean.CotCab> merged = new ArrayList<prime.bean.CotCab>(list.size());
            for (prime.bean.CotCab C : list) {
                merged.add(entityManager.merge(C));
            }
            list.clear();
            list.addAll(merged);
        }
    
    }
}