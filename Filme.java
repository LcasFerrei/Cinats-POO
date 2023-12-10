public class Filme {
    private String nome;         // Atributo privado da classe 

    public Filme(String nome) {  // Construtor da classe 
        this.nome = nome;
    }

    public String getNome() {   // Método para obter o nome do filme
        return nome;   
    }
}
