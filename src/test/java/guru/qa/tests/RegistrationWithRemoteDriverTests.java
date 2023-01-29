package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationWithRemoteDriverTests extends TestBaseExtended {

    @Test
    @Tag("remote")
    void successfulRegistrationTest() {
        String userName = "Anatoly";
        String lastName = "Slabodenyuk";
        String eMail = "slabodenyukanatoly@gmail.com";
        String userNumber = "1234567890";

        step("Перейти на форму регистрации", () -> {
            open("/automation-practice-form");
        });

        step("Проверить, что форма имеет правильное название", () -> {
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
        
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        step("Заполнить форму", () -> {
        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(eMail);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.jpg");
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();
        });

        step("Проверить корректность заполнения данных в таблице", () -> {
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(userName), text(lastName),
                text(eMail), text(userNumber));
        });
    }
}
