import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TerminalHandler {
    public static ArrayList<String> execute(String inputString) {
        String[] command = inputString.split(" ");
        ArrayList<String> outputLines = new ArrayList<String>();
        try {
            Process process = Runtime.getRuntime().exec(command);

            // Create a BufferedReader to read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = reader.readLine();
            // Read and print the output line by line
            while (line != null) {
                outputLines.add(line);
                line = reader.readLine();
            }
            // Close the BufferedReader
            reader.close();
        } catch (IOException e) {
            System.out.println("ERROR:\tTerminalHandler.java was unable to execute terminal command \"" + inputString + "\"");
            e.printStackTrace();
        }

        return outputLines;
    }
}