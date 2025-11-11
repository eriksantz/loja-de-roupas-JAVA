package br.com.fmp.lojaroupas.dao;

import br.com.fmp.lojaroupas.model.Categoria; 
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

public class CategoriaDAO {

    
    public void create(Categoria c) {
        
        Connection con = ConnectionFactory.getConnection(); 
        PreparedStatement stmt = null;                      
        
        try {
            
            String sql = "INSERT INTO categoria (nome, descricao, dataCadastro, status) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            
           
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            stmt.setDate(3, new java.sql.Date(c.getDataCadastro().getTime())); 
            stmt.setString(4, c.getStatus());
            
          
            stmt.executeUpdate(); 
            
            
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso!");
            
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
        } finally {
          
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

 
    public List<Categoria> read() {
        
        Connection con = ConnectionFactory.getConnection(); 
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        
        List<Categoria> categorias = new ArrayList<>(); 
        
        try {
            
            String sql = "SELECT * FROM categoria";
            stmt = con.prepareStatement(sql);
            
            
            rs = stmt.executeQuery();
            
           
            while (rs.next()) {
                
               
                Categoria c = new Categoria(); 
                
              
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setDataCadastro(rs.getDate("dataCadastro"));
                c.setStatus(rs.getString("status"));
                
              
                categorias.add(c);
            }
            
        } catch (SQLException ex) {
          
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao ler dados: " + ex.getMessage());
        } finally {
          
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
       
        return categorias; 
    }

  
    public void update(Categoria c) {
        
        Connection con = ConnectionFactory.getConnection(); 
        PreparedStatement stmt = null;                      
        
        try {
            
            String sql = "UPDATE categoria SET nome = ?, descricao = ?, dataCadastro = ?, status = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
      
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            stmt.setDate(3, new java.sql.Date(c.getDataCadastro().getTime())); 
            stmt.setString(4, c.getStatus());
            stmt.setInt(5, c.getId()); 
            
        
            stmt.executeUpdate(); 
            
           
            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
            
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex.getMessage());
        } finally {
       
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

   
   
    public void delete(int id) {
        
    
        int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja excluir esta categoria?", 
                "Atenção!", 
                JOptionPane.YES_NO_OPTION);
        
     
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
      
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;                      
        
        try {
            
            String sql = "DELETE FROM categoria WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
            
            stmt.setInt(1, id); 
            
          
            stmt.executeUpdate(); 
            
            
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
            
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex.getMessage());
        } finally {
          
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

} 