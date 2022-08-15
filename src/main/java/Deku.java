import java.util.Scanner;

public class Deku {
    private BotList botList;

    Deku() {
        this.botList = new BotList();
    }

    private final String SEPARATOR = "__________________________________";
    private void introduction() {
        String logo ="    ____  ________ ____  __\n" +
                "   / __ \\/ ____/ //_/ / / /\n" +
                "  / / / / __/ / ,< / / / / \n" +
                " / /_/ / /___/ /| / /_/ /  \n" +
                "/_____/_____/_/ |_\\____/   \n";
        System.out.println(logo +
                "\nHello I'm DEKU\nHow may I help you today?\n" +
                SEPARATOR);
    }
    /*
    * Method to start the chat-bot
    */
    public void start() {
        this.introduction();
        boolean active = true;
        while (active) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                active = false;
            }
            System.out.println(
                    this.parseReply(userInput)
            );
        }
    }

    private String parseReply(String input) {
        String reply = input;
        switch (input) {
            case ("bye"):
                reply = "Bye! Until next time!";
                break;
            case ("list"):
                reply =  this.botList.toString();
                break;
            default:
                reply = this.botList.add(input);
        }
        return SEPARATOR + "\n" + reply + "\n" + SEPARATOR;
    }

    /*
    * Default main method
    */
    public static void main(String[] args) {
        Deku dekuBot = new Deku();
        dekuBot.start();
    }
}
