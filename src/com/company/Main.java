package com.company;

import java.io.*;

public class Main {

    public static boolean success = false;

    public static void main(String[] args) throws IOException {

        String attemptStorage = "/Users/boris/Desktop/file.txt";
        String passwordStorage = "/Users/boris/Desktop/Password.txt";

        System.out.println("Please, enter the password:");

        while (!success) {
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
          String attemptFromConsole = bufferedReader.readLine();
          addTofile(attemptFromConsole, attemptStorage);
          compareAttemptWithPassword(attemptStorage, passwordStorage);
        }

    }

    private static boolean compareAttemptWithPassword(String attemptStorage, String passwordStorage) throws IOException {
        InputStream temp = new FileInputStream(attemptStorage);
        InputStream password = Main.class.getResourceAsStream("/password"); //new FileInputStream(passwordStorage); //
        try {
            while (true) {
                int bytesFromTemp = temp.read();
                int bytesFromPassword = password.read();

                if (bytesFromTemp != bytesFromPassword) {
                    System.out.println("Wrong password");
                    return false;
                }

                if (bytesFromTemp == -1) {
                    System.out.println("Right password");
                    success = true;
                    return true;
                }
            }

        } finally {
            if (temp != null)
                temp.close();
            if (password != null)
                password.close();
        }
    }

    private static void addTofile(String attemptFromConsole, String attemptStorage) throws IOException {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(attemptStorage, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(attemptFromConsole);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}




