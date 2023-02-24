package com.seguroveic.service;

import com.seguroveic.dto.ApoliceStatusDTO;
import com.seguroveic.entities.Apolice;
import com.seguroveic.repositories.ApoliceRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ApoliceService {

    private final ApoliceRepository apoRepository;

    public ApoliceService(ApoliceRepository apoRepository) {
        this.apoRepository = apoRepository;
    }

    private int getRandomNumber() {
        Random random = new Random();
        int min = 10000;
        int max = 99999;

        return random.ints(min, max).findFirst().getAsInt();
    }

    public Integer gerarNumeroUnico() {
        Integer numero;

        do {
            numero = getRandomNumber();
        } while(apoRepository.findByNumero(numero).size() > 0);

        return numero;
    }

    public String validarApolice(Apolice apolice) {
        StringBuilder msg = new StringBuilder();

        if (apolice.getNumero() == null) {
            msg.append("Erro ao gerar número da apólice.");
        }

        if (apolice.getPlaca() == null || apolice.getPlaca().isEmpty()) {
            msg.append("Informe a placa do veículo segurado.");
        }

        if (apolice.getValorSeguro() == null) {
            msg.append("Informe o valor do seguro (valorSeguro).");
        }

        String mensagemVigencia = validarVigencias(apolice.getInicioVigencia(), apolice.getFinalVigencia());

        if (mensagemVigencia != null) {
            msg.append(mensagemVigencia);
        }

        return msg.toString();
    }

    private String validarVigencias(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            return "Informe o início e o final da vigência.";
        }

        if (inicio.compareTo(fim) >= 0) {
            return "A data do início da vigência deve ser maior que o final da vigência.";
        }

        return null;
    }

    private String verificarStatus(Apolice apolice) {
        String status = "";

        if (LocalDate.now().compareTo(apolice.getFinalVigencia()) == 0) {
            status = "VIGENTE (Mas sua apólice vence hoje)";
        }

        if (LocalDate.now().compareTo(apolice.getFinalVigencia()) > 0) {
            long dias = apolice.getFinalVigencia().until(LocalDate.now(), ChronoUnit.DAYS);
            status = "VENCIDA (já venceu à " + dias + " dias)";
        }

        if (LocalDate.now().compareTo(apolice.getFinalVigencia()) < 0) {
            long dias = LocalDate.now().until(apolice.getFinalVigencia(), ChronoUnit.DAYS);
            status = "VIGENTE (irá vencer daqui à " + dias + " dias)";
        }

        return status;
    }

    public List<ApoliceStatusDTO> consultarApolices(Integer numero) {
        List<ApoliceStatusDTO> apolices = new ArrayList<>();

        for (Apolice apolice : apoRepository.findByNumero(numero)) {
            ApoliceStatusDTO ap = new ApoliceStatusDTO();
            ap.setId(apolice.getId());
            ap.setNumero(apolice.getNumero());
            ap.setPlaca(apolice.getPlaca());
            ap.setValorSeguro(String.format("%,.2f", apolice.getValorSeguro()));
            ap.setStatus(verificarStatus(apolice));

            apolices.add(ap);
        }

        return apolices;
    }
}
