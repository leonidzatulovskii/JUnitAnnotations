package calculator;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AnnotationsTests {

    SelenideElement buttons = $("._3VJMP");
    SelenideElement output = $("._22Nn6");

    //CsvSource Annotation
    @CsvSource( value = {
            "5, *, 9, 45",
            "9, /, 3, 3",
            "7, -, 8, -1"
    })
    @ParameterizedTest(name = "Проверка результата выражения {0} {1} {2}")
    void csvSourceTest (String firstInput, String action, String secondInput, String result) {
        Selenide.open("https://regtool.net/ru/calkylyator/");
        buttons.$(byText(firstInput)).click();
        buttons.$(byText(action)).click();
        buttons.$(byText(secondInput)).click();
        buttons.$(byText("=")).click();
        output.shouldHave(text(result));
    }


    //MethodSource Annotation
    static Stream<Arguments> methodSourceArguments() {
        return Stream.of(
                Arguments.of("5", "*", "9", "45"),
                Arguments.of("9", "/", "3", "3"),
                Arguments.of("7", "-", "8", "-1")
        );
    }

    @MethodSource("methodSourceArguments")
    @ParameterizedTest(name = "Проверка результата выражения {0} {1} {2}")
    void methodSourceTest (String firstInput, String action, String secondInput, String result) {
        Selenide.open("https://regtool.net/ru/calkylyator/");
        buttons.$(byText(firstInput)).click();
        buttons.$(byText(action)).click();
        buttons.$(byText(secondInput)).click();
        buttons.$(byText("=")).click();
        output.shouldHave(text(result));
    }

    //EnumSource Annotation
    @EnumSource(CalculatorActions.class)
    @ParameterizedTest(name = "Вывод значения выражение 6 {0} 3")
    void enumSourceTest (CalculatorActions action) {
        Selenide.open("https://regtool.net/ru/calkylyator/");
        buttons.$(byText("6")).click();
        buttons.$(byText(action.action)).click();
        buttons.$(byText("3")).click();
        buttons.$(byText("=")).click();
        String result = output.getText();
        System.out.println("6 " + action.action + " 3 = " + result);
    }
}
