/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Vista;

import aplicacionglobalfoodtrading.Controlador.ControladorBodega;
import aplicacionglobalfoodtrading.Controlador.ControladorCategoria;
import aplicacionglobalfoodtrading.Controlador.ControladorImpuesto;
import aplicacionglobalfoodtrading.Controlador.ControladorOtroImp;
import aplicacionglobalfoodtrading.Controlador.ControladorProducto;
import aplicacionglobalfoodtrading.Controlador.ControladorProveedor;
import aplicacionglobalfoodtrading.Controlador.ControladorSubcategoria;
import aplicacionglobalfoodtrading.Modelo.Empleado_Directo;
import aplicacionglobalfoodtrading.Modelo.Producto;
import aplicacionglobalfoodtrading.Modelo.Proveedor;
import aplicacionglobalfoodtrading.Modelo.Recurso;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Ricardo Andres
 */
public class Lista_Productos extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    ControladorProducto cp = new ControladorProducto();
    ControladorProveedor cprov = new ControladorProveedor();
    ControladorCategoria cc = new ControladorCategoria();
    ControladorSubcategoria cs = new ControladorSubcategoria();
    ControladorBodega cb = new ControladorBodega();
    ControladorImpuesto ci = new ControladorImpuesto();
    ControladorOtroImp co = new ControladorOtroImp();
    
    public Lista_Productos() {
        initComponents();
        CrearTabla();
        CargarTablaGeneral();
    }

   public void CrearTabla() {
        String titulos[] = {"Codigo", "Nombre", "Proveedor","Precio de Costo", "Precio Venta", "Utilidad", "Tipo Producto", "Precio Neto", "Precio + Impuesto"};
        modelo.setColumnIdentifiers(titulos);
        TablaProductos.setModel(modelo);
    }

    public void CargarTablaGeneral() {
        ArrayList<Producto> lp = new ArrayList();
        try {
            LimpiarTabla();
            lp = this.cp.ListadoProductos();

//                 for(Empleado_Directo d : led){
//                     System.out.println("Cedula : "+d.getIdentificacion());
//                 }

            for (Producto p : lp) {
                Proveedor pr = cprov.ProvedorxCodigo(p.getFk_proveedor());
                System.err.println(pr.getNombre());
                String datos[] = {p.getCod_prod(), p.getNombre_prod(), pr.getNombre(), String.valueOf(p.getPrec_cost_prod()) ,String.valueOf(p.getPrec_vent_prod()), String.valueOf(p.getUtilidad_prod()), p.getTipo_prod(), String.valueOf(p.getPrecio_neto_prod()),String.valueOf(p.getPrecio_mas_impuesto())};
                modelo.addRow(datos);
            }
        } catch (java.lang.NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error de carga de datos en tabla " + ex.getMessage());
        }
    }
    
    
    public void LimpiarTabla() {
        int filas = TablaProductos.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }
    
    
    public void exportarExcel(ArrayList<Producto> lr) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Listado Productos");
                hoja.setDisplayGridlines(true);
                String[] titulos = {"CÓDIGO", "NOMBRE", "TIPO", "PROVEEDOR", "COSTO PRODUCTO", "COSTO EN DOLAR", "PRECIO DE VENTA", "UTILIDAD", "CATEGORIA", "SUBCATEGORIA", "BODEGA","SE VENDE X","VENTA CONTIENE","SE COMPRA X","COMPRA CONTIENE","TIPO","ESTA ACTIVO","NOMBRE IMPUESTO 1","APLICA VENTA","APLICA COMPRA","NOMBRE IMPUESTO 2","APLICA VENTA","APLICA COMPRA","PRECIO NETO PRODUCTO","PRECIO MAS IMPUESTOS"};

                for (int f = 0; f < titulos.length; f++) {
                    org.apache.poi.ss.usermodel.Row fila = hoja.createRow(f);
                    for (int c = 0; c < titulos.length; c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(titulos[c].toString());
                        }
                    }
                }
                //int filaInicio = 1;
