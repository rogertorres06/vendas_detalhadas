package repositorios;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;

import java.util.stream.Collectors;

import model.Produto;

public class RepositorioProdutos {

    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        if (produtos.contains(produto)) {
            throw new InputMismatchException("Ja existe esse produto cadastrado");
        }
        produtos.add(produto);
    }

    public Produto consultarProduto(int codigo) {
        verificarProdutosCadastradas();
        for (Produto produto : produtos) {
            if (produto.getCodigo() == (codigo)) {
                return produto;
            }
        }
        throw new InputMismatchException("Nao existe produto cadastrado");
    }

    public void listarProdutos() {
        verificarProdutosCadastradas();
        for (Produto produto : produtos) {
            System.out.println(produto);

        }
        relatorioDeValor(produtos);

    }

    public void relatorioDeValor(List<Produto> produtoRelatorio) {
        DoubleSummaryStatistics valor = produtoRelatorio.stream()
                .collect(Collectors.summarizingDouble((Produto::getValor)));
                System.out.println("=====================================================================");
        System.out.printf("Maior Valor: %.1f - Menor Valor: %.1f - Valor Médio: %.1f\n",
                valor.getMax(), valor.getMin(), valor.getAverage());
                System.out.println("=====================================================================");
    }

    private void verificarProdutosCadastradas() {
        if (produtos.isEmpty()) {
            throw new NullPointerException("Nao contem nenhuma Produto cadastrada");
        }
    }
    public void relatorioValorMedia(List<Produto> produtoRelatorio) {
        DoubleSummaryStatistics valor = produtoRelatorio.stream()
                .collect(Collectors.summarizingDouble((Produto::getValor)));

        System.out.printf("Maior Valor: %.1f - Menor Valor: %.1f - Valor Médio: %.1f\n",
                valor.getMax(), valor.getMin(), valor.getAverage());
    }

}
