import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class to call custom exceptions
public class ValidatePassword {
    public static void isValid(String password) throws PasswordException {
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean isNum = false;
        boolean specialCase = false;
        //For loop to see what in the string is missing
        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)) {
                upperCase = true;
            }
            if(Character.isLowerCase(c)) {
                lowerCase = true;
            }
            if(Character.isDigit(c)) {
                isNum = true;
            }
        }

        /*
            Code for checking for special character in string from
            https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
         */
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher match = pattern.matcher(password);
        specialCase = match.find();

        //If statements to determine which exception to throw
        if(!upperCase) {
            throw new UpperCaseCharacterMissing("Password: Missing uppercase character");
        }
        if(!lowerCase) {
            throw new LowerCaseCharacterMissing("Password: Missing lowercase character");
        }
        if(!specialCase) {
            throw new SpecialCharacterMissing("Password: Missing special character");
        }
        if(!isNum) {
            throw new NumberCharacterMissing("Password: Missing number character");
        }
        if(password.length() < 9) {
            throw new Minimum8CharactersRequired("Password: 8 characters minimum");
        }
    }
}
