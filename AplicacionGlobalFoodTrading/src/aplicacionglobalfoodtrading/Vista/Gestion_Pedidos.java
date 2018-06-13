package aplicacionglobalfoodtrading.Vista;

import aplicacionglobalfoodtrading.Controlador.ControladorCliente;
import aplicacionglobalfoodtrading.Controlador.ControladorImpuesto;
import aplicacionglobalfoodtrading.Controlador.ControladorProducto;
import aplicacionglobalfoodtrading.Controlador.Controlador_Detalle_Pedido;
import aplicacionglobalfoodtrading.Controlador.Controlador_Pedido;
import aplicacionglobalfoodtrading.Modelo.Cliente;
import aplicacionglobalfoodtrading.Modelo.Detalle_Pedido;
import aplicacionglobalfoodtrading.Modelo.Pedido;
import aplicacionglobalfoodtrading.Modelo.Producto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricardo Carmona
 */
public class Gestion_Pedidos extends javax.swing.JFrame {

    Controlador_Detalle_Pedido cdp = new Controlador_Detalle_Pedido();
    Controlador_Pedido cp = new Controlador_Pedido();
    ControladorImpuesto ci = new ControladorImpuesto();
    ControladorProducto conpr = new ControladorProducto();
    public static DefaultTableModel modelo_Pedido = new DefaultTableModel();
    ControladorCliente cc = new ControladorCliente();

    public Gestion_Pedidos() {
        initComponents();
        CreatTablaPedido();
        txtCodigoPedido.setText(GenerarCodigo());
    }

    public Gestion_Pedidos(Pedido p) {
        initComponents();
        CreatTablaPedido();
        txtCodigoPedido.setText(GenerarCodigo());
        btnGuardar.setEnabled(false);
        btnLista.setEnabled(false);
        CargarForm(p);
    }
    
    
    public void LimpiarForm(){
         txtCodigoPedido.setText(GenerarCodigo());
        txtFechaPedido.setText("");
        txtIdCliente.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTipoId.setText("");
        LimpiarTabla();
        lblTotalIva.setText("0");
        lblTotalPagar.setText("0");
        
    }

    public void CargarForm(Pedido p) {
        txtCodigoPedido.setText(p.getId_pedido());
        txtFechaPedido.setText(p.getFecha());
        txtIdCliente.setText(p.getId_cliente());
        Cliente c = cc.ClientexID(p.getId_cliente());
        txtNombres.setText(c.getNombre());
        txtApellidos.setText(c.getApellido());
        txtTipoId.setText(c.getTipoid());
        CargarTablaDetalle(p.getLista_Detalles());
        lblTotalIva.setText(String.valueOf(p.getTotal_iva()));
        lblTotalPagar.setText(String.valueOf(p.getTotal_pedido()));
    }
    
    
    public void CargarTablaDetalle(ArrayList<Detalle_Pedido> ld){
        LimpiarTabla();
        
        for(Detalle_Pedido dp : ld){
            Producto p = conpr.RetornarProductoxCod(dp.getId_producto());
            String datos[] = {dp.getId_producto(),p.getNombre_prod(),String.valueOf(dp.getCantidad()),String.valueOf(dp.getValor_producto()),String.valueOf(dp.getIva()),String.valueOf(dp.getValortotal())};
            modelo_Pedido.addRow(datos);
        }
    }

    public String GenerarCodigo() {
        ArrayList<Pedido> lp = new ArrayList();
        lp = cp.ListadoPedidos();
        int cod;
        String cods;
        String transf = "";
        if (lp.isEmpty()) {
            cod = 1;
            transf = "0000";
            transf = transf + String.valueOf(cod);
        } else {
            cods = cp.ListadoPedidos().get(cp.ListadoPedidos().size() - 1).getId_pedido().toString();
            System.err.println("Codigo retornado : " + cods);
            cod = Integer.parseInt(cods);
            cod = cod + 1;

            if (cod > 99 && cod < 999) {
                transf = "000";
                transf = transf + String.valueOf(cod);
            } else if (cod > 999 && cod < 9999) {
                transf = "00";
                transf = transf + String.valueOf(cod);
            } else if (cod > 9999) {
                transf = "0";
                transf = transf + String.valueOf(cod);
            } else if (cod < 100) {
                transf = "0000";
                transf = transf + String.valueOf(cod);
            }

        }
        return transf;
    }

