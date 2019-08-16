package com.kasiyanov.consoleValidation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PhoneListValidatorTest {

    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void checkPhoneValidationOnePhone() {
        boolean expected = true;
        String onePhone = "37529 1112233";
        boolean actual = PhoneListValidator.getInstance().isValid(onePhone);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkRoleValidationTwoPhones() {
        boolean expected = true;
        String twoPhones = " 37529 1112233  ,  37529 1112233  ";
        boolean actual = PhoneListValidator.getInstance().isValid(twoPhones);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkRoleValidationTreePhones() {
        boolean expected = true;
        String treePhones = " 37529 1112233  ,  37529 1112233  , 37529 1112233   ";
        boolean actual = PhoneListValidator.getInstance().isValid(treePhones);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkRoleValidationFourPhones() {
        String expectedOutput = "Введено более трёх номеров телефонов, " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String fourPhones = "37529 1112233,37529 1112233,37529 1112233,37529 1112233";
        PhoneListValidator.getInstance().isValid(fourPhones);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkRoleValidationEmptyLine() {
        String expectedOutput = "Не введено ни одного номера телефонов, " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String emptyLine = "         ";
        PhoneListValidator.getInstance().isValid(emptyLine);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkRoleValidationBlankLine() {
        String expectedOutput = "Не введено ни одного номера телефонов, " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String blankLine = "";
        PhoneListValidator.getInstance().isValid(blankLine);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkPhoneValidationIncorrectPatternOne() {
        String expectedOutput = "Один или более из номеров телефонов введён не корректно - " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String onePhone = "3759 1112233, 37529 1112233";
        PhoneListValidator.getInstance().isValid(onePhone);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkPhoneValidationIncorrectPatternTwo() {
        String expectedOutput = "Один или более из номеров телефонов введён не корректно - " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String onePhone = "37429 1112233, 37529 1112233, 37529 1112233";
        PhoneListValidator.getInstance().isValid(onePhone);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkPhoneValidationIncorrectPatternTree() {
        String expectedOutput = "Один или более из номеров телефонов введён не корректно - " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String onePhone = "37529 111223";
        PhoneListValidator.getInstance().isValid(onePhone);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkPhoneValidationIncorrectPatternFour() {
        String expectedOutput = "Один или более из номеров телефонов введён не корректно - " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String onePhone = "375291112233";
        PhoneListValidator.getInstance().isValid(onePhone);
        Assert.assertEquals(expectedOutput, getOutput());
    }
}