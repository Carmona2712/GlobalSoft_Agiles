/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Vista;

import aplicacionglobalfoodtrading.Controlador.Controlador_Empleado_Indirecto;
import aplicacionglobalfoodtrading.Modelo.Empleado_Indirecto;
import com.sun.rowset.internal.Row;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Ricardo Carmona
 */
public class Lista_Empleados_Indirectos extends javax.swing.JFrame {

    Controlador_Empleado_Indirecto cei = new Controlador_Empleado_Indirecto();
    DefaultTableModel modelo = new DefaultTableModel();

    public Lista_Empleados_Indirectos() {
        initComponents();
        CrearTabla();
        CargarTabla();
    }

    public void CrearTabla() {
        String titulos[] = {"Tipo_ID", "IDentificacion", "Nombres", "Apellidos", "Cargo", "Salario"};
        modelo.setColumnIdentifiers(titulos);
        TablaEmpleadosInDirectos.setModel(modelo);
    }

    public void CargarTabla() {
        ArrayList<Empleado_Indirecto> lei = new ArrayList();
        lei = cei.ListadoEmpleadosIndirectos();
        LimpiarTabla();

        for (Empleado_Indirecto e : lei) {
            String Datos[] = {e.getTipo_id(), e.getIdentificacion(), e.getNombres(), e.getApellidos(), e.getCargo(), String.valueOf(e.getSalario())};
            modelo.addRow(Datos);
        }

    }

