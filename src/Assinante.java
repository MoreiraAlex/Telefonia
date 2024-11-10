import java.util.ArrayList;

public class Assinante {
    private long cpf;
    private String nome;
    private int numero;

    protected ArrayList<Chamada> chamadas;

    protected int numChamadas;

    Assinante(long cpf, String nome, int numero){
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        chamadas = new ArrayList<Chamada>();
    }

    public long getCpf(){
        return this.cpf;
    }

    public String toString(){
        return "Nome: " + this.nome + "\nCPF: " + this.cpf + "\nNumero: " + this.numero;
    }
}
