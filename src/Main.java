import java.util.InputMismatchException;
import java.util.Scanner;

class Conta{
    private int numero;
    private String nome;
    private double saldo;

    Conta(int numero, String nome){
        this.numero = numero;
        this.nome = nome;
        this.saldo = 0;
    }

    public void depositar(double valor){
        this.saldo += valor;
    }

    public void sacar(double valor) throws IllegalArgumentException{
        if (valor <= 0 || valor > this.saldo){
            throw new IllegalArgumentException("Valor invalido, operacao cancelada");
        }
        this.saldo -= valor;
    }

    public void transferir(Conta contaDestino, double valor) throws IllegalArgumentException{
        if (valor <= 0 || valor > this.saldo){
            throw new IllegalArgumentException("Valor invalido, operacao cancelada");
        }
        this.saldo -= valor;
        contaDestino.saldo += valor;
    }



    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }
}
class Agencia{
    private int numero;
    private String nome;
    private Conta[] contas;

    Agencia(int numero, String nome){
        this.numero = numero;
        this.nome = nome;
        this.contas = new Conta[10];
    }

    public void criarConta(int numero, String nome){
        Conta conta = new Conta(numero,nome);
        for (int i = 0; i < contas.length; i++){
            if (contas[i] == null){
                contas[i] = conta;
                break;
            }
        }

    }

    public void removerConta(int numero){
        boolean foiEncontrado = false;
        for (int i = 0; i < contas.length; i++){
            if (contas[i] != null) {
                if (contas[i].getNumero() == numero) {
                    int j;
                    foiEncontrado = true;
                    for (j = i; j < contas.length - 1; j++) {
                        contas[j] = contas[j + 1];
                    }
                    contas[j] = null;
                }
            }
            else {
                break;
            }
        }
        if (foiEncontrado == false){
            System.out.println("Numero nao encontrado");
        }
    }

    public void listarContas(){
        System.out.println("----CONTAS----");
        System.out.println();
        if (contas[0] != null) {
            for (int i = 0;i < contas.length; i++) {
                if (contas[i] != null) {
                    System.out.println((i + 1) + ". " + contas[i].getNome() + " - " + contas[i].getNumero());
                    System.out.println();
                }
            }
        }
    }

    public Conta selecionarConta(int numero){
        for (int i = 0; i < contas.length; i++){
            if (contas[i] != null) {
                if (contas[i].getNumero() == numero) {
                    return contas[i];
                }
            }
        }
        return null;
    }

    public int contarContas(){
        int i;
        for (i = 0; i < contas.length; i++){
            if (contas[i] == null){
                return i;
            }
        }
        return i+1;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

}

class Banco {
    private String nome;
    private Agencia[] agencias;

    public Banco(String nome) {
        this.nome = nome;
        this.agencias = new Agencia[10];
    }

    public void criarAgencia(int numero, String nome){
        int i;
        Agencia agencia = new Agencia(numero,nome);
        for (i = 0; i < agencias.length; i++){
            if (agencias[i] == null){
                agencias[i] = agencia;
                break;
            }
        }


    }

    public void removerAgencia(int numero){
        boolean foiEncontrado = false;
        for (int i = 0; i < agencias.length; i++){
            if (agencias[i] != null) {
                if (agencias[i].getNumero() == numero) {
                    int j;
                    foiEncontrado = true;
                    for (j = i; j < agencias.length - 1; j++) {
                        agencias[j] = agencias[j + 1];
                    }
                    agencias[j] = null;
                }
            }
            else {
                break;
            }
        }
        if (foiEncontrado == false){
            System.out.println("Numero nao encontrado");
        }

    }

    public void listarAgencias(){
        System.out.println("----AGENCIAS----");
        System.out.println();
        if (agencias[0] != null) {
            for (int i = 0;i < agencias.length; i++) {
                if (agencias[i] != null) {
                    System.out.println((i + 1) + ". " + agencias[i].getNome() + " - " + agencias[i].getNumero());
                    System.out.println();
                }
            }
        }
    }

    public Agencia selecionarAgencia(int numero){
        for (int i = 0; i < agencias.length; i++){
            if (agencias[i] != null) {
                if (agencias[i].getNumero() == numero) {
                    return agencias[i];
                }
            }
        }
        return null;
    }

    public int contarAgencias(){
        int i;
        for (i = 0; i < agencias.length; i++){
            if (agencias[i] == null){
                return i;
            }
        }
        return i+1;
    }

    public String getNome() {
        return nome;
    }

