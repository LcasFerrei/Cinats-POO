import java.util.ArrayList;
import java.util.List;      // Importações para utilizar as classes ArrayList

public class Relatorio {
    private List<Cliente> clientes;
    private List<Filme> filmes;          // Listas para armazenar clientes, filmes, sessões e ingressos vendidos
    private List<Sessao> sessoes;
    private List<Ingresso> vendas;

    public Relatorio() {
        this.clientes = new ArrayList<>();
        this.filmes = new ArrayList<>();      // Construtor da classe iniciando as listas
        this.sessoes = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarFilme(Filme filme) {
        filmes.add(filme);                            // Métodos para adicionar clientes, filmes, sessões e registrar vendas
    }

    public void adicionarSessao(Sessao sessao) {
        sessoes.add(sessao);
    }

    public void registrarVenda(Ingresso ingresso) {
        vendas.add(ingresso);
    }

    public void exibirRelatorioFinal() {  
        // Exibir relatórios finais com base nos dados armazenados
        System.out.println("Quantidade de Ingressos Vendidos: " + vendas.size());
        System.out.println("Detalhes das Vendas:");

        int ingressosInteira = 0;
        int ingressosMeia = 0;          // Variáveis para contagem de ingressos e combos vendidos
        int quantidadeCombos = 0;

        for (Ingresso ingresso : vendas) {  // Loop para exibir detalhes de cada venda e contar os tipos de ingressos
            System.out.println(ingresso);
            System.out.println("--------------");

            if (ingresso instanceof IngressoInteira) {
                ingressosInteira++;
            } else if (ingresso instanceof IngressoMeia) {      // Contagem de ingressos Inteira e Meia
                ingressosMeia++;
            }

            if (ingresso.getValorCombo() > 0) {         // Contagem de combos vendidos
                quantidadeCombos++;
            }
        }
        // Exibe a quantidade de ingressos Inteira, Meia, combos vendidos e o lucro total
        System.out.println("Ingressos Inteira Vendidos: " + ingressosInteira);
        System.out.println("Ingressos Meia Vendidos: " + ingressosMeia);
        System.out.println("Quantidade de Combos Vendidos: " + quantidadeCombos);
        System.out.println("Lucro Total: R$" + calcularLucroTotal());
    }

    private double calcularLucroTotal() {  // Método para calcular o lucro total com base nos ingressos vendidos
        double lucroTotal = 0.0;

        for (Ingresso ingresso : vendas) {
            lucroTotal += ingresso.calcularValorTotal();
        }

        return lucroTotal;
    }
}
