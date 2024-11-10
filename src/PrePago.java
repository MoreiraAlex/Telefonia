import java.util.ArrayList;
import java.util.Date;

public class PrePago extends Assinante {
    private float creditos;
    private int numRecargas;

    protected ArrayList<Recarga> recargas;


    PrePago(long cpf, String nome, int numero){
        super(cpf, nome, numero);
        recargas = new ArrayList<Recarga>();
    }

    public void Recarregar(Date data, float valor){
        recargas.add(new Recarga(data, valor));
        numRecargas += 1;
        creditos += valor;
    }

    public void FazerChamada(Date data, int duracao){
        if (creditos >= 1.45) {
            chamadas.add(new Chamada(data, duracao));
            numChamadas += 1;
            creditos -= 1.45;
        } else {
            System.out.println("Créditos insuficientes");
        }
    }

    public void ImprimirFatura(int mes){
        toString();
        System.out.println("###Chamadas###");
        for (Chamada chamada : chamadas) {
            if(chamada.getData().getMonth() == mes){
                System.out.println(chamada.toString());
            }            
        }

        System.out.println("###Recargas###");
        for (Recarga recarga : recargas) {
            if(recarga.getData().getMonth() == mes){
                System.out.println(recarga.toString());
            }            
        }

        System.out.println("###Total###");

        float valorChamadas = 0;
        for (Chamada chamada : chamadas) {
            if(chamada.getData().getMonth() == mes){
                valorChamadas += 1.45;
            }            
        }

        float valorRecargas = 0;
        for (Recarga recarga : recargas) {
            if(recarga.getData().getMonth() == mes){
                valorRecargas += recarga.getValor();
            }            
        }

        System.out.println("Chamadas: R$ " + valorChamadas + "\nRecargas: R$ " + valorRecargas);
        System.out.println("Créditos restantes: R$ " + creditos);
    }
}
