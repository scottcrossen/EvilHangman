package cs240.byu.edu.evilhangman_android.StudentPackage;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by slxn42 on 9/16/16.
 */
public class EvilHangman implements StudentEvilHangmanGameController {
    private Set<String> current_words=new TreeSet();
    private int number_of_guesses=0;
    private int current_guess=0;
    private Set<Character> used_characters=new TreeSet<Character>();
    private Key current_key;
    @Override public GAME_STATUS getGameStatus() {
        if(!(current_key.toString().contains("-"))) return GAME_STATUS.PLAYER_WON;
        if(current_guess<number_of_guesses) return GAME_STATUS.NORMAL;
        if(current_guess==number_of_guesses) return GAME_STATUS.PLAYER_LOST;
        return null;
    }
    @Override public int getNumberOfGuessesLeft() {
        return number_of_guesses-current_guess;
    }
    @Override public String getCurrentWord() {
        return current_key.toString();
    }
    @Override public Set<Character> getUsedLetters() {
        return used_characters;
    }
    @Override public void setNumberOfGuesses(int number_of_guesses_to_start) {
        number_of_guesses=number_of_guesses_to_start;
    }
    @Override public void startGame(InputStreamReader dictionary, int word_length) {
        Scanner dictionary_input=new Scanner(dictionary);
        while (dictionary_input.hasNext()) {
            String next_word= dictionary_input.next().toLowerCase();
            if(next_word.length()==word_length)
                current_words.add(next_word);
        }
        dictionary_input.close();
        current_key=new Key(word_length);
    }
    @Override public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        if(!(used_characters.add(guess))) throw new GuessAlreadyMadeException();
        current_guess++;
        Map<Key,Set<String>> word_sets= new HashMap<>();
        Iterator<String> iter = current_words.iterator();
        while(iter.hasNext()){
            String current_word=iter.next();
            Key current_key=new Key(current_word, guess);
            if(!(word_sets.containsKey(current_key)))
                word_sets.put(current_key,new HashSet<String>());
            word_sets.get(current_key).add(current_word);
        }
        Key current_code=new Key();
        current_words=new HashSet<String>();
        for (Map.Entry<Key, Set<String>> iter1 : word_sets.entrySet()) {
            if(iter1.getValue().size()>current_words.size()) {
                current_words=iter1.getValue();
                current_code=iter1.getKey();
            }
            else if(iter1.getValue().size()==current_words.size()){
                if(current_code.count()>iter1.getKey().count() || (current_code.count()==iter1.getKey().count() && iter1.getKey().hashCode()>current_code.hashCode())) {
                    current_words = iter1.getValue();
                    current_code = iter1.getKey();
                }
            }
        }
        current_key.catenate(current_code);
        return current_words;
    }
}
