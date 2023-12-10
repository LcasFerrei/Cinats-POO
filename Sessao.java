import java.io.File;  //para manipulação de arquivos no sistema de arquivos
import java.io.FileNotFoundException;  //representa uma exceção para quando um arquivo não é encontrado
import java.io.PrintWriter; //para escrever dados formatados em um arquivo
import java.util.Scanner; // para ler dados de um arquivo ou da entrada padrão

public class Sessao { 
    private Filme filme;
    private String horario; // Atributos da classe
    private boolean[][] poltronas;  // Estado de reserva das poltronas
    private File stateFile;  //armazenar o estado das poltronas

    public Sessao(Filme filme, String horario) { // Construtor da classe
        this.filme = filme;
        this.horario = horario;  // Inicialização dos atributos
        this.stateFile = new File(filme.getNome() + "_" + horario + "_poltronas_state.txt");  // Criação do arquivo de estado das poltronas com base no nome do filme e horário
        loadPoltronasState();     // Carrega o estado atual das poltronas

    }

    public void loadPoltronasState() { // Método para carregar o estado atual das poltronas a partir do arquivo
        try (Scanner scanner = new Scanner(stateFile)) {
            poltronas = new boolean[6][10];  // Inicialização da matriz de poltronas com as dimensões padrão
            for (int i = 0; i < poltronas.length; i++) { // Loop externo para iterar sobre as filas de poltronas
                for (int j = 0; j < poltronas[0].length; j++) {  // Loop interno para iterar sobre as poltronas em uma fila específica
                    if (scanner.hasNextBoolean()) {  // Leitura do estado das poltronas do arquivo
                        poltronas[i][j] = scanner.nextBoolean();
                    }
                }
            }
        } catch (FileNotFoundException e) { // Caso o arquivo não seja encontrado, imprime uma mensagem e cria um novo estado de poltronas
            System.out.println("Arquivo de estado das poltronas não encontrado. Criando um novo estado.");
            poltronas = new boolean[6][10];
        }
    }

    public void savePoltronasState() { // Método para salvar o estado atual das poltronas no arquivo
        try (PrintWriter writer = new PrintWriter(stateFile)) {
            for (int i = 0; i < poltronas.length; i++) { // Iteração sobre a matriz de poltronas para escrever o estado no arquivo
                for (int j = 0; j < poltronas[0].length; j++) {
                    writer.println(poltronas[i][j]); // Escreve o estado da poltrona (reservada ou não) no arquivo, seguido por uma quebra de linha
                }
            }
        } catch (FileNotFoundException e) { // Se ocorrer uma exceção de FileNotFoundException (arquivo não encontrado),
             e.printStackTrace();           // o código dentro deste bloco será executado.
           
        }
    }

    public void resetPoltronas() {    // Método para resetar o estado das poltronas, marcando todas como desocupadas

        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[0].length; j++) {
                poltronas[i][j] = false;
            }
        }
        savePoltronasState(); // Salva o estado das poltronas
    }

    public Filme getFilme() {    // Método para obter o filme associado à sessão
        return filme;
    }

    public String getHorario() {      // Método para obter o horário da sessão
        return horario;
    }

    public boolean reservarPoltrona(int fila, int numeroPoltrona) {     // Método para reservar uma poltrona na sessão
        if (fila >= 0 && fila < poltronas.length && numeroPoltrona >= 0 && numeroPoltrona < poltronas[0].length) {   // Verificação se a fila e o número da poltrona estão dentro dos limites da matriz
            if (!poltronas[fila][numeroPoltrona]) {             // Verificação se a poltrona está disponível para reserva
                poltronas[fila][numeroPoltrona] = true;
                savePoltronasState();
                return true; // Indica sucesso na reserva
            }
        }
        return false; //falha na reserva
    }

    public void mostrarLayoutPoltronas() { // Método para exibir o layout atual das poltronas
        System.out.println("Layout das Poltronas:");
        System.out.print("  "); // cabeçalho numérico indicando os números das poltronas
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < poltronas.length; i++) { // Loop externo para iterar sobre as filas de poltronas
            System.out.print((char) ('A' + i) + " ");     // Imprime a letra da fila, usando a representação de caracteres ('A' + i)
            for (int j = 0; j < poltronas[0].length; j++) {     // Loop interno para iterar sobre as poltronas em uma fila específica
                System.out.print(poltronas[i][j] ? "X " : "_ ");   // Imprime 'X' se a poltrona estiver reservada, ou '_' se estiver livre
            }
            System.out.println();
        }
    }

    public double getPrecoInteira() {     // Método para obter o preço da entrada inteira para a sessão
        return 10.0;
    }

    public double getPrecoMeia() {     // Método para obter o preço da entrada meia para a sessão
        return 5.0;
    }
}
