/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.fmp.lojaroupas.view;
import br.com.fmp.lojaroupas.dao.CategoriaDAO;
import br.com.fmp.lojaroupas.dao.ProdutoDAO;
import br.com.fmp.lojaroupas.model.Acessorios;
import br.com.fmp.lojaroupas.model.Categoria;
import br.com.fmp.lojaroupas.model.Produto;
import br.com.fmp.lojaroupas.model.Roupas;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date; // Esta você vai precisar para os botões depois

/**
 *
 * @author erikf
 */
public class TelaProduto extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaProduto.class.getName());

  
        /**
     * Creates new form TelaProduto
     */
    public TelaProduto() {
        initComponents();
        
        // --- LÓGICA 1 (ABERTURA) ---
        // 1. Carrega os produtos na tabela
        readJTable();
        
        // 2. Carrega as categorias no ComboBox (DropDown)
        loadComboBoxCategorias(); // <-- A CHAMADA (o "gatilho") FICA AQUI
        
        // --- LÓGICA 2 (O "INTERRUPTOR") ---
        // 3. Define o estado inicial dos painéis dinâmicos
        
        // VERMELHO AQUI? Vá no "Design" e renomeie os JPanels
        pnlRoupa.setVisible(true); // Começa mostrando o painel de Roupa
        pnlAcessorio.setVisible(false); // E escondendo o de Acessório
        
    } // <-- FIM DO CONSTRUTOR

    // -----------------------------------------------------------------
    // --- MÉTODO LOADCOMBOBOX (FORA DO CONSTRUTOR) ---
    // Este método agora é "irmão" do construtor
    // -----------------------------------------------------------------
    private void loadComboBoxCategorias() {
        
        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> categorias = dao.read(); // Pega a lista de categorias
        
        // VERMELHO AQUI? Vá no "Design" e renomeie o JComboBox
        cmbCategoria.removeAllItems(); // Limpa o ComboBox
        
        // Adiciona cada categoria da lista ao ComboBox
        for (Categoria c : categorias) {
            cmbCategoria.addItem(c); 
            // NOTA: Estamos adicionando o *objeto* Categoria inteiro, 
            // mas o ComboBox é inteligente e mostrará o .toString() dele.
        }
    }
    
  
    public void readJTable() {
        
        DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
        modelo.setNumRows(0); 
        
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.read(); // Chama o DAO polimórfico

        // Percorre a lista mista
        for (Produto p : produtos) {
            
            String tipo = ""; // Variável para guardar o tipo
            
            // Verifica o tipo de objeto
            if (p instanceof Roupas) {
                tipo = "Roupa";
            } else if (p instanceof Acessorios) {
                tipo = "Acessório";
            }
            
            // Adiciona as 6 colunas
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getQuantidadeEstoque(),
                p.getCategoria().getNome(), // Pega o NOME da categoria (do JOIN)
                tipo
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtPreco = new javax.swing.JTextField();
        txtEstoque = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox<>();
        btnExcluir = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        radRoupa = new javax.swing.JRadioButton();
        radAcessorio = new javax.swing.JRadioButton();
        pnlRoupa = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTamanho = new javax.swing.JTextField();
        txtMaterialRoupa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pnlAcessorio = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTipoAcessorio = new javax.swing.JTextField();
        txtMaterialAcessorio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Nome");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 9, -1, -1));

        jLabel1.setText("Preço");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 37, -1, -1));

        jLabel2.setText("Estoque");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 65, -1, -1));

        jLabel3.setText("Categoria");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 93, -1, -1));

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 6, 110, -1));

        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });
        jPanel1.add(txtPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 34, 110, -1));

        txtEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstoqueActionPerformed(evt);
            }
        });
        jPanel1.add(txtEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 110, -1));

        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 90, 110, -1));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        buttonGroup1.add(radRoupa);
        radRoupa.setText("Roupa");
        radRoupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radRoupaActionPerformed(evt);
            }
        });
        jPanel1.add(radRoupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        buttonGroup1.add(radAcessorio);
        radAcessorio.setText("Acessorio");
        radAcessorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radAcessorioActionPerformed(evt);
            }
        });
        jPanel1.add(radAcessorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        pnlRoupa.setAlignmentX(1.0F);
        pnlRoupa.setAlignmentY(1.0F);

        jLabel9.setText("Tamanho:");

        txtTamanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTamanhoActionPerformed(evt);
            }
        });

        txtMaterialRoupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaterialRoupaActionPerformed(evt);
            }
        });

        jLabel5.setText("Material Roupa:");

        javax.swing.GroupLayout pnlRoupaLayout = new javax.swing.GroupLayout(pnlRoupa);
        pnlRoupa.setLayout(pnlRoupaLayout);
        pnlRoupaLayout.setHorizontalGroup(
            pnlRoupaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRoupaLayout.createSequentialGroup()
                .addGroup(pnlRoupaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRoupaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addGroup(pnlRoupaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGap(32, 32, 32)
                .addGroup(pnlRoupaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaterialRoupa, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        pnlRoupaLayout.setVerticalGroup(
            pnlRoupaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRoupaLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnlRoupaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRoupaLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRoupaLayout.createSequentialGroup()
                        .addComponent(txtTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(pnlRoupaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaterialRoupa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(34, 34, 34))
        );

        jPanel1.add(pnlRoupa, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, 110));

        pnlAcessorio.setAlignmentX(1.0F);
        pnlAcessorio.setAlignmentY(1.0F);
        pnlAcessorio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Tipo:");
        pnlAcessorio.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel7.setText("Material Acessório:");
        pnlAcessorio.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 43, -1, -1));

        txtTipoAcessorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoAcessorioActionPerformed(evt);
            }
        });
        pnlAcessorio.add(txtTipoAcessorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 120, -1));

        txtMaterialAcessorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaterialAcessorioActionPerformed(evt);
            }
        });
        pnlAcessorio.add(txtMaterialAcessorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 120, -1));

        jPanel1.add(pnlAcessorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 384, -1));

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "PREÇO", "ESTOQUE", "CATEGORIA", "TIPO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void radAcessorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAcessorioActionPerformed
                                          
    // Esconde o painel de Roupa
    pnlRoupa.setVisible(false);
    // Mostra o painel de Acessório
    pnlAcessorio.setVisible(true);

    }//GEN-LAST:event_radAcessorioActionPerformed

    private void txtTamanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTamanhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamanhoActionPerformed

    private void txtMaterialRoupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaterialRoupaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaterialRoupaActionPerformed

    private void txtTipoAcessorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoAcessorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoAcessorioActionPerformed

    private void radRoupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radRoupaActionPerformed
                                        
    // Mostra o painel de Roupa
    pnlRoupa.setVisible(true);
    // Esconde o painel de Acessório
    pnlAcessorio.setVisible(false);
    }//GEN-LAST:event_radRoupaActionPerformed

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoActionPerformed

    private void txtEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstoqueActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
                                               
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' não pode estar vazio.");
            return;
        }
        if (txtPreco.getText().trim().isEmpty() || txtEstoque.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos 'Preço' e 'Estoque' são obrigatórios.");
            return;
        }
        
       
        Produto p = null; 
        
       
        if (radRoupa.isSelected()) {
            
            
            Roupas r = new Roupas();
            
           
            r.setNome(txtNome.getText());
            r.setPreco(Double.parseDouble(txtPreco.getText()));
            r.setQuantidadeEstoque(Integer.parseInt(txtEstoque.getText()));
            r.setCategoria((Categoria) cmbCategoria.getSelectedItem());
            
          
            r.setTamanho(txtTamanho.getText());
            r.setMaterial(txtMaterialRoupa.getText());
            
            p = r;
            
        } else if (radAcessorio.isSelected()) {
            
           
            Acessorios a = new Acessorios();
            
          
            a.setNome(txtNome.getText());
            a.setPreco(Double.parseDouble(txtPreco.getText()));
            a.setQuantidadeEstoque(Integer.parseInt(txtEstoque.getText()));
            a.setCategoria((Categoria) cmbCategoria.getSelectedItem());
            
           
            a.setTipo(txtTipoAcessorio.getText());
            a.setMaterial(txtMaterialAcessorio.getText());
            
            p = a; 
            
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um tipo (Roupa ou Acessório).");
            return;
        }

      
        ProdutoDAO dao = new ProdutoDAO();
        dao.create(p); 

     
        txtNome.setText("");
        txtPreco.setText("");
        txtEstoque.setText("");
        txtTamanho.setText("");
        txtMaterialRoupa.setText("");
        txtTipoAcessorio.setText("");
        txtMaterialAcessorio.setText("");
        
   
        readJTable();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void txtMaterialAcessorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaterialAcessorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaterialAcessorioActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
   
        if (tblProdutos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.");
            return;
        }

        int id = (int) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0);
        ProdutoDAO dao = new ProdutoDAO();
        dao.delete(id); 

      
        txtNome.setText("");
        txtPreco.setText("");
        txtEstoque.setText("");
        txtTamanho.setText("");
        txtMaterialRoupa.setText("");
        txtTipoAcessorio.setText("");
        txtMaterialAcessorio.setText("");

        // 5. ATUALIZA A TABELA
        readJTable();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // 1. VALIDAÇÃO: Verifica se uma linha foi selecionada
        if (tblProdutos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para atualizar.");
            return;
        }
        
        // 2. VALIDAÇÃO: Verifica campos comuns
        if (txtNome.getText().trim().isEmpty() || txtPreco.getText().trim().isEmpty() || txtEstoque.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos 'Nome', 'Preço' e 'Estoque' são obrigatórios.");
            return;
        }

        Produto p = null; 
        
        
        int id = (int) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0);

       
        if (radRoupa.isSelected()) {
            
           
            Roupas r = new Roupas();
            
         
            r.setId(id); 
            r.setNome(txtNome.getText());
            r.setPreco(Double.parseDouble(txtPreco.getText()));
            r.setQuantidadeEstoque(Integer.parseInt(txtEstoque.getText()));
            r.setCategoria((Categoria) cmbCategoria.getSelectedItem());
            
         
            r.setTamanho(txtTamanho.getText());
            r.setMaterial(txtMaterialRoupa.getText());
            
            p = r; 
            
        } else if (radAcessorio.isSelected()) {
            
         
            Acessorios a = new Acessorios();
            
   
            a.setId(id); 
            a.setNome(txtNome.getText());
            a.setPreco(Double.parseDouble(txtPreco.getText()));
            a.setQuantidadeEstoque(Integer.parseInt(txtEstoque.getText()));
            a.setCategoria((Categoria) cmbCategoria.getSelectedItem());
            a.setTipo(txtTipoAcessorio.getText());
            a.setMaterial(txtMaterialAcessorio.getText());
            
            p = a;
        }

       
        ProdutoDAO dao = new ProdutoDAO();
        dao.update(p); 

      
        txtNome.setText("");
        txtPreco.setText("");
        txtEstoque.setText("");
        txtTamanho.setText("");
        txtMaterialRoupa.setText("");
        txtTipoAcessorio.setText("");
        txtMaterialAcessorio.setText("");
        
        
        readJTable();
    }//GEN-LAST:event_btnAtualizarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaProduto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Categoria> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlAcessorio;
    private javax.swing.JPanel pnlRoupa;
    private javax.swing.JRadioButton radAcessorio;
    private javax.swing.JRadioButton radRoupa;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtMaterialAcessorio;
    private javax.swing.JTextField txtMaterialRoupa;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtTamanho;
    private javax.swing.JTextField txtTipoAcessorio;
    // End of variables declaration//GEN-END:variables
}
