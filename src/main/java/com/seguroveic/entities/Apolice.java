package com.seguroveic.entities;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Objects;

public class Apolice {

    @Id
    private String id;
    private Integer numero;
    private LocalDate inicioVigencia;
    private LocalDate finalVigencia;
    private String placa;
    private Double valorSeguro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public LocalDate getFinalVigencia() {
        return finalVigencia;
    }

    public void setFinalVigencia(LocalDate finalVigencia) {
        this.finalVigencia = finalVigencia;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apolice apolice = (Apolice) o;
        return numero == apolice.numero &&
                Double.compare(apolice.valorSeguro, valorSeguro) == 0 &&
                Objects.equals(id, apolice.id) &&
                Objects.equals(inicioVigencia, apolice.inicioVigencia) &&
                Objects.equals(finalVigencia, apolice.finalVigencia) &&
                Objects.equals(placa, apolice.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, inicioVigencia, finalVigencia, placa, valorSeguro);
    }

    @Override
    public String toString() {
        return "Apolice{" +
                "id='" + id + '\'' +
                ", numero=" + numero +
                ", inicioVigencia=" + inicioVigencia +
                ", finalVigencia=" + finalVigencia +
                ", placa='" + placa + '\'' +
                ", valorSeguro=" + valorSeguro +
                '}';
    }
}
