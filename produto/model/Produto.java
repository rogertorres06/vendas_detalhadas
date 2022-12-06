package model;

public class Produto {

    private String nome;
    private int codigo;
    private Double valor = 0.0;
    private int qtdEstoque;

    public Produto(String nome, int codigo, Double valor, int qtdEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public boolean equals(Object obj) {

        return getCodigo() == (((Produto) obj).getCodigo()) ||
                getNome().equals(((Produto) obj).getNome());
    }

    @Override
    public String toString() {
        return String.format("\nProduto: %s  \nCodigo: %d  \nValor: R$ %.1f  \nEstoque: %d", getNome(), getCodigo(),
                getValor(), getQtdEstoque());
    }

}
