package SSP.Gaming;


import SSP.Players.Computer;
import SSP.Players.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class StoneScissorsPaper {

    private static final Logger loggerDebug = LoggerFactory.getLogger("logger.debug");
    private static final Logger loggerWarn = LoggerFactory.getLogger("logger.warn");
    private static final Logger loggerInfo = LoggerFactory.getLogger("logger.info");
    private static final Logger loggerError = LoggerFactory.getLogger("logger.error");


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        loggerInfo.info("User has entered the game.");

        loggerInfo.info("Please enter your Name.");
        Player player = new Player(sc.nextLine());
        Computer computer = new Computer();

        loggerInfo.info("How much games do you want to play?");
        String numberOfGames = sc.nextLine();

        boolean gameOrNo = check(Double.parseDouble(numberOfGames));
        while (!(gameOrNo)) {
            loggerError.error("Please enter a positive integer number.");
            numberOfGames = sc.nextLine();
            gameOrNo = check(Double.parseDouble(numberOfGames));
        }
        int games = Integer.parseInt(numberOfGames);
        loggerInfo.info("User is going to play " + games + " rounds.");
        int counter = 0;
        String bid = " ";

        while (bid.equalsIgnoreCase("Q") || games > counter) {
            System.out.println("Make a bid: SS - for scissors, P - for paper, S - for stone, Q - to quit the game.");
            bid = sc.nextLine().toUpperCase();
            loggerInfo.info("User bet on " + bid);
            switch (bid) {
                case "S":
                case "P":
                case "SS":
                    int wOL = winOrLose(bid);
                    announce(wOL);
                    counter++;
                    if (wOL > 0) {
                        player.setWins(player.getWins() + 1);
                        computer.setLoses(computer.getLoses() + 1);
                    } else if (wOL < 0) {
                        player.setLoses(player.getLoses() + 1);
                        computer.setWins(computer.getWins() + 1);
                    } else {
                        player.setDraws(player.getDraws() + 1);
                        computer.setDraws(computer.getDraws() + 1);
                    }
                    break;
                case "Q":
                    loggerInfo.info("User decides to end the game.");
                    System.exit(-1);
                    break;
                default:
                    loggerError.error("Unknown bid. Please repeat.");
            }
            loggerInfo.info("User played " + counter + " rounds. " + (games - counter) + " rounds left.");
        }

        System.out.println("Where do you want to save your results? Paste the path, please.");

        String path = sc.nextLine() + "results.txt";

        while (!(checkIn(path))) {
            loggerError.error("Please, write down correct path.");
            path = sc.nextLine() + "/results.txt";
        }

        String wnr = winner(counter, player.getWins(), computer.getWins());
        loggerInfo.info(wnr);

        OutputStream output = new FileOutputStream(path, false);
        if (!(Files.exists(Path.of(path)))) {
            Path results = Files.createFile(Path.of(path));
        } else {
            output.write(" \n ".getBytes(StandardCharsets.UTF_8));
            output.write(player.toString().getBytes(StandardCharsets.UTF_8));
            output.write(" \n ".getBytes(StandardCharsets.UTF_8));
            output.write(computer.toString().getBytes(StandardCharsets.UTF_8));
            output.write(" \n ".getBytes(StandardCharsets.UTF_8));
            output.write(wnr.getBytes(StandardCharsets.UTF_8));
            output.write(" \n ".getBytes(StandardCharsets.UTF_8));
        }
        System.out.println("Now you can check your results in a file located on the path you specified. Thank you for a game and see you soon!");
        loggerInfo.info("User has ended the game.");
    }

    public static int winOrLose(String bid) {

        int random = (int) (Math.random() * 3);
        String bid1 = switch (random) {
            case 1 -> "SS";
            case 2 -> "S";
            case 3 -> "P";
            default -> " ";
        };

        loggerInfo.info("Computer bet on " + bid1);

        if (bid.equals("S") && bid1.equals("S")) {
            return 0;
        } else if (bid.equals("P") && bid1.equals("P")) {
            return 0;
        } else if (bid.equals("SS") && bid1.equals("SS")) {
            return 0;
        } else if (bid.equals("S") && bid1.equals("P")) {

            return -1;
        } else if (bid.equals("SS") && bid1.equals("P")) {
            return 1;
        } else if (bid.equals("P") && bid1.equals("S")) {
            return 1;
        } else if (bid.equals("P") && bid1.equals("SS")) {
            return -1;
        } else if (bid.equals("S") && bid1.equals("SS")) {
            return 1;
        } else if (bid.equals("SS") && bid1.equals("S")) {
            return -1;
        } else {
            return -100;
        }
    }

    public static void announce(int value) {
        if (value > 0) {
            loggerInfo.info("User win.");
        } else if (value < 0) {
            loggerInfo.info("Computer win.");
        } else {
            loggerInfo.info("Draw.");
        }
    }


    public static boolean check(double number) {
        if ((number % 1) == 0 && number > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String winner(int allGamesPlayed, int yourWins, int computerWins) {
        if ((yourWins - computerWins) > 0) {
            return "Congratulations! User beat computer!";
        } else if ((computerWins - yourWins) > 0) {
            return "User lose. Computer defeated him.";
        } else if ((yourWins - computerWins) == 0) {
            return "It's a draw.";
        } else {
            return " ";
        }

    }

    public static boolean checkIn(String str) {
        if (Path.of(str).isAbsolute()) {
            return true;
        } else {
            return false;
        }
    }
}