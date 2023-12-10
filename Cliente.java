public class Cliente {
    private String nome;
    private String email;     // Atributos privados da classe
    private String telefone;

    public Cliente(String nome, String email, String telefone) {   // Construtor da classe
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {   // Métodos para obter o nome do cliente, email e telefone
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + "\nEmail: " + email + "\nTelefone: " + telefone;   // Retorna a string com as informações do cliente
    }
}
