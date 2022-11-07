package br.edu.univas;
import java.util.Scanner;

public class Dictionary {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int option;
        String[] words = new String[100];
        String[] translatedWords = new String[100];

        do {
            buildMenu();

            option = scan.nextInt();

            buildSwitch(option);

        } while (option != 9 || words[99] == null);
    }

    public static void buildMenu() {
        System.out.println("\nBem vindo ao dicionário em java, qual operação deseja fazer?\n");
        System.out.println("1 - Cadastrar nova palavra");
        System.out.println("2 - Editar palavra");
        System.out.println("3 - Excluir palavra");
        System.out.println("4 - Consultar palavra");
        System.out.println("9 - Sair (os dados serão perdidos)\n");
    }

    public static void buildSwitch(int option) {
        switch (option) {
            case 1 -> registerWord();
            case 2 -> editWord();
            case 3 -> deleteWord();
            case 4 -> searchWord();
            case 9 -> System.out.println("\nAdeus!");
            default -> System.out.println("\nOpção inválida!");
        }
    }

    public static void registerWord() {
        System.out.println("\nRegistrar!");
    }

    public static void editWord() {
        System.out.println("\nEditar!");
    }

    public static void deleteWord() {
        System.out.println("\nDeletar!");
    }

    public static void searchWord() {
        System.out.println("\nConsultar!");
    }

}
