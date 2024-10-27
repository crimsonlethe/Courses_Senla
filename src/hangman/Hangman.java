package hangman;
import java.util.Random;
import java.lang.System;
public class Hangman {
    private String secretWord;
    private char[] secretWordBlanks;
    private int numberOfLeftGuesses;
    static final String[] WORDS = {"человек", "работа", "вопрос", "сторона", "страна", "случай",
            "голова", "ребенок", "система", "отношение", "женщина", "деньги", "машина", "проблема",
            "решение", "история", "власть", "тысяча", "возможность", "результат", "область", "статья",
            "компания", "группа", "развитие", "процесс", "условие", "средство", "начало", "уровень",
            "минута", "качество", "дорога", "действие", "государство", "любовь", "взгляд", "общество",
            "деятельность", "организация", "президент", "комната", "порядок", "момент", "письмо",
            "помощь", "ситуация", "состояние", "квартира", "внимание", "смерть", "программа",
            "задача", "предприятие", "разговор", "правительство", "производство", "информация",
            "положение", "интерес", "федерация", "правило", "управление", "мужчина", "партия",
            "сердце", "движение", "материал", "неделя", "чувство", "газета", "причина", "основа",
            "товарищ", "культура", "данные", "мнение", "документ", "институт", "проект", "встреча",
            "директор", "служба", "судьба", "девушка", "очередь", "состав", "количество", "событие",
            "объект", "создание", "значение", "период", "искусство", "структура", "пример"};

    public Hangman() {
        Random generator = new Random();
        int index = generator.nextInt(WORDS.length);
        secretWord = WORDS[index];
        secretWordBlanks = new char[secretWord.length()];
        secretWordBlanks = "_".repeat(secretWordBlanks.length).toCharArray();
        numberOfLeftGuesses = 6;
    }
    public int getLives() {
        return numberOfLeftGuesses;
    }
    public boolean checkLetter(char letter) {
        int i = secretWord.indexOf(letter);
        if(i == -1) {
            numberOfLeftGuesses -= 1;
            return false;
        }
        replaceBlanks(letter);
        return true;
    }
    public boolean checkWin() {
        String word = new String(secretWordBlanks);
        if(secretWord.equals(word)) return true;
        return false;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void displayBoard() {
        System.out.println("Загаданное слово ");
        String word = new String(secretWordBlanks);
        System.out.print(word + "\n");
        String guessesMessage = String.format("Количество оставшихся жизней: %d", numberOfLeftGuesses);
        System.out.println(guessesMessage);
    }
    private void replaceBlanks(char letter) {

        int i = secretWord.indexOf(letter);
        while(i >= 0) {
            secretWordBlanks[i] = letter;
            i = secretWord.indexOf(letter, i + 1);
        }
    }
}