    public void CreatTablaPedido() {
        String titulos[] = {"ID Producto", "Producto", "Cantidad", "Valor Producto", "IVA x UND", "Total IVA", "Valor Total"};
        modelo_Pedido.setColumnIdentifiers(titulos);
        tabla_Pedido.setModel(modelo_Pedido);
    }

    public static void AgregarProducto(Producto p, float valor_imp) {
        int cant = -1;
        do {

            try {
                cant = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de productos"));
            } catch (java.lang.NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ser un valor numerico o un valor positivo ( # > 0)");
                cant = -1;
            }

        } while (cant <= 0);
        System.out.println("Precio mas impuesto = " + p.getPrecio_mas_impuesto());
        System.out.println("cantidad = " + cant);
        System.out.println("IVA = " + valor_imp);
        float valortotalpedido = p.getPrecio_mas_impuesto() * cant;
        String datos_producto[] = {p.getCod_prod(), p.getNombre_prod(), String.valueOf(cant), String.valueOf(p.getPrecio_neto_prod()), String.valueOf(valor_imp), String.valueOf(valortotalpedido)};
        modelo_Pedido.addRow(datos_producto);
    }

    public String ConvertidorFecha(String fecha) {
        String dia;
        String mes;
        String anio;
        String[] fechar;
        String fechaf;

        fechar = fecha.split("/");
        dia = fechar[1];
        mes = fechar[0];
        anio = fechar[2];
        fechaf = anio + "-" + mes + "-" + dia;
        return fechaf;
    }

   public void LimpiarTabla() {
        int filas = tabla_Pedido.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo_Pedido.removeRow(0);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        btnLista = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFechaPedido = new datechooser.beans.DateChooserCombo();
        txtCodigoPedido = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTipoId = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Pedido = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        lblTotalIva = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTotalPagar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jMenuItem1.setText("Eliminar Producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/delete32x32.png"))); // NOI18N
        jButton7.setToolTipText("Eliminar Empleado mediante su identificacion");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/delete48x48.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Lista32x32.png"))); // NOI18N
        btnLista.setToolTipText("Ver Lista de Empleados");
        btnLista.setBorderPainted(false);
        btnLista.setContentAreaFilled(false);
        btnLista.setFocusPainted(false);
        btnLista.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Lista48x48.png"))); // NOI18N
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/1477830534_edit-clear32x32.png"))); // NOI18N
        btnLimpiar.setToolTipText("Restaurar Campos por defecto");
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/1477830839_edit-clear48x48.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Previous32x32.png"))); // NOI18N
        btnAtras.setToolTipText("Volver a la pagina anterior");
        btnAtras.setBorderPainted(false);
        btnAtras.setContentAreaFilled(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Previous48x48.png"))); // NOI18N
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/save32x32.png"))); // NOI18N
        btnGuardar.setToolTipText("Registrar Informacion del empleado");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/save48x48.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(135, 135, 135)
                .addComponent(btnLista)
                .addGap(123, 123, 123)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jButton7)
                .addGap(59, 59, 59))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Codigo Pedido");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Cliente ID");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Fecha");

        txtCodigoPedido.setEditable(false);

        txtIdCliente.setEditable(false);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/lupa16x16.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Tipo Identificacion");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Nombres");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Apellidos");

        txtTipoId.setEditable(false);

        txtNombres.setEditable(false);

        txtApellidos.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoId, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(txtApellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtCodigoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipoId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tabla_Pedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_Pedido.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tabla_Pedido);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("TOTAL IVA :");

        lblTotalIva.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTotalIva.setText("0");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("TOTAL A PAGAR :");

        lblTotalPagar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTotalPagar.setText("0");

        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/Add_Menu_Item.png"))); // NOI18N
        jButton1.setText("Agregar Producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicacionglobalfoodtrading/Iconos/if_minus_1645995.png"))); // NOI18N
        jButton2.setText("Eliminar Producto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(lblTotalIva)
                    .addComponent(jLabel9)
                    .addComponent(lblTotalPagar)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        Listado_Pedidos lp = new Listado_Pedidos();
        lp.setVisible(true);
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtIdCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Pedido p = new Pedido();
            p.setId_pedido(txtCodigoPedido.getText());
            p.setFecha(ConvertidorFecha(txtFechaPedido.getText()));
            p.setId_cliente(txtIdCliente.getText());
            p.setTotal_iva(Float.parseFloat(lblTotalIva.getText()));
            p.setTotal_pedido(Float.parseFloat(lblTotalPagar.getText()));
            if (cp.RegistrarPedido(p) > 0) {
                int totalf = tabla_Pedido.getRowCount();
                for (int i = 0; i < totalf; i++) {
                    Detalle_Pedido d = new Detalle_Pedido();
                    d.setFk_codigo_pedido(p.getId_pedido());
                    d.setId_producto(modelo_Pedido.getValueAt(i, 0).toString());
                    d.setCantidad(Integer.parseInt(modelo_Pedido.getValueAt(i, 2).toString()));
                    d.setValor_producto(Float.parseFloat(modelo_Pedido.getValueAt(i, 3).toString()));
                    d.setIva(Float.parseFloat(modelo_Pedido.getValueAt(i, 5).toString()));
                    d.setValortotal(Float.parseFloat(modelo_Pedido.getValueAt(i, 6).toString()));
                    cdp.RegistrarDetalle_Pedido(d);
                }
                LimpiarForm();
                JOptionPane.showMessageDialog(null,"Pedido Registrado Correctamente !!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Error al Registrar Pedido");
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Listado_Clientes_Pedido lcp = new Listado_Clientes_Pedido();
        lcp.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Lista_Productos_Pedidos lpd = new Lista_Productos_Pedidos();
        lpd.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (tabla_Pedido.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el producto a eliminar");
        } else {
            int filap = tabla_Pedido.getSelectedRow();
            modelo_Pedido.removeRow(filap);
            float totaliva = 0;
            float totalpago = 0;
            System.err.println("numero de filas = " + filap);
            if (tabla_Pedido.getRowCount() == 0) {
                lblTotalIva.setText("0");
                lblTotalPagar.setText("0");
            } else {
                for (int i = 0; i < filap; i++) {
                    totaliva = totaliva + Float.parseFloat(modelo_Pedido.getValueAt(i, 5).toString());
                    totalpago = totalpago + Float.parseFloat(modelo_Pedido.getValueAt(i, 6).toString());
                }

                lblTotalIva.setText(String.valueOf(totaliva));
                lblTotalPagar.setText(String.valueOf(totalpago));
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (tabla_Pedido.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el producto a eliminar");
        } else {
            int filap = tabla_Pedido.getSelectedRow();
            modelo_Pedido.removeRow(filap);
            float totaliva = 0;
            float totalpago = 0;
            System.err.println("numero de filas = " + filap);
            if (tabla_Pedido.getRowCount() == 0) {
                lblTotalIva.setText("0");
                lblTotalPagar.setText("0");
            } else {
                for (int i = 0; i < filap; i++) {
                    totaliva = totaliva + Float.parseFloat(modelo_Pedido.getValueAt(i, 5).toString());
                    totalpago = totalpago + Float.parseFloat(modelo_Pedido.getValueAt(i, 6).toString());
                }

                lblTotalIva.setText(String.valueOf(totaliva));
                lblTotalPagar.setText(String.valueOf(totalpago));

            }

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gestion_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_Pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_Pedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLista;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblTotalIva;
    public static javax.swing.JLabel lblTotalPagar;
    public static javax.swing.JTable tabla_Pedido;
    public static javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCodigoPedido;
    private datechooser.beans.DateChooserCombo txtFechaPedido;
    public static javax.swing.JTextField txtIdCliente;
    public static javax.swing.JTextField txtNombres;
    public static javax.swing.JTextField txtTipoId;
    // End of variables declaration//GEN-END:variables
}
