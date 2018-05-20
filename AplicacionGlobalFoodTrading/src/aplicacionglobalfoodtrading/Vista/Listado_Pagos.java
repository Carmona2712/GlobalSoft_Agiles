/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Vista;

import aplicacionglobalfoodtrading.Controlador.Controlador_Empleado_Directo;
import aplicacionglobalfoodtrading.Controlador.Controlador_Empleado_Indirecto;
import aplicacionglobalfoodtrading.Controlador.Controlador_Pago_Empleado;
import aplicacionglobalfoodtrading.Modelo.Empleado;
import aplicacionglobalfoodtrading.Modelo.Empleado_Directo;
import aplicacionglobalfoodtrading.Modelo.Empleado_Indirecto;
import aplicacionglobalfoodtrading.Modelo.Pago_Empleado;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricardo Carmona
 */
public class Listado_Pagos extends javax.swing.JFrame {

    Controlador_Pago_Empleado cpe = new Controlador_Pago_Empleado();
    DefaultTableModel modelo = new DefaultTableModel();
    Controlador_Empleado_Directo ced = new Controlador_Empleado_Directo();
    Controlador_Empleado_Indirecto cei = new Controlador_Empleado_Indirecto();

    public Listado_Pagos() {
        initComponents();
        this.txtFechaFinal.setEnabled(false);
        this.txtFechaInicial.setEnabled(false);
        CrearTablaPAgos();
        CargarTablaGeneral();
    }

    public void CrearTablaPAgos() {
        String Titulos[] = {"Codigo Pago", "Fecha de Pago", "Mes Pagado", "Tipo ID", "Identificación", "Nombres", "Apellidos", "Valos Pagado"};
        this.modelo.setColumnIdentifiers(Titulos);
        TablaPagos.setModel(modelo);
    }

    public void CargarTablaGeneral() {

        ArrayList<Pago_Empleado> lp = new ArrayList();
        lp = cpe.ListadoPagoEmpleados();
        LimpiarTabla();
        for (Pago_Empleado p : lp) {

            Empleado_Indirecto ei;
            Empleado_Directo ed;
            String nombres;
            String apellidos;
            String tipoId;
            ei = cei.RetornarEmpleadoIndirectoXCodigo(p.getFk_Empleado());
            ed = ced.Empleadoxid(p.getFk_Empleado());

            if (ei == null) {
                nombres = ed.getNombre();
                apellidos = ed.getApellido();
                tipoId = ed.getTipoid();
            } else {
                nombres = ei.getNombres();
                apellidos = ei.getApellidos();
                tipoId = ei.getTipo_id();
            }
            String fechares;
            String dia;
            String mes;
            String anio;
            String[] partes = p.getFecha_Pago().split("-");
            anio = partes[0];
            mes = partes[1];
            dia = partes[2];
            fechares = dia + "-" + mes + "-" + anio;
            String datos[] = {p.getCodigo_Pago(), fechares, p.getMes_pagado(), tipoId, p.getFk_Empleado(), nombres, apellidos, String.valueOf(p.getTotal_Pago())};
            modelo.addRow(datos);
        }

    }

    public void CargarTablaXRangoFechas() {

        ArrayList<Pago_Empleado> lp = new ArrayList();
        String dia1,mes1,anio1,dia2,mes2,anio2;
        String fechai,fechaf;
        String[] partes1 = this.txtFechaInicial.getText().split("-");
        dia1 = partes1[1];
        mes1 = partes1[0];
        anio1 = partes1[2];
        String[] partes2 = this.txtFechaFinal.getText().split("-");
        dia2 = partes2[1];
        mes2 = partes2[0];
        anio2 = partes2[2];
        fechai = anio1+"-"+mes1+"-"+dia1;
        fechaf = anio2+"-"+mes2+"-"+dia2;
        System.out.println("Fecha inicial : "+fechai);
        System.out.println("Fecha final : "+fechaf);
        lp = cpe.ListadoPagoEmpleadosEntreFechas(fechai,fechaf);
        LimpiarTabla();
        for (Pago_Empleado p : lp) {

            Empleado_Indirecto ei;
            Empleado_Directo ed;
            String nombres;
            String apellidos;
            String tipoId;
            ei = cei.RetornarEmpleadoIndirectoXCodigo(p.getFk_Empleado());
            ed = ced.Empleadoxid(p.getFk_Empleado());

            if (ei == null) {
                nombres = ed.getNombre();
                apellidos = ed.getApellido();
                tipoId = ed.getTipoid();
            } else {
                nombres = ei.getNombres();
                apellidos = ei.getApellidos();
                tipoId = ei.getTipo_id();
            }
            String fechares;
            String dia;
            String mes;
            String anio;
            String[] partes = p.getFecha_Pago().split("-");
            anio = partes[0];
            mes = partes[1];
            dia = partes[2];
            fechares = dia + "-" + mes + "-" + anio;
            String datos[] = {p.getCodigo_Pago(),fechares, p.getMes_pagado(), tipoId, p.getFk_Empleado(), nombres, apellidos, String.valueOf(p.getTotal_Pago())};
            modelo.addRow(datos);
        }

    }

    public void LimpiarTabla() {
        int filas = TablaPagos.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPagos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        CboOpcion = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        txtFechaInicial = new datechooser.beans.DateChooserCombo();
        txtFechaFinal = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TablaPagos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TablaPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPagosMouseClicked(evt);
            }
        });
        TablaPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TablaPagosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TablaPagos);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Previous32x32.png"))); // NOI18N
        jButton1.setToolTipText("Volver a la pagina anterior");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Previous48x48.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("LISTADO DE PAGO A CLIENTES");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Informacion_Adcional_48x48.png"))); // NOI18N
        jButton2.setToolTipText("Informacion mas detallada");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Informacion_Adcional_64x64.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        CboOpcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        CboOpcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo Pago", "Identificacion", "Rango Fechas", "Tipo Empleado" }));
        CboOpcion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CboOpcionItemStateChanged(evt);
            }
        });
        CboOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboOpcionActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/1477811446_Search32x32.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/1477811460_Search48x48.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtbuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("A");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CboOpcion, 0, 132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CboOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPagosMouseClicked
        if (evt.getClickCount() == 1) {
            System.out.println("Se ha hecho un click");
        }
        if (evt.getClickCount() == 2) {
            System.out.println("Se ha hecho doble click");
        }
    }//GEN-LAST:event_TablaPagosMouseClicked

    private void TablaPagosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaPagosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaPagosKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CboOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboOpcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboOpcionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        if (CboOpcion.getSelectedItem().toString().equals("Rango Fechas")) {
            CargarTablaXRangoFechas();
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void CboOpcionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CboOpcionItemStateChanged
        // TODO add your handling code here:
        if (CboOpcion.getSelectedItem().toString().equals("Rango Fechas")) {
            txtFechaFinal.setEnabled(true);
            txtFechaInicial.setEnabled(true);
        }
    }//GEN-LAST:event_CboOpcionItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Listado_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listado_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listado_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listado_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Listado_Pagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboOpcion;
    private javax.swing.JTable TablaPagos;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private datechooser.beans.DateChooserCombo txtFechaFinal;
    private datechooser.beans.DateChooserCombo txtFechaInicial;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
