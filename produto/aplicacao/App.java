package aplicacao;

import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class App {
    public static List<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.telaPrincipal();

    }

}
