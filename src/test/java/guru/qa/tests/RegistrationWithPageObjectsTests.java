package guru.qa.tests;

import guru.qa.pages.RegistrationPage;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import com.github.javafaker.Faker;

public class RegistrationWithPageObjectsTests extends TestBase{
    Faker faker = new Faker();

    RegistrationPage registrationPage = new RegistrationPage();
    @Test
    void successfulRegistrationTest() {
        String userName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String eMail = faker.internet().emailAddress();
        String userNumber = faker.number().digits(10);
        String currentAddress = faker.address().streetAddress();

        String birthMonth = "January";
        String birthYear = "1993";
        String birthDay = "06";

        registrationPage.openPage()
                .setFirstName(userName)
                .setLastName(lastName)
                .setEmail(eMail)
                .setGender()
                .setUserNumber(userNumber)
                .setBirthDate(birthDay, birthMonth, birthYear)
                .setSubjects()
                .setHobbies()
                .setPictures()
                .setUserAddress(currentAddress)
                .setState()
                .setCity()
                .submit();

        registrationPage.verifyRegistrationModalAppears();
        $(".table-responsive").shouldHave(text(userName), text(lastName),
                text(eMail), text(userNumber));
    }
}