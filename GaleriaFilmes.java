public class GaleriaFilmes {
    private Filme[] filmes;            //atributo privado representando um array de objetos da classe

    public void incluirFilmes(Filme[] filmes) {   // Método para incluir um array de filmes na galeria
        this.filmes = filmes;       // Atribui o array de filmes fornecido ao atributo da classe
    }

    public Filme[] listarFilmes() {     // Método para listar os filmes na galeria

        return filmes;      // Retorna o array de filmes presente no atributo da classe
    }
}
