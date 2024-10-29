package password;
import java.util.*;
import java.security.SecureRandom;
import java.lang.System;

public class PasswordGenerator {
    static final String PUNCTUATION = "!#$%&*+-=?@^_?";
    static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    static final String DIGITS = "0123456789";

    public static void main(String[] args) {
        int passwordLength = getLengthFromInput();
        String password = generatePassword(passwordLength);
        System.out.println("Пароль: " + password);
    }

    public static String generatePassword(int passwordLength) {
        /* Method generatePassword randomly generates a password(String) of given length,
        password includes at least one character of PUNCTUATION, UPPER, LOWER, DIGITS
         */
        SecureRandom random = new SecureRandom();
        int substrLength = passwordLength - 3;
        // need to subtract 3 so there
        // would be at least one symbol in each category (PUNCTUATION, UPPER, LOWER, DIGITS)
        int[] arrLengths = new int[4];
        for(int i = 0; i < 3; i++) {
            arrLengths[i] = random.nextInt(substrLength) + 1;
            substrLength = substrLength - arrLengths[i] + 1;
            //need to plus one because the number of locked places
            // for each category has been reduced by one as we already
            // calculated the number of symbols in password in one category
        }
        arrLengths[3] = substrLength;
        String passwordStr = "";
        passwordStr = getRandomCharactersFromString(PUNCTUATION, arrLengths[0]) +
                getRandomCharactersFromString(UPPER, arrLengths[1]) +
                getRandomCharactersFromString(LOWER, arrLengths[2]) +
                getRandomCharactersFromString(DIGITS, arrLengths[3]);
        // need to make passwordStr an ArrayList to shuffle and randomize a password
        ArrayList<Character> charList = new ArrayList<>();
        for (char c : passwordStr.toCharArray()) {
            charList.add(c);
        }
        Collections.shuffle(charList);
        String password = StringRepresentationOfArrayList(charList);
        return password;
    }

    public static String StringRepresentationOfArrayList(ArrayList<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    public static String getRandomCharactersFromString(String str, int substrLength) {
        //Method is needed to obtain a random characters of each category of symbols
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder();
        int randomIndex;
        for(int i = 0; i < substrLength; i++) {
            randomIndex = random.nextInt(str.length());
            result.append(str.charAt(randomIndex));
        }
        return new String(result);
    }
    public static int getLengthFromInput() {
        System.out.println("Введите длину пароля от 8 до 12: ");
        Scanner scanner = new Scanner(System.in);
        int passwordLength = 0;
        while (passwordLength == 0) {
            try {
                passwordLength = scanner.nextInt();
                System.out.println(passwordLength);
                if(passwordLength > 12 || passwordLength < 8) {
                    System.out.println("Длина пароля должна быть от 8 до 12");
                    passwordLength = 0;
                }
            } catch (Exception InputMismatchException) {
                scanner.nextLine();
                System.out.println("Введите число!");
            }

        }
        return passwordLength;
    }
}
