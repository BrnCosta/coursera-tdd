package tdd.caixa.classes;

import tdd.caixa.interfaces.Hardware;
import tdd.caixa.interfaces.ServicoRemoto;

public class CaixaEletronico {

    private ServicoRemoto servicoRemoto;
    private Hardware hardware;
    private ContaCorrente conta;

    public String logar() {

        String numeroConta;
        try {
            numeroConta = hardware.pegarNumeroDaContaCartao();
            this.conta = servicoRemoto.recuperarConta(numeroConta);

            return "Usuário Autenticado";
        } catch (Exception e) {
            return "Não foi possível autenticar o usuário";
        }
    }

    public String saldo() {
        return String.format("O saldo é R$%.2f", this.conta.getSaldo());
    }

    public String sacar(double valor) {
        if (valor > this.conta.getSaldo())
            return "Saldo insuficiente";

        try {
            this.hardware.entregarDinheiro();
            this.conta = this.servicoRemoto.persistirConta(this.conta.getNumeroConta(), this.conta.getSaldo() - valor);
            return "Retire seu dinheiro";
        } catch (Exception e) {
            return "Falha ao sacar";
        }
    }

    public String depositar(double valor) {
        try {
            this.hardware.lerEnvelope();
            this.conta = this.servicoRemoto.persistirConta(this.conta.getNumeroConta(), this.conta.getSaldo() + valor);
            return "Depósito recebido com sucesso";
        } catch (Exception e) {
            return "Falha ao depositar";
        }
    }

    public void adicionaServicoRemoto(ServicoRemoto servicoRemoto) {
        this.servicoRemoto = servicoRemoto;
    }

    public void adicionaHardware(Hardware hardware) {
        this.hardware = hardware;
    }
}
