package tdd.caixa;

import static org.junit.Assert.*;

import org.junit.Test;

import tdd.caixa.classes.CaixaEletronico;
import tdd.caixa.mock.MockHardware;
import tdd.caixa.mock.MockServicoRemoto;

public class TestCaixaEletronico {

    @Test
    public void test_loginConta() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
        caixa.adicionaServicoRemoto(mockServicoRemoto);

        MockHardware mockHardware = new MockHardware();
        mockHardware.definirNumeroCartao("123456");
        caixa.adicionaHardware(mockHardware);

        String login = caixa.logar();
        assertEquals("Usuário Autenticado", login);
    }

    @Test
    public void test_erroAoPegarNumeroCartao() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
        mockServicoRemoto.definirSaldo(150);

        MockHardware mockHardware = new MockHardware();
        mockHardware.ativaFalhaRecuperaNumeroCartao();

        caixa.adicionaServicoRemoto(mockServicoRemoto);
        caixa.adicionaHardware(mockHardware);

        String login = caixa.logar();
        assertEquals("Não foi possível autenticar o usuário", login);
    }

    @Test
    public void test_contaComSaldo() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mock = new MockServicoRemoto();
        mock.definirSaldo(150);

        caixa.adicionaServicoRemoto(mock);

        MockHardware mockHardware = new MockHardware();
        mockHardware.definirNumeroCartao("123456");
        caixa.adicionaHardware(mockHardware);

        caixa.logar();

        String saldo = caixa.saldo();
        assertEquals("O saldo é R$150,00", saldo);
    }

    @Test
    public void test_sacar() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mock = new MockServicoRemoto();
        mock.definirSaldo(150);

        caixa.adicionaServicoRemoto(mock);

        MockHardware mockHardware = new MockHardware();
        mockHardware.definirNumeroCartao("123456");
        caixa.adicionaHardware(mockHardware);

        caixa.logar();

        String saque = caixa.sacar(50);
        String saldo = caixa.saldo();

        assertEquals("Retire seu dinheiro", saque);
        assertEquals("O saldo é R$100,00", saldo);
    }

    @Test
    public void test_sacarFalhaEntregaDinheiro() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mock = new MockServicoRemoto();
        mock.definirSaldo(150);

        caixa.adicionaServicoRemoto(mock);

        MockHardware mockHardware = new MockHardware();
        mockHardware.definirNumeroCartao("123456");
        mockHardware.ativaFalhaEntregarDinheiro();
        caixa.adicionaHardware(mockHardware);

        caixa.logar();

        String saque = caixa.sacar(50);
        String saldo = caixa.saldo();

        assertEquals("Falha ao sacar", saque);
        assertEquals("O saldo é R$150,00", saldo);
    }

    @Test
    public void test_sacarSaldoInsuficiente() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mock = new MockServicoRemoto();
        mock.definirSaldo(50);

        caixa.adicionaServicoRemoto(mock);

        MockHardware mockHardware = new MockHardware();
        mockHardware.definirNumeroCartao("123456");
        caixa.adicionaHardware(mockHardware);

        caixa.logar();

        String saque = caixa.sacar(100);
        String saldo = caixa.saldo();

        assertEquals("Saldo insuficiente", saque);
        assertEquals("O saldo é R$50,00", saldo);
    }

    @Test
    public void test_depositar() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mock = new MockServicoRemoto();
        mock.definirSaldo(150);

        caixa.adicionaServicoRemoto(mock);

        MockHardware mockHardware = new MockHardware();
        mockHardware.definirNumeroCartao("123456");
        caixa.adicionaHardware(mockHardware);

        caixa.logar();

        String saque = caixa.depositar(50);
        String saldo = caixa.saldo();

        assertEquals("Depósito recebido com sucesso", saque);
        assertEquals("O saldo é R$200,00", saldo);
    }

    @Test
    public void test_depositarFalhaAoLerEnvelope() {
        CaixaEletronico caixa = new CaixaEletronico();

        MockServicoRemoto mock = new MockServicoRemoto();
        mock.definirSaldo(150);

        caixa.adicionaServicoRemoto(mock);

        MockHardware mockHardware = new MockHardware();
        mockHardware.definirNumeroCartao("123456");
        mockHardware.ativaFalhaLerEnvelope();
        caixa.adicionaHardware(mockHardware);

        caixa.logar();

        String saque = caixa.depositar(50);
        String saldo = caixa.saldo();

        assertEquals("Falha ao depositar", saque);
        assertEquals("O saldo é R$150,00", saldo);
    }
}
