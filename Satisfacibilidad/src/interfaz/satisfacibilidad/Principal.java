/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.satisfacibilidad;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.satisfacibilidad.LogicaSatisfacibilidad;

/**
 *
 * @author PC
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    Robot robot;
    LogicaSatisfacibilidad logicaSatisfacibilidad;
    GenerarTabla generar;
    int posicionRetornaBoton = 0;

    public Principal() {
        initComponents();
        logicaSatisfacibilidad = new LogicaSatisfacibilidad();
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtInsertarFormula.requestFocus();
        setLocationRelativeTo(this);
        this.setResizable(false);
        //txtInsertarFormula.setEditable(false);
        txtFormulasProposicionales.setEditable(false);
        lblPosicionCursor.setVisible(false);
        lblPosicion.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregarFormula = new javax.swing.JButton();
        btnGenerarTabla = new javax.swing.JButton();
        btnVerificarSatisfa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnAgregarDobleImplicacion = new javax.swing.JButton();
        btnAgregarImplicacion = new javax.swing.JButton();
        btnAgregarO = new javax.swing.JButton();
        btnAgregarY = new javax.swing.JButton();
        btnAgregarNegacion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarP = new javax.swing.JButton();
        btnAgregarQ = new javax.swing.JButton();
        btnAgregarR = new javax.swing.JButton();
        btnAgregarS = new javax.swing.JButton();
        btnAgregarT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInsertarFormula = new javax.swing.JTextArea();
        btnBorrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFormulasProposicionales = new javax.swing.JTextArea();
        btnPosicionarCursor = new javax.swing.JButton();
        lblPosicionCursor = new javax.swing.JLabel();
        lblPosicion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAgregarFormula.setText("Agregar");
        btnAgregarFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFormulaActionPerformed(evt);
            }
        });

        btnGenerarTabla.setText("Generar Tabla");
        btnGenerarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarTablaActionPerformed(evt);
            }
        });

        btnVerificarSatisfa.setText("Verificar Satisfacibilidad");
        btnVerificarSatisfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarSatisfaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Operadores logicos"));

        btnAgregarDobleImplicacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarDobleImplicacion.setText("( )<->( )");
        btnAgregarDobleImplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDobleImplicacionActionPerformed(evt);
            }
        });

        btnAgregarImplicacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarImplicacion.setText("( )->( )  ");
        btnAgregarImplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImplicacionActionPerformed(evt);
            }
        });

        btnAgregarO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarO.setText("( )v( )");
        btnAgregarO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarOActionPerformed(evt);
            }
        });

        btnAgregarY.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarY.setText("( )^( )");
        btnAgregarY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarYActionPerformed(evt);
            }
        });

        btnAgregarNegacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarNegacion.setText("~( )");
        btnAgregarNegacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNegacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarO, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarImplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAgregarDobleImplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarY, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnAgregarNegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(191, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarY, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarO, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarImplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarDobleImplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnAgregarNegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(157, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Letras proposicionales"));

        btnAgregarP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarP.setText("P");
        btnAgregarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPActionPerformed(evt);
            }
        });

        btnAgregarQ.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarQ.setText("Q");
        btnAgregarQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarQActionPerformed(evt);
            }
        });

        btnAgregarR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarR.setText("R");
        btnAgregarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRActionPerformed(evt);
            }
        });

        btnAgregarS.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarS.setText("S");
        btnAgregarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSActionPerformed(evt);
            }
        });

        btnAgregarT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAgregarT.setText("T");
        btnAgregarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarQ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAgregarT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarP)
                        .addComponent(btnAgregarQ)
                        .addComponent(btnAgregarR)
                        .addComponent(btnAgregarS)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtInsertarFormula.setColumns(20);
        txtInsertarFormula.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtInsertarFormula.setRows(5);
        txtInsertarFormula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtInsertarFormulaMouseClicked(evt);
            }
        });
        txtInsertarFormula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInsertarFormulaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtInsertarFormula);

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        txtFormulasProposicionales.setColumns(20);
        txtFormulasProposicionales.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtFormulasProposicionales.setRows(5);
        jScrollPane2.setViewportView(txtFormulasProposicionales);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnPosicionarCursor.setText("Posicionar Cursor");
        btnPosicionarCursor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPosicionarCursorActionPerformed(evt);
            }
        });

        lblPosicionCursor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPosicionCursor.setText("Posicion Cursor: ");

        lblPosicion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPosicion.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnGenerarTabla)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnVerificarSatisfa)
                                        .addGap(131, 131, 131)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregarFormula, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPosicionarCursor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPosicionCursor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPosicionCursor, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(lblPosicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnVerificarSatisfa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGenerarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPosicionarCursor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTablaActionPerformed

    }//GEN-LAST:event_btnGenerarTablaActionPerformed

    private void btnAgregarNegacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNegacionActionPerformed
        agregarOperadoresLogicos("~()");
    }//GEN-LAST:event_btnAgregarNegacionActionPerformed

    private void btnAgregarYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarYActionPerformed
        agregarOperadoresLogicos("()^()");
    }//GEN-LAST:event_btnAgregarYActionPerformed

    private void btnAgregarOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOActionPerformed
        agregarOperadoresLogicos("()v()");
    }//GEN-LAST:event_btnAgregarOActionPerformed

    private void btnAgregarImplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImplicacionActionPerformed
        agregarOperadoresLogicos("()->()");
    }//GEN-LAST:event_btnAgregarImplicacionActionPerformed

    private void btnAgregarDobleImplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDobleImplicacionActionPerformed
        agregarOperadoresLogicos("()<->()");
    }//GEN-LAST:event_btnAgregarDobleImplicacionActionPerformed

    private void btnAgregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPActionPerformed

        txtInsertarFormula.insert("P", logicaSatisfacibilidad.ubicarCursorConLetra(txtInsertarFormula));
        logicaSatisfacibilidad.getLetrasAgregadas().add("P");

    }//GEN-LAST:event_btnAgregarPActionPerformed

    private void btnAgregarQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarQActionPerformed

        txtInsertarFormula.insert("Q", logicaSatisfacibilidad.ubicarCursorConLetra(txtInsertarFormula));
        logicaSatisfacibilidad.getLetrasAgregadas().add("Q");

    }//GEN-LAST:event_btnAgregarQActionPerformed

    private void btnAgregarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRActionPerformed

        txtInsertarFormula.insert("R", logicaSatisfacibilidad.ubicarCursorConLetra(txtInsertarFormula));
        logicaSatisfacibilidad.getLetrasAgregadas().add("R");

    }//GEN-LAST:event_btnAgregarRActionPerformed

    private void btnAgregarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSActionPerformed

        txtInsertarFormula.insert("S", logicaSatisfacibilidad.ubicarCursorConLetra(txtInsertarFormula));
        logicaSatisfacibilidad.getLetrasAgregadas().add("S");

    }//GEN-LAST:event_btnAgregarSActionPerformed

    private void txtInsertarFormulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInsertarFormulaKeyPressed

    }//GEN-LAST:event_txtInsertarFormulaKeyPressed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if (!logicaSatisfacibilidad.getLetrasAgregadas().empty()) {
            String borrar = logicaSatisfacibilidad.getLetrasAgregadas().pop();
            txtInsertarFormula.setText(txtInsertarFormula.getText().replace(borrar, ""));
            if (txtInsertarFormula.getText().contains(")")) {
                txtInsertarFormula.setCaretPosition(txtInsertarFormula.getText().indexOf(")"));
            }
        } else {
            lblPosicionCursor.setVisible(false);
            lblPosicion.setVisible(false);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnAgregarFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFormulaActionPerformed
        if (txtInsertarFormula.getText().length() >= 4) {
            logicaSatisfacibilidad.getAgregarFormula().add(txtInsertarFormula.getText());
            txtFormulasProposicionales.append(txtInsertarFormula.getText());
            txtFormulasProposicionales.append("\n");
            txtInsertarFormula.setText("");
            logicaSatisfacibilidad.imprimirMapa();
        }
    }//GEN-LAST:event_btnAgregarFormulaActionPerformed

    private void btnVerificarSatisfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarSatisfaActionPerformed
        if (logicaSatisfacibilidad.getAgregarFormula().size() >= 3) {
            System.out.println("algo");

        }
    }//GEN-LAST:event_btnVerificarSatisfaActionPerformed

    private void btnAgregarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTActionPerformed
        txtInsertarFormula.insert("T", logicaSatisfacibilidad.ubicarCursorConLetra(txtInsertarFormula));
        logicaSatisfacibilidad.getLetrasAgregadas().add("T");
    }//GEN-LAST:event_btnAgregarTActionPerformed

    private void btnPosicionarCursorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPosicionarCursorActionPerformed
        this.posicionRetornaBoton = (int) logicaSatisfacibilidad.ubicarCursorConBoton(txtInsertarFormula);
        lblPosicionCursor.setVisible(true);
        lblPosicion.setVisible(true);
        lblPosicion.setText(String.valueOf(this.logicaSatisfacibilidad.getPosicionesBoton()));

    }//GEN-LAST:event_btnPosicionarCursorActionPerformed

    private void txtInsertarFormulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtInsertarFormulaMouseClicked

    }//GEN-LAST:event_txtInsertarFormulaMouseClicked

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDobleImplicacion;
    private javax.swing.JButton btnAgregarFormula;
    private javax.swing.JButton btnAgregarImplicacion;
    private javax.swing.JButton btnAgregarNegacion;
    private javax.swing.JButton btnAgregarO;
    private javax.swing.JButton btnAgregarP;
    private javax.swing.JButton btnAgregarQ;
    private javax.swing.JButton btnAgregarR;
    private javax.swing.JButton btnAgregarS;
    private javax.swing.JButton btnAgregarT;
    private javax.swing.JButton btnAgregarY;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGenerarTabla;
    private javax.swing.JButton btnPosicionarCursor;
    private javax.swing.JButton btnVerificarSatisfa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPosicion;
    private javax.swing.JLabel lblPosicionCursor;
    private javax.swing.JTextArea txtFormulasProposicionales;
    private javax.swing.JTextArea txtInsertarFormula;
    // End of variables declaration//GEN-END:variables

    public void agregarOperadoresLogicos(String formula) {
        if (this.posicionRetornaBoton != 0) {
            if (txtInsertarFormula.getText().length() == 0) {
                this.posicionRetornaBoton = 0;
            }
            txtInsertarFormula.insert(formula, this.posicionRetornaBoton);
        } else {
            txtInsertarFormula.insert(formula, logicaSatisfacibilidad.ubicarCursor(txtInsertarFormula));
        }
        logicaSatisfacibilidad.getLetrasAgregadas().add(formula);
        lblPosicionCursor.setVisible(false);
        lblPosicion.setVisible(false);
        this.posicionRetornaBoton = 0;
    }

}
