package br.edu.univas;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Dictionary {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        int option;
        int pos = 0;
        String[] result;
        String[] words = new String[3];
        String[] translatedWords = new String[3];

        do {
            TimeUnit.MILLISECONDS.sleep(1500);
            buildMenu();
            System.out.print("Selecione: ");
            option = scan.nextInt();

            switch (option) {
                case 1 -> {
                    if (hasNullSlots(words)) {
                        result = registerEditWord();
                        doRegister(pos, words, translatedWords, result);
                    } else {
                        System.out.println("\nVocê atingiu o limite de cadastros disponíveis, exclua algumas palavras caso queira efetuar mais cadastros.");
                    }
                }
                case 2 -> doEdit(words, translatedWords);
                case 3 -> deleteWord(words, translatedWords);
                case 4 -> searchWord(words, translatedWords);
                case 5 -> listWords(words);
                case 9 -> System.out.println("\nAdeus!");
                default -> System.out.println("\nOpção inválida!");
            }
        } while (option != 9);
    }

    public static void buildMenu() {
        System.out.println("\nBem vindo ao dicionário em java, qual operação deseja fazer?\n");
        System.out.println("1 - Cadastrar nova palavra.");
        System.out.println("2 - Editar palavra.");
        System.out.println("3 - Excluir palavra.");
        System.out.println("4 - Consultar palavra.");
        System.out.println("5 - Listar palavras cadastradas.");
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

        int position = compareWords(result[0], words);

        if (position == -1) {
            words[pos] = result[0];
            translatedWords[pos] = result[1];
            System.out.println("\nPalavra cadastrada com sucesso!");
        } else {
            System.out.println("\nPalavra já existente!");
        }
    }

    public static void doEdit(String[] words, String[] translatedWords) {
        int position = getPosition(words, "editar");

        if (position == -1) {
            System.out.println("\nPalavra não encontrada!");
        } else {
            String[] result = registerEditWord();
            int existPosition = compareWords(result[0], words);
            if (existPosition == -1 || existPosition == position) {
                words[position] = result[0];
                translatedWords[position] = result[1];
                System.out.println("\nPalavra editada com sucesso!");
            } else {
                System.out.println("\nPalavra já existente!");
            }
        }
    }

    public static void deleteWord(String[] words, String[] translatedWords) {
        Scanner scan = new Scanner(System.in);
        int position = getPosition(words, "excluir");

        if (position == -1) {
            System.out.println("\nPalavra não encontrada!");
        } else {
            System.out.println("Tem certeza que deseja excluir essa palavra? \n1 - Sim \n2 - Não");
            int option = scan.nextInt();
            if (option == 1) {
                words[position] = null;
                translatedWords[position] = null;
                System.out.println("\nPalavra e tradução excluídas com sucesso!");
            } else if (option == 2) {
                System.out.println("\nOperação cancelada, retornando ao menu principal.");
            } else {
                System.out.println("\nOpção inválida, retornando ao menu principal.");
            }
        }
    }

    public static void searchWord(String[] words, String[] translatedWords) {
        int position = getPosition(words, "consultar");

        if (position == -1) {
            System.out.println("\nPalavra não encontrada!");
        } else {
            System.out.println("\nPalavra: " + words[position]);
            System.out.println("Tradução: " + translatedWords[position] + "\n");
        }
    }

    public static void listWords(String[] words) throws InterruptedException {
        int index = 0;

        System.out.println("\nListagem das palavras cadastradas:");
        for (String word : words) {
            if (word != null) {
                System.out.println(++index + ": " + word);
            }
        }
        System.out.println("\nOBS: Para visualizar a tradução de alguma palavra, utilize a opção Consultar");
        TimeUnit.MILLISECONDS.sleep(750);
    }

    private static int getPosition(String[] words, String option) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nInforme em português a palavra que deseja " + option + ":");
        String search = scan.next();

        return compareWords(search, words);
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

        for (String word : words) {
            if (word != null) {
                hasValue++;
            }
        }

        return hasValue != words.length;
    }
}
