package tdd.caixa.mock;

import tdd.caixa.interfaces.Hardware;

public class MockHardware implements Hardware {

    private String numeroCartao;
    private boolean falhaRecuperaNumeroCartao = false;
    private boolean falhaEntregaDinheiro = false;
    private boolean falhaLerEnvelope = false;

    @Override
    public String pegarNumeroDaContaCartao() {
        if (falhaRecuperaNumeroCartao)
            throw new RuntimeException("Problema ao recuperar número do cartão");

        return numeroCartao;
    }

    @Override
    public void entregarDinheiro() {
        if (falhaEntregaDinheiro)
            throw new RuntimeException("Problema ao entregar o dinheiro");
    }

    @Override
    public void lerEnvelope() {
        if (falhaLerEnvelope)
            throw new RuntimeException("Problema ao entregar o dinheiro");
    }

    public void ativaFalhaRecuperaNumeroCartao() {
        this.falhaRecuperaNumeroCartao = true;
    }

    public void ativaFalhaEntregarDinheiro() {
        this.falhaEntregaDinheiro = true;
    }

    public void ativaFalhaLerEnvelope() {
        this.falhaLerEnvelope = true;
    }

    public void definirNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
