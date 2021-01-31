package by.nikita.sockets.client.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public String readTxtFile() throws IOException {
        String listWithAllText = "";

        FileReader fileReader = new FileReader("D:\\Java\\Sokets\\src\\main\\resources\\Temp");
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                listWithAllText = listWithAllText.concat(s);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }

        return listWithAllText;
    }
}