    public void LimpiarTabla() {
        int filas = TablaEmpleadosInDirectos.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

//    public void exportarExcel(ArrayList<Empleado_Indirecto> li) throws IOException {
//        JFileChooser chooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
//        chooser.setFileFilter(filter);
//        chooser.setDialogTitle("Guardar archivo");
//        chooser.setAcceptAllFileFilterUsed(false);
//        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//            String ruta = chooser.getSelectedFile().toString().concat(".xls");
//            try {
//                File archivoXLS = new File(ruta);
//                if (archivoXLS.exists()) {
//                    archivoXLS.delete();
//                }
//                archivoXLS.createNewFile();
//                Workbook libro = new HSSFWorkbook();
//                FileOutputStream archivo = new FileOutputStream(archivoXLS);
//                Sheet hoja = libro.createSheet("Listado de Empleados indirectos");
//                hoja.setDisplayGridlines(false);
//
//                String[] titulos = {"TIPO ID", "IDENTIFICACIÓN", "NOMBRES", "APELLIDOS", "FECHA NACIMIENTO", "TELEFONO", "E_MAIL", "CARGO", "DIRECCIÓN", "PAIS", "CIUDAD", "FECHA CONTRATACIÓN", "SALARIO", "GENERO"};
//
//                for (int f = 0; f < titulos.length; f++) {
//                    Row fila = (Row) hoja.createRow(f);
//                    for (int c = 0; c < titulos.length; c++) {
//
//                        if (f == 0) {
//                            celda.setCellValue(t.getColumnName(c));
//                        }
//                    }
//                }
//
////                String[] titulos = {"TIPO ID","IDENTIFICACIÓN","NOMBRES","APELLIDOS","FECHA NACIMIENTO","TELEFONO","E_MAIL","CARGO","DIRECCIÓN","PAIS","CIUDAD","FECHA CONTRATACIÓN","SALARIO","GENERO"}; 
////                Row r = (Row) hoja.createRow(0);
////                for(int i=0;i<titulos.length;i++){
////                    if(){
////                        
////                    }
////                }
////            
////                int filaInicio = 1;
////                for (int f = 0; f < t.getRowCount(); f++) {
////                    Row fila = hoja.createRow(filaInicio);
////                    filaInicio++;
////                    for (int c = 0; c < t.getColumnCount(); c++) {
////                        Cell celda = fila.createCell(c);
////                        if (t.getValueAt(f, c) instanceof Double) {
////                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
////                        } else if (t.getValueAt(f, c) instanceof Float) {
////                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
////                        } else {
////                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
////                        }
////                    }
////                }
//                libro.write(archivo);
//                archivo.close();
//                Desktop.getDesktop().open(archivoXLS);
//            } catch (IOException | NumberFormatException e) {
//                throw e;
//            }
//        }
//    }

    public void exportarExcel(ArrayList<Empleado_Indirecto> li) throws IOException {
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
                Sheet hoja = libro.createSheet("Listado empleados indirectos");
                hoja.setDisplayGridlines(true);
                String[] titulos = {"TIPO ID", "IDENTIFICACIÓN", "NOMBRES", "APELLIDOS", "FECHA NACIMIENTO", "TELEFONO", "E_MAIL", "CARGO", "DIRECCIÓN", "PAIS", "CIUDAD", "FECHA CONTRATACIÓN", "SALARIO", "GENERO"};

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
                for (int i = 0; i < li.size(); i++) {
                    org.apache.poi.ss.usermodel.Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    String datos[] = {li.get(i).getTipo_id(), li.get(i).getIdentificacion(), li.get(i).getNombres(), li.get(i).getApellidos(), li.get(i).getFecha_nac(), li.get(i).getTelefono(), li.get(i).getCorreo(), li.get(i).getCargo(), li.get(i).getDireccion(), li.get(i).getPais(), li.get(i).getCiudad(), li.get(i).getFecha_contratacion(), String.valueOf(li.get(i).getSalario()), li.get(i).getGenero()};
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
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEmpleadosInDirectos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        CboOpcion = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        btnExportarExcel = new javax.swing.JButton();

        jMenuItem1.setText("Ver Informacion Completa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Ver Estado Financiero");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TablaEmpleadosInDirectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaEmpleadosInDirectos.setComponentPopupMenu(jPopupMenu1);
        TablaEmpleadosInDirectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaEmpleadosInDirectosMouseClicked(evt);
            }
        });
        TablaEmpleadosInDirectos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TablaEmpleadosInDirectosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TablaEmpleadosInDirectos);

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
        jLabel1.setText("LISTADO DE EMPLEADOS DIRECTOS");

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
        CboOpcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cedula", "Apellido", "Pais", "Cargo" }));
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

        btnExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/excelP.png"))); // NOI18N
        btnExportarExcel.setToolTipText("Exportar a Excel");
        btnExportarExcel.setBorderPainted(false);
        btnExportarExcel.setContentAreaFilled(false);
        btnExportarExcel.setFocusPainted(false);
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CboOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnExportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CboOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportarExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 54, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
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

    private void TablaEmpleadosInDirectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaEmpleadosInDirectosMouseClicked

    }//GEN-LAST:event_TablaEmpleadosInDirectosMouseClicked

    private void TablaEmpleadosInDirectosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaEmpleadosInDirectosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaEmpleadosInDirectosKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (TablaEmpleadosInDirectos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado");
        } else {
            int f = TablaEmpleadosInDirectos.getSelectedRow();
            int c = 1;
            String ide = TablaEmpleadosInDirectos.getValueAt(f, c).toString();
            Empleado_Indirecto ei = cei.RetornarEmpleadoIndirectoXCodigo(ide);
            Empleado_IndirectoVista eiv = new Empleado_IndirectoVista(ei);
            eiv.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CboOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboOpcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboOpcionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_txtbuscarKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (TablaEmpleadosInDirectos.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado");
        } else {
            int f = TablaEmpleadosInDirectos.getSelectedRow();
            int c = 1;
            String ide = TablaEmpleadosInDirectos.getValueAt(f, c).toString();
            Empleado_Indirecto ei = cei.RetornarEmpleadoIndirectoXCodigo(ide);
            Empleado_IndirectoVista eiv = new Empleado_IndirectoVista(ei);
            eiv.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
        try {
            // TODO add your handling code here:
            exportarExcel(this.cei.ListadoEmpleadosIndirectos());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnExportarExcelActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        
        if(TablaEmpleadosInDirectos.getSelectedRow()<0){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un subscriptor");
        }else{
            
            
            
            
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(Lista_Empleados_Indirectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lista_Empleados_Indirectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lista_Empleados_Indirectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lista_Empleados_Indirectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lista_Empleados_Indirectos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboOpcion;
    private javax.swing.JTable TablaEmpleadosInDirectos;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
