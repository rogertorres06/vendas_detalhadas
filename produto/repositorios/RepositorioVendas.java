package repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import model.Venda;
import static java.util.stream.Collectors.toList;

public class RepositorioVendas {
    private List<Venda> vendas = new ArrayList<>();
    private List<Venda> vendasFiltro = new ArrayList<>();

    public void adicionarVenda(Venda venda) {
        if (vendas.contains(venda)) {
            throw new InputMismatchException("Ja existe esse produto cadastrado");
        }
        vendas.add(venda);

    }

    public void relatorioVendas(LocalDate datainicial, LocalDate dataifinal) {
        vendasFiltro = vendas.stream()
                .filter(p -> p.getDataVenda().compareTo(datainicial) >= 0
                        && p.getDataVenda().compareTo(dataifinal) <= 0)
                .collect(toList());

        vendasFiltro.forEach(System.out::println);

        relatorioDeValor(vendasFiltro);
    }

    public void relatorioDeValor(List<Venda> produtoRelatorio) {
        DoubleSummaryStatistics valor = produtoRelatorio.stream()
                .collect(Collectors.summarizingDouble((v -> {
                    return v.getProdutoVendido().getValor() * v.getQuantidadeVendida();
                })));
        System.out.println("=================================================================");
        System.out.printf("    Valor Médio do periodo: %.1f\n",
                valor.getAverage());
        System.out.println("=================================================================");
    }

}
