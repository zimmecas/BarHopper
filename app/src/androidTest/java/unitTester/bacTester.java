package unitTester;

import android.test.InstrumentationTestCase;

import edu.gvsu.cis.zimmecas.barhopper.BACCalculator;

/**
 * Created by louissullivan on 4/27/16.
 */
public class bacTester extends InstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }



    public void testBac(){
           givenDataToBac();

    }

    private void givenDataToBac() {
        BACCalculator bac = new BACCalculator();
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
