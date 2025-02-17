
package com.mycompany.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class Editar extends javax.swing.JPanel {

    /**
     * Creates new form Editar
     */
    public Editar() {
        initComponents();
        InitStyles();
        
    }

private void Buscar(String archivo, int id) {
    try {
        System.out.println("Intentando abrir archivo: " + archivo);

        File file = new File(archivo);
        if (!file.exists()) {
            System.out.println("Error: El archivo no existe.");
            TextArea.setText("Error: Archivo no encontrado.");
            return;
        }

        RandomAccessFile raf = new RandomAccessFile(archivo, "r"); // Modo lectura
        TextArea.setText(""); // Limpiar el JTextArea

        int tamRegistro = 34; // Tamaño fijo del registro
        long pos = (id - 1) * tamRegistro; // Posición exacta del registro en el archivo
        System.out.println("Posición calculada: " + pos);
        System.out.println("Tamaño del archivo: " + raf.length());

        if (pos >= raf.length()) { // Verificar si el ID existe en el archivo
            System.out.println("No se encontró el ID: " + id);
            TextArea.setText("No se encontró el ID: " + id);
            raf.close();
            return;
        }

        raf.seek(pos); // Ir a la posición exacta

        byte[] buffer = new byte[tamRegistro]; 
        raf.readFully(buffer); // Leer exactamente 34 bytes

        String linea = new String(buffer);
        System.out.println("Linea leída: '" + linea + "'");

        // Extraer el ID desde el archivo
        String idLeido = linea.substring(0, 3).trim();
        System.out.println("ID leído: '" + idLeido + "'");
        System.out.println("ID esperado: '" + String.format("%03d", id) + "'");

        if (!idLeido.equals(String.format("%03d", id))) {
            System.out.println("No se encontró el ID: " + id);
            TextArea.setText("No se encontró el ID: " + id);
        } else {
            System.out.println("Coincidencia encontrada: " + linea);
            String usuario = linea.substring(3, 18).trim(); // Extraer el usuario
            String clave = linea.substring(18, 33).trim(); // Extraer la clave
            TextArea.append(usuario + ":" + clave + "\n"); // Agregar al JTextArea
        }

        raf.close(); // Cerrar archivo
    } catch (IOException e) {
        System.out.println("Error I/O: " + e.getMessage());
        TextArea.setText("Error al leer el archivo.");
    }
}


    private void Editar(String ruta, int id) {
        try {
            RandomAccessFile raf = new RandomAccessFile(ruta, "rw"); // Modo lectura/escritura

            int tamRegistro = 34; // Tamaño fijo del registro
            long pos = (id - 1) * tamRegistro; // Posición exacta del registro en el archivo

            if (pos >= raf.length()) { // Verificar si el ID existe en el archivo
                JOptionPane.showMessageDialog(null, "ID no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                raf.close();
                return;
            }

            raf.seek(pos); // Ir a la posición exacta

            byte[] buffer = new byte[tamRegistro];
            raf.readFully(buffer); // Leer exactamente 34 bytes

            String lineaExistente = new String(buffer).trim();

            System.out.println("Linea Existente: '" + lineaExistente + "'");
            System.out.println("Formato ID: '" + String.format("%03d", id) + "'");
            System.out.println("Contenido leído: '" + Arrays.toString(buffer) + "'");

            if (lineaExistente.isEmpty() || !lineaExistente.startsWith(String.format("%03d", id))) {
                JOptionPane.showMessageDialog(null, "ID no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                raf.close();
                return;
            }

            // Obtener los nuevos datos desde el JTextArea
            String texto = TextArea.getText().trim();
            String[] partesArea = texto.split(":");

            if (partesArea.length < 2) {
                JOptionPane.showMessageDialog(null, "Formato incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                raf.close();
                return;
            }

            // Formatear los datos con tamaño fijo
            String nuevoUsuario = String.format("%-15s", partesArea[0]); // Usuario de 15 caracteres
            String nuevaClave = String.format("%-15s", partesArea[1]);   // Clave de 15 caracteres
            String nuevaLinea = String.format("%03d%s%s\n", id, nuevoUsuario, nuevaClave);

            raf.seek(pos); // Volver a la posición exacta
            raf.writeBytes(nuevaLinea); // Escribir el nuevo registro

            JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito.");

            raf.close(); // Cerrar archivo
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al editar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




     private void InitStyles(){
    titleEditar.putClientProperty( "FlatLaf.style", "font: 200% $semibold.font" );
    TextID.putClientProperty( "JComponent.roundRect", true );
    ID_E.putClientProperty( "FlatLaf.styleClass",  "font: 200% $light.font" );
    InformacionUsuarioText.putClientProperty( "FlatLaf.styleClass",  "font: 200% $light.font" );
    btnActualizar.putClientProperty( "JButton.buttonType", "roundRect" );
    btnBuscarEditar.putClientProperty( "JButton.buttonType", "roundRect" );
    TextArea.putClientProperty( "JComponent.roundRect", true );
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleEditar = new javax.swing.JLabel();
        texto = new javax.swing.JLabel();
        TextID = new javax.swing.JTextField();
        ID_E = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        InformacionUsuarioText = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnBuscarEditar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        titleEditar.setText("Editar");

        texto.setText("Por favor, ingrese el ID del usuario que desea editar.");

        TextID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDActionPerformed(evt);
            }
        });

        ID_E.setText("ID");

        TextArea.setColumns(20);
        TextArea.setRows(5);
        jScrollPane1.setViewportView(TextArea);

        InformacionUsuarioText.setText("Informacion del Usuario");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBuscarEditar.setText("Buscar");
        btnBuscarEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(titleEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarEditar))
                            .addComponent(ID_E, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(InformacionUsuarioText, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titleEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texto)
                .addGap(18, 18, 18)
                .addComponent(ID_E, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEditar))
                .addGap(7, 7, 7)
                .addComponent(InformacionUsuarioText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    String ruta =  Principal.rutaArchivo;
    int id = Integer.parseInt(TextID.getText());
        Editar(ruta, id);   
        
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBuscarEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEditarActionPerformed
        String ruta = Principal.rutaArchivo;
        System.out.println(ruta);
        int Id = Integer.parseInt(TextID.getText());
        Buscar(ruta, Id);
        
        
        
        
        
    }//GEN-LAST:event_btnBuscarEditarActionPerformed

    private void TextIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID_E;
    private javax.swing.JLabel InformacionUsuarioText;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JTextField TextID;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarEditar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel texto;
    private javax.swing.JLabel titleEditar;
    // End of variables declaration//GEN-END:variables
}
