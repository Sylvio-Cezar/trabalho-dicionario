package br.edu.univas;
import java.util.Scanner;

public class Dictionary {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int option;
        int pos = 0;
        String[] result;
        String[] words = new String[100];
        String[] translatedWords = new String[100];

        do {
            buildMenu();

            option = scan.nextInt();

            switch (option) {
                case 1 -> {
                    boolean hasSlots = hasNullSlots(words);
                    if (hasSlots) {
                        result = registerEditWord();
                        doRegister(pos, words, translatedWords, result);
                    } else {
                        System.out.println("Você atingiu o limite de cadastros disponíveis, exclua algumas palavras caso queira efetuar mais cadastros.");
                    }
                }
                case 2 -> doEdit(words, translatedWords);
                case 3 -> deleteWord(words, translatedWords);
                case 4 -> searchWord(words, translatedWords);
                case 9 -> System.out.println("\nAdeus!");
                default -> System.out.println("\nOpção inválida!");
            }

            System.out.println("\n--------------ÁREA DE TESTES----------------");
            for (int i = 0; i <= 4; i++) {
                System.out.println(words[i]);
                System.out.println(translatedWords[i]);
            }
            System.out.println("--------------------------------------------\n");

        } while (option != 9);
    }

    public static void buildMenu() {
        System.out.println("\nBem vindo ao dicionário em java, qual operação deseja fazer?\n");
        System.out.println("1 - Cadastrar nova palavra.");
        System.out.println("2 - Editar palavra.");
        System.out.println("3 - Excluir palavra.");
        System.out.println("4 - Consultar palavra.");
        System.out.println("9 - Sair (os dados serão perdidos).\n");
    }

    public static String[] registerEditWord() {
        Scanner scan = new Scanner(System.in);
        String[] registered = new String[2];

        System.out.println("\nInforme a palavra em português:");
        registered[0] = scan.next();
        System.out.println("Informe a tradução da palavra:");
        registered[1] = scan.next();

        return registered;
    }

    public static void doRegister(int pos, String[] words, String[] translatedWords, String[] result) {
        for (int i = 0; i < words.length; i++) {
            if (words[i] == null) {
                pos = i;
                break;
            }
        }

        int position = compareWords(words[pos], words);

        if (position == -1) {
            words[pos] = result[0];
            translatedWords[pos] = result[1];
        } else {
            System.out.println("Palavra já existente!");
        }

    }

    public static void doEdit(String[] words, String[] translatedWords) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nInforme em português a palavra que deseja editar:");
        String search = scan.next();

        int position = compareWords(search, words);

        if (position == -1) {
            System.out.println("Palavra não encontrada!");
        } else {
            String[] result = registerEditWord();
            words[position] = result[0];
            translatedWords[position] = result[1];
        }
    }

    public static void deleteWord(String[] words, String[] translatedWords) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nInforme em português a palavra que deseja excluir:");
        String search = scan.next();

        int position = compareWords(search, words);

        if (position == -1) {
            System.out.println("Palavra não encontrada!");
        } else {
            words[position] = null;
            translatedWords[position] = null;
            System.out.println("\nPalavra e tradução excluídas com sucesso!");
        }
    }

    public static void searchWord(String[] words, String[] translatedWords) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nInforme em português a palavra que deseja consultar:");
        String search = scan.next();

        int position = compareWords(search, words);

        if (position == -1) {
            System.out.println("Palavra não encontrada!");
        } else {
            System.out.println("\nPalavra: " + words[position]);
            System.out.println("Tradução: " + translatedWords[position] + "\n");
        }
    }

    public static int compareWords(String search, String[] words) {
        int pos = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null && words[i].equals(search)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static boolean hasNullSlots(String[] words) {
        int hasValue = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i] != null) {
                hasValue++;
            }
        }

        return hasValue != words.length;
    }

}
