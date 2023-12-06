/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cleanarchitecture.adapter;

/**
 *
 * @author Pedro Henrique
 */
public class TipoPgtoView extends javax.swing.JFrame {

  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private EntityManager entityManager;
    private List<TipoPgto> list;
    private Query query;
    private TipoPgtoService tipoPgtoService;

    public TipoPgtoView() {
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }

        tipoPgtoService = new TipoPgtoService(entityManager);
    }

    private void initComponents() {

        inserirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirButtonActionPerformed(evt);
            }
        });

        excluirButton.setText("Excluir");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE,
                masterTable,
                org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"),
                excluirButton,
                org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        excluirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        salvarButton.setText("Salvar");
        salvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButtonActionPerformed(evt);
            }
        });

    }

    private void inserirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tipoPgtoService.inserirTipoPgto(list);
    }

    private void excluirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tipoPgtoService.excluirTipoPgto(list, masterTable.getSelectedRows());
    }

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tipoPgtoService.cancelarAlteracoes(list, query);
    }

    private void salvarButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tipoPgtoService.salvarAlteracoes(list);
    }

    public static void main(final String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TipoPgtoView tp = new TipoPgtoView();
                tp.setVisible(true);
                tp.setTitle(args[0]);
            }
        });
    }

}
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
