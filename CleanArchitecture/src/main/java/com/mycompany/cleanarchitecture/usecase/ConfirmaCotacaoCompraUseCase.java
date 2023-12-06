/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class ConfirmaCotacaoCompraUseCase {
    private void deleteSelectedDetails() {
        int index = masterTable.getSelectedRow();
        prime.bean.CotCab C = list.get(masterTable.convertRowIndexToModel(index));
        Collection<prime.bean.CotDet> cs = C.getCotDetCollection();
        int[] selected = detailTable.getSelectedRows();
        List<prime.bean.CotDet> toRemove = new ArrayList<prime.bean.CotDet>(selected.length);

        for (int idx=0; idx<selected.length; idx++) {
            selected[idx] = detailTable.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<prime.bean.CotDet> iter = cs.iterator();
            while (count++ < selected[idx]) {
                iter.next();
            }
            prime.bean.CotDet c = iter.next();
            toRemove.add(c);
            entityManager.remove(c);
        }

        cs.removeAll(toRemove);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
    }
    
    private void createNewDetail() {
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
        int row = cs.size() - 1;
        detailTable.setRowSelectionInterval(row, row);
        detailTable.scrollRectToVisible(detailTable.getCellRect(row, 0, true));
    }
}
