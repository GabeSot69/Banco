import java.util.InputMismatchException;
import java.util.Scanner;

class Agencia{
    private int numero;
    private String nome;

    Agencia(int numero, String nome){
        this.numero = numero;
        this.nome = nome;
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
    private Agencia[] agencias = new Agencia[10];

    public Banco(String nome) {
        this.nome = nome;
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
            if (agencias[i].getNumero() == numero){
                return agencias[i];
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
            System.out.print("Insira um valor: ");
            try {
                num = s.nextInt();
            }
            catch (InputMismatchException e){
                s.nextLine();
                System.out.println("Insira um valor válido");
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
                        System.out.println("Capacidade máxima alcançada");
                    }
                    break;
                case 2:
                    if (Icomp.contarAgencias() == 0){
                        System.out.println("Não é possível remover, lista vazia");
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