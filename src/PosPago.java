import java.util.Date;

public class PosPago extends Assinante{
    private float assinatura;

    PosPago(long cpf, String nome, int numero, float assinatura){
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }

    public void FazerChamada(Date data, int duracao){
        chamadas.add(new Chamada(data, duracao));
        numChamadas += 1;
    }

    public void ImprimirFatura(int mes){
        toString();
        System.out.println("###Chamadas###");
        for (Chamada chamada : chamadas) {
            if(chamada.getData().getMonth() == mes){
                System.out.println(chamada.toString());
            }            
        }

        System.out.println("###Total###");
        float valorChamadas = 0;
        for (Chamada chamada : chamadas) {
            if(chamada.getData().getMonth() == mes){
                valorChamadas += 1.04;
            }            
        }
        System.out.println("Fatura: R$ " + valorChamadas + assinatura);
    }
}
