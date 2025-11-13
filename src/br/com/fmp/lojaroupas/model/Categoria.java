package br.com.fmp.lojaroupas.model;

import java.util.Date; 

public class Categoria {

    private int id;
    private String nome;
    private String descricao;
    private Date dataCadastro;
    private String status;

    public int getId() {
        return id;
    }

   
    @Override
    public String toString() {
        return getNome(); 
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
