import javafx.util.Pair;
import model.Grammar;
import model.Parser;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        // FOR G1
        //String fileName = "/Users/teodoradan/Desktop/Team-FLCD/Lab5/src/data/g1";

        // FOR G2
        String fileName = "/Users/teodoradan/Desktop/Team-FLCD/Lab5/src/data/g2";

        // Sequence for G1
        String g1SequenceFilename = "/Users/teodoradan/Desktop/Team-FLCD/Lab5/src/data/seq1";

        // PIF for G2
        String pifFileName = "/Users/teodoradan/Desktop/Team-FLCD/Lab5/src/data/pif";

        Grammar grammar = new Grammar(fileName);
        grammar.readFromFile();
        Parser parser = new Parser(grammar);
        parser.generateSets();

        while (true) {
            display_menu();
            String command = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter command: ");
            command = reader.readLine();

            switch (command) {
                case "1":
                    System.out.println("Non-terminals: ");
                    System.out.println(grammar.getSetOfNonTerminals());
                    System.out.println("\n");
                    break;
                case "2":
                    System.out.println("Terminals: ");
                    System.out.println(grammar.getSetOfTerminals());
                    System.out.println("\n");
                    break;
                case "3":
                    System.out.println("Production: ");
                    System.out.println(grammar.getSetOfProductions());
                    System.out.println("\n");
                    break;
                case "4":
                    System.out.println("Starting symbol: ");
                    System.out.println(grammar.getStartingSymbol());
                    System.out.println("\n");
                    break;
                case "5":
                    System.out.println("Enter non-terminal: ");
                    Scanner input = new Scanner(System.in);
                    String non_terminal = input.next();
                    System.out.println(grammar.productionForNonTerminal(non_terminal));
                    System.out.println("\n");
                    break;
                case "6":
                    System.out.println(parser.getFirstSet());
                    break;
                case "7":
                    System.out.println(parser.getFollowSet());
                    break;
                case "8":
                    parser.createParseTable();
                    System.out.println(parser.getParserOutput());
                    break;
                case "9":
                    parser.createParseTable();
                    List<String> sequence = readSequence(g1SequenceFilename);
                    System.out.println(parser.parseSequence(sequence));
                    break;
                case "10":
                    parser.createParseTable();
                    List<String> seq = readSequenceFromPif(pifFileName);
                    System.out.println(parser.parseSequence(seq));
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.err.println("Unrecognized option");
                    break;
            }
        }
    }

    private static void display_menu() {
        System.out.println("1 - Show non-terminals");
        System.out.println("2 - Show terminals");
        System.out.println("3 - Show productions");
        System.out.println("4 - Show starting symbol");
        System.out.println("5 - Production for given non-terminal");
        System.out.println("6 - First set");
        System.out.println("7 - Follow set");
        System.out.println("8 - Parsing table");
        System.out.println("9 - Parse sequence G1 (without PIF)");
        System.out.println("10 - Parse sequence G2 (with PIF)");
        System.out.println("0 - Exit \n");
    }

    private static List<Pair<String, Integer>> readPif(String fileName) throws FileNotFoundException {
        List<Pair<String, Integer>> PIF = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] pair = scanner.nextLine().split("\\s+");
            Pair<String, Integer> elementFromPif = new Pair<>(pair[0], Integer.parseInt(pair[1]));
            PIF.add(elementFromPif);
        }

        return PIF;
    }

    private static List<String> readSequence(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        List<String> output = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String sequence = scanner.nextLine();
            output.addAll(Arrays.asList(sequence.replace("\n", "").split(" ")));
        }

        return output;
    }

    private static List<String> readSequenceFromPif(String fileName) throws FileNotFoundException {
        List<Pair<String, Integer>> PIF = readPif(fileName);
        List<String> output = new ArrayList<>();

        for (Pair<String,Integer> token : PIF) {
            if (token.getValue() == -1) {
                output.add(token.getKey());
            } else {
                output.add("IDENTIFIER");
            }
        }

        return output;
    }
}
