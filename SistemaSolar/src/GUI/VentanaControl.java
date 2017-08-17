/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Modelo.GestorEscena;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author jeste
 */
public class VentanaControl extends javax.swing.JFrame {

    private GestorEscena gestor;
    
    /**
     * Creates new form VentanaControl
     */
    public VentanaControl(GestorEscena g) {
        super();
        this.gestor = g;
        initComponents();
        setLocation (100, 100);
        
        addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            closeApp(0);
          }
        });
        
        
        Visualization visualization = new Visualization (this, false, gestor.getCanvasVistas());
        visualization.setLocation(this.getWidth() + 100, 100);
        visualization.setVisible(true);

        Visualization visualizationPlanta = new Visualization (this, false, gestor.getCanvasPlanta());
        visualizationPlanta.setLocation(this.getWidth() + visualization.getWidth() + 100, 100);
        visualizationPlanta.setVisible(true);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CamarasGroup = new javax.swing.ButtonGroup();
        PanelCamaras = new javax.swing.JPanel();
        movil = new javax.swing.JRadioButton();
        luna = new javax.swing.JRadioButton();
        nave = new javax.swing.JRadioButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PanelCamaras.setBorder(javax.swing.BorderFactory.createTitledBorder("Camaras"));
        PanelCamaras.setToolTipText("");
        PanelCamaras.setName(""); // NOI18N

        CamarasGroup.add(movil);
        movil.setSelected(true);
        movil.setText("Móvil");
        movil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movilActionPerformed(evt);
            }
        });

        CamarasGroup.add(luna);
        luna.setText("Luna");
        luna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunaActionPerformed(evt);
            }
        });

        CamarasGroup.add(nave);
        nave.setText("Nave");
        nave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCamarasLayout = new javax.swing.GroupLayout(PanelCamaras);
        PanelCamaras.setLayout(PanelCamarasLayout);
        PanelCamarasLayout.setHorizontalGroup(
            PanelCamarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCamarasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCamarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movil)
                    .addComponent(luna)
                    .addComponent(nave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelCamarasLayout.setVerticalGroup(
            PanelCamarasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCamarasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(movil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(luna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nave)
                .addContainerGap())
        );

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salir)
                    .addComponent(PanelCamaras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelCamaras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void movilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movilActionPerformed
        gestor.setCamaraMovil();
    }//GEN-LAST:event_movilActionPerformed

    private void lunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunaActionPerformed
        gestor.setCamaraLuna();
    }//GEN-LAST:event_lunaActionPerformed

    private void naveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naveActionPerformed
        gestor.setCamaraNave();
    }//GEN-LAST:event_naveActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        closeApp(0);
    }//GEN-LAST:event_salirActionPerformed

    private void closeApp (int code) {
        gestor.closeApp(code);
    }
    
    public void showWindow(){
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup CamarasGroup;
    private javax.swing.JPanel PanelCamaras;
    private javax.swing.JRadioButton luna;
    private javax.swing.JRadioButton movil;
    private javax.swing.JRadioButton nave;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
