package by.nikita.sockets.client.console;

import by.nikita.sockets.client.entity.Message;
import by.nikita.sockets.client.entity.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader {

    public int inputInt() {
        Scanner in = new Scanner(System.in);
        int numberInt;
        while (!in.hasNextInt()) {
            System.out.println("Please input correct number");
            in.next();
        }
        numberInt = in.nextInt();
        return numberInt;
    }

    public String inputString() {
        Scanner in = new Scanner(System.in);
        String string;
        string = in.nextLine();
        return string;
    }

    public Message inputParameters(int numberOfOperation, String allText) {
        Message message = new Message();
        Text text = new Text();
        List<String> listWithWords = new ArrayList<>();
        String exit = "-1";
        switch (numberOfOperation) {
            case 1: case 2: case 3: case 5: case 6: case 7: case 8: case 14: case 15:
                break;
            case 4: case 12:
                ConsoleWriter.printConditionSizeWord();
                message.setCountOfLetter(inputInt());
                break;
            case 9: case 13:
                ConsoleWriter.printLetterCondition();
                exit = inputString();
                text.setSubstring(exit);
                break;
            case 10:
                ConsoleWriter.printWordCondition();
                while (!exit.equals("0")) {
                    exit = inputString();
                    if (!exit.equals("0")) {
                        listWithWords.add(exit);
                    }
                }
                message.setList(listWithWords);
                break;
            case 11:
                ConsoleWriter.printFirstAndLastLetter();
                for (int i = 0; i < 2; i++) {
                    exit = inputString();
                    listWithWords.add(exit);
                }
                message.setList(listWithWords);
                break;
            case 16:
                ConsoleWriter.printConditionNumberString();
                message.setCountOfLetter(inputInt());
                ConsoleWriter.printConditionSizeWord();
                message.setLengthString(inputInt());
                ConsoleWriter.printConditionsForSubstring();
                exit = inputString();
                text.setSubstring(exit);
                break;
            default:
        }
        text.setAllText(allText);
        message.setTextObject(text);
        message.setNumberOfOperation(numberOfOperation);
        return message;
    }
}