    public Agencia[] getAgencias() {
        return agencias;
    }
}

public class Main{
    public static void main(String[] args) {
        Banco Icomp = new Banco("Icomp");
        Scanner s = new Scanner(System.in);
        int num = 0;
        System.out.println("Bem vindo ao sistema de gerenciamento do Banco " + Icomp.getNome());
        System.out.println();
        while (num != 5){

            System.out.println("----MENU PRINCIPAL----");
            System.out.println();
            System.out.println("1. Criar agencia");
            System.out.println("2. Remover agencia");
            System.out.println("3. Listar agencias");
            System.out.println("4. Selecionar agencia");
            System.out.println("5. Sair");

            try {
                num = s.nextInt();
            }
            catch (InputMismatchException e){
                s.nextLine();
                System.out.println("Insira um valor v??lido");
                System.out.println();
                continue;
            }
            switch (num){
                case 1:
                    if (Icomp.contarAgencias() <= 10) {
                        System.out.println("Digite o nome da agencia");
                        s.nextLine();
                        String nome = s.nextLine();
                        System.out.println("Digite o numero da agencia");
                        int numero = s.nextInt();
                        Icomp.criarAgencia(numero,nome);
                        System.out.println("Operacao Concluida");
                    }
                    else {
                        System.out.println("Capacidade m??xima alcan??ada");
                    }
                    break;
                case 2:
                    if (Icomp.contarAgencias() == 0){
                        System.out.println("N??o ?? poss??vel remover, lista vazia");
                    }
                    else {
                        System.out.println("Digite o numero da agencia");
                        int numero = s.nextInt();
                        Icomp.removerAgencia(numero);
                        System.out.println("Operacao Concluida");
                        s.nextLine();
                    }
                    break;
                case 3:
                    Icomp.listarAgencias();
                    break;
                case 4:
                    System.out.println("Digite o numero da agencia");
                    int numeroAgencia = s.nextInt();
                    Agencia agenciaSelecionada = Icomp.selecionarAgencia(numeroAgencia);
                    if (agenciaSelecionada == null){
                        System.out.println("Numero nao encontrado, operacao cancelada");
                    }
                    else {
                        int escolhaConta = 0;
                        while (escolhaConta != 5) {
                            System.out.println("----Menu da Agencia - " + agenciaSelecionada.getNome() + "----");
                            System.out.println();
                            System.out.println("1. Criar conta");
                            System.out.println("2. Remover conta");
                            System.out.println("3. Listar contas");
                            System.out.println("4. Selecionar conta");
                            System.out.println("5. Voltar");
                            try {
                                escolhaConta = s.nextInt();
                            }
                            catch (InputMismatchException e){
                                s.nextLine();
                                System.out.println("Insira um valor v??lido");
                                System.out.println();
                                continue;
                            }
                            switch (escolhaConta){
                                case 1:
                                    if (agenciaSelecionada.contarContas() <= 10) {
                                        System.out.println("Digite o nome do titular");
                                        s.nextLine();
                                        String nomeTitular = s.nextLine();
                                        System.out.println("Digite o numero da conta");
                                        int numeroConta = s.nextInt();
                                        agenciaSelecionada.criarConta(numeroConta,nomeTitular);
                                        System.out.println("Operacao Concluida");
                                    }
                                    else {
                                        System.out.println("Capacidade m??xima alcan??ada");
                                    }
                                    break;
                                case 2:
                                    if (agenciaSelecionada.contarContas()== 0){
                                        System.out.println("N??o ?? poss??vel remover, lista vazia");
                                    }
                                    else {
                                        System.out.println("Digite o numero da conta");
                                        int numeroConta = s.nextInt();
                                        agenciaSelecionada.removerConta(numeroConta);
                                        System.out.println("Operacao Concluida");
                                        s.nextLine();
                                    }
                                    break;
                                case 3:
                                    agenciaSelecionada.listarContas();
                                    break;
                                case 4:
                                    System.out.println("Digite o numero da conta");
                                    int numeroConta = s.nextInt();
                                    Conta contaSelecionada = agenciaSelecionada.selecionarConta(numeroConta);
                                    if (contaSelecionada == null){
                                        System.out.println("Numero nao encontrado, opcao cancelada");
                                    }
                                    else {
                                        int escolhaOperacao = 0;
                                        while (escolhaOperacao != 5) {
                                            System.out.println("----Menu da Conta - " + contaSelecionada.getNome() + "----");
                                            System.out.println();
                                            System.out.println("1. Depositar");
                                            System.out.println("2. Sacar");
                                            System.out.println("3. Transferir");
                                            System.out.println("4. Ver saldo");
                                            System.out.println("5. Voltar");
                                            try {
                                                escolhaOperacao = s.nextInt();
                                            }
                                            catch (InputMismatchException e){
                                                s.nextLine();
                                                System.out.println("Insira um valor valido");
                                                System.out.println();
                                                continue;
                                            }
                                            switch (escolhaOperacao){
                                                case 1:
                                                    System.out.println("Insira o valor que deseja depositar");
                                                    double valorDeposito = s.nextDouble();
                                                    contaSelecionada.depositar(valorDeposito);
                                                    System.out.println("Operacao Concluida");
                                                    break;
                                                case 2:
                                                    System.out.println("Insira o valor que deseja sacar");
                                                    double valorSaque = s.nextDouble();
                                                    try {
                                                        contaSelecionada.sacar(valorSaque);
                                                        System.out.println("Operacao concluida");
                                                    }
                                                    catch (IllegalArgumentException e){
                                                        System.out.println(e.getMessage());
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("Insira o numero da conta que deseja transferir");
                                                    int numeroContaDestino = s.nextInt();
                                                    Conta contaDestino = agenciaSelecionada.selecionarConta(numeroContaDestino);
                                                    if (contaDestino == null){
                                                        System.out.println("Numero nao encontrado, operacao cancelada");
                                                    }
                                                    else {
                                                        System.out.println("Insira o valor que deseja transferir");
                                                        double valorTransferencia = s.nextDouble();
                                                        try {
                                                            contaSelecionada.transferir(contaDestino,valorTransferencia);
                                                            System.out.println("Operacao concluida");
                                                        }
                                                        catch (IllegalArgumentException e){
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }
                                                    break;
                                                case 4:
                                                    System.out.print("Nome - " + contaSelecionada.getNome() + " | Numero da conta - " + contaSelecionada.getNumero() + " | Saldo - ");
                                                    System.out.printf("%.2f\n", contaSelecionada.getSaldo());
                                                    break;
                                                case 5:
                                                    break;
                                                default:
                                                    System.out.println("Opcao nao encontrada");
                                                    break;
                                            }
                                            System.out.println();
                                        }
                                    }
                                    break;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Opcao nao encontrada");
                                    break;
                            }
                            System.out.println();

                        }
                    }
                    break;
                case 5:
                    System.out.println("Volte Sempre :)");
                    break;
                default:
                    System.out.println("Opcao nao encontrada");
                    break;
            }
            System.out.println();
        }
    }


}