package com.seguroveic.dto;

public class ApoliceStatusDTO {

    private String id;
    private Integer numero;
    private String status;
    private String placa;
    private String valorSeguro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(String valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}
