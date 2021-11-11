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
        String inType = " ";
        double delta = 0.01;
        double expected = 1;
        double actual;
        actual = Converter.getInput(inType);
        Assert.assertEquals(expected, actual, delta);
    }
    @Test
    public void testGetResult(){
        double input = 1234;
        double rate = 0.01;
        double expected = 12.34;
        double delta = 0.0001;
        double actual;
        actual = Converter.getResult(rate, input);
        Assert.assertEquals(expected, actual, delta);
    }
    @Test
    public void testConvertCurrency(){
        int choice = 1;
        int output = 2;
        double input = 1;
        boolean expected = true;
        boolean actual;
        actual = Converter.convertCurrency(choice, output, input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testConvertLength(){
        int choice = 1;
        int output = 2;
        double input = 1;

        boolean expected = true;
        boolean actual;
        actual = Converter.convertLength(choice, output, input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testConvertTime(){
        int choice = 1;
        int output = 2;
        double input = 1;
        boolean expected = true;
        boolean actual;
        actual = Converter.convertTime(choice, output, input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testConvertSquare(){
        int choice = 1;
        int output = 2;
        double input = 1;
        boolean expected = true;
        boolean actual;
        actual = Converter.convertSquare(choice, output, input);
        Assert.assertEquals(expected, actual);
    }
//    @Test
//    public void testConvertVolume(){
//        int choice = 1;
//        int output = 2;
//        double rate = 100;
//        double input = 1;
//        double result = 0;
//        boolean expected = true;
//        boolean actual;
//        actual = Converter.convertSquare(choice, output, rate, input, result);
//        Assert.assertEquals(expected, actual);
//    }
    @Test
    public void testConvertSpeed(){
        int choice = 1;
        int output = 2;
        double input = 1;
        boolean expected = true;
        boolean actual;
        actual = Converter.convertSpeed(choice, output, input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testConvertTemperature(){
        int choice = 1;
        int output = 2;
        double input = 1;
        boolean expected = true;
        boolean actual;
        actual = Converter.convertTemperature(choice, output, input);
        Assert.assertEquals(expected, actual);
    }
//    @Test
//    public void testConvertWeight(){
//        int choice = 1;
//        int output = 2;
//        char grad = 176;
//        double input = 1;
//        double result = 0;
//        boolean expected = true;
//        boolean actual;
//        actual = Converter.convertWeight(choice, output, input, result, grad);
//        Assert.assertEquals(expected, actual);
//    }

}
