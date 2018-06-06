/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Vista;

import aplicacionglobalfoodtrading.Controlador.ControladorCliente;
import aplicacionglobalfoodtrading.Controlador.ControladorProducto;
import aplicacionglobalfoodtrading.Controlador.ControladorProveedor;
import aplicacionglobalfoodtrading.Controlador.Controlador_Detalle_Pedido;
import aplicacionglobalfoodtrading.Controlador.Controlador_Pedido;
import aplicacionglobalfoodtrading.Modelo.Cliente;
import aplicacionglobalfoodtrading.Modelo.Detalle_Pedido;
import aplicacionglobalfoodtrading.Modelo.Pedido;
import aplicacionglobalfoodtrading.Modelo.Producto;
import aplicacionglobalfoodtrading.Modelo.Proveedor;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Ricardo Carmona
 */
public class Estaditicas_Productos extends javax.swing.JFrame {

    JFreeChart grafica_productos;
    JFreeChart grafica_Proveedor;
    JFreeChart grafica_producto_mas_vendido;
    JFreeChart grafica_Mejores_Clitentes;
    Controlador_Pedido cp = new Controlador_Pedido();
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    DefaultPieDataset dataProd = new DefaultPieDataset();
    DefaultCategoryDataset datasetprod = new DefaultCategoryDataset();
    DefaultCategoryDataset dataclie = new DefaultCategoryDataset();
    ControladorProducto cprd = new ControladorProducto();
    ControladorProveedor cpr = new ControladorProveedor();
    Controlador_Detalle_Pedido cdp = new Controlador_Detalle_Pedido();
    ControladorCliente cc = new ControladorCliente();

    public Estaditicas_Productos() {
        initComponents();
        CargarDataSetVentas();
        CargarDataSetProveedores();
        CargarDataSetProductosMasVendidos();
        CargarDataSetMejoresClientes();
    }

    public void CargarDataSetVentas() {
        try {

            for (Pedido p : this.cp.ListadoPedidos()) {
                dataset.addValue(p.getTotal_pedido(), "Ventas x Fecha", p.getFecha());
            }
        } catch (java.lang.NullPointerException ex) {
            System.out.println("ex : " + ex.getMessage());
        }
//            dataset.setValue(100, "SITIO_1", "Lunes");
///           dataset.setValue(120, "SITIO_1", "Martes");
///            dataset.setValue(110, "SITIO_1", "Miércoles");
////            dataset.setValue(103, "SITIO_1", "Jueves");
////            dataset.setValue(106, "SITIO_1", "Viernes");
        grafica_productos = ChartFactory.createBarChart("Ventas x Fechas", "Venta x Dia", "Valor Total", dataset, PlotOrientation.VERTICAL, true, true, false);

        BufferedImage img = grafica_productos.createBufferedImage(lblGrafica.getWidth() - 10, lblGrafica.getHeight() - 10);
        // this.getContentPane().add(img);
        lblGrafica.setIcon(new ImageIcon(img));
    }

    public void CargarDataSetProveedores() {
        try {
            for (Proveedor prv : this.cpr.ListadoProveedores()) {
                int cont = 0;
                for (Producto pd : this.cprd.ListadoProductos()) {
                    if (cpr.ProvedorxCodigo(pd.getFk_proveedor()).getNombre().equals(prv.getNombre())) {
                        cont = cont + 1;
                    }
                    dataProd.setValue(prv.getNombre(), cont);
                }

            }
        } catch (java.lang.NullPointerException ex) {
            System.out.println("ex : " + ex.getMessage());
        }
//            dataset.setValue(100, "SITIO_1", "Lunes");
///           dataset.setValue(120, "SITIO_1", "Martes");
///            dataset.setValue(110, "SITIO_1", "Miércoles");
////            dataset.setValue(103, "SITIO_1", "Jueves");
////            dataset.setValue(106, "SITIO_1", "Viernes");
        grafica_Proveedor = ChartFactory.createPieChart3D("Productos x Proveedor", dataProd);
        BufferedImage img = grafica_Proveedor.createBufferedImage(lblGraficoProveedor.getWidth() - 10, lblGraficoProveedor.getHeight() - 10);
        // this.getContentPane().add(img);
        lblGraficoProveedor.setIcon(new ImageIcon(img));
    }

