/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Vista;

import aplicacionglobalfoodtrading.Controlador.Controlador_Pedido;
import aplicacionglobalfoodtrading.Modelo.Pedido;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricardo Carmona
 */
public class Listado_Pedidos extends javax.swing.JFrame {

    Controlador_Pedido cp = new Controlador_Pedido();
    DefaultTableModel modelo = new DefaultTableModel();

    public Listado_Pedidos() {
        initComponents();
        CrearTabla();
        CargarTabla();
        txtFechaFinal3.setEnabled(false);
        txtFechaInicial3.setEnabled(false);
    }

    public String ConvertidorFecha(String fecha) {
        String dia;
        String mes;
        String anio;
        String[] fechar;
        String fechaf;

        fechar = fecha.split("-");
        dia = fechar[1];
        mes = fechar[0];
        anio = fechar[2];
        fechaf = anio + "-" + mes + "-" + dia;
        return fechaf;
    }

    public void CrearTabla() {
        String[] Titulos = {"Codigo Pedido", "Fecha", "Total Productos", "Total Iva", "Total Pago"};
        modelo.setColumnIdentifiers(Titulos);
        TablaPedido.setModel(modelo);
    }

    public void CargarTabla() {
        ArrayList<Pedido> lp = cp.ListadoPedidos();
        LimpiarTabla();
        for (Pedido p : lp) {
            String datos[] = {p.getId_pedido(), p.getFecha(), String.valueOf(p.getLista_Detalles().size()), String.valueOf(p.getTotal_iva()), String.valueOf(p.getTotal_pedido())};
            modelo.addRow(datos);
        }
    }

    public void CargarTablaXFechas() {
        String fechai = ConvertidorFecha(txtFechaInicial3.getText());
        String fechaf = ConvertidorFecha(txtFechaFinal3.getText());
        ArrayList<Pedido> lp = cp.ListadoPedidosxRangoFechas(fechai, fechaf);
        LimpiarTabla();
        for (Pedido p : lp) {
            String datos[] = {p.getId_pedido(), p.getFecha(), String.valueOf(p.getLista_Detalles().size()), String.valueOf(p.getTotal_iva()), String.valueOf(p.getTotal_pedido())};
            modelo.addRow(datos);
        }
    }

    public void CargarTablaXcod(String cod) {
        ArrayList<Pedido> lp = new ArrayList();
        if (cp.BuscarPedidoxCod(cod) == null) {
            LimpiarTabla();
        } else {
            lp.add(cp.BuscarPedidoxCod(cod));
            LimpiarTabla();
            for (Pedido p : lp) {
                String datos[] = {p.getId_pedido(), p.getFecha(), String.valueOf(p.getLista_Detalles().size()), String.valueOf(p.getTotal_iva()), String.valueOf(p.getTotal_pedido())};
                modelo.addRow(datos);
            }
        }

    }

