/*
 * The MIT License
 *
 * Copyright 2018 Tyler Hickam <tylershub.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ciphers;

import javax.swing.ButtonGroup;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Tyler Hickam <tylershub.com>
 */
public class Caesar extends javax.swing.JPanel {
    /**
     * Creates new form Caesar
     */
    public Caesar() {
        initComponents();
        ButtonGroup modeButtons = new ButtonGroup();
        modeButtons.add(encryptRadio);
        modeButtons.add(decryptRadio);
        
        inputTextArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateOutput(inputTextArea.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateOutput(inputTextArea.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        keySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                updateOutput(inputTextArea.getText());
            }
        });
    }
    
    private void updateOutput(String input) {
        char[] charArray = input.toCharArray();
        int[] intArray = new int[charArray.length];
        
        for(int i = 0; i < charArray.length; i++) {
            intArray[i] = (int)charArray[i];
        }
        
        int[] cryptedInts = encryptOrDecrypt(intArray);
        char[] cryptedChars = new char[cryptedInts.length];
        
        for(int i = 0; i < cryptedInts.length; i++) {
            cryptedChars[i] = (char)cryptedInts[i];
        }
        
        String output = new String(cryptedChars);
        outputTextArea.setText(output);
    }
    
    private int[] encryptOrDecrypt(int[] ints) {
        int modeAdjuster = 1;
        int key = keySlider.getValue();
        
        if(decryptRadio.isSelected()) {
            modeAdjuster = -1;
        }
        
        for(int i = 0; i < ints.length; i++) {
            int charInt = ints[i];
            int posAdjustment = 0;
            
            if(charInt >= 65 && charInt <= 90) {
                posAdjustment = 65;
            } else if(charInt >= 97 && charInt <= 122) {
                posAdjustment = 97;
            }
            
            if(posAdjustment != 0) {
                int adjustedInt = charInt - posAdjustment;
                int cryptedInt = Math.floorMod(adjustedInt + (modeAdjuster*key),26)+posAdjustment;
                ints[i] = cryptedInt;
            }
        }
        
        return ints;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cipherNameLabel = new javax.swing.JLabel();
        cipherDescription = new javax.swing.JLabel();
        inputLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputTextArea = new javax.swing.JTextArea();
        keySlider = new javax.swing.JSlider();
        keyLabel = new javax.swing.JLabel();
        outputLabel = new javax.swing.JLabel();
        modeLabel = new javax.swing.JLabel();
        encryptRadio = new javax.swing.JRadioButton();
        decryptRadio = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(800, 600));

        cipherNameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cipherNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cipherNameLabel.setText("Caesar's Cipher");

        cipherDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cipherDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cipherDescription.setText("A simple cipher that shifts original characters by a defined number (key)");

        inputLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        inputLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputLabel.setText("Input");

        inputTextArea.setColumns(20);
        inputTextArea.setRows(5);
        jScrollPane1.setViewportView(inputTextArea);

        keySlider.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        keySlider.setMajorTickSpacing(1);
        keySlider.setMaximum(25);
        keySlider.setMinimum(1);
        keySlider.setPaintLabels(true);
        keySlider.setPaintTicks(true);
        keySlider.setSnapToTicks(true);
        keySlider.setToolTipText("");
        keySlider.setValue(1);

        keyLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        keyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        keyLabel.setText("Key");

        outputLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        outputLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outputLabel.setText("Output");

        modeLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        modeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modeLabel.setText("Mode");

        encryptRadio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        encryptRadio.setSelected(true);
        encryptRadio.setText("Encrypt");
        encryptRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptRadioActionPerformed(evt);
            }
        });

        decryptRadio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        decryptRadio.setText("Decrypt");
        decryptRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptRadioActionPerformed(evt);
            }
        });

        outputTextArea.setEditable(false);
        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        jScrollPane2.setViewportView(outputTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cipherNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cipherDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(inputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(keyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(keySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(modeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(decryptRadio)
                                            .addComponent(encryptRadio))))
                                .addGap(0, 91, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(cipherNameLabel)
                .addGap(15, 15, 15)
                .addComponent(cipherDescription)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(keySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keyLabel))
                        .addGap(38, 38, 38)
                        .addComponent(outputLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encryptRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(decryptRadio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void encryptRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptRadioActionPerformed
        // TODO add your handling code here:
        updateOutput(inputTextArea.getText());
    }//GEN-LAST:event_encryptRadioActionPerformed

    private void decryptRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptRadioActionPerformed
        // TODO add your handling code here:
        updateOutput(inputTextArea.getText());
    }//GEN-LAST:event_decryptRadioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cipherDescription;
    private javax.swing.JLabel cipherNameLabel;
    private javax.swing.JRadioButton decryptRadio;
    private javax.swing.JRadioButton encryptRadio;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JTextArea inputTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JSlider keySlider;
    private javax.swing.JLabel modeLabel;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextArea outputTextArea;
    // End of variables declaration//GEN-END:variables
}
