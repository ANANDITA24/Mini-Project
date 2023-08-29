import java.awt.*;
import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

class Wordle {
    public static String readFileAsString(String fileName)
            throws Exception {
        String data = "";
        data = new String(
                Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static void main(String[] arg) throws Exception // Main function
    {
        String inputFileName = "words.txt"; // Change to your input file name
        String outputFileName = "output.txt"; // Change to your output file name

        try {
            FileReader reader = new FileReader(inputFileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder modifiedContent = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                modifiedContent.append(line).append(","); // Append a comma after each word
            }

            bufferedReader.close();

            FileWriter writer = new FileWriter(outputFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(modifiedContent.toString());
            bufferedWriter.close();

            System.out.println("Commas added and saved to " + outputFileName);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        // read file and store random string in array
        List<String> stringList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(inputFileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        // Convert the list to an array
        String[] stringArray = stringList.toArray(new String[0]);

        // Generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(stringArray.length);

        // Retrieve the random string
        String randomString = stringArray[randomIndex];

        // System.out.println("Randomly chosen string: " + randomString); // random
        // string chossen
        String Green = "\u001b[32m";
        String Reset = "\u001b[0m";
        String Yellow = "\u001b[33m";
        String correct = randomString;
        String guess = "";
        for (int round = 6; round > 0; round--) {
            boolean found = false;
            Scanner obj = new Scanner(System.in);
            System.out.println("\n\nYou have " + round + " chance.");
            do {

                System.out.println("Plese guess 5 letter word :");
                guess = obj.nextLine();
                if (guess.length() != 5) {
                    System.out.println("Please enter exactly 5 letters.");
                } else {
                    // check user input exist in file or not
                    try {
                        FileReader reader = new FileReader(inputFileName);
                        BufferedReader bufferedReader = new BufferedReader(reader);

                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.equalsIgnoreCase(guess)) {
                                found = true;
                                break;
                            }
                        }

                        bufferedReader.close();

                        if (found) {
                            System.out.println("");
                        } else {
                            System.out.println("The word does not exist in the file.");
                        }
                    } catch (IOException e) {
                        System.err.println("An error occurred: " + e.getMessage());
                    }
                }
            } while ((guess.length() != 5) || (!found));
            for (int i = 0; i < 5; i++) {
                if (guess.substring(i, i + 1).equals(correct.substring(i, i + 1))) {
                    System.out.print(Green + guess.substring(i, i + 1) + Reset);
                } else if (correct.indexOf(guess.substring(i, i + 1)) > -1) {
                    System.out.print(Yellow + guess.substring(i, i + 1) + Reset);
                } else {
                    System.out.print(guess.substring(i, i + 1));
                }
            }
            System.out.println("");
            if (guess.equals(correct)) {
                System.out.println("Correct! You Win");
                break;
            }
        }
        if (!guess.equals(correct)) {
            System.out.println("Wrong! The correct word is " + correct + ".");

        }
    }
}