//                for (int f = 0; f < li.size(); f++) {
//                    org.apache.poi.ss.usermodel.Row fila = hoja.createRow(filaInicio);
//                    filaInicio++;
//                    for (int c = 0; c < titulos.length; c++) {
//                        Cell celda = fila.createCell(c);
//                        if (t.getValueAt(f, c) instanceof Double) {
//                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
//                        } else if (t.getValueAt(f, c) instanceof Float) {
//                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
//                        } else {
//                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
//                        }
//                    }
//                }
                    
                int filaInicio = 1;
                for (int i = 0; i < lr.size(); i++) {
                    
                    String imp1,imp2,imp1ven,imp1comp,imp2vent,imp2comp;
                    
                    if(ci.RetornarImpuestoxCod(lr.get(i).getFk_impuesto1())==null){
                        imp1 = "Ninguno";
                        imp1comp = "No";
                        imp1ven = "No";
                    }else{
                        imp1 = ci.RetornarImpuestoxCod(lr.get(i).getFk_impuesto1()).getDescripcion();
                        
                        if(lr.get(i).getImpuesto1_aplic_compra()==1){
                            imp1comp = "Si";
                        }else{
                            imp1comp = "No";
                        }
                        
                        if(lr.get(i).getImpuesto1_aplic_venta()==1){
                            imp1ven = "Si";
                        }else{
                            imp1ven = "No";
                        }
                        
                    }
                    
                    if(co.RetornarOtroImpuestoxCod(lr.get(i).getFk_impuesto2())==null){
                        imp2 = "Ninguno";
                        imp2comp = "No";
                        imp2vent = "No";
                    }else{
                        imp2 = co.RetornarOtroImpuestoxCod(lr.get(i).getFk_impuesto2()).getDescripcion();
                        
                        if(lr.get(i).getImpuesto2_aplic_compra()==1){
                            imp2comp = "Si";
                        }else{
                            imp2comp = "No";
                        }
                        
                        if(lr.get(i).getImpuesto2_aplic_venta()==1){
                            imp2vent = "Si";
                        }else{
                            imp2vent = "No";
                        }
                        
                    }
                    
                    
                    org.apache.poi.ss.usermodel.Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    String datos[] = {lr.get(i).getCod_prod(),lr.get(i).getNombre_prod(),lr.get(i).getTipo_prod(),this.cprov.ProvedorxCodigo(lr.get(i).getFk_proveedor()).getNombre(),String.valueOf(lr.get(i).getPrec_cost_prod()),String.valueOf(lr.get(i).getPrec_cost_prod_dolar()),String.valueOf(lr.get(i).getPrec_vent_prod()),String.valueOf(lr.get(i).getUtilidad_prod()),cc.RetornarCategoriaxCod(lr.get(i).getFk_categoria()).getCategoria(),cs.RetornarSubcategoriaxCod(lr.get(i).getFk_subcategoria()).getNombre(),cb.RetornarBodegaxCod(lr.get(i).getFk_bodega()).getNombre_b(),lr.get(i).getSe_vende_x(),String.valueOf(lr.get(i).getVende_contiene()),lr.get(i).getSe_Compra_x(),String.valueOf(lr.get(i).getCompra_contiene()),lr.get(i).getTipo_prod(),String.valueOf(lr.get(i).getEsta_activo()),imp1,imp1ven,imp1comp,imp2,imp2vent,imp2comp,String.valueOf(lr.get(i).getPrecio_neto_prod()),String.valueOf(lr.get(i).getPrecio_mas_impuesto())};
                    for (int c = 0; c < datos.length; c++) {
                        Cell celda = fila.createCell(c);
                        System.out.println(datos[c]);
                        celda.setCellValue(datos[c]);
                    }

                }

                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        CboOpcion = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        jMenuItem1.setText("Ver Informacion Completa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaProductos.setComponentPopupMenu(jPopupMenu1);
        TablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductosMouseClicked(evt);
            }
        });
        TablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TablaProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

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
        jLabel1.setText("LISTADO DE PRODUCTOS");

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
        CboOpcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Nombre", "Proveedor", "Tipo", "Inactivos" }));
        CboOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboOpcionActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Buscar32x32.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Buscar48x48.png"))); // NOI18N
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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/excel32_x_32.png"))); // NOI18N
        jButton3.setToolTipText("Exportar Listado de productos a excel");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/excel_48_x_48 (2).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CboOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CboOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                .addGap(13, 13, 13))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductosMouseClicked
        if (evt.getClickCount() == 1) {
            System.out.println("Se ha hecho un click");
        }
        if (evt.getClickCount() == 2) {
            System.out.println("Se ha hecho doble click");
        }
    }//GEN-LAST:event_TablaProductosMouseClicked

    private void TablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaProductosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaProductosKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        Empleado_Directo ed = new Empleado_Directo();
//        int f;
//        int c = 1;
//        if (TablaProductos.getSelectedRow() < 0) {
//            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila con registro valido");
//        } else {
//            f = TablaProductos.getSelectedRow();
//            String id = String.valueOf(TablaProductos.getValueAt(f, c));
//            ed = ced.Empleadoxid(id);
//            Registro_Empleados_Directos re = new Registro_Empleados_Directos(ed);
//            re.setVisible(true);
       // }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CboOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboOpcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboOpcionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        // TODO add your handling code here:

////        if (CboOpcion.getSelectedItem().toString().equals("Pais")) {
////            CargarTablaxPais();
////        }
////        if (CboOpcion.getSelectedItem().toString().equals("Apellido")) {
////            CargarTablaGeneral();
////        }
////        if (CboOpcion.getSelectedItem().toString().equals("Identificacion")) {
////            CargarTablaxIdentificacion();
////        }
////        if (CboOpcion.getSelectedItem().toString().equals("Cargo")) {
////            CargarTablaxCargo();
////        }

    }//GEN-LAST:event_txtbuscarKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(TablaProductos.getSelectedRow()<0){
            JOptionPane.showMessageDialog(null,"Debe seleccionar un producto");
        }else{
            int f = TablaProductos.getSelectedRow();
            int c = 0;
            
            String codigo = TablaProductos.getValueAt(f, c).toString();
            Producto p = cp.RetornarProductoxCod(codigo);
            RegistroProductos rp = new RegistroProductos(p);
            rp.setVisible(true);
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            exportarExcel(cp.ListadoProductos());
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(null,"Error al crear hoja de excel : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lista_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lista_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lista_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lista_Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lista_Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboOpcion;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
