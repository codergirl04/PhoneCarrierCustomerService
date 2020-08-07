import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.lang.*;

public class SmartCarrier {

    // private instance fields including scanner, which is used by multiple methods
    private TreeMap <String, ArrayList<Item>> treeMap;
    private String location;
    private Scanner scanner;

    // default constructor
    public SmartCarrier() {
        this.treeMap = new TreeMap<String, ArrayList<Item>>();
        this.location = "";
    }

    // non-default constructor
    public SmartCarrier(TreeMap<String, ArrayList<Item>> treeMap, String location) {
        this.treeMap = treeMap;
        this.location = location;
    }

    // reads a text file to build the TreeMap's entries
    public void init() {
        BufferedReader reader;
        String line;
        Path inputFilePath = Paths.get("src/DataFile.txt");
        try {
            reader =  Files.newBufferedReader(inputFilePath, StandardCharsets.US_ASCII);
            while ((line = reader.readLine()) != null) {
                addEntry(line);
            }
            reader.close ();
        } catch (IOException e)  {
            e.printStackTrace ();
        }
    }

    // helper method for init which instantiates the correct type of object and adds a
    // new Message object to the TreeMap object
    public void addEntry(String line) {
        Message<?> newMessage = createMessageObject(line);
        String from = newMessage.getFrom();
        if (treeMap.containsKey(from)) {
            treeMap.get(from).add(newMessage);
        } else {
            ArrayList<Item> list = new ArrayList<Item>();
            list.add(newMessage);
            treeMap.put(from, list);
        }

    }

    //shows the menu, asks for the user's choice, and executes it
    public void run() {
        boolean quit = false;
        this.scanner = new Scanner(System.in).useDelimiter("\\n");
        do {
            // display the menu
            System.out.println("FOOTHILL WIRELESS at " + location +
                    "\nMESSAGE UTILIZATION AND ACCOUNT ADMIN\n" +
                    "1. List all accounts\n" +
                    "2. Erase the first media message\n" +
                    "3. Equals test\n" +
                    "4. Quit");
            System.out.println("Enter a choice (1-4): ");
            if(scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine();
                switch (input) {
                    case 1 -> listAllAccounts();
                    case 2 -> eraseFirstMedia();
                    case 3 -> equalsTest();
                    case 4 -> quit = true;
                    default -> System.out.println("Invalid input.");
                }
            }
            else {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        } while(!quit);
        quit();
    }

    // lists the account number, all messages, and total charges
    private void listAllAccounts() {
        for (Map.Entry<String, ArrayList<Item>> entry : treeMap.entrySet()) {
            ArrayList<Item> value = entry.getValue ();
            ListIterator<Item> iterator = value.listIterator();
            double charge = 0;
            System.out.println("Account: " + entry.getKey());
            while (iterator.hasNext()) {
                Item next = iterator.next();
                charge += next.getCharge();
                System.out.println(next.toString());
            }
            System.out.printf("Total charges: %.2f\n", charge);
            System.out.println("--------------------------------------------------" +
                    "-------------------------------------------------------------");
        }
    }

    // erases the first Media type message from each account
    // iterates through treeMap
    private void eraseFirstMedia() {
        for (Map.Entry<String, ArrayList<Item>> entry : treeMap.entrySet()) {
            ArrayList<Item> value = entry.getValue ();
            eraseHelper(value);
        }
    }

    // helper method which iterates through ArrayList and removes the first Media message
    private void eraseHelper(List<? extends Item> list) {
        for (Item element : list) {
            if (element instanceof Message<?>) {
                if (((Message<?>) element).getMessage() instanceof Media) {
                    list.remove(element);
                    break;
                }
            }
        }
    }

    // takes user input (message type, time, from, to, and message-specific data)
    // for two messages and checks if they're equal
    private void equalsTest() {
        try {
            Message<?> message = null;
            System.out.println("First message info (enter as a complete line): ");
            if (scanner.hasNext()) {
                String line = scanner.nextLine();
                message = createMessageObject(line);
            }
            System.out.println("Second message info (enter as a complete line): ");
            if (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (createMessageObject(line).equals(message)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input.");
        }
    }

    // helper method for equalsTest which takes the message information and
    // returns the correct type of Message object
    private Message<?> createMessageObject(String line) {
        String[] data = line.split(",");

        String type = data[0];
        int time = Integer.parseInt(data[1]);
        String from = data[2];
        String to = data[3];

        switch (type) {
            case "T" -> {
                String text = data[4];
                Text newText = new Text(text);
                return new Message<Text>(time, from, to, newText.charge(), newText);
            }
            case "M" -> {
                double size = Double.parseDouble(data[4]);
                String format = data[5];
                Media newMedia = new Media(size, format);
                return new Message<Media>(time, from, to, newMedia.charge(), newMedia);
            }
            case "V" -> {
                int duration = Integer.parseInt(data[4]);
                String format = data[5];
                Voice newVoice = new Voice(duration, format);
                return new Message<Voice>(time, from, to, newVoice.charge(), newVoice);
            }
            default -> {
                return new Message<>();
            }
        }
    }

    // closes the scanner and exits the program
    private void quit() {
        this.scanner.close();
        System.exit(0);
    }
}
