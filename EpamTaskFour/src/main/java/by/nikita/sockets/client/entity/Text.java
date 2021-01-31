package by.nikita.sockets.client.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Text implements Serializable {

    private String allText;
    private String substring;

    public String getAllText() {
        return allText;
    }

    public void setAllText(String allText) {
        this.allText = allText;
    }

    public String getSubstring() {
        return substring;
    }

    public void setSubstring(String substring) {
        this.substring = substring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text = (Text) o;

        if (allText != null ? !allText.equals(text.allText) : text.allText != null) return false;
        return substring != null ? substring.equals(text.substring) : text.substring == null;
    }

    @Override
    public int hashCode() {
        int result = allText != null ? allText.hashCode() : 0;
        result = 31 * result + (substring != null ? substring.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Text = " + allText + substring;
    }
}
