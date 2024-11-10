import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Telefonia {
    private int numPrePagos, numPosPagos;
    
    private ArrayList<PrePago> prePagos;
    private ArrayList<PosPago> posPagos;
    
    Telefonia(){
        prePagos = new ArrayList<PrePago>();
        posPagos = new ArrayList<PosPago>();
    }

    public void CadastrarAssinante(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pré Pago ou Pós Pago? (1 ou 2)");
        int plano = scanner.nextInt();

        System.out.println("Nome: ");
        String nome = scanner.next();
        System.out.println("CPF(Somente numeros): ");
        long cpf = scanner.nextLong();
        System.out.println("Numero: ");
        int numero = scanner.nextInt();

        if (plano == 1){
            prePagos.add(new PrePago(cpf, nome, numero));
            numPrePagos += 1;
        }

        if (plano == 2){
            posPagos.add(new PosPago(cpf, nome, numero, 34.99f));
            numPosPagos += 1;
        }
        
        if (plano < 1 || plano > 2){
            System.out.println("Selecione uma opção valida!");
        }
    }

    public void ListarAssinante(){
        System.out.println("###Assinantes Pré Pagos###");
        for (PrePago prePago : prePagos) {
            System.out.println("--------------------------");
            System.out.println(prePago.toString());
        }
        System.out.println("--------------------------");

        System.out.println("###Assinantes Pós Pagos###");
        for (PosPago posPago : posPagos) {
            System.out.println("--------------------------");
            System.out.println(posPago.toString());
        }
        System.out.println("--------------------------");
    }

    public void FazerChamada(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pré Pago ou Pós Pago? (1 ou 2)");
        int plano = scanner.nextInt();

        System.out.println("CPF(Somente numeros): ");
        long cpf = scanner.nextLong();

        String data = scanner.next();
        Date dataFormatada = new Date();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
            dataFormatada = formato.parse(data);
        } catch (Exception e) {
            System.out.println("Erro ao formatar a data: " + e.getMessage());
        }

        System.out.println("Duração: ");
        int duracao = scanner.nextInt();

        if (plano == 1){

            PrePago assinante = LocalizarPrePago(cpf);

            if(assinante != null){
                assinante.FazerChamada(dataFormatada, duracao);
            } else {
                System.out.println("CPF não encontrado em nossa base de dados!");
            }
        }

        if (plano == 2){
            PosPago assinante = LocalizarPosPago(cpf);

            if(assinante != null){
                assinante.FazerChamada(new Date(), duracao);
            } else {
                System.out.println("CPF não encontrado em nossa base de dados!");
            }
        }
        
        if (plano < 1 || plano > 2){
            System.out.println("Selecione uma opção valida!");
        }
    }

    public void FazerRecarga(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("CPF(Somente numeros): ");
        long cpf = scanner.nextLong();

        System.out.println("Data(ddmmaaaa): ");
        String data = scanner.next();
        Date dataFormatada = new Date();
        try {
            SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
            dataFormatada = formato.parse(data);
        } catch (Exception e) {
            System.out.println("Erro ao formatar a data: " + e.getMessage());
        }

        System.out.println("Valor: ");
        float valor = scanner.nextFloat();

        PrePago assinante = LocalizarPrePago(cpf);

        if(assinante != null){
            assinante.Recarregar(dataFormatada, valor);
        } else {
            System.out.println("CPF não encontrado em nossa base de dados!");
        }
    }

    private PrePago LocalizarPrePago(long cpf){
        for (PrePago prePago : prePagos) {
            if(prePago.getCpf() == cpf)
                return prePago;
        }
        return null;
    }

    private PosPago LocalizarPosPago(long cpf){
        for (PosPago posPago : posPagos) {
            if(posPago.getCpf() == cpf)
                return posPago;
        }
        return null;
    }

    public void ImprimirFaturas(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Deseja imprimir a fatura de qual mês?(digite o numero do mes)");
        int mes = scanner.nextInt();
        mes -= 1;

        System.out.println("###Assinantes Pré Pagos###");
        for (PrePago prePago : prePagos) {
            System.out.println("--------------------------");
            prePago.ImprimirFatura(mes);
        }
        System.out.println("--------------------------");

        System.out.println("###Assinantes Pós Pagos###");
        for (PosPago posPago : posPagos) {
            System.out.println("--------------------------");
            posPago.ImprimirFatura(mes);
        }
        System.out.println("--------------------------");
    }

    public void main(){
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while(loop){
            System.out.println("\nBem vindo a Telefonia!");
            System.out.println("Digite o numero da função que deseja realizar: ");
            System.out.println("Cadastrar assinante(1)");
            System.out.println("Listar assinantes(2)");
            System.out.println("Fazer chamadas(3)");
            System.out.println("Fazer recargas(4)");
            System.out.println("Imprimir faturas(5)");
            System.out.println("Sair do programa(qualquer outra tecla)");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1: 
                    CadastrarAssinante();                    
                    break;
                case 2: 
                    ListarAssinante();                    
                    break;
                case 3: 
                    FazerChamada();                    
                    break;
                case 4: 
                    FazerRecarga();                    
                    break;
                case 5: 
                    ImprimirFaturas();                  
                    break;         
                default:
                    loop = false;
            }
        }
    }
}
