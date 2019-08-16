package com.kasiyanov.consoleValidation;

import org.junit.Assert;
import org.junit.Test;

public class SurNameValidatorTest {

    @Test
    public void checkSurnameValidation() {
        boolean expected = true;
        String line = "Surname";
        boolean actual = SurNameValidator.getInstance().isValid(line);
        Assert.assertEquals(expected, actual);
    }

}