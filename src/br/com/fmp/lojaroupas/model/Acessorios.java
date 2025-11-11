package br.com.fmp.lojaroupas.model;

public class Acessorios extends Produto {
    
    private String tipo;
    private String material;
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
