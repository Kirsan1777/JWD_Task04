package by.nikita.sockets.client.entity;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private Text textObject;
    private int numberOfOperation;
    private int countOfLetter;
    private int lengthString;
    List<String> listWithResults;


    public Message() {
    }

    public List<String> getList() {
        return listWithResults;
    }

    public void setList(List<String> list) {
        this.listWithResults = list;
    }

    public Text getTextObject() {
        return textObject;
    }

    public void setTextObject(Text textObject) {
        this.textObject = textObject;
    }

    public int getNumberOfOperation() {
        return numberOfOperation;
    }

    public void setNumberOfOperation(int numberOfOperation) {
        this.numberOfOperation = numberOfOperation;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCountOfLetter() {
        return countOfLetter;
    }

    public int getLengthString() {
        return lengthString;
    }

    public void setLengthString(int lengthString) {
        this.lengthString = lengthString;
    }

    public List<String> getListWithResults() {
        return listWithResults;
    }

    public void setListWithResults(List<String> listWithResults) {
        this.listWithResults = listWithResults;
    }

    public void setCountOfLetter(int countOfLetter) {
        this.countOfLetter = countOfLetter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (numberOfOperation != message.numberOfOperation) return false;
        if (countOfLetter != message.countOfLetter) return false;
        if (textObject != null ? !textObject.equals(message.textObject) : message.textObject != null) return false;
        return listWithResults != null ? listWithResults.equals(message.listWithResults) : message.listWithResults == null;
    }

    @Override
    public int hashCode() {
        int result = textObject != null ? textObject.hashCode() : 0;
        result = 31 * result + numberOfOperation;
        result = 31 * result + countOfLetter;
        result = 31 * result + (listWithResults != null ? listWithResults.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message: " + textObject + numberOfOperation + countOfLetter + listWithResults;
    }
}
