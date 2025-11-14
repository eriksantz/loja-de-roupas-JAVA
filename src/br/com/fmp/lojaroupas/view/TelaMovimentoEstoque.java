/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.fmp.lojaroupas.view;

import br.com.fmp.lojaroupas.dao.MovimentoEstoqueDAO;
import br.com.fmp.lojaroupas.dao.ProdutoDAO;
import br.com.fmp.lojaroupas.model.MovimentoEstoque; 
import br.com.fmp.lojaroupas.model.Produto;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel; 
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erikf
 */
public class TelaMovimentoEstoque extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaMovimentoEstoque.class.getName());

    
    public TelaMovimentoEstoque() {
        initComponents();
      
    loadComboBoxProdutos();   
    loadComboBoxTipoMovimento();
    readJTable();               
    }
    
    // --- MÉTODO NOVO 1: Carregar Produtos no ComboBox ---
    private void loadComboBoxProdutos() {
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.read(); 
        
        cmbProduto.removeAllItems(); // (Confirme que seu JComboBox se chama 'cmbProduto' no Design)
        
        for (Produto p : produtos) {
            cmbProduto.addItem(p); 
        }
    }
    
    // --- MÉTODO NOVO 2: Carregar "Entrada/Saída" no ComboBox ---
    private void loadComboBoxTipoMovimento() {
        // (Confirme que seu JComboBox se chama 'cmbTipoMovimento' no Design)
        cmbTipoMovimento.removeAllItems();
        cmbTipoMovimento.setModel(new DefaultComboBoxModel<>(new String[] {
            "ENTRADA",
            "SAIDA"
        }));
    }

    // --- MÉTODO READJTABLE (ADAPTADO) ---
    public void readJTable() {
        // (Confirme que sua JTable se chama 'tblMovimentos' no Design)
        DefaultTableModel modelo = (DefaultTableModel) tblMovimentos.getModel();
        modelo.setNumRows(0); 

        MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO(); 
        List<MovimentoEstoque> movimentos = dao.read(); 

        for (MovimentoEstoque m : movimentos) { 
            modelo.addRow(new Object[]{ 
                m.getId(),
                m.getProduto().getNome(), // Pega o NOME do produto (do JOIN)
                m.getTipoMovimento(),
                m.getQuantidade(),
                m.getDataMovimento()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        btnAtualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnCadastrar1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnAtualizar1 = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmbProduto = new javax.swing.JComboBox<>();
        cmbTipoMovimento = new javax.swing.JComboBox<>();
        txtQuantidade = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMovimentos = new javax.swing.JTable();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        jLabel2.setText("Descrição");

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(txtNome))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(btnExcluir))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(btnAtualizar)))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnCadastrar)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btnCadastrar))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnAtualizar)))
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOME", "DESCRIÇÃO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategorias);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Produto:");

        btnCadastrar1.setText("Cadastrar");
        btnCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo Movimento:");

        btnAtualizar1.setText("Atualizar");
        btnAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizar1ActionPerformed(evt);
            }
        });

        btnExcluir1.setText("Excluir");
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Quantidade:");

        cmbTipoMovimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCadastrar1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoMovimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuantidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(btnExcluir1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(btnAtualizar1)))
                        .addGap(4, 4, 4)))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cmbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCadastrar1))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizar1)
                    .addComponent(jLabel4)
                    .addComponent(cmbTipoMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnExcluir1)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );

        tblMovimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Produto", "Tipo", "Quantidade", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMovimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMovimentosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMovimentos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
      
    if (txtQuantidade.getText().trim().isEmpty()) { // (Confirme que seu JTextField se chama 'txtQuantidade')
        JOptionPane.showMessageDialog(null, "O campo 'Quantidade' não pode estar vazio.");
        return;
    }
    
    MovimentoEstoque m = new MovimentoEstoque(); 
    
    m.setProduto((Produto) cmbProduto.getSelectedItem()); 
    m.setTipoMovimento((String) cmbTipoMovimento.getSelectedItem());
    m.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
    m.setDataMovimento(new Date()); // Pega a data atual

    MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO(); 
    dao.create(m); 

    txtQuantidade.setText("");
    readJTable();

    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed

    if (tblMovimentos.getSelectedRow() == -1) { 
        JOptionPane.showMessageDialog(null, "Selecione um movimento para atualizar.");
        return;
    }
    
    if (txtQuantidade.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "O campo 'Quantidade' não pode estar vazio.");
        return; 
    }

    MovimentoEstoque m = new MovimentoEstoque(); 
    m.setId((int) tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 0));
    m.setProduto((Produto) cmbProduto.getSelectedItem()); 
    m.setTipoMovimento((String) cmbTipoMovimento.getSelectedItem());
    m.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
    m.setDataMovimento((Date) tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 4)); // Mantém a data original

    MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO(); 
    dao.update(m); 

    txtQuantidade.setText("");
    readJTable();

    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

    if (tblMovimentos.getSelectedRow() == -1) { 
        JOptionPane.showMessageDialog(null, "Selecione um movimento para excluir.");
        return;
    }

    int id = (int) tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 0); 

    MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO(); 
    dao.delete(id); 

    txtQuantidade.setText("");
    readJTable();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked

        if (tblCategorias.getSelectedRow() != -1) {

            // 2. Pega os valores da linha selecionada
            // .getValueAt(linha_selecionada, coluna_desejada)
            // Lembre-se que as colunas são: 0=ID, 1=NOME, 2=DESCRIÇÃO

            String nome = tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 1).toString();
            String descricao = tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 2).toString();

            // 3. Coloca esses valores nos campos de texto
            txtNome.setText(nome);
            txtDescricao.setText(descricao);
        }
    }//GEN-LAST:event_tblCategoriasMouseClicked

    private void btnCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar1ActionPerformed
    
    if (txtQuantidade.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "O campo 'Quantidade' não pode estar vazio.");
        return;
    }
    
    MovimentoEstoque m = new MovimentoEstoque(); 
    
    m.setProduto((Produto) cmbProduto.getSelectedItem()); 
    m.setTipoMovimento((String) cmbTipoMovimento.getSelectedItem());
    m.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
    m.setDataMovimento(new Date()); 

    MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO(); 
    dao.create(m); 

    txtQuantidade.setText("");
    readJTable();
    }//GEN-LAST:event_btnCadastrar1ActionPerformed

    private void btnAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizar1ActionPerformed
    
    if (tblMovimentos.getSelectedRow() == -1) { 
        JOptionPane.showMessageDialog(null, "Selecione um movimento para atualizar.");
        return;
    }
    
    if (txtQuantidade.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "O campo 'Quantidade' não pode estar vazio.");
        return; 
    }
    MovimentoEstoque m = new MovimentoEstoque(); 
    
    m.setId((int) tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 0));
    m.setProduto((Produto) cmbProduto.getSelectedItem()); 
    m.setTipoMovimento((String) cmbTipoMovimento.getSelectedItem());
    m.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
    m.setDataMovimento((java.util.Date) tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 4));

    MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO(); 
    dao.update(m); 


    txtQuantidade.setText("");
    cmbProduto.setSelectedIndex(0);
    cmbTipoMovimento.setSelectedIndex(0);


    readJTable();
    }//GEN-LAST:event_btnAtualizar1ActionPerformed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed

    if (tblMovimentos.getSelectedRow() == -1) { 
        JOptionPane.showMessageDialog(null, "Selecione um movimento para excluir.");
        return;
    }

    int id = (int) tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 0); 

    MovimentoEstoqueDAO dao = new MovimentoEstoqueDAO(); 
    dao.delete(id); 

    txtQuantidade.setText("");
    readJTable();
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void tblMovimentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMovimentosMouseClicked

   if (tblMovimentos.getSelectedRow() != -1) { 
        
        // Colunas: 0=ID, 1=PRODUTO(nome), 2=TIPO, 3=QTD, 4=DATA
        
        // Acha o Produto no ComboBox
        String nomeProduto = tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 1).toString();
        for (int i = 0; i < cmbProduto.getItemCount(); i++) {
            Produto p = (Produto) cmbProduto.getItemAt(i);
            if (p.getNome().equals(nomeProduto)) {
                cmbProduto.setSelectedIndex(i);
                break;
            }
        }
        
        // Acha o Tipo no ComboBox
        String tipoMov = tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 2).toString();
        for (int i = 0; i < cmbTipoMovimento.getItemCount(); i++) {
            String tipo = (String) cmbTipoMovimento.getItemAt(i);
            if (tipo.equals(tipoMov)) {
                cmbTipoMovimento.setSelectedIndex(i);
                break;
            }
        }

        // Seta a Quantidade
        txtQuantidade.setText(tblMovimentos.getValueAt(tblMovimentos.getSelectedRow(), 3).toString());
    }
    }//GEN-LAST:event_tblMovimentosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMovimentoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMovimentoEstoque().setVisible(true); // <-- MUDOU
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnAtualizar1;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCadastrar1;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JComboBox<Produto> cmbProduto;
    private javax.swing.JComboBox<String> cmbTipoMovimento;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTable tblMovimentos;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
