public abstract class Ingresso {
    protected String tipo;
    protected Cliente cliente;
    protected Sessao sessao;            // Atributos protegidos da classe para armazenar informações do ingresso
    protected int fila;
    protected int poltrona;
    protected double valorCombo;

    public Ingresso(String tipo, Cliente cliente, Sessao sessao, int fila, int poltrona) {  // Construtor da classe
        this.tipo = tipo;
        this.cliente = cliente;
        this.sessao = sessao;               // Inicialização dos atributos com os valores fornecidos
        this.fila = fila;                   
        this.poltrona = poltrona;
        this.valorCombo = 0.0; // Inicializado com zero
    }

    public abstract double calcularPreco();  // Método abstrato para calcular o preço do ingresso

    public void adicionarCombo(double valorCombo) {  // Método para adicionar o valor do combo ao ingresso
        this.valorCombo = valorCombo;
    }

    public double getValorCombo() {  // Método para obter o valor do combo
        return valorCombo;
    }

    public double calcularValorTotal() {  // Método para calcular o valor total do ingresso mais o combo
        return calcularPreco() + valorCombo;
    }

    @Override // Sobrescrita do método toString para representação textual do objeto
    public String toString() {
        String tipoIngresso = (tipo.equals("1")) ? "Inteira" : "Meia"; // Determinando se o ingresso é inteira ou meia

        String relatorio = "Ingresso: " + tipoIngresso +
                "\n" + cliente +
                "\nFilme: " + sessao.getFilme().getNome() +      // Construção do relatório com informações do ingresso
                "\nSessão: " + sessao.getHorario() +
                "\nPoltrona Escolhida: " + (char) (fila + 'A') + (poltrona + 1) +
                "\nPreço do Ingresso: R$" + calcularPreco();

        if (valorCombo > 0) {    // Verificação se há combo e adição das informações do combo ao relatório
            String comboDescricao = "";
            if (valorCombo == 42.0) {
                comboDescricao = "Pipoca grande + Refrigerante 1L + Doce";
            } else if (valorCombo == 25.0) {
                comboDescricao = "Pipoca Média + Refrigerante 500ml";
            } else if (valorCombo == 16.0) {
                comboDescricao = "Refrigerante 300ml + Doce";
            }
            relatorio += "\nCombo: " + comboDescricao + " R$ " + valorCombo;
        }

        relatorio += "\nValor Total: R$" + calcularValorTotal();   // Adição do valor total ao relatório

        return relatorio;   // Retorno do relatório final
    }
}

class IngressoInteira extends Ingresso {  // Subclasse que representa um Ingresso Inteira
    public IngressoInteira(Cliente cliente, Sessao sessao, int fila, int poltrona) {  // Construtor da classe
        super("1", cliente, sessao, fila, poltrona); // Chama o construtor da superclasse com tipo 1 inteira
    }

    @Override // Implementação do método abstrato para calcular o preço do ingresso inteira
    public double calcularPreco() {
        return sessao.getPrecoInteira();
    }
}

class IngressoMeia extends Ingresso {  // Subclasse que representa um Ingresso Meia
    public IngressoMeia(Cliente cliente, Sessao sessao, int fila, int poltrona) {  // Construtor da classe
        super("2", cliente, sessao, fila, poltrona); // Chama o construtor da superclasse com o tipo "2" (meia)
    }

    @Override
    public double calcularPreco() {  // Implementação do método abstrato para calcular o preço do ingresso meia
        return sessao.getPrecoMeia();
    }
}
