package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Math.*;

public class ReflexCalculator {
    public ReflexCalculator() throws NoSuchMethodException {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(36000);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 36000.");
                System.exit(1);
            }

            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>ReflexCalculator</title>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <h1>Calculadora</h1>\n" +
                    "        <form action=\"/hello\">\n" +
                    "        </form> \n" +
                    "        <div id=\"getrespmsg\"></div>\n" +
                    "        </script>\n" +
                    "    </body>\n" +
                    "</html>";

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }


    }


    Class c = Math.class;
    Method m = c.getDeclaredMethod("cos", Double.TYPE);
    Method m2 = c.getMethod("cos", Double.TYPE);

    //m.invoke(null, Double.parseDouble("2.3"));


}
