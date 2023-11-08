package tdd.caixa.interfaces;

import tdd.caixa.classes.ContaCorrente;

public interface ServicoRemoto {

    public ContaCorrente recuperarConta(String numeroConta);

    public ContaCorrente persistirConta(String numeroConta, double novoSaldo);
}
