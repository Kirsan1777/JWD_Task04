package by.nikita.sockets.server.service;

import java.util.ArrayList;
import java.util.List;

public class ParserService {

    private static final String WORD_SEPARATOR = " ";
    private static final String FIND_ALL_SENTENCES = "[.?!]";
    private static final String FIND_SENTENCES_WITH_CODE = "[{}\\\\/#&;=]";
    private static final String FIND_ALL_WORDS_IN_SENTENCES = "[\\,\\:\\;\\s]";


    public List<String> getAllSentenceOutString(String entranceString) {
        List<String> list = new ArrayList<>();
        String[] temp = entranceString.split(FIND_ALL_SENTENCES);
        for (int i = 0; i < temp.length; i++) {
            list.add(temp[i]);
        }
        list = deleteCodeInText(list);
        return list;
    }

    public List<String> deleteCodeInText(List<String> listWithSentences) {
        List<String> listWithoutCode = new ArrayList<>();
        for (String str : listWithSentences) {
            String[] test = str.split(FIND_SENTENCES_WITH_CODE);
            if (test.length == 1) {
                listWithoutCode.add(str);
            }
        }

        return listWithoutCode;
    }

    public List<String> getAllWordsInSentence(String sentence) {
        List<String> wordsList = new ArrayList<>();
        String[] words = sentence.split(FIND_ALL_WORDS_IN_SENTENCES);
        for (String word : words) {
            if (!word.equals("") && !word.equals(WORD_SEPARATOR)) {
                wordsList.add(word);
            }
        }
        return wordsList;
    }

}