    public void CargarDataSetProductosMasVendidos() {
        try {
            for (Pedido p : this.cp.ListadoPedidos()) {
                for (Detalle_Pedido dp : this.cdp.ListadoDetallePedidos(p.getId_pedido())) {
                    datasetprod.addValue(dp.getCantidad(),"Productos",cprd.RetornarProductoxCod(dp.getId_producto()).getNombre_prod());
                }
            }
        } catch (java.lang.NullPointerException ex) {
            System.out.println("ex : " + ex.getMessage());
        }
//            dataset.setValue(100, "SITIO_1", "Lunes");
///           dataset.setValue(120, "SITIO_1", "Martes");
///            dataset.setValue(110, "SITIO_1", "Miércoles");
////            dataset.setValue(103, "SITIO_1", "Jueves");
////            dataset.setValue(106, "SITIO_1", "Viernes");
        grafica_producto_mas_vendido = ChartFactory.createBarChart3D("Productos Más Vendidos","Producto Vendido","Cantidad",datasetprod,PlotOrientation.HORIZONTAL,true,true,false);

        BufferedImage img = grafica_producto_mas_vendido.createBufferedImage(lblProductoMasVendido.getWidth() - 10, lblProductoMasVendido.getHeight() - 10);
        // this.getContentPane().add(img);
        lblProductoMasVendido.setIcon(new ImageIcon(img));
    }
    
    public void CargarDataSetMejoresClientes() {
        try {
            for (Cliente c : this.cc.ListadoClientes()) {
                for (Pedido p : this.cp.ListadoPedidos()) {
                    if(p.getId_cliente().equals(c.getIdentificacion())){
                     dataclie.addValue(p.getTotal_pedido(),"Clientes",c.getNombre()+" "+c.getApellido());   
                    }
                }
            }
        } catch (java.lang.NullPointerException ex) {
            System.out.println("ex : " + ex.getMessage());
        }
//            dataset.setValue(100, "SITIO_1", "Lunes");
///           dataset.setValue(120, "SITIO_1", "Martes");
///            dataset.setValue(110, "SITIO_1", "Miércoles");
////            dataset.setValue(103, "SITIO_1", "Jueves");
////            dataset.setValue(106, "SITIO_1", "Viernes");
        grafica_Mejores_Clitentes = ChartFactory.createBarChart("Mejores Clientes","Total Comprado","Clientes",dataclie,PlotOrientation.VERTICAL,true,true,false);

        BufferedImage img = grafica_Mejores_Clitentes.createBufferedImage(lblMejoresClientes.getWidth() - 10, lblMejoresClientes.getHeight() - 10);
        // this.getContentPane().add(img);
        lblMejoresClientes.setIcon(new ImageIcon(img));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGrafica = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblGraficoProveedor = new javax.swing.JLabel();
        lblProductoMasVendido = new javax.swing.JLabel();
        lblMejoresClientes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblGrafica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGrafica.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventas x Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Previous32x32.png"))); // NOI18N
        jButton1.setToolTipText("Volver Atras");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Previous48x48.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setText("PANEL ESTADÍSTICO");

        lblGraficoProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos x Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        lblProductoMasVendido.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblProductoMasVendido.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos más vendidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        lblMejoresClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mejores Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(497, 497, 497)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblGraficoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                            .addComponent(lblGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblProductoMasVendido, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                            .addComponent(lblMejoresClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProductoMasVendido, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(lblGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGraficoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMejoresClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Estaditicas_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estaditicas_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estaditicas_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estaditicas_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estaditicas_Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGrafica;
    private javax.swing.JLabel lblGraficoProveedor;
    private javax.swing.JLabel lblMejoresClientes;
    private javax.swing.JLabel lblProductoMasVendido;
    // End of variables declaration//GEN-END:variables
}
