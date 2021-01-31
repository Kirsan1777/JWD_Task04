package by.nikita.sockets.server.connection;

import by.nikita.sockets.client.entity.Message;
import by.nikita.sockets.server.service.TextEditorService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.apache.log4j.Logger;

public class SocketServer {

    private static final Logger log = Logger.getLogger(SocketServer.class);
    private static Socket clientSocket;
    private static ServerSocket server;

    public static void main(String[] args) {
        TextEditorService textEditor = new TextEditorService();

        try {
            try {
                Message message;
                Message messageServer;
                int kod = -1;
                server = new ServerSocket(2);
                log.info("Server started!");
                clientSocket = server.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());


                while (kod != 0) {
                    message = (Message) objectInputStream.readObject();

                    messageServer = new Message();
                    List<String> returnedList;
                    returnedList = textEditor.chosenEditText(message.getTextObject().getAllText(), message.getNumberOfOperation(), message.getLengthString(), message.getCountOfLetter(), message.getTextObject().getSubstring(), message.getList());
                    messageServer.setList(returnedList);

                    objectOutputStream.writeObject(messageServer);
                    objectOutputStream.flush();
                    kod = message.getNumberOfOperation();
                }

            } finally {
                clientSocket.close();
                server.close();
                log.info("Server closed");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
            log.error("Error in code server");
        }

    }
}