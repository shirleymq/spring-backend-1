package com.sales.market.util;

import org.hibernate.cfg.NotYetImplementedException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NumericUtilsTest {

    private static final String ONE_ROMAN = "I";

    @org.testng.annotations.Test
    public void testToRoman() {
        NumericUtils numericUtils = new NumericUtils();
        String romano = numericUtils.toRoman(1);
        assertEquals(romano, ONE_ROMAN);
    }

    @org.testng.annotations.Test(expectedExceptions = NotYetImplementedException.class)
    public void testToArabic() {
        NumericUtils numericUtils = new NumericUtils();
        Integer arabic = numericUtils.toArabic(ONE_ROMAN);
        assertTrue(arabic == 1);
    }
}
