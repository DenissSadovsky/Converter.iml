import org.junit.Test;
import org.junit.Assert;

public class UnitTests {
    @Test
    public void testGetChoice() {
        int maxChoice = 8;
        int minChoice = 1;
        int expected = 2;
        int actual;
        actual = Converter.getChoice(maxChoice, minChoice);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetOutput(){
        int maxChoice = 8;
        int minChoice = 1;
        int choice = 2;
        int expected = 3;
        int actual;
        actual = Converter.getOutput(maxChoice, minChoice, choice);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetInput(){
        int maxChoice = 8;
        int minChoice = 1;
        String inType = " ";
        double expected = 1;
        double actual;
        actual = Converter.getInput(inType);
        Assert.assertEquals(expected, actual);
    }
}
