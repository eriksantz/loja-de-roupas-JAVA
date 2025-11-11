package br.com.fmp.lojaroupas.model;

public class Roupas extends Produto {
    
    private String tamanho;
    private String material;

    
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
