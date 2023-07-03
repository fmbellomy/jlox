package jlox.interpreter;

import org.junit.Test;

import java.io.IOException;

public class MainTest {
    @Test
    public void mainTest() throws IOException {
        String[] args = { "loxSamples/fibonacci.lox" };
        Main.main(args);
    }

}
