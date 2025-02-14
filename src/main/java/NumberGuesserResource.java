import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.NoCache;

@Path("/number_guesser")
public class NumberGuesserResource {

    @Inject NumberGuesserService numberGuesserService;

    private final String htmlPage = """
            <!DOCTYPE html><html lang="en">
                <h1>Welcome to the Number Guesser Game!</h1>
                <form action="number_guesser" method="post">
                    <label for="f_number">Guessed number:</label>
                    <input type="number" min="1" max="6" id="f_number" name="f_number">
                    <input type="submit" value="Submit">
                    <p style="color: red">%s</p>
                </form>
            </html>
            """;

    @GET
    @NoCache
    @Produces(MediaType.TEXT_HTML)
    public String getNumberGuesserGame() {
        return String.format(htmlPage, "");
    }

    @POST
    @NoCache
    @Produces(MediaType.TEXT_HTML)
    public String setNumberGuesserGame(@FormParam("f_number") String player_guess) {
        try {
            return String.format(htmlPage, numberGuesserService.play(player_guess));
        } catch (Exception e) {
            return String.format(htmlPage, e.getMessage());
        }
    }

    @GET
    public Response getNumberGuesser() {
        return Response.status(200).build();
    }

}
