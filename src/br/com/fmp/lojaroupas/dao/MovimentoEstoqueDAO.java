/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fmp.lojaroupas.dao;
import br.com.fmp.lojaroupas.model.MovimentoEstoque;
import br.com.fmp.lojaroupas.model.Produto;
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

public class MovimentoEstoqueDAO {

    // -----------------------------------------------------------------
    // --- MÉTODO CREATE (CRIAR) ---
    // -----------------------------------------------------------------
    public void create(MovimentoEstoque m) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
          
            String sql = "INSERT INTO movimento_estoque (id_produto, tipoMovimento, quantidade, dataMovimento) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            
          
            stmt.setInt(1, m.getProduto().getId()); 
            stmt.setString(2, m.getTipoMovimento());
            stmt.setInt(3, m.getQuantidade());
            stmt.setDate(4, new java.sql.Date(m.getDataMovimento().getTime()));
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Movimento salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar movimento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // -----------------------------------------------------------------
    // --- MÉTODO READ (LER) ---
    // -----------------------------------------------------------------
    public List<MovimentoEstoque> read() {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        
        List<MovimentoEstoque> movimentos = new ArrayList<>(); 
        
        try {
          
            String sql = "SELECT m.*, p.nome as produto_nome "
                       + "FROM movimento_estoque m "
                       + "LEFT JOIN produto p ON m.id_produto = p.id";
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                MovimentoEstoque m = new MovimentoEstoque();
                
                // 1. Cria o objeto Produto "filho"
                Produto p = new Produto() {}; 
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("produto_nome")); 

               
                m.setId(rs.getInt("id"));
                m.setTipoMovimento(rs.getString("tipoMovimento"));
                m.setQuantidade(rs.getInt("quantidade"));
                m.setDataMovimento(rs.getDate("dataMovimento"));
                m.setProduto(p); 
                
                movimentos.add(m); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovimentoEstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return movimentos; 
    }

    // -----------------------------------------------------------------
    // --- MÉTODO UPDATE (ATUALIZAR) ---
    // -----------------------------------------------------------------
    public void update(MovimentoEstoque m) {
        
        Connection con = ConnectionFactory.getConnection(); 
        PreparedStatement stmt = null;                      
        
        try {
            String sql = "UPDATE movimento_estoque SET id_produto = ?, tipoMovimento = ?, quantidade = ?, dataMovimento = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, m.getProduto().getId());
            stmt.setString(2, m.getTipoMovimento());
            stmt.setInt(3, m.getQuantidade());
            stmt.setDate(4, new java.sql.Date(m.getDataMovimento().getTime()));
            stmt.setInt(5, m.getId()); // O 'id' é o último '?' (do WHERE)
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Movimento atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar movimento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // -----------------------------------------------------------------
    // --- MÉTODO DELETE (EXCLUIR) ---
    // -----------------------------------------------------------------
    public void delete(int id) {
        
        int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja excluir este movimento?", 
                "Atenção!", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;                      
        
        try {
            String sql = "DELETE FROM movimento_estoque WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id); 
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Movimento excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir movimento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

} 