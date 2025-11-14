/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fmp.lojaroupas.dao;

import br.com.fmp.lojaroupas.model.Fornecedor;
import br.com.fmp.lojaroupas.model.Pedido;
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

public class PedidoDAO {

    // -----------------------------------------------------------------
    // --- MÉTODO CREATE (CRIAR) ---
    // -----------------------------------------------------------------
    public void create(Pedido p) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            // SQL para a tabela 'pedido'
            String sql = "INSERT INTO pedido (id_fornecedor, dataPedido, status, valorTotal) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            
            // Pegamos o ID do objeto Fornecedor dentro do Pedido
            stmt.setInt(1, p.getFornecedor().getId()); 
            stmt.setDate(2, new java.sql.Date(p.getDataPedido().getTime()));
            stmt.setString(3, p.getStatus());
            stmt.setDouble(4, p.getValorTotal());
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Pedido salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar pedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // -----------------------------------------------------------------
    // --- MÉTODO READ (LER) ---
    // -----------------------------------------------------------------
    public List<Pedido> read() {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        
        List<Pedido> pedidos = new ArrayList<>(); 
        
        try {
            // SQL com JOIN: Trazemos o pedido (p.*) E o nome do fornecedor (f.nome)
            String sql = "SELECT p.*, f.nome as fornecedor_nome "
                       + "FROM pedido p "
                       + "LEFT JOIN fornecedor f ON p.id_fornecedor = f.id";
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Pedido p = new Pedido();
                
                // 1. Cria o objeto Fornecedor "filho"
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id_fornecedor"));
                f.setNome(rs.getString("fornecedor_nome")); // Pega o nome que veio do JOIN

                // 2. Popula o Pedido
                p.setId(rs.getInt("id"));
                p.setDataPedido(rs.getDate("dataPedido"));
                p.setStatus(rs.getString("status"));
                p.setValorTotal(rs.getDouble("valorTotal"));
                p.setFornecedor(f); // Associa o fornecedor ao pedido
                
                pedidos.add(p); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return pedidos; 
    }

    // -----------------------------------------------------------------
    // --- MÉTODO UPDATE (ATUALIZAR) ---
    // -----------------------------------------------------------------
    public void update(Pedido p) {
        
        Connection con = ConnectionFactory.getConnection(); 
        PreparedStatement stmt = null;                      
        
        try {
            String sql = "UPDATE pedido SET id_fornecedor = ?, dataPedido = ?, status = ?, valorTotal = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, p.getFornecedor().getId());
            stmt.setDate(2, new java.sql.Date(p.getDataPedido().getTime()));
            stmt.setString(3, p.getStatus());
            stmt.setDouble(4, p.getValorTotal());
            stmt.setInt(5, p.getId()); // O 'id' é o último '?' (do WHERE)
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar pedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // -----------------------------------------------------------------
    // --- MÉTODO DELETE (EXCLUIR) ---
    // -----------------------------------------------------------------
    public void delete(int id) {
        
        int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja excluir este pedido?", 
                "Atenção!", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;                      
        
        try {
            String sql = "DELETE FROM pedido WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id); 
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

} 