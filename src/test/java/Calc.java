import java.io.IOException;

public class Calc {
    public Calc(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }
}
