package hangman;
import java.util.Scanner;
public class Game {
    public static void main(String[] args){
        startGame();
    }
    public static void startGame() {
        String usedLetters = "";
        System.out.println("Игра \"Висельница\"");
        Hangman game = new Hangman();char newLetter;
        gameplay(game, usedLetters);
        game.displayBoard();
        if(game.getLives() == 0){
            System.out.println("Вы проиграли!");
            System.out.print("Загаданное слово: ");
            System.out.println(game.getSecretWord());
        } else {
            System.out.println("Вы выиграли!");
        }
    }
    public static void gameplay(Hangman game, String usedLetters) {
        Scanner scanner = new Scanner(System.in);
        char newLetter;
        while(game.getLives() != 0 && !game.checkWin()) {
            game.displayBoard();
            newLetter = getLetter(scanner);
            if (usedLetters.indexOf(newLetter) != -1) {
                System.out.println(String.format("Буква \'%c\' уже была загадана", newLetter));
            } else {
                usedLetters += newLetter;
                boolean guessed = game.checkLetter(newLetter);
                if (guessed == true) {
                    System.out.println(String.format("Буква \'%c\' есть в загаданном слове!", newLetter));
                } else {
                    System.out.println(String.format("Буквы \'%c\' нет в загаданном слове!", newLetter));
                }
            }
        }
    }

    public static char getLetter(Scanner scanner) {
        System.out.println("Введите букву: ");
        char letter = scanner.next().charAt(0);
        if (letter == '\0' || !Character.isLetter(letter)) {
            System.out.println("Вводить можно только буквы!");
            letter = scanner.next().charAt(0);
        }
        return letter;
    }

}
