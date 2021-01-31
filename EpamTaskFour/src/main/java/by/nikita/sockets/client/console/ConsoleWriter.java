package by.nikita.sockets.client.console;

import by.nikita.sockets.client.entity.Message;

public class ConsoleWriter {

    public static void printOptionalMenu() {
        System.out.println(
                " 1 - findSameWords\n 2 - getAllSentencesAscending\n 3 - findUniqueWord\n 4 - findWordsInInterrogativeSentence\n 5 - swapFirstAndLastWordInSentences\n 6 - sortTextToAlphabet\n 7 - sortAllWordsInTextByVowelShare\n 8 - sortByFirstConsonantLetter\n 9 - sortWordsByCountOfLetter\n 10 - findCountWordsInText\n 11 - deleteMaxLengthSubstring\n 12 - deleteAllWordsWitchStartedOnConsonant\n 13 - sortWordsInTextByCountOfLetter \n 14 - findMaxLengthPalindrom\n 15 -convertEveryWord \n 16 - replaceWordsWithSubstring"
        );
    }


    public static void printResultOperation(Message message) {
        for (String str : message.getList()) {
            System.out.println(str);
        }
    }

    public static void printWordCondition() {
        System.out.println("Please input words, if you want to stop input 0: ");
    }

    public static void printFirstAndLastLetter() {
        System.out.println("Please at the begining input first letter after that input last letter: ");
    }

    public static void printLetterCondition() {
        System.out.println("Please input letter");
    }

    public static void printConditionSizeWord() {
        System.out.println("Please input word size");
    }

    public static void printConditionNumberString() {
        System.out.println("Please input number string");
    }

    public static void printConditionsForSubstring() {
        System.out.println("Please input substring to replace");
    }

    public static void printQuestion() {
        System.out.println("What do you want to do with text: ");
    }
}
