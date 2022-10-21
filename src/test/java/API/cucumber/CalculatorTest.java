package API.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public final class CalculatorTest {
    private int firstValue;
    private int secondValue;

    @When("первое число равно {int}")
    public void setFirstValue(int value) {
        firstValue = value;
    }

    @And("второе число равно {int}")
    public void setSecondValue(int value) {
        secondValue = value;
    }

    @Then("сумма чисел равна {int}")
    public void sum(int expectedResult) {
        int result = firstValue + secondValue;
        System.out.println("Сумма чисел равна: " + result);
        Assertions.assertEquals(expectedResult, result, "Сумма не равна " + expectedResult);
    }
}
