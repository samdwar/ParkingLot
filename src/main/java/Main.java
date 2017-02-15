import constants.Constants;
import utils.CommandInterpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sam on 15/2/17.
 */
public class Main {
    public static void main(String[] args) {
        boolean isInputIsFile = false;
        if (args != null && args.length > 0 && args[0] != null) {
            isInputIsFile = true;
        }
        CommandInterpreter commandInterpreter = new CommandInterpreter();
        /*Input is file then read all line from file*/
        if (isInputIsFile) {
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                String line;
                while ((line = br.readLine()) != null) {
                    commandInterpreter.interpretCommand(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            /*Input would from STDIN so read line by line and pass for further processing*/
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                /*In case you want to exit then 'exit' as command*/
                if (line.equalsIgnoreCase(Constants.EXIT)) {
                    break;
                }
                commandInterpreter.interpretCommand(line);
            }
        }

    }
}
