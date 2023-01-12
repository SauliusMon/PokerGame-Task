package com.saulius.pokergame.datareading;

import java.io.File;
import java.util.Scanner;

public class ReadingPokerTextFile {

    public static void readFile (File fileToRead) {

        try {
            Scanner myReader = new Scanner(fileToRead);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
