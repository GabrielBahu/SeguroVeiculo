package com.seguroveic.dto;

public class ErroDTO {

    private int codigo;
    private String modulo;
    private String detalhe;

    public ErroDTO(int codigo, String modulo, String detalhe) {
        this.codigo = codigo;
        this.modulo = modulo;
        this.detalhe = detalhe;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
}
