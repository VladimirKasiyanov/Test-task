package com.kasiyanov.consoleValidation;

import org.junit.Assert;
import org.junit.Test;

public class NameValidatorTest {

    @Test
    public void checkNameValidation() {
        boolean expected = true;
        String line = "Name";
        boolean actual = NameValidator.getInstance().isValid(line);
        Assert.assertEquals(expected, actual);
    }
}