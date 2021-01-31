package by.nikita.sockets.server.service;


import java.util.*;

public class TextEditorService {

    private ParserService parserService = new ParserService();


    private static final String WORDS_SEPARATOR = " ";
    private static final String SENTENCES_SEPARATOR = "[,.!?\\s]";
    private static final String FIND_INTERROGATIVE_SENTENCE = "[?]";
    private static final String FIND_AFFIRMATIVE_EXCLAMATORY_SENTENCE = "[.!]";
    private static final String FIND_PUNCTUATION_MARKS = "[,.!?()]";
    private static final String FIND_CONSONANT_LETTER = "[\b[цкнгшщзхждлрпвфчсмтб]]";
    private static final String FIND_ENDING_WORDS = "[,:;\\s]";

    public List<String> findSameWords(String stringWithAllText) {
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        List<String> listWithResult = new ArrayList<>();
        List<String> listWithWords = new ArrayList<>();
        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str));
        }
        String maxRepeatsWord = "";
        int maxRepeatsCount = 0;

        for (String word : listWithWords) {
            int repeatsCount = 0;
            for (String str : listWithAllSentences) {
                List<String> listWithSentences = parserService.getAllWordsInSentence(str.toLowerCase(Locale.ROOT));
                if (listWithSentences.contains(word.toLowerCase(Locale.ROOT))) {
                    repeatsCount++;
                }
            }
            if (repeatsCount > maxRepeatsCount) {
                maxRepeatsCount = repeatsCount;
                maxRepeatsWord = word;
            }
        }
        listWithResult.add("Word - " + maxRepeatsWord);
        listWithResult.add("Repeat - " + maxRepeatsCount);

        return listWithResult;
    }

    public List<String> getAllSentencesAscending(String stringWithAllText) {
        Map<String, Integer> wordsCount = new HashMap<>();
        List<Integer> listWithValue = new ArrayList<>();
        List<String> listWithSortSentences = new ArrayList<>();
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        listWithAllSentences = parserService.deleteCodeInText(listWithAllSentences);
        for (String str : listWithAllSentences) {
            String[] arrWithWords = str.split(WORDS_SEPARATOR);
            wordsCount.put(str, arrWithWords.length);
        }
        for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
            int count = 0;
            for (int i : listWithValue) {
                if (i == entry.getValue()) {
                    count++;
                }
            }
            if (count == 0) {
                listWithValue.add(entry.getValue());
            }
        }
        Collections.sort(listWithValue);
        for (int i = 0; i < listWithValue.size(); i++) {
            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                if (entry.getValue() == listWithValue.get(i)) {
                    listWithSortSentences.add(entry.getKey());
                }
            }
        }


        return listWithSortSentences;
    }

    public List<String> findUniqueWord(String stringWithAllText) {
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        List<String> listWithResult;
        List<String> listWithWords;
        listWithWords = parserService.getAllWordsInSentence(listWithAllSentences.get(0));
        listWithResult = new ArrayList<>();
        listWithAllSentences.remove(0);
        for (String word : listWithWords) {
            int count = 0;
            for (String sentence : listWithAllSentences) {
                if (sentence.contains(word)) {
                    count++;
                }
            }
            if (count == 0) {
                listWithResult.add(word);
            }
        }
        return listWithResult;
    }

    public List<String> findWordsInInterrogativeSentence(String stringWithAllText, int number) {
        List<String> list = new ArrayList<>();
        List<String> listWithWords = new ArrayList<>();
        String[] temp = stringWithAllText.split(FIND_INTERROGATIVE_SENTENCE);
        for (int i = 0; i < temp.length; i++) {
            String[] getTargetSentences = temp[i].split(FIND_AFFIRMATIVE_EXCLAMATORY_SENTENCE);
            list.add(getTargetSentences[getTargetSentences.length - 1]);
        }

        for (String str : list) {
            listWithWords.addAll(findWordWithByTheNumberOfLetter(str, number));
        }
        listWithWords = clearDuplicateWords(listWithWords);
        return listWithWords;
    }

    public List<String> swapFirstAndLastWordInSentences(String stringWithAllText) {
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        List<String> listWithResult = new ArrayList<>();
        for (String i : listWithAllSentences) {
            String[] temp = i.split("[\\s]");
            String str = "";
            str = str.concat(temp[temp.length - 1]).concat(WORDS_SEPARATOR);
            for (int j = 1; j < temp.length - 2; j++) {
                str = str.concat(temp[j]).concat(WORDS_SEPARATOR);
            }
            str = str.concat(temp[0]).concat(WORDS_SEPARATOR);
            listWithResult.add(str);
        }
        return listWithResult;
    }

    public List<String> sortTextToAlphabet(String stringWithAllText) {
        String[] deletePoint = stringWithAllText.split(FIND_PUNCTUATION_MARKS);
        stringWithAllText = "";
        for (String str : deletePoint) {
            if (!str.equals(WORDS_SEPARATOR)) {
                stringWithAllText = stringWithAllText.concat(str).concat(WORDS_SEPARATOR);
            }
        }
        String[] temp = stringWithAllText.split("[\\s]");
        List<String> listWithAllSentences = Arrays.asList(temp);
        Collections.sort(listWithAllSentences);
        return listWithAllSentences;
    }


    public List<String> sortAllWordsInTextByVowelShare(String stringWithAllText) {
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        List<String> listWithWords;
        listWithWords = new ArrayList<>();
        Map<String, Double> wordAndCoef = new HashMap<>();

        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str));
        }
        listWithWords = parserService.deleteCodeInText(listWithWords);

        for (String word : listWithWords) {
            int count = 0;

            word = word.toLowerCase(Locale.ROOT);
            char[] arrCh = word.toCharArray();
            for (char letter : arrCh) {
                if (arrCh.length > 1) {
                    if (letter == 'у' || letter == 'е' || letter == 'ы' || letter == 'а' || letter == 'о' || letter == 'э' || letter == 'я' || letter == 'и' || letter == 'ю') {
                        count++;
                    }
                }
            }
            if (count > 0) {
                double coef = word.length() / count;
                wordAndCoef.put(word, coef);
            }
        }
        wordAndCoef = sortByValue(wordAndCoef);
        listWithWords.clear();
        for (Map.Entry<String, Double> entry : wordAndCoef.entrySet()) {
            listWithWords.add(entry.getKey());
        }
        return listWithWords;
    }

    public List<String> sortByFirstConsonantLetter(String stringWithAllText) {
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        List<String> listWithWords;
        List<String> listWithResult;
        listWithWords = new ArrayList<>();
        List<String> listWithVowelsWords = new ArrayList<>();

        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str));
        }
        for (String word : listWithWords) {
            word = word.toLowerCase(Locale.ROOT);
            char[] ch = word.toCharArray();
            if (ch.length > 1) {
                if (ch[0] == 'у' || ch[0] == 'е' || ch[0] == 'ы' || ch[0] == 'а' || ch[0] == 'о' || ch[0] == 'э' || ch[0] == 'я' || ch[0] == 'и' || ch[0] == 'ю') {
                    String newWord = word;
                    newWord = newWord + ch[0];
                    newWord = newWord.substring(1);
                    listWithVowelsWords.add(newWord);
                }
            }
        }
        Collections.sort(listWithVowelsWords);
        for (String str : listWithVowelsWords) {
            char[] ch = str.toCharArray();
            str = ch[ch.length - 1] + str;
            str = str.substring(0, str.length() - 1);
            listWithWords.add(str);
        }
        listWithResult = clearDuplicateWords(listWithWords);
        return listWithResult;
    }

    public List<String> sortWordsByCountOfLetter(String stringWithAllText, String letter) {
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        List<String> listWithResult = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();
        List<String> listWithWords = new ArrayList<>();
        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str.toLowerCase(Locale.ROOT)));
        }

        for (String str : listWithWords) {
            char[] arr = str.toCharArray();
            double count = 0;
            for (char ch : arr) {
                if (ch == letter.toCharArray()[0]) {
                    count++;
                }
            }
            map.put(str, count);
        }
        map = sortByValue(map);
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            listWithResult.add(entry.getKey() + WORDS_SEPARATOR + entry.getValue());
        }
        return listWithResult;
    }

    public List<String> findCountWordsInText(String stringWithAllText, List<String> words) {
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        List<String> listWithResult = new ArrayList<>();
        Map<String, Double> wordsCount = new HashMap<>();
        List<String> listWithWords = new ArrayList<>();
        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str.toLowerCase(Locale.ROOT)));
        }
        for (String word : words) {
            double count = 0;
            for (String str : listWithWords) {
                if (str.equals(word)) {
                    count++;
                }
            }
            wordsCount.put(word, count);
        }
        wordsCount = sortByValue(wordsCount);
        for (Map.Entry<String, Double> entry : wordsCount.entrySet()) {
            listWithResult.add(entry.getKey() + WORDS_SEPARATOR + entry.getValue());
        }
        return listWithResult;
    }

    public List<String> deleteMaxLengthSubstring(String stringWithAllText, List<String> letters) {
        List<String> listWithResult;
        listWithResult = new ArrayList<>();
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);

        String regex = letters.get(0) + ".+" + letters.get(1);

        for (String str : listWithAllSentences) {
            String[] kickString = str.split(regex);
            for (int i = 0; i < kickString.length; i++) {
                listWithResult.add(kickString[i]);
            }
        }
        return listWithResult;
    }

    public List<String> deleteAllWordsWitchStartedOnConsonant(String stringWithAllText, int length) {
        String[] arrWords = stringWithAllText.split(SENTENCES_SEPARATOR);
        List<String> listWithResult = new ArrayList<>();
        String stringWithDeletedWords = "";
        for (String str : arrWords) {
            if (!str.matches(FIND_CONSONANT_LETTER) && str.length() == length) {
                stringWithDeletedWords = stringWithDeletedWords.concat(str).concat(WORDS_SEPARATOR);
            }
        }
        listWithResult.add("Deleted words" + stringWithDeletedWords);
        return listWithResult;
    }

    public List<String> sortWordsInTextByCountOfLetter(String stringWithAllText, String letter) {
        List<String> listWithResult = new ArrayList<>();
        List<String> listWithWords = new ArrayList<>();
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        Map<String, Double> wordsAndCount = new HashMap<>();
        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str.toLowerCase(Locale.ROOT)));
        }
        for (String word : listWithWords) {
            char[] charWord = word.toCharArray();
            double count = 0;
            for (char letterInWord : charWord) {
                if (letterInWord == letter.toCharArray()[0]) {
                    count++;
                }
            }
            if (count > 0) {
                wordsAndCount.put(word, count);
            }
        }
        wordsAndCount = sortByValue(wordsAndCount);
        for (Map.Entry<String, Double> entry : wordsAndCount.entrySet()) {
            listWithResult.add(entry.getKey() + WORDS_SEPARATOR + entry.getValue());
        }
        return listWithResult;
    }

    public List<String> findMaxLengthPalindrom(String stringWithAllText) {
        List<String> listWithResult = new ArrayList<>();
        List<String> listWithWords = new ArrayList<>();
        String correctString = "";
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str.toLowerCase(Locale.ROOT)));
        }
        for (String str : listWithWords) {
            correctString = correctString.concat(str);
        }
        List<String> allPalindroms = new ArrayList<>(findEvenPalindrom(correctString));
        allPalindroms.addAll(findOddPalindrom(correctString));
        allPalindroms = sortedListByLength(allPalindroms);
        int sizeWord = allPalindroms.get(allPalindroms.size() - 1).length();
        for (String maxSizeWord : allPalindroms) {
            if (maxSizeWord.length() == sizeWord) {
                listWithResult.add(maxSizeWord);
            }
        }
        return listWithResult;
    }

    public List<String> convertEveryWord(String stringWithAllText) {
        StringBuilder sb = new StringBuilder();
        List<String> listWithWords = new ArrayList<>();
        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        listWithAllSentences = parserService.deleteCodeInText(listWithAllSentences);
        List<String> listWithCorrectWords = new ArrayList<>();
        String stringWithAllWords = "";
        for (String str : listWithAllSentences) {
            listWithWords.addAll(parserService.getAllWordsInSentence(str));
        }
        for (String str : listWithWords) {
            str = str.concat(WORDS_SEPARATOR);
            str.chars().distinct().forEach(c -> sb.append((char) c));
            stringWithAllWords = sb.toString();
        }
        listWithCorrectWords.add(stringWithAllWords);
        return listWithCorrectWords;
    }

    public List<String> replaceWordsWithSubstring(String stringWithAllText, int numberString, int lengthString/*7 optimal*/, String upString) {//Работает

        List<String> listWithAllSentences = parserService.getAllSentenceOutString(stringWithAllText);
        String replaceString = listWithAllSentences.get(numberString);
        String[] allWords = replaceString.split(FIND_ENDING_WORDS);
        replaceString = "";
        for (String str : allWords) {
            if (str.length() == lengthString) {
                str = upString;
            }
            replaceString = replaceString.concat(str).concat(WORDS_SEPARATOR);
        }
        listWithAllSentences.set(numberString, replaceString);
        return listWithAllSentences;
    }

    public List<String> findWordWithByTheNumberOfLetter(String line, int number) {
        List<String> string = new ArrayList<>();
        List<String> temp = parserService.getAllWordsInSentence(line);
        for (String i : temp) {
            if (i.length() == number) {
                string.add(i);
            }
        }
        return string;
    }

    public List<String> clearDuplicateWords(List<String> list) {
        List<String> string = new ArrayList<>();
        for (String str : list) {
            int count = 0;
            for (String i : string) {
                if (i.equals(str)) {
                    count++;
                }
            }
            if (count == 0) {
                string.add(str);
            }
        }
        return string;
    }

    public Map<String, Double> sortByValue(Map<String, Double> map) {
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String, Double> result = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public List<String> findEvenPalindrom(String stringWithCorrectText) {
        char[] arrWords = stringWithCorrectText.toCharArray();
        List<String> palindroms = new ArrayList<>();

        for (int i = 0; i < arrWords.length - 2; i++) {
            StringBuilder str = new StringBuilder();
            if (arrWords[i] == arrWords[i + 1]) {
                int j = 1;
                str.append(arrWords[i]);
                str.append(arrWords[i]);
                while (arrWords[i - j] == arrWords[i + j + 1]) {
                    str.append(arrWords[i - j]);
                    str.insert(0, arrWords[i - j]);
                    j++;
                }
            }
            if (str.length() > 2) {
                palindroms.add(str.toString());
            }
        }
        return palindroms;
    }

    public List<String> findOddPalindrom(String stringWithCorrectText) {
        char[] arrWords = stringWithCorrectText.toCharArray();
        List<String> palindroms = new ArrayList<>();

        for (int i = 1; i < arrWords.length - 2; i++) {
            StringBuilder str = new StringBuilder();
            if (arrWords[i - 1] == arrWords[i + 1]) {
                int j = 1;
                str.append(arrWords[i]);
                while (arrWords[i - j] == arrWords[i + j]) {
                    str.append(arrWords[i - j]);
                    str.insert(0, arrWords[i - j]);
                    j++;
                }
            }
            if (str.length() > 2) {
                palindroms.add(str.toString());
            }
        }
        return palindroms;
    }

    public List<String> sortedListByLength(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).length() > list.get(j + 1).length()) {
                    String str = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, str);
                }
            }
        }
        return list;
    }

    public List<String> chosenEditText(String strWithAllText, int kod, int lengthString, int sizeWord, String letter, List<String> listLetters) {
        List<String> text = new ArrayList<>();
        switch (kod) {
            case 1:
                text = findSameWords(strWithAllText);
                break;
            case 2:
                text = getAllSentencesAscending(strWithAllText);
                break;
            case 3:
                text = findUniqueWord(strWithAllText);
                break;
            case 4:
                text = findWordsInInterrogativeSentence(strWithAllText, sizeWord);
                break;
            case 5:
                text = swapFirstAndLastWordInSentences(strWithAllText);
                break;
            case 6:
                text = sortTextToAlphabet(strWithAllText);
                break;
            case 7:
                text = sortAllWordsInTextByVowelShare(strWithAllText);
                break;
            case 8:
                text = sortByFirstConsonantLetter(strWithAllText);
                break;
            case 9:
                text = sortWordsByCountOfLetter(strWithAllText, letter);
                break;
            case 10:
                text = findCountWordsInText(strWithAllText, listLetters);
                break;
            case 11:
                text = deleteMaxLengthSubstring(strWithAllText, listLetters);
                break;
            case 12:
                text = deleteAllWordsWitchStartedOnConsonant(strWithAllText, sizeWord);
                break;
            case 13:
                text = sortWordsInTextByCountOfLetter(strWithAllText, letter);
                break;
            case 14:
                text = findMaxLengthPalindrom(strWithAllText);
                break;
            case 15:
                text = convertEveryWord(strWithAllText);
                break;
            case 16:
                text = replaceWordsWithSubstring(strWithAllText, sizeWord, lengthString, letter);
                break;
            default:

        }
        return text;
    }


}
