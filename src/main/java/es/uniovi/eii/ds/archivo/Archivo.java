package es.uniovi.eii.ds.archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class Archivo {
    StringBuilder text = new StringBuilder();

    public void open(String filename) {
        try {
            text = new StringBuilder(readFile(filename));
        } catch (Exception e) {
            System.out.println("Document could not be opened");
        }
    }

    private String readFile(String filename) {
        InputStream in = getClass().getResourceAsStream("/" + filename);
        if (in == null)
            throw new IllegalArgumentException("File not found: " + filename);

        try (BufferedReader input = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder result = new StringBuilder();
            String line;
            boolean firstLine = true;
            while ((line = input.readLine()) != null) {
                if (!firstLine)
                    result.append(System.lineSeparator());
                result.append(line);
                firstLine = false;
            }
            return result.toString();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void replace(String find, String replace) {
        text = new StringBuilder(text.toString().replace(find, replace));
    }

    public void insert(String[] words) {
        for (String word : words) {
            text.append(" ").append(word);
        }
    }

    public void delete() {
        int indexOfLastWord = text.toString().trim().lastIndexOf(" ");
        if (indexOfLastWord == -1)
            text = new StringBuilder("");
        else
            text.setLength(indexOfLastWord);
    }

    public String getText() {
        return text.toString();
    }

}
