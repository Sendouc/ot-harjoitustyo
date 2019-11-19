package runningdiaryapp.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import runningdiaryapp.ui.TUI;

public class TUITest {

    private TUI tui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        tui = new TUI();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void endingMethodWorksCorrectly() {
        Scanner s = new Scanner(System.in);
        tui.end(s);
        assertEquals("See you next time! Have fun running.", outContent.toString().replaceAll("[\\r\\n]+", ""));
    }
}