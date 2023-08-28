import java.awt.*;
import java.lang.*;
import java.util.*;

class Wordle {
    public static void main(String[] arg) {
        String Green = "\u001b[32m";
        String Reset = "\u001b[0m";
        String Yellow = "\u001b[33m";
        String correct = "SHAKE";
        String guess = "";
        for (int round = 0; round < 6; round++) {
            System.out.println("Plese guess 5 letter word :");
            Scanner obj = new Scanner(System.in);
            guess = obj.nextLine().toUpperCase();
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