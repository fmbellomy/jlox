package jlox.interpreter;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MainTest {
    private String[] makeScriptPath(String scriptName) {
        return new String[] { "../loxSamples/" + scriptName };
    }

    @Test
    public void car() throws IOException {
        Main.main(makeScriptPath("car.lox"));
    }

    @Test
    public void cake() throws IOException {
        Main.main(makeScriptPath("cake.lox"));
    }

    @Test
    public void classInstantiation() throws IOException {
        Main.main(makeScriptPath("classInstantiation.lox"));
    }

    @Test
    public void fibonacci() throws IOException {
        Main.main(makeScriptPath("fibonacci.lox"));
    }

    @Test
    public void fn() throws IOException {
        Main.main(makeScriptPath("fn.lox"));
    }

    @Test
    public void mathstatements() throws IOException {
        Main.main(makeScriptPath("mathstatements.lox"));
    }

    @Test
    public void scope() throws IOException {
        Main.main(makeScriptPath("scope.lox"));
    }

    @Test
    public void closure() throws IOException {
        Main.main(makeScriptPath("closure.lox"));
    }
}
