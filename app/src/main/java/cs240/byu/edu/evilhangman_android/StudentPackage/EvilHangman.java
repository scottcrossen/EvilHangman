package cs240.byu.edu.evilhangman_android.StudentPackage;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by slxn42 on 9/16/16.
 */
public class EvilHangman implements StudentEvilHangmanGameController {
    private Set<String> current_words;
    private int number_of_guesses=0;
    private int current_guess=0;
    private Set<Character> used_characters;
    private Key current_key;
    @Override
    public GAME_STATUS getGameStatus() {
        if(current_guess<number_of_guesses) return GAME_STATUS.NORMAL;
        if(current_words.size()==1) return GAME_STATUS.PLAYER_WON;
        if(current_guess==number_of_guesses) return GAME_STATUS.PLAYER_LOST;
        return null;
    }

    @Override
    public int getNumberOfGuessesLeft() {
        return number_of_guesses-current_guess;
    }

    @Override
    public String getCurrentWord() {
        return current_key.toString();
    }

    @Override
    public Set<Character> getUsedLetters() {
        return used_characters;
    }

    @Override
    public void setNumberOfGuesses(int number_of_guesses_to_start) {
        number_of_guesses=number_of_guesses_to_start;
    }

    @Override
    public void startGame(InputStreamReader dictionary, int word_length) {
        Scanner dictionary_input=new Scanner(dictionary);
        while (dictionary_input.hasNext()) {
            String next_word= dictionary_input.next();
            if(next_word.length()==word_length)
                current_words.add(next_word);
        }
        dictionary_input.close();
        current_key=new Key(word_length);
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        if(!(used_characters.add(guess))) throw new GuessAlreadyMadeException();
        current_guess++;
        //update key
        return current_words;
    }
}
