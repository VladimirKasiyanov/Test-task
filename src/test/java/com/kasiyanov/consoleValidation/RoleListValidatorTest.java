package com.kasiyanov.consoleValidation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RoleListValidatorTest {

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
    public void checkRoleValidationOneRole() {
        boolean expected = true;
        String oneRole = "buyer";
        boolean actual = RoleListValidator.getInstance().isValid(oneRole);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkRoleValidationTwoRoles() {
        boolean expected = true;
        String twoRoles = " buyer  ,  manager  ";
        boolean actual = RoleListValidator.getInstance().isValid(twoRoles);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkRoleValidationTreeRoles() {
        boolean expected = true;
        String treeRoles = " buyer  ,  manager  , admin   ";
        boolean actual = RoleListValidator.getInstance().isValid(treeRoles);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkRoleValidationFourRoles() {
        String expectedOutput = "Введено более трёх ролей, введите от одной до трёх ролей пользователя через запятую\r\n";
        String fourRoles = "buyer,manager,admin,guest";
        RoleListValidator.getInstance().isValid(fourRoles);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkRoleValidationEmptyLine() {
        String expectedOutput = "Не введено ни одной роли, введите от одной до трёх ролей пользователя через запятую\r\n";
        String emptyLine = "         ";
        RoleListValidator.getInstance().isValid(emptyLine);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkRoleValidationBlankLine() {
        String expectedOutput = "Не введено ни одной роли, введите от одной до трёх ролей пользователя через запятую\r\n";
        String blankLine = "";
        RoleListValidator.getInstance().isValid(blankLine);
        Assert.assertEquals(expectedOutput, getOutput());
    }
}