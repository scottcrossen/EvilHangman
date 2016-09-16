package cs240.byu.edu.evilhangman_android.StudentPackage;

import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by slxn42 on 9/16/16.
 */
public class EvilHangman implements StudentEvilHangmanGameController {
    @Override
    public void startGame(int wordLength) {
        startGame(, wordLength);
    }

    @Override
    public void startGame(InputStreamReader dictionary, int wordLength) {
        
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        return null;
    }
}
