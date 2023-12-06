/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cleanarchitecture.usecase;

/**
 *
 * @author gisla
 */
public class CotacaoCompraUseCase extends JPanel{
    
    private EntityManager entityManager;
    private List<CotCab> list;

    public CotacaoUseCase(EntityManager entityManager, List<CotCab> list) {
        this.entityManager = entityManager;
        this.list = list;
    }

    @Override
    public void deleteDetail(int index, int[] selected) {
        CotCab cotCab = list.get(index);
        Collection<CotDet> cotDets = cotCab.getCotDetCollection();
        List<CotDet> toRemove = new ArrayList<>(selected.length);

        for (int idx : selected) {
            int selectedIdx = detailTable.convertRowIndexToModel(idx);
            Iterator<CotDet> iter = cotDets.iterator();
            int count = 0;
            while (count++ < selectedIdx) iter.next();
            CotDet cotDet = iter.next();
            toRemove.add(cotDet);
            entityManager.remove(cotDet);
        }

        cotDets.removeAll(toRemove);
    }

    @Override
    public void addNewDetail(int index) {
        CotCab cotCab = list.get(index);
        Collection<CotDet> cotDets = cotCab.getCotDetCollection();

        if (cotDets == null) {
            cotDets = new LinkedList<>();
            cotCab.setCotDetCollection((List)cotDets);
        }

        CotDet cotDet = new CotDet();
        entityManager.persist(cotDet);

        ImportaRequisicao ir = new ImportaRequisicao(null, true);
        ir.recebeObjeto(cotDet);
        ir.setVisible(true);

        cotDet.setCotCab(cotCab);
        cotDets.add(cotDet);
    }

    @Override
    public void refreshCotacoes(List<Object> data) {
    }

    @Override
    public void addNewCotacao() {
        CotCab cotCab = new CotCab();
        entityManager.persist(cotCab);
        list.add(cotCab);
    }

    @Override
    public void saveChanges() {
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<CotCab> merged = new ArrayList<>(list.size());
            for (CotCab cotCab : list) {
                merged.add(entityManager.merge(cotCab));
            }
            list.clear();
            list.addAll(merged);
        }
    }
    
}
