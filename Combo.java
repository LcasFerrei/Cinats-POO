public class Combo {
    private String descricaoCombo;     // Atributos privados da classe
    private double precoCombo;

    public Combo(String descricaoCombo, double precoCombo) {    // Construtor da classe
        this.descricaoCombo = descricaoCombo;
        this.precoCombo = precoCombo;
    }

    public String getDescricaoCombo() {
        return descricaoCombo;
    }
                                                // Métodos para obter a descrição do combo e o preço
    public double getPrecoCombo() {
        return precoCombo;
    }
}
