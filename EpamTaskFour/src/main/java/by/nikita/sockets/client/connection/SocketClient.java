package by.nikita.sockets.client.connection;

import by.nikita.sockets.client.console.ConsoleReader;
import by.nikita.sockets.client.console.ConsoleWriter;
import by.nikita.sockets.client.entity.Message;
import by.nikita.sockets.client.entity.Text;
import by.nikita.sockets.client.service.FileService;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SocketClient {

    private static final Logger log = Logger.getLogger(SocketClient.class);
    private static Socket clientSocket;

    public static void main(String[] args) {
        Text text = new Text();
        FileService fileWorker = new FileService();
        ConsoleReader reader = new ConsoleReader();

        try {
            try {

                clientSocket = new Socket("localhost", 2);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                Message message;


                int numberOfOperation = -1;

                while (numberOfOperation != 0) {
                    text.setAllText(fileWorker.readTxtFile());
                    ConsoleWriter.printOptionalMenu();
                    ConsoleWriter.printQuestion();
                    numberOfOperation = reader.inputInt();

                    message = reader.inputParameters(numberOfOperation, text.getAllText());


                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();

                    message = (Message) objectInputStream.readObject();

                    ConsoleWriter.printResultOperation(message);
                }
            } finally {
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            log.error("Error in code client");
        }
    }
}
