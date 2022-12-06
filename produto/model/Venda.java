package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    private Produto produtoVendido;
    private int quantidadeVendida;
    private Double ValorUnitario;
    private LocalDate dataVenda;
    private DateTimeFormatter dv = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Venda(Produto produtoVendido, String dataVenda, int quantidadeVendida) {
        this.produtoVendido = produtoVendido;
        setDataVenda(dataVenda);
        this.quantidadeVendida = quantidadeVendida;
    }

    public Produto getProdutoVendido() {
        return produtoVendido;
    }

    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public Double getValorUnitario() {
        return ValorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        ValorUnitario = valorUnitario;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = LocalDate.parse(dataVenda, dv);
    }

    @Override
    public String toString() {
        return String.format(" %s - data da Venda: %s - Quantidade: %d - Valor:R$ %.2f - Valor Total: R$ %.2f",
                getProdutoVendido(), getDataVenda().format(dv),
                getQuantidadeVendida(), produtoVendido.getValor(), (produtoVendido.getValor() * quantidadeVendida));
    }

}