    public void LimpiarTabla() {
        int filas = TablaPedido.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPedido = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        CboOpcion3 = new javax.swing.JComboBox<>();
        btnBuscar3 = new javax.swing.JButton();
        txtbuscar3 = new javax.swing.JTextField();
        txtFechaInicial3 = new datechooser.beans.DateChooserCombo();
        txtFechaFinal3 = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();

        jMenuItem1.setText("Ver Pedido");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TablaPedido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TablaPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaPedido.setComponentPopupMenu(jPopupMenu1);
        TablaPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPedidoMouseClicked(evt);
            }
        });
        TablaPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TablaPedidoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TablaPedido);

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
        jLabel1.setText("LISTADO DE PEDIDOS");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/if_info_1930258 32x32.png"))); // NOI18N
        jButton7.setToolTipText("Informacion mas detallada del pedido");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/if_info_1930258 64X64 (1).png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        CboOpcion3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        CboOpcion3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo Pedido", "Rango Fechas" }));
        CboOpcion3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CboOpcion3ItemStateChanged(evt);
            }
        });
        CboOpcion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboOpcion3ActionPerformed(evt);
            }
        });

        btnBuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/1477811446_Search32x32.png"))); // NOI18N
        btnBuscar3.setBorderPainted(false);
        btnBuscar3.setContentAreaFilled(false);
        btnBuscar3.setFocusPainted(false);
        btnBuscar3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/1477811460_Search48x48.png"))); // NOI18N
        btnBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar3ActionPerformed(evt);
            }
        });

        txtbuscar3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar3KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("A");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtbuscar3)
                    .addComponent(txtFechaInicial3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CboOpcion3, 0, 132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(70, 70, 70)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFechaFinal3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboOpcion3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaInicial3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtFechaFinal3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
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
                        .addGap(208, 208, 208)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPedidoMouseClicked
        if (evt.getClickCount() == 1) {
            System.out.println("Se ha hecho un click");
        }
        if (evt.getClickCount() == 2) {
            System.out.println("Se ha hecho doble click");
        }
    }//GEN-LAST:event_TablaPedidoMouseClicked

    private void TablaPedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaPedidoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaPedidoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (TablaPedido.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un pedido");
        } else {
            Pedido p;
            int f;
            int c = 0;
            f = TablaPedido.getSelectedRow();
            String cod = modelo.getValueAt(f, c).toString();
            p = cp.BuscarPedidoxCod(cod);
            Gestion_Pedidos gp = new Gestion_Pedidos(p);
            gp.setVisible(true);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void CboOpcion3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CboOpcion3ItemStateChanged
        // TODO add your handling code here:
        if (CboOpcion3.getSelectedItem().toString().equals("Rango Fechas")) {
            txtFechaFinal3.setEnabled(true);
            txtFechaInicial3.setEnabled(true);
            btnBuscar3.setEnabled(true);
            txtbuscar3.setEnabled(false);
            CargarTablaXFechas();
        } else {
            btnBuscar3.setEnabled(true);
            txtFechaFinal3.setEnabled(false);
            txtFechaInicial3.setEnabled(false);
            txtbuscar3.setEnabled(true);
            CargarTablaXcod(txtbuscar3.getText());
        }
    }//GEN-LAST:event_CboOpcion3ItemStateChanged

    private void CboOpcion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboOpcion3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboOpcion3ActionPerformed

    private void btnBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar3ActionPerformed
        // TODO add your handling code here:
        if (CboOpcion3.getSelectedItem().toString().equals("Rango Fechas")) {
            CargarTablaXFechas();
        } else {
            CargarTablaXcod(txtbuscar3.getText());
        }

    }//GEN-LAST:event_btnBuscar3ActionPerformed

    private void txtbuscar3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscar3KeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (TablaPedido.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un pedido");
        } else {
            Pedido p;
            int f;
            int c = 0;
            f = TablaPedido.getSelectedRow();
            String cod = modelo.getValueAt(f, c).toString();
            p = cp.BuscarPedidoxCod(cod);
            Gestion_Pedidos gp = new Gestion_Pedidos(p);
            gp.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Listado_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listado_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listado_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listado_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Listado_Pedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboOpcion;
    private javax.swing.JComboBox<String> CboOpcion1;
    private javax.swing.JComboBox<String> CboOpcion3;
    private javax.swing.JTable TablaPedido;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscar3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private datechooser.beans.DateChooserCombo txtFechaFinal;
    private datechooser.beans.DateChooserCombo txtFechaFinal1;
    private datechooser.beans.DateChooserCombo txtFechaFinal3;
    private datechooser.beans.DateChooserCombo txtFechaInicial;
    private datechooser.beans.DateChooserCombo txtFechaInicial1;
    private datechooser.beans.DateChooserCombo txtFechaInicial3;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtbuscar1;
    private javax.swing.JTextField txtbuscar3;
    // End of variables declaration//GEN-END:variables
}
