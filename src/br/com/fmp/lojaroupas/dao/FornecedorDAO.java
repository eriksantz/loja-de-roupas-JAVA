package br.com.fmp.lojaroupas.dao;

import br.com.fmp.lojaroupas.model.Fornecedor;
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

public class FornecedorDAO {

    
    public void create(Fornecedor f) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            String sql = "INSERT INTO fornecedor (nome, contato, email, telefone) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getContato());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar fornecedor: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    
    public List<Fornecedor> read() {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        
        List<Fornecedor> fornecedores = new ArrayList<>(); 
        
        try {
            String sql = "SELECT * FROM fornecedor"; 
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Fornecedor f = new Fornecedor(); 
                
               
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setContato(rs.getString("contato"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                
                fornecedores.add(f); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return fornecedores; 
    }

   
    public void update(Fornecedor f) {
        
        Connection con = ConnectionFactory.getConnection(); 
        PreparedStatement stmt = null;                      
        
        try {
          
            String sql = "UPDATE fornecedor SET nome = ?, contato = ?, email = ?, telefone = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getContato());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getTelefone());
            stmt.setInt(5, f.getId()); // O 'id' é o último '?' (do WHERE)
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar fornecedor: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

   
    public void delete(int id) {
        
        int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja excluir este fornecedor?", 
                "Atenção!", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;                      
        
        try {
            String sql = "DELETE FROM fornecedor WHERE id = ?"; 
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id); 
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

} 