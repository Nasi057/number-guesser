import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

import java.util.Random;

@ApplicationScoped
public class NumberGuesserService {

    private static final int COMPUTER_GUESS_RANGE = 6;
    private static final Random RANDOM = new Random();

    private int RANDOM_NUMBER = RANDOM.nextInt(COMPUTER_GUESS_RANGE) + 1;

    private static int convertPlayerInputToNumber(String player_guess) {
        int convertedGuess;
        try {
            convertedGuess = Integer.parseInt(player_guess.replaceAll(" ", ""));
        } catch (Exception e) {
            throw new WebApplicationException("should be a number", 400);
        }
        if (convertedGuess > COMPUTER_GUESS_RANGE || convertedGuess < 0) {
            throw new WebApplicationException("number is invalid", 400);
        }
        return convertedGuess;
    }

    public String play(String player_guess) {
        if (convertPlayerInputToNumber(player_guess) == RANDOM_NUMBER) {
                RANDOM_NUMBER = RANDOM.nextInt(COMPUTER_GUESS_RANGE) + 1;
                return "you won";
        } else {
            return "try again";
        }
    }

}
