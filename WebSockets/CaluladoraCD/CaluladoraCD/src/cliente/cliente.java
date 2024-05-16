
package cliente;



import database.Conexion;
import interfaces.OperacionesRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class cliente extends javax.swing.JFrame {

    private Connection conexion;
    private  Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    Registry registry;
    OperacionesRMI i;
    private ArrayList<String> operaciones = new ArrayList<>();
    

  
    
    public cliente() {
       try {
            initComponents();
            setResizable(false);
            CON = Conexion.getInstancia();

            registry = LocateRegistry.getRegistry("192.168.226.123", 1099);
            i = (OperacionesRMI) registry.lookup("Calculadora");
            setLocationRelativeTo(this);
            txtResultado.setEditable(false);

            // Establecer conexión a la base de datos
            //conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/operaciones", "root", "Santiago2001");
            mostrarOperaciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void borrar() {
        txtNum1.setText("");
        txtNum2.setText("");
        txtResultado.setText("");
    }

    public void mensaje(String mensaje) {

        JOptionPane.showMessageDialog(null, mensaje);

    }

    public boolean validar() {

        boolean bnd = false;

        if ("".equals(txtNum1.getText())) {
            mensaje("Debes de introducir un un numero en este campo 1");
            txtNum1.requestFocus();
        } else if ("".equals(txtNum2.getText())) {
            mensaje("Debes de introducir un un numero en este campo 2");
            txtNum2.requestFocus();

        } else {
            bnd = true;
        }

        return bnd;

    }
    
     public void ejecutar(String accion) {

        if (validar()) {

            try {

                double r = 0;
                double n1 = Double.parseDouble(txtNum1.getText());
                double n2 = Double.parseDouble(txtNum2.getText());

                switch (accion) {
                    case "sumar":
                        r = i.sumar(n1, n2);
                        break;
                    case "restar":
                        r = i.restar(n1, n2);
                        break;
                    case "multiplicar":
                        r = i.multiplicar(n1, n2);
                        break;
                    case "dividir":
                        r = i.dividir(n1, n2);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "No elegiste una opcion correcta");

                }

                DecimalFormat df = new DecimalFormat("0.00");
                txtResultado.setText(df.format(r));
                
                 // Guardar la operación en la base de datos
                guardarOperacion(n1, n2, accion, r);

            } catch (RemoteException ex) {
                Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }
     
     private void guardarOperacion(double n1, double n2, String accion, double resultado) {
        try {
            // Preparar la sentencia SQL
            String sql = "INSERT INTO operaciones (numero1, numero2, accion, resultado) VALUES (?, ?, ?, ?)";
            ps = CON.conectar().prepareStatement(sql);
            ps.setDouble(1, n1);
            ps.setDouble(2, n2);
            ps.setString(3, accion);
            ps.setDouble(4, resultado);
            
            // Ejecutar la sentencia SQL
            int executeUpdate = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        mostrarOperaciones();
    }
    
      private void mostrarOperaciones() {
        try {
            // Preparar la consulta SQL
            String sql = "SELECT numero1, numero2, accion, resultado FROM operaciones";
            ps = CON.conectar().prepareStatement(sql);
            
            // Ejecutar la sentencia SQL
            rs=ps.executeQuery();
            
            // Crear el modelo de tabla
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Número 1");
            model.addColumn("Número 2");
            model.addColumn("Acción");
            model.addColumn("Resultado");
            
            // Llenar la tabla con los datos recuperados
            while (rs.next()) {
                double numero1 = rs.getDouble("numero1");
                double numero2 = rs.getDouble("numero2");
                String accion = rs.getString("accion");
                double resultado = rs.getDouble("resultado");
                model.addRow(new Object[]{numero1, numero2, accion, resultado});
            }
            
            // Establecer el modelo de tabla en tu componente JTable
            jTable3.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNum1 = new javax.swing.JTextField();
        txtNum2 = new javax.swing.JTextField();
        txtResultado = new javax.swing.JTextField();
        btnSumar = new javax.swing.JButton();
        btnMultiplicar = new javax.swing.JButton();
        btnRestar = new javax.swing.JButton();
        btnDividir = new javax.swing.JButton();
        btnAC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("numero 1:");

        jLabel2.setText("numero 2:");

        jLabel3.setText("numero 3:");

        txtNum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum1ActionPerformed(evt);
            }
        });

        btnSumar.setText("+");
        btnSumar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarActionPerformed(evt);
            }
        });

        btnMultiplicar.setText("x");
        btnMultiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplicarActionPerformed(evt);
            }
        });

        btnRestar.setText("-");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });

        btnDividir.setText("/");
        btnDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDividirActionPerformed(evt);
            }
        });

        btnAC.setText("AC");
        btnAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNum1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(txtNum2)
                    .addComponent(txtResultado))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSumar)
                            .addComponent(btnRestar))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDividir)
                            .addComponent(btnMultiplicar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnAC)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 147, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSumar)
                    .addComponent(btnMultiplicar))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestar)
                    .addComponent(btnDividir))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAC))
                .addGap(82, 82, 82)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum1ActionPerformed

    private void btnMultiplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiplicarActionPerformed
        // TODO add your handling code here:
           ejecutar("multiplicar");
    }//GEN-LAST:event_btnMultiplicarActionPerformed

    private void btnACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACActionPerformed
        // TODO add your handling code here:
         borrar();
    }//GEN-LAST:event_btnACActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        // TODO add your handling code here:
        ejecutar("restar");
    }//GEN-LAST:event_btnRestarActionPerformed

    private void btnDividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDividirActionPerformed
        // TODO add your handling code here:
         ejecutar("dividir");
    }//GEN-LAST:event_btnDividirActionPerformed

    private void btnSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarActionPerformed
        // TODO add your handling code here:
         ejecutar("sumar");
    }//GEN-LAST:event_btnSumarActionPerformed

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
            java.util.logging.Logger.getLogger(cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new cliente().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAC;
    private javax.swing.JButton btnDividir;
    private javax.swing.JButton btnMultiplicar;
    private javax.swing.JButton btnRestar;
    private javax.swing.JButton btnSumar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField txtNum1;
    private javax.swing.JTextField txtNum2;
    private javax.swing.JTextField txtResultado;
    // End of variables declaration//GEN-END:variables
}
