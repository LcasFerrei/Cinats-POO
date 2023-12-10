import java.util.InputMismatchException; //situações em que o usuário insere um valor não numérico quando um número é esperado.
import java.util.Scanner; //entradas do usuário

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Relatorio relatorio = new Relatorio();
        Cliente cliente = null;                         // Inicialização de objetos e variáveis necessários
        GaleriaFilmes galeria = new GaleriaFilmes();
        Sessao sessao = null;
        Filme[] filmes = null;
        Ingresso ingresso = null;

        while (true) {      // Loop principal do programa
            System.out.println("Cinats: O destino dos amantes do cinema");
            System.out.println("Menu Inicial:");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Escolher um Filme");
            System.out.println("3 - Sair");

            int opcaoInicial = getIntInput(scanner);      // Obtém a opção inicial do usuário

            if (opcaoInicial == 1) {                    // Verifica a escolha do usuário
                scanner.nextLine(); // Limpa o buffer
                System.out.println("Cadastro:");
                System.out.print("Digite seu nome: ");
                String nomeCliente = scanner.nextLine();
                System.out.print("Digite seu email: ");
                String emailCliente = getEmailInput(scanner);
                System.out.print("Digite seu telefone: ");
                String telefoneCliente = getNumericInput(scanner);
                cliente = new Cliente(nomeCliente, emailCliente, telefoneCliente);
                relatorio.adicionarCliente(cliente);
                System.out.println("Cliente cadastrado com sucesso!");
            } else if (opcaoInicial == 2) {   // Escolha de filme
                if (cliente == null) {
                    System.out.println("Você precisa cadastrar um cliente primeiro.");
                    continue;
                }

                while (true) {
                    System.out.println("\nMenu de Filmes:");
                    System.out.println("1 - Incluir 4 filmes no Catálogo");
                    System.out.println("2 - Escolher um Filme");
                    System.out.println("3 - Sair");

                    int opcaoFilmes = getIntInput(scanner);  // Obtém a opção de filmes do usuário

                    if (opcaoFilmes == 1) {
                        int quantidadeFilmes = 4;   // Inclusão de filmes no catálogo
                        filmes = new Filme[quantidadeFilmes];
                        scanner.nextLine();  // Limpa o buffer

                        for (int i = 0; i < quantidadeFilmes; i++) {
                            System.out.print("Digite o nome do Filme " + (i + 1) + ": ");
                            String nomeFilme = scanner.nextLine();
                            filmes[i] = new Filme(nomeFilme);
                        }
                        galeria.incluirFilmes(filmes);
                        System.out.println("Catálogo de filmes incluído com sucesso!");
                    } else if (opcaoFilmes == 2) {
                        if (filmes == null) {  // Escolha de filme para sessão
                            System.out.println("Você precisa incluir o Catálogo de filmes primeiro.");
                            continue;
                        }

                        System.out.println("\nCatálogo de Filmes:");
                        for (int i = 0; i < filmes.length; i++) {
                            System.out.println((i + 1) + " - " + filmes[i].getNome());
                        }
                        System.out.print("Escolha um filme (1-" + filmes.length + "): ");

                        int escolhaFilme = getIntInput(scanner);
                        if (escolhaFilme < 1 || escolhaFilme > filmes.length) {
                            System.out.println("Escolha de filme inválida.");
                            continue;
                        }

                        // Escolha de sessão
                        System.out.println("\nEscolha a Sessão:");
                        System.out.println("1 - 14h");
                        System.out.println("2 - 15h");
                        System.out.println("3 - 17h");
                        System.out.println("4 - 20h");
                        System.out.print("Escolha a sessão (1-4): ");

                        int escolhaSessao = getIntInput(scanner);
                        if (escolhaSessao < 1 || escolhaSessao > 4) {
                            System.out.println("Escolha de sessão inválida.");
                            continue;
                        }
                        // Definição do horário da sessão
                        String horarioSessao = "";
                        if (escolhaSessao == 1) {
                            horarioSessao = "14h";
                        } else if (escolhaSessao == 2) {
                            horarioSessao = "15h";
                        } else if (escolhaSessao == 3) {
                            horarioSessao = "17h";
                        } else {
                            horarioSessao = "20h";
                        }
                        // Criação da sessão com o filme escolhido e o horário
                        if (sessao != null) sessao = new Sessao(filmes[escolhaFilme - 1], horarioSessao);
                        else sessao = new Sessao(filmes[escolhaFilme - 1], horarioSessao);

                        sessao.mostrarLayoutPoltronas(); // Mostra o layout das poltronas

                        // Escolha da fila
                        System.out.print("Escolha a fila (A-F): ");
                        char fila;

                        while (true) { // Tratamento de exceção para entrada inválida da fila
                            try {
                                fila = scanner.next().toUpperCase().charAt(0);
                                if (fila < 'A' || fila > 'F') throw new InputMismatchException();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Fila inválida. Por favor, insira uma letra de A a F. Tente novamente:");
                                scanner.nextLine(); // Limpa o buffer
                            }
                        }

                        // Escolha do número de poltrona
                        int numeroPoltrona;
                        do {
                            System.out.print("Escolha a poltrona (1-10): ");
                            numeroPoltrona = getIntInput(scanner);
                            if (numeroPoltrona < 1 || numeroPoltrona > 10) {
                                System.out.println("Número de poltrona inválido. Por favor, insira um número de 1 a 10. Tente novamente:");
                            }
                        } while (numeroPoltrona < 1 || numeroPoltrona > 10);

                        // Escolha do tipo de ingresso
                        System.out.println("\nEscolha o tipo de ingresso:");
                        System.out.println("1 - Inteira - R$ 10,00");
                        System.out.println("2 - Meia - R$ 5,00");
                        System.out.print("Escolha o tipo de ingresso (1-2): ");

                        // Adicionando tratamento de exceção para entrada inválida do usuário
                        int escolhaTipoIngresso = getIntInput(scanner);
                        if (escolhaTipoIngresso < 1 || escolhaTipoIngresso > 2) {
                            System.out.println("Escolha de ingresso inválida.");
                            continue;
                        }
                        // Criação do novo ingresso com base na escolha do usuário
                        Ingresso novoIngresso;
                        if (escolhaTipoIngresso == 1) {
                            novoIngresso = new IngressoInteira(cliente, sessao, fila - 'A', numeroPoltrona - 1);
                        } else {
                            novoIngresso = new IngressoMeia(cliente, sessao, fila - 'A', numeroPoltrona - 1);
                        }
                        // Reserva da poltrona e adição de combo
                        if (sessao.reservarPoltrona(fila - 'A', numeroPoltrona - 1)) {
                            ingresso = novoIngresso;
                            System.out.println("Poltrona reservada com sucesso!");

                            System.out.println("\nEscolher Combo:"); // Escolha de combo
                            System.out.println("1 - Combo");
                            System.out.println("2 - Sair");

                            int escolhaCombo = getIntInput(scanner);

                            if (escolhaCombo == 1) {     // Adição do combo escolhido ao ingresso
                                System.out.println("\nCombo:");
                                System.out.println("1 - Pipoca grande + Refrigerante 1L + Doce R$ 42,00");
                                System.out.println("2 - Pipoca Média + Refrigerante 500ml R$ 25,00");
                                System.out.println("3 - Refrigerante 300ml + Doce R$ 16,00");
                                System.out.print("Escolha o combo (1-3): ");

                                int escolhaComboOpcao = getIntInput(scanner);

                                double valorCombo = 0.0;
                                if (escolhaComboOpcao == 1) {
                                    valorCombo = 42.0;
                                } else if (escolhaComboOpcao == 2) {
                                    valorCombo = 25.0;
                                } else if (escolhaComboOpcao == 3) {
                                    valorCombo = 16.0;
                                }
                                ingresso.adicionarCombo(valorCombo);
                            }

                            relatorio.registrarVenda(ingresso); // Registro da venda e exibição do relatório

                            System.out.println("\nRelatório:\n");
                            System.out.println(ingresso);
                        } else {
                            System.out.println("Poltrona já está ocupada.");
                        }
                    } else if (opcaoFilmes == 3) { // Encerra o programa
                        break;
                    }
                }
            } else if (opcaoInicial == 3) {
                break;
            }
        }

        System.out.println("\nRelatório Final:\n");   // Exibição do relatório final
        relatorio.exibirRelatorioFinal();

        scanner.close();
    }

    private static int getIntInput(Scanner scanner) { // Método para obter um input inteiro do usuário com tratamento de exceção
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
    }

    private static String getNumericInput(Scanner scanner) { // Método para obter um input numérico do usuário com validação
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                return input;
            } else {
                System.out.println("Por favor, insira apenas números. Tente novamente:");
            }
        }
    }

    private static String getEmailInput(Scanner scanner) { // Método para obter um input de e-mail do usuário com validação
        while (true) {
            String input = scanner.nextLine();
            if (input.contains("@")) {
                return input;
            } else {
                System.out.println("Por favor, insira um e-mail válido. Tente novamente:");
            }
        }
    }
}
