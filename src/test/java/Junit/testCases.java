package Junit;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class testCases {

    public int sum(int n1, int n2){
        return n1 + n2;
    }

    @Test public void sumaValida() throws Exception {
        int res = sum(2, 5);
        Assert.assertTrue("Suma 2 + 5 valida", res == 7);
    }


    @Test public void sumaInvalida() throws Exception {
        int res = sum(2, 5);
        Assert.assertFalse("Suma 2 + 5 invalida", res == 2);
    }



}
