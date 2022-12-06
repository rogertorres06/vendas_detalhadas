package aplicacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Produto;
import model.Venda;
import repositorios.RepositorioProdutos;
import repositorios.RepositorioVendas;

public class Menu {

    private RepositorioProdutos repo = new RepositorioProdutos();
    private RepositorioVendas repoVendas = new RepositorioVendas();
    Scanner leia = new Scanner(System.in);
    int opcao;

    public void telaPrincipal() {

        do {
            mostrarMenu();
            try {
                switch (opcao) {

                    case 1:
                        cadastrarProduto();

                        break;

                    case 2:

                        consultarProduto();
                        break;

                    case 3:

                        listarProdutos();
                        break;

                    case 4:
                        VendasPorPeríodo();
                        break;

                    case 5:
                        realizarVendas();

                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        voltarMenu();

                }
            } catch (Exception e) {
                System.out.println("e.getMessage()");
                voltarMenu();
            }

        } while (opcao != 0);

    }

    public void mostrarMenu() {
        // MENU DE OPÇÕES QUE EXISTE NO PROGRAMA*/
        limpa();
        System.out.println("**MENU**");
        try {
            System.out.println("1 - Incluir produto");

            System.out.println("2 - Consultar produto");

            System.out.println("3 - Listagem de produtos");

            System.out.println("4 - Vendas por período - detalhado");

            System.out.println("5 - Realizar venda");

            System.out.println("0 - Sair");

            System.out.print("Opção: ");

            opcao = leia.nextInt();

            leia.nextLine();
        } catch (Exception e) {
            System.out.println("Valor Invalido!!!");
        }
    }

    public void cadastrarProduto() {
        limpa();
        System.out.println("***INCLUIR PRODUTO***");
        try {
            System.out.println();
            System.out.print("Digite o codigo do produto: ");

            int codigoProduto = leia.nextInt();
            leia.nextLine();

            System.out.print("Digite o nome do produto: ");

            String nome = leia.nextLine();

            System.out.print("Digite o valor do produto: ");

            Double valor = leia.nextDouble();
            leia.nextLine();

            System.out.print("Digite a quantidade: ");

            int quantidade = leia.nextInt();
            leia.nextLine();

            repo.adicionar(new Produto(nome, codigoProduto, valor, quantidade));
            System.out.println("Produto Cadastrado com sucesso.");
        } catch (Exception e) {
            System.out.println("Valor Invalido!!!");
        }
        voltarMenu();

    }

    public void consultarProduto() {
        limpa();
        System.out.println("***CONSULTAR PRODUTO***");
        try {
            System.out.println("\nDigite o codigo do produto: ");
            int codigo = leia.nextInt();
            leia.nextLine();
            Produto p = repo.consultarProduto(codigo);
            limpa();
            System.out.println("\nPRODUTO ENCONTRADO: " + p);
        } catch (Exception e) {
            System.out.println("Valor Invalido!!!");
        }
        voltarMenu();

    }

    public void listarProdutos() {
        limpa();
        System.out.println("**** LISTA DE PRODUTOS****");
        repo.listarProdutos();

        voltarMenu();

    }

    public void realizarVendas() {

        limpa();
        System.out.println("***VENDAS***");
        try {
            System.out.print("Codigo do produto: ");
            int codigoBusca = leia.nextInt();
            leia.nextLine();
            Produto buscarcodigo = repo.consultarProduto(codigoBusca);
            System.out.println("\nPRODUTO ENCONTRADO: " + buscarcodigo.getNome());
            System.out.print("Quantidade do produto: ");
            int quantidadeVendida = leia.nextInt();
            leia.nextLine();
            System.out.print("Data da venda: ");
            String dataVenda = leia.nextLine();

            if (buscarcodigo.getQtdEstoque() >= quantidadeVendida) {
                repoVendas.adicionarVenda(new Venda(buscarcodigo, dataVenda, quantidadeVendida));
                buscarcodigo.setQtdEstoque(buscarcodigo.getQtdEstoque() - quantidadeVendida);
                System.out.println("Venda realizada com sucesso.");
            } else {
                System.out.println(" venda nao realizada, sem estoque do produto");
            }
        } catch (Exception e) {
            System.out.println("Formato da data invalido!!");

        }

        voltarMenu();

    }

    public void VendasPorPeríodo() {
        limpa();
        System.out.println("*****RELATORIO DE VENDAS*****");
        try {
            System.out.println("Qual a data inicial: ");
            String data1 = leia.nextLine();
            System.out.println("Qual a data final: ");
            String data2 = leia.nextLine();

            DateTimeFormatter dv = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate datainicial = LocalDate.parse(data1, dv);
            LocalDate datafinal = LocalDate.parse(data2, dv);
            System.out.println("========================================================================");
            System.out.println("        Periodo de emissão: " + datainicial.format(dv) + " A " + datafinal.format(dv));
            System.out.println("========================================================================");

            repoVendas.relatorioVendas(datainicial, datafinal);

        } catch (Exception e) {
            System.out.println("Formato da data invalido!!!!!");
        }
        voltarMenu();

    }

    public void voltarMenu() {

        System.out.println("Digite [ENTER] para voltar ao menu");
        leia.nextLine();

    }

    public void limpa() {
        System.out.print("Everything on the console will cleared");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
