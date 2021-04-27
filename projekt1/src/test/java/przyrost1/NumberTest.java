package przyrost1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NumberTest {

    @Test
    public void calculate() {
        Number test = new Number();
        Assert.assertTrue(test.CheckPeseltest("90060804786"));
    }

    @Test
    //sprawdzanie poprawności, pesel nie jest poprawny (cyfra kontrol.)
    public void Correcttreue() {
        Number test2 = new Number();
        Assert.assertTrue(test2.ToCheckIfCorrect("81100216357"));
    }
    @Test
    //sprawdzanie poprawności, pesel nie jest poprawny (cyfra kontrol.)
    public void Correctfalse() {
        Number test2 = new Number();
        Assert.assertFalse(test2.ToCheckIfCorrect("81100216355"));
    }
}