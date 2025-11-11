package br.com.fmp.lojaroupas.dao;

// Importações necessárias
import br.com.fmp.lojaroupas.model.Acessorios;
import br.com.fmp.lojaroupas.model.Categoria;
import br.com.fmp.lojaroupas.model.Produto;
import br.com.fmp.lojaroupas.model.Roupas;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutoDAO {

 
    public void create(Produto p) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
         
            String sql;
            
         
            if (p instanceof Roupas) {
              
                sql = "INSERT INTO produto (nome, id_categoria, preco, quantidadeEstoque, tipo_produto, tamanho, material_roupa) "
                    + "VALUES (?, ?, ?, ?, 'ROUPA', ?, ?)";
                
                stmt = con.prepareStatement(sql);
                
             
                stmt.setString(1, p.getNome());
                stmt.setInt(2, p.getCategoria().getId());
                stmt.setDouble(3, p.getPreco());
                stmt.setInt(4, p.getQuantidadeEstoque());
                
             
                Roupas r = (Roupas) p; 
                stmt.setString(5, r.getTamanho());
                stmt.setString(6, r.getMaterial());

          
            } else if (p instanceof Acessorios) {
               
                sql = "INSERT INTO produto (nome, id_categoria, preco, quantidadeEstoque, tipo_produto, tipo_acessorio, material_acessorio) "
                    + "VALUES (?, ?, ?, ?, 'ACESSORIO', ?, ?)";
                
                stmt = con.prepareStatement(sql);
                
            
                stmt.setString(1, p.getNome());
                stmt.setInt(2, p.getCategoria().getId());
                stmt.setDouble(3, p.getPreco());
                stmt.setInt(4, p.getQuantidadeEstoque());
                
                Acessorios a = (Acessorios) p; 
                stmt.setString(5, a.getTipo());
                stmt.setString(6, a.getMaterial());
                
            } else {
               
                throw new SQLException("Tipo de produto desconhecido.");
            }

            
            stmt.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
        
    }
    // -----------------------------------------------------------------
    // --- MÉTODO READ (LER) ---
    // -----------------------------------------------------------------
    /**
     * READ - Método Polimórfico.
     * Lê a tabela 'produto' e, com base na coluna 'tipo_produto',
     * decide se instancia um objeto 'Roupas' ou 'Acessorios'.
     *
     */
    public List<Produto> read() {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        
        List<Produto> produtos = new ArrayList<>(); // A lista mista
        
        try {
            // 1. SQL para ler tudo. Trazemos as colunas da mãe E das filhas
            //    Usamos 'LEFT JOIN' para pegar a Categoria junto.
            String sql = "SELECT p.*, c.nome as cat_nome, c.descricao as cat_desc "
                       + "FROM produto p "
                       + "LEFT JOIN categoria c ON p.id_categoria = c.id";
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            // 2. Loop para ler linha por linha
            while (rs.next()) {
                
                Produto produto = null; // Começa como nulo
                String tipoProduto = rs.getString("tipo_produto"); // Pega o tipo

                // 3. Pega a Categoria (que veio do JOIN)
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNome(rs.getString("cat_nome"));
                cat.setDescricao(rs.getString("cat_desc"));
                // (Não pegamos os outros dados da categoria para simplificar)

                // 4. A DECISÃO POLIMÓRFICA
                if ("ROUPA".equals(tipoProduto)) {
                    
                    Roupas r = new Roupas();
                    
                    // Popula os dados comuns (de Produto)
                    r.setId(rs.getInt("id"));
                    r.setNome(rs.getString("nome"));
                    r.setPreco(rs.getDouble("preco"));
                    r.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                    r.setCategoria(cat);
                    
                    // Popula os dados específicos (de Roupas)
                    r.setTamanho(rs.getString("tamanho"));
                    r.setMaterial(rs.getString("material_roupa"));
                    
                    produto = r; // O produto desta linha é uma Roupa

                } else if ("ACESSORIO".equals(tipoProduto)) {
                    
                    Acessorios a = new Acessorios();
                    
                    // Popula os dados comuns (de Produto)
                    a.setId(rs.getInt("id"));
                    a.setNome(rs.getString("nome"));
                    a.setPreco(rs.getDouble("preco"));
                    a.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                    a.setCategoria(cat);
                    
                    // Popula os dados específicos (de Acessorios)
                    a.setTipo(rs.getString("tipo_acessorio"));
                    a.setMaterial(rs.getString("material_acessorio"));
                    
                    produto = a; // O produto desta linha é um Acessório
                }

                // 5. Adiciona o produto (seja ele qual for) na lista
                if (produto != null) {
                    produtos.add(produto);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        // 6. Retorna a lista mista
        return produtos; 
    }
    // -----------------------------------------------------------------
    // --- MÉTODO UPDATE (ATUALIZAR) ---
    // -----------------------------------------------------------------
    /**
     * UPDATE - Método Polimórfico.
     * Atualiza um produto existente usando a lógica 'instanceof'.
     *
     */
    public void update(Produto p) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            String sql;
            
            // 1. Verifica se o objeto 'p' é uma 'Roupa'
            if (p instanceof Roupas) {
                sql = "UPDATE produto SET nome = ?, id_categoria = ?, preco = ?, quantidadeEstoque = ?, "
                    + "tamanho = ?, material_roupa = ? "
                    + "WHERE id = ?"; // O ID é a chave
                
                stmt = con.prepareStatement(sql);
                
                // Dados comuns
                stmt.setString(1, p.getNome());
                stmt.setInt(2, p.getCategoria().getId());
                stmt.setDouble(3, p.getPreco());
                stmt.setInt(4, p.getQuantidadeEstoque());
                
                // Dados específicos de Roupa
                Roupas r = (Roupas) p;
                stmt.setString(5, r.getTamanho());
                stmt.setString(6, r.getMaterial());
                
                // ID para o WHERE
                stmt.setInt(7, p.getId());

            // 2. Verifica se o objeto 'p' é um 'Acessorio'
            } else if (p instanceof Acessorios) {
                sql = "UPDATE produto SET nome = ?, id_categoria = ?, preco = ?, quantidadeEstoque = ?, "
                    + "tipo_acessorio = ?, material_acessorio = ? "
                    + "WHERE id = ?"; // O ID é a chave
                
                stmt = con.prepareStatement(sql);
                
                // Dados comuns
                stmt.setString(1, p.getNome());
                stmt.setInt(2, p.getCategoria().getId());
                stmt.setDouble(3, p.getPreco());
                stmt.setInt(4, p.getQuantidadeEstoque());
                
                // Dados específicos de Acessorio
                Acessorios a = (Acessorios) p;
                stmt.setString(5, a.getTipo());
                stmt.setString(6, a.getMaterial());
                
                // ID para o WHERE
                stmt.setInt(7, p.getId());
                
            } else {
                throw new SQLException("Tipo de produto desconhecido para atualização.");
            }

            // 3. Executa o SQL
            stmt.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    // -----------------------------------------------------------------
    // --- MÉTODO DELETE (EXCLUIR) ---
    // -----------------------------------------------------------------
    /**
     * DELETE - Método simples.
     * Deleta qualquer produto (Roupa ou Acessório) da tabela única.
     *
     */
    public void delete(int id) {
        
        // Confirmação do usuário
        int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja excluir este produto?", 
                "Atenção!", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;                      
        
        try {
            String sql = "DELETE FROM produto WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id); 
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
            
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
} 

