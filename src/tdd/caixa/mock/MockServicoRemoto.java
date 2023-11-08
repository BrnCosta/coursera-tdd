package tdd.caixa.mock;

import tdd.caixa.classes.ContaCorrente;
import tdd.caixa.interfaces.ServicoRemoto;

public class MockServicoRemoto implements ServicoRemoto {

    private double saldo = 0;

    @Override
    public ContaCorrente recuperarConta(String numeroConta) {
        return new ContaCorrente(numeroConta, this.saldo);
    }

    @Override
    public ContaCorrente persistirConta(String numeroConta, double novoSaldo) {
        return new ContaCorrente(numeroConta, novoSaldo);
    }

    public void definirSaldo(double saldo) {
        this.saldo = saldo;
    }
}
