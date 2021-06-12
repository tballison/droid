/*
 * Copyright (c) 2016, The National Archives <pronom@nationalarchives.gov.uk>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following
 * conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of the The National Archives nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package uk.gov.nationalarchives.droid.gui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.openide.util.NbBundle;

import uk.gov.nationalarchives.droid.profile.ProfileResourceNode;
import uk.gov.nationalarchives.droid.profile.referencedata.Format;

/**
 *
 * @author rflitcroft
 */
public class MultiIdentificationDialog extends JDialog {

    private DefaultTableModel dataModel;
    private ProfileForm profileForm;
    
    /** 
     * Creates new form MultiIdentificationDialog. 
     * @param parent the dialog's parent
     */
    public MultiIdentificationDialog(ProfileForm parent) {
        super();
        profileForm = parent;
        setModal(true);
        initComponents();
        
        MouseAdapter mouseAdapter = new PuidLinkMouseListener();
        
        jTable1.addMouseMotionListener(mouseAdapter);
        jTable1.addMouseListener(mouseAdapter);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        closeButton = new JButton();
        nodeName = new JLabel();

        setTitle(NbBundle.getMessage(MultiIdentificationDialog.class, "MultiIdentificationDialog.title")); // NOI18N
        setAlwaysOnTop(true);

        headerLabel.setLabelFor(jTable1);

        headerLabel.setText(NbBundle.getMessage(MultiIdentificationDialog.class, "MultiIdentificationDialog.headerLabel.text")); // NOI18N
        jTable1.setModel(new DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        closeButton.setText(NbBundle.getMessage(MultiIdentificationDialog.class, "MultiIdentificationDialog.closeButton.text")); // NOI18N
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        nodeName.setText(NbBundle.getMessage(MultiIdentificationDialog.class, "MultiIdentificationDialog.nodeName.text")); // NOI18N
        nodeName.setMinimumSize(new Dimension(14, 14));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(closeButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addComponent(headerLabel, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(nodeName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(nodeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(closeButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton closeButton;
    private JLabel headerLabel;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JLabel nodeName;
    // End of variables declaration//GEN-END:variables
    
    /**
     * @param node the node with multiple identifications.
     */
    public void showDialog(ProfileResourceNode node) {
        
        headerLabel.setText(String.format(
                "%s Format Identifications for",
                node.getFormatIdentifications().size()));
        nodeName.setText(java.net.URLDecoder.decode(node.getUri().toString()));
        newDataModel(node.getFormatIdentifications());
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    
    @SuppressWarnings("serial")
    private void newDataModel(List<Format> identifications) {
        
        Object puidColumn = "PUID";
        dataModel = new DefaultTableModel(new Object[] {
            puidColumn, "Format", "Version", "MIME type",
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Format format : identifications) {
            dataModel.addRow(new Object[] {
                    format.getPuid(),     
                    format.getName(),     
                    format.getVersion(),     
                    format.getMimeType(),     
            });
        }
           
        jTable1.setModel(dataModel);
        jTable1.getColumn(puidColumn).setCellRenderer(new PuidRenderer());    
        
    }
    
    
    private static final class PuidRenderer extends JLabel implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            setOpaque(true);
            setText("<html><a href=\"\">" + value + "</a></html>");
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }
    
    private class PuidLinkMouseListener extends MouseAdapter {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            Point point = e.getPoint();
            int pointedColumn = jTable1.columnAtPoint(point);
            if (jTable1.convertColumnIndexToModel(pointedColumn) == 0) {
                jTable1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else { 
                jTable1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent event) {
            Point point = event.getPoint();
            int pointedColumn = jTable1.columnAtPoint(point);
            
            if (jTable1.convertColumnIndexToModel(pointedColumn) == 0) {
                String puid = (String) jTable1.getValueAt(jTable1.rowAtPoint(point), jTable1.columnAtPoint(point));
                
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Action.BROWSE)) {
                    try {
                        desktop.browse(URI.create(profileForm.getPronumURLPrefix(puid)));
                    } catch (IOException e) {
                        DialogUtils.showGeneralErrorDialog(
                                profileForm.getDroidMainUi(), "IOException", "Resource not found.");
                    }
                }
            }
        }
    }
}